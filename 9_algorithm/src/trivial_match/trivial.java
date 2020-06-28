package trivial_match;
//2015112232�����

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.Random;

public class trivial {

	static int N = 500000;
	
	public static void refDNA() {
		// ���� ������ 50������ ���� �ֱ�

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt");
			FileWriter fw = new FileWriter(file);

			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis()); // �õ尪�� ���÷� �ٲپ �������� ����

			for (int i = 0; i < N; i++) {
				fw.write(nucleic(rand.nextInt(4))); // 0~3������ ��
			}

			fw.close();
		} catch (Exception e) {
			System.out.println("makeDNA: " + e);
		}

	}

	public static char nucleic(int n) {
		// ���� ���� ����

		switch (n) {
		case 0: // A
			return 'A';
		case 1: // T
			return 'T';
		case 2: // G
			return 'G';
		case 3: // C
			return 'C';
		}

		return 0;
	}

	public static void myDNA(int x) {
		// ���Ⱑ x�� Ȯ���� mismatch�� ��
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt"); // �б� ����
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt"); // ���� ����
			FileReader fr = new FileReader(file1);
			FileWriter fw = new FileWriter(file2);

			int singlech;
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			int count = 0;
			char ch;

			while ((singlech = fr.read()) != -1) { // ������ �б�
				ch = (char) singlech;
				if(((rand.nextInt(100)+1)%(100/x)) == 0) { //x�ۼ�Ʈ Ȯ���� �ٸ� ����
					ch = nucleic(rand.nextInt(4));
					while((char) singlech == ch) {
						ch = nucleic(rand.nextInt(4));
					}
				}
				fw.write(ch);
			}

			fr.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("myDNA: " + e);
		}
	}


	public static void shortread(int k, int n) {
		//shortread �����ؼ� ���Ͽ� ����
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt"); // �б� ����
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\shortread.txt"); // ���� ����
			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // �б� ���� ����
			FileWriter fw = new FileWriter(file2);

			byte[] data = new byte[k]; // ���� ���� ����
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis()); // �õ尪�� ���÷� �ٲپ �������� ����
			int point;
			String str_data;

			for (int i = 0; i < n; i++) {
				point = rand.nextInt(N - k); // k���� �б����� ��ġ ��������
				raf.seek(point); // ��ġ�� �̵�
				raf.read(data); // k�� ��ŭ �б�
				str_data = new String(data).trim();
				fw.write(str_data);
			}

			fw.close();
		} catch (Exception e) {
			System.out.println("shortread: " + e);
		}
	}
	
	

	public static void matchDNA(int k, int n) {
		// reference DNA�� shortread�� �̿��Ͽ� restoreDNA����
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\shortread.txt");
			File file3 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");
			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // �б� ���� ����

			FileReader fr2 = new FileReader(file2);
			FileWriter fw = new FileWriter(file3);

			String[] array_shortread = new String[n];
			String str_data;
			char[] char_short = new char[k]; // ���ڸ� �޾Ƽ� string�� ����
			byte[] data = new byte[k]; // k�� ����Ʈ ���� �迭
			String answer;
			int singlech;
			int j = 0, i = 0;
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis()); // �õ尪�� ���÷� �ٲپ �������� ����

			while ((singlech = fr2.read()) != -1) {// shortread������ k���ھ��߶� String�迭�� ����
				char_short[j] = (char) singlech;
				j++;
				if (j == k) { // k���� �߶� ���ڿ��� ����
					array_shortread[i] = String.valueOf(char_short);
					i++;
					j = 0;
				}
			}

			long start = System.currentTimeMillis();
			for (i = 0; i < N - k; i++) {
				raf.seek(i);
				raf.read(data);
				str_data = new String(data).trim();
				answer = submatchDNA(str_data, array_shortread);
				if (answer == null) { // ��ġ �ϴ°� ���ٸ�
					fw.write(nucleic(rand.nextInt(4)));
				} else {// ���⼭�� �߰�
					fw.write(answer);
					i = i + k-1; // kĭ �ڷ�
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("�ɸ��� �ð�:" + (end - start) + "ms");

			raf.close();
			fr2.close();
			fw.close();

		} catch (Exception e) {
			System.out.println("matchDNA: " + e);
		}
	}

	public static String submatchDNA(String data, String[] shortread) {
		// shortread��� ���Ͽ� mismatch�� 2�� ���϶�� �� ���⼭���� return�Ѵ�.

		char[] arr = data.toCharArray();
		char[] short_dna;
		int mismatch = 0;

		for (int i = 0; i < shortread.length; i++) {
			mismatch = 0;
			short_dna = shortread[i].toCharArray();
			for (int j = 0; j < arr.length; j++) {
				if (arr[j] != short_dna[j]) {
					mismatch++;
				}
			}
			if (mismatch <= 2) {
				return shortread[i];
			}
		}
		return null;
	}

	

	public static int verse() {
		// ������� ��ġ�ϴ°�
		int mismatch = 0;// �ٸ� Ƚ��
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // ������ ��
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // �ٸ��ٸ� mismatch�� ����
					mismatch++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mismatch;
	}
	
	public static int verse2() {
		// ������� ��ġ�ϴ°�
		int mismatch = 0;// �ٸ� Ƚ��
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // ������ ��
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // �ٸ��ٸ� mismatch�� ����
					mismatch++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mismatch;
	}


	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k, n,x;
		System.out.print("shortRead ����(k): "); // k,n���� �Է�
		k = sc.nextInt();
		System.out.print("");
		System.out.print("shortRead ����(n): ");
		n = sc.nextInt();
		System.out.print("");
		System.out.print("x% �Է�: ");
		x = sc.nextInt();

		refDNA(); // refDNA ����
		myDNA(x); // x% Ȯ���� �ٸ��������
		shortread(k, n); // myDNA�� shortread����
		matchDNA(k, n); // ��Ī
		System.out.println("restoreDNA�� myDNA��ġ����: " + (double) (N - verse()) / (double) N * 100 + "%");
		System.out.println("R_DNA�� myDNA��ġ����: " + (double) (N - verse2()) / (double) N * 100 + "%");
		sc.close();
	}

}
