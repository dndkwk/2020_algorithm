package algorithm_final;
//2015112232�����

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Random;

public class final_algorithm {

	static int N = 50000; // DNA �� ����: �ִ� 30��
	static int M = 1500; // short read ���� : 2000��~2��
	static int L = 50; // short read ���� : 32~100
	static int D = 2; // mismatch ��� ����
	static int X = 2; // (2%) mismatch �߻� Ȯ��,500~1000����� 1�� ��Ÿ����
	static int q = 1033381;// �Ҽ�

	static int[] DNA_hash;
	static int[] shortread_hash;
	static String[] shortread;

	public static void refDNA() {
		// ���� ������ N���� ���� �ֱ�
		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");
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
		System.out.println("refDNA��");
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

	public static void myDNA() {
		// ���Ⱑ x�� Ȯ���� mismatch ������ ���Ͽ� ����
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt"); // �б� ����
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt"); // ���� ����
			FileReader fr = new FileReader(file1);
			FileWriter fw = new FileWriter(file2);

			int singlech;
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			// int count = 0;
			char ch;

			while ((singlech = fr.read()) != -1) { // ������ �б�
				ch = (char) singlech;
				if (((rand.nextInt(100) + 1) % (100 / X)) == 0) { // x�ۼ�Ʈ Ȯ���� �ٸ� ����
					ch = nucleic(rand.nextInt(4));
					while ((char) singlech == ch) {
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
		System.out.println("myDNA��");
	}

	public static void shortread() {
		// short read ������ ���� ����
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt"); // �б� ����
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\shortread.txt"); // ���� ����
			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // �б� ���� ����
			FileWriter fw = new FileWriter(file2);

			shortread = new String[M];
			byte[] data = new byte[L]; // L���� ���� shortread ����
			Random rand = new Random(); // ������ġ
			rand.setSeed(System.currentTimeMillis()); // �õ尪�� ���÷� �ٲپ �������� ����
			int point;
			String str_data;

			for (int i = 0; i < M; i++) { // M���� short read��
				point = rand.nextInt(N - L); // L���� �б����� ��ġ ��������
				raf.seek(point); // ��ġ�� �̵�
				raf.read(data); // L�� ��ŭ �б�
				str_data = new String(data).trim();
				shortread[i] = str_data;// String[]�� ���� ����
				fw.write(str_data);// ����
			}

			raf.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("shortread: " + e);
		}

		System.out.println("shortread��");
	}

	public static void rabin_karp(int q) {
		// ���ī�� �̿��Ͽ� ���ڿ� ��Ī
		// M���� hash������ ����

		DNA_hash = new int[N - L]; // DNA hash���� ����
		shortread_hash = new int[M]; // shortread hash���� ����

		int i, j;
		int d = 4; // ACTG 4��
		int v_dna = 0;
		int v_short = 0;
		int h = 1;
		char[] array = new char[L];

		for (i = 0; i < L - 1; i++) { // mod ����
			h = (h * d) % q;
		}

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");
			RandomAccessFile raf = new RandomAccessFile(file, "r"); // �б� ���� ����

			String temp;
			String start_temp;
			String end_temp;
			byte[] data = new byte[1];

			// ��� ī���� �̿��Ͽ� �迭�� hash���� ��������
			// DNA �ؽð� ����
			long start = System.currentTimeMillis();
			for (i = 0; i < DNA_hash.length; i++) {
				if (i == 0) {
					// ù��° �ؽð� ���ϱ�
					for (j = 0; j < L; j++) {
						raf.seek(j);
						raf.read(data);
						temp = new String(data).trim();
						v_dna = (d * v_dna + temp.charAt(0)) % q;
					}
					DNA_hash[i] = v_dna;
				} else {
					// ������ �ؽð���
					raf.seek(i - 1);
					raf.read(data);
					start_temp = new String(data).trim();
					raf.seek(i - 1 + L);
					raf.read(data);
					end_temp = new String(data).trim();
					v_dna = (d * (v_dna - start_temp.charAt(0) * h) + end_temp.charAt(0)) % q;
					if (v_dna < 0) {// �������� ������
						v_dna = (v_dna + q);
					}
					DNA_hash[i] = v_dna;
				}
			}

			// shortread hash�� ����
			for (i = 0; i < shortread_hash.length; i++) {
				array = shortread[i].toCharArray();
				v_short = 0;// �ʱ�ȭ

				for (j = 0; j < array.length; j++) {
					// shortread�� hash���� ���ϰ� ����
					v_short = (d * v_short + array[j]) % q;
				}
				shortread_hash[i] = v_short;
			}
			long end = System.currentTimeMillis();
			System.out.println("rabin_karp �ɸ��� �ð�:" + (end - start) + "ms");
		} catch (Exception e) {
			System.out.println("rabin_karp");
		}

		System.out.println("rabin_karp��");

	}

	public static void DNA_match() {
		// hash������ ���Ͽ� shortread�ĺ� ã��
		// ã�� �� ���ڿ����� ��
		// ���� ���ٸ� trivial��� R_DNA�� �� �ڸ� ���� �Ѱ� �߰�

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");// �б�
			File file3 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");// ����

			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // �б� ���� ����
			FileWriter fw = new FileWriter(file3);
			String temp; // ������ ������ ������ ����
			byte[] data = new byte[1];
			byte[] first = new byte[L];

			long start = System.currentTimeMillis();
			for (int i = 0; i < N; i++) {
				// ���� �ϳ��ϳ��� ���̱�
				if(i < N-L) {
					temp = submatch(i);
					if (temp != null) {
						// ���� �ִٸ�
						fw.write(temp);
						i = i + L - 1;// �̸�ŭ �ǳʶ�
					} else if (temp == null) {
						// trivial����
						raf.seek(i);
						raf.read(first);
						temp = new String(first).trim();
						temp = trivial(temp);
						if (temp != null) {
							fw.write(temp);// ���� �߰�
							i = i + L - 1;// �̸�ŭ �ǳʶ�
						} else {
							// trivial�ε� ��ã����
							// R_DNA���� �ѹ��� �߰�
							raf.seek(i + L);
							raf.read(data);
							fw.write(new String(data).trim());
						}
					}
				}else {
					raf.seek(i + L);
					raf.read(data);
					fw.write(new String(data).trim());
				}
				
			}
			long end = System.currentTimeMillis();
			System.out.println("DNA_match �ɸ��� �ð�:" + (end - start) + "ms");
			raf.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("DNA_match");
		}

		System.out.println("DNA_match��");
	}

	public static String trivial(String data) {
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
			if (mismatch <= D) {
				return shortread[i];
			}
		}
		return null;
	}

