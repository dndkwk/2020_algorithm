package algorithm_final;
//2015112232백근주

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;
import java.util.Random;

public class final_algorithm {

	static int N = 50000; // DNA 총 길이: 최대 30억
	static int M = 1500; // short read 개수 : 2000만~2억
	static int L = 50; // short read 길이 : 32~100
	static int D = 2; // mismatch 허용 개수
	static int X = 2; // (2%) mismatch 발생 확률,500~1000염기당 1개 나타난다
	static int q = 1033381;// 소수

	static int[] DNA_hash;
	static int[] shortread_hash;
	static String[] shortread;

	public static void refDNA() {
		// 파일 생성후 N개의 문자 넣기
		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");
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
		System.out.println("refDNA끝");
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

	public static void myDNA() {
		// 염기가 x퍼 확률로 mismatch 생성후 파일에 저장
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt"); // 읽기 파일
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt"); // 쓰기 파일
			FileReader fr = new FileReader(file1);
			FileWriter fw = new FileWriter(file2);

			int singlech;
			Random rand = new Random();
			rand.setSeed(System.currentTimeMillis());
			// int count = 0;
			char ch;

			while ((singlech = fr.read()) != -1) { // 끝까지 읽기
				ch = (char) singlech;
				if (((rand.nextInt(100) + 1) % (100 / X)) == 0) { // x퍼센트 확률로 다른 염기
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
		System.out.println("myDNA끝");
	}

	public static void shortread() {
		// short read 생성후 파일 저장
		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt"); // 읽기 파일
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\shortread.txt"); // 쓰기 파일
			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // 읽기 전용 파일
			FileWriter fw = new FileWriter(file2);

			shortread = new String[M];
			byte[] data = new byte[L]; // L문자 길이 shortread 저장
			Random rand = new Random(); // 랜덤위치
			rand.setSeed(System.currentTimeMillis()); // 시드값을 수시로 바꾸어서 랜덤값을 생성
			int point;
			String str_data;

			for (int i = 0; i < M; i++) { // M개의 short read들
				point = rand.nextInt(N - L); // L개씩 읽기위한 위치 랜덤생성
				raf.seek(point); // 위치로 이동
				raf.read(data); // L개 만큼 읽기
				str_data = new String(data).trim();
				shortread[i] = str_data;// String[]에 따로 저장
				fw.write(str_data);// 저장
			}

			raf.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("shortread: " + e);
		}

		System.out.println("shortread끝");
	}

	public static void rabin_karp(int q) {
		// 라빈카프 이용하여 문자열 매칭
		// M개의 hash값들을 저장

		DNA_hash = new int[N - L]; // DNA hash값들 저장
		shortread_hash = new int[M]; // shortread hash값들 저장

		int i, j;
		int d = 4; // ACTG 4개
		int v_dna = 0;
		int v_short = 0;
		int h = 1;
		char[] array = new char[L];

		for (i = 0; i < L - 1; i++) { // mod 연산
			h = (h * d) % q;
		}

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");
			RandomAccessFile raf = new RandomAccessFile(file, "r"); // 읽기 전용 파일

			String temp;
			String start_temp;
			String end_temp;
			byte[] data = new byte[1];

			// 라빈 카프를 이용하여 배열에 hash값을 저장하자
			// DNA 해시값 저장
			long start = System.currentTimeMillis();
			for (i = 0; i < DNA_hash.length; i++) {
				if (i == 0) {
					// 첫번째 해시값 구하기
					for (j = 0; j < L; j++) {
						raf.seek(j);
						raf.read(data);
						temp = new String(data).trim();
						v_dna = (d * v_dna + temp.charAt(0)) % q;
					}
					DNA_hash[i] = v_dna;
				} else {
					// 나머지 해시값들
					raf.seek(i - 1);
					raf.read(data);
					start_temp = new String(data).trim();
					raf.seek(i - 1 + L);
					raf.read(data);
					end_temp = new String(data).trim();
					v_dna = (d * (v_dna - start_temp.charAt(0) * h) + end_temp.charAt(0)) % q;
					if (v_dna < 0) {// 음수값이 나오면
						v_dna = (v_dna + q);
					}
					DNA_hash[i] = v_dna;
				}
			}

			// shortread hash값 저장
			for (i = 0; i < shortread_hash.length; i++) {
				array = shortread[i].toCharArray();
				v_short = 0;// 초기화

				for (j = 0; j < array.length; j++) {
					// shortread의 hash값을 구하고 저장
					v_short = (d * v_short + array[j]) % q;
				}
				shortread_hash[i] = v_short;
			}
			long end = System.currentTimeMillis();
			System.out.println("rabin_karp 걸리는 시간:" + (end - start) + "ms");
		} catch (Exception e) {
			System.out.println("rabin_karp");
		}

		System.out.println("rabin_karp끝");

	}

	public static void DNA_match() {
		// hash값들을 비교하여 shortread후보 찾음
		// 찾은 후 문자열들을 비교
		// 만약 없다면 trivial사용 R_DNA의 그 자리 문자 한개 추가

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");// 읽기
			File file3 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");// 쓰기

			RandomAccessFile raf = new RandomAccessFile(file1, "r"); // 읽기 전용 파일
			FileWriter fw = new FileWriter(file3);
			String temp; // 마지막 끝문자 저장할 변수
			byte[] data = new byte[1];
			byte[] first = new byte[L];

			long start = System.currentTimeMillis();
			for (int i = 0; i < N; i++) {
				// 문자 하나하나씩 붙이기
				if(i < N-L) {
					temp = submatch(i);
					if (temp != null) {
						// 값이 있다면
						fw.write(temp);
						i = i + L - 1;// 이만큼 건너뜀
					} else if (temp == null) {
						// trivial실행
						raf.seek(i);
						raf.read(first);
						temp = new String(first).trim();
						temp = trivial(temp);
						if (temp != null) {
							fw.write(temp);// 문자 추가
							i = i + L - 1;// 이만큼 건너뜀
						} else {
							// trivial로도 못찾으면
							// R_DNA에서 한문자 추가
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
			System.out.println("DNA_match 걸리는 시간:" + (end - start) + "ms");
			raf.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("DNA_match");
		}

		System.out.println("DNA_match끝");
	}

	public static String trivial(String data) {
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
			if (mismatch <= D) {
				return shortread[i];
			}
		}
		return null;
	}

	public static String submatch(int i) {
		// 1단계: hash 값 같은 것들 중 찾기
		// 2단계: R_DNA값 가져오기
		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");// 읽기
			RandomAccessFile raf = new RandomAccessFile(file, "r"); // 읽기 전용 파일

			byte[] data = new byte[L];
			String str;

			for (int j = 0; j < shortread_hash.length; j++) {
				// hash값 비교하여 하나씩 붙인다. 여기선 mismatch가 안일어나는 DNA는 골라진다.
				if (DNA_hash[i] == shortread_hash[j]) {
					// 같은 hash값을 가지면 이제 문자열을 비교해야한다.
					raf.seek(i);
					raf.read(data);
					str = new String(data).trim();
					if (compareString(str, j)) {
						// 문자열 비교를 통해 같은지 확인
						return shortread[j];
					}
				}
			}
		} catch (Exception e) {
			System.out.println("submatch");
		}
		return null;// rabin_karp로 발견된게 없다.
	}

	public static boolean compareString(String dna, int j) {
		// hash값이 같으면 해당하는 문자열 비교
		int mismatch = 0;
		char[] dna_array = dna.toCharArray();
		char[] short_array = shortread[j].toCharArray();

		for (int i = 0; i < L; i++) {
			if (dna_array[i] != short_array[i]) {
				mismatch++;
			}
			if (mismatch > D) {
				// 문자열이 너무 다르므로 틀림
				return false;
			}
		}
		return true;// snp을 가정한 문자열이 맞음
	}

	public static int verse() {
		// 어느정도 일치하는가
		int mismatch = 0;// 다른 횟수
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\restoreDNA.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // 끝까지 비교
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // 다르다면 mismatch값 증가
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
		// 어느정도 일치하는가
		int mismatch = 0;// 다른 횟수
		int singlech1;
		int singlech2;

		try {
			File file1 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\ref.txt");
			File file2 = new File("C:\\Users\\Baek Guen Joo\\Desktop\\mydna.txt");
			FileReader fr1 = new FileReader(file1);
			FileReader fr2 = new FileReader(file2);

			while ((singlech1 = fr1.read()) != -1) { // 끝까지 비교
				singlech2 = fr2.read();
				if ((char) singlech1 != (char) singlech2) { // 다르다면 mismatch값 증가
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
		// 라빈카프로 해보자

		// refDNA();// R_DNA생성
		// myDNA();// myDNA생성
		shortread();// shortread생성
		rabin_karp(q);
		DNA_match();// 매칭시작

		System.out.println("repair_dna_1000와 Mydna_1000일치정도: " + (double) (N - verse()) / (double) N * 100 + "%");
		System.out.println("test_ref_1000와 Mydna_1000일치정도: " + (double) (N - verse2()) / (double) N * 100 + "%");

	}
}
