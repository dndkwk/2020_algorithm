package trivial_match;
//2015112232백근주

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
		// 파일 생성후 50만개의 문자 넣기

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt");
			FileWriter fw = new FileWriter(file);

			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis()); // 시드값을 수시로 바꾸어서 랜덤값을 생성

			for (int i = 0; i < N; i++) {
				fw.write(nucleic(rand.nextInt(4))); // 0~3사이의 값
			}

			fw.close();
		} catch (Exception e) {
			System.out.println("makeDNA: " + e);
		}

	}

	public static char nucleic(int n) {
		// 랜덤 염기 생성

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
		// 염기가 x퍼 확률로 mismatch가 됨
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt"); // 읽기 파일
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt"); // 쓰기 파일
			FileReader fr = new FileReader(file1);
			FileWriter fw = new FileWriter(file2);

			int singlech;
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			int count = 0;
			char ch;

			while ((singlech = fr.read()) != -1) { // 끝까지 읽기
				ch = (char) singlech;
				if(((rand.nextInt(100)+1)%(100/x)) == 0) { //x퍼센트 확률로 다른 염기
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
		//shortread 생성해서 파일에 저장
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt"); // 읽기 파일
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\shortread.txt"); // 쓰기 파일
			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // 읽기 전용 파일
			FileWriter fw = new FileWriter(file2);

			byte[] data = new byte[k]; // 파일 정보 저장
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis()); // 시드값을 수시로 바꾸어서 랜덤값을 생성
			int point;
			String str_data;

			for (int i = 0; i < n; i++) {
				point = rand.nextInt(N - k); // k개씩 읽기위한 위치 랜덤생성
				raf.seek(point); // 위치로 이동
				raf.read(data); // k개 만큼 읽기
				str_data = new String(data).trim();
				fw.write(str_data);
			}

			fw.close();
		} catch (Exception e) {
			System.out.println("shortread: " + e);
		}
	}
	
	

	public static void matchDNA(int k, int n) {
		// reference DNA와 shortread를 이용하여 restoreDNA생성
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\shortread.txt");
			File file3 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");
			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // 읽기 전용 파일

			FileReader fr2 = new FileReader(file2);
			FileWriter fw = new FileWriter(file3);

			String[] array_shortread = new String[n];
			String str_data;
			char[] char_short = new char[k]; // 문자를 받아서 string에 저장
			byte[] data = new byte[k]; // k개 바이트 저장 배열
			String answer;
			int singlech;
			int j = 0, i = 0;
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis()); // 시드값을 수시로 바꾸어서 랜덤값을 생성

			while ((singlech = fr2.read()) != -1) {// shortread파일을 k문자씩잘라 String배열에 저장
				char_short[j] = (char) singlech;
				j++;
				if (j == k) { // k개씩 잘라서 문자열에 저장
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
				if (answer == null) { // 일치 하는게 없다면
					fw.write(nucleic(rand.nextInt(4)));
				} else {// 염기서열 발견
					fw.write(answer);
					i = i + k-1; // k칸 뒤로
				}
			}
			long end = System.currentTimeMillis();
			System.out.println("걸리는 시간:" + (end - start) + "ms");

			raf.close();
			fr2.close();
			fw.close();

		} catch (Exception e) {
			System.out.println("matchDNA: " + e);
		}
	}

	public static String submatchDNA(String data, String[] shortread) {
		// shortread들과 비교하여 mismatch가 2개 이하라면 그 염기서열을 return한다.

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
		// 어느정도 일치하는가
		int mismatch = 0;// 다른 횟수
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // 끝까지 비교
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // 다르다면 mismatch값 증가
					mismatch++;
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return mismatch;
	}
	
	public static int verse2() {
		// 어느정도 일치하는가
		int mismatch = 0;// 다른 횟수
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\R_DNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\myDNA.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // 끝까지 비교
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // 다르다면 mismatch값 증가
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
		System.out.print("shortRead 길이(k): "); // k,n동적 입력
		k = sc.nextInt();
		System.out.print("");
		System.out.print("shortRead 개수(n): ");
		n = sc.nextInt();
		System.out.print("");
		System.out.print("x% 입력: ");
		x = sc.nextInt();

		refDNA(); // refDNA 생성
		myDNA(x); // x% 확률로 다른염기생성
		shortread(k, n); // myDNA의 shortread생성
		matchDNA(k, n); // 매칭
		System.out.println("restoreDNA와 myDNA일치정도: " + (double) (N - verse()) / (double) N * 100 + "%");
		System.out.println("R_DNA와 myDNA일치정도: " + (double) (N - verse2()) / (double) N * 100 + "%");
		sc.close();
	}

}