	public static String submatch(int i) {
		// 1�ܰ�: hash �� ���� �͵� �� ã��
		// 2�ܰ�: R_DNA�� ��������
		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");// �б�
			RandomAccessFile raf = new RandomAccessFile(file, "r"); // �б� ���� ����

			byte[] data = new byte[L];
			String str;

			for (int j = 0; j < shortread_hash.length; j++) {
				// hash�� ���Ͽ� �ϳ��� ���δ�. ���⼱ mismatch�� ���Ͼ�� DNA�� �������.
				if (DNA_hash[i] == shortread_hash[j]) {
					// ���� hash���� ������ ���� ���ڿ��� ���ؾ��Ѵ�.
					raf.seek(i);
					raf.read(data);
					str = new String(data).trim();
					if (compareString(str, j)) {
						// ���ڿ� �񱳸� ���� ������ Ȯ��
						return shortread[j];
					}
				}
			}
		} catch (Exception e) {
			System.out.println("submatch");
		}
		return null;// rabin_karp�� �߰ߵȰ� ����.
	}

	public static boolean compareString(String dna, int j) {
		// hash���� ������ �ش��ϴ� ���ڿ� ��
		int mismatch = 0;
		char[] dna_array = dna.toCharArray();
		char[] short_array = shortread[j].toCharArray();

		for (int i = 0; i < L; i++) {
			if (dna_array[i] != short_array[i]) {
				mismatch++;
			}
			if (mismatch > D) {
				// ���ڿ��� �ʹ� �ٸ��Ƿ� Ʋ��
				return false;
			}
		}
		return true;// snp�� ������ ���ڿ��� ����
	}

	public static int verse() {
		// ������� ��ġ�ϴ°�
		int mismatch = 0;// �ٸ� Ƚ��
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // ������ ��
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // �ٸ��ٸ� mismatch�� ����
					mismatch++;
				}
			}

			fr1.close();
			fr2.close();
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
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // ������ ��
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // �ٸ��ٸ� mismatch�� ����
					mismatch++;
				}
			}

			fr1.close();
			fr2.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return mismatch;
	}

	public static void main(String[] args) {
		// ���ī���� �غ���

		// refDNA();// R_DNA����
		// myDNA();// myDNA����
		shortread();// shortread����
		rabin_karp(q);
		DNA_match();// ��Ī����

		System.out.println("repair_dna_1000�� Mydna_1000��ġ����: " + (double) (N - verse()) / (double) N * 100 + "%");
		System.out.println("test_ref_1000�� Mydna_1000��ġ����: " + (double) (N - verse2()) / (double) N * 100 + "%");

	}
}
