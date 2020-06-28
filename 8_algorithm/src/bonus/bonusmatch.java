package bonus;

//2015112232
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class bonusmatch {

	public static void bruteforce(char[] input, char[] p) {
		int n = input.length;
		int m = p.length;
		int i, j;

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\br_output.txt");
			FileWriter fw = new FileWriter(file);

			if ((n == 0) || (m == 0)) { // 문장 혹은 패턴이 없다면
				System.out.println("패턴 또는 문장이 없습니다.");
				fw.close();
				return;
			}

			for (i = 0; i <= n - m; i++) {
				for (j = 0; j < m; j++) {
					if (input[i + j] != p[j]) { // 패턴이 다르다면
						break;// for문 탈출
					}
				}
				if (j == m) {// 패턴 글자가 전부 같다면 출력
					fw.write("텍스트의 " + i + "번째부터 나타남\r\n");
				}
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void rabin_karp(char[] input, char[] p, int q) {
		int d = 10; // 10진수
		int n = input.length;
		int m = p.length;
		int i, j;
		int v_p = 0;
		int v_input = 0;
		int h = 1;

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\rabin_output.txt");
			FileWriter fw = new FileWriter(file);

			if ((n == 0) || (m == 0)) {// 문장 혹은 패턴이 없다면
				System.out.println("패턴 또는 문장이 없습니다.");
				fw.close();
				return;
			}

			for (i = 0; i < m - 1; i++) { // mod연산
				h = (h * d) % q;
			}

			for (i = 0; i < m; i++) {// 패턴의 해시값과 input의 첫번째 값 해시값
				v_p = (d * v_p + p[i]) % q;
				v_input = (d * v_input + input[i]) % q;
			}

			for (i = 0; i <= n - m; i++) {

				if (v_p == v_input) {// 해시값이 같다면
					for (j = 0; j < m; j++) {// 패턴의 글자와 하나씩 비교
						if (input[i + j] != p[j]) {
							break;
						}
					}
					if (j == m) {
						fw.write("텍스트의 " + i + "번째부터 나타남\r\n");
					}
				}
				if (i < n - m) {// 다음 번째의 해시값 구하기
					v_input = (d * (v_input - input[i] * h) + input[i + m]) % q;

					if (v_input < 0) {// 음수는 양수로 바꾼다
						v_input = (v_input + q);
					}

				}
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void kmp(char[] input, char[] p) {
		int[] table = compute(p);
		int n = input.length;
		int m = p.length;
		int i, j = 0;

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\kmp_output.txt");
			FileWriter fw = new FileWriter(file);

			if ((n == 0) || (m == 0)) {// 문장 혹은 패턴이 없다면
				System.out.println("패턴 또는 문장이 없습니다.");
				fw.close();
				return;
			}

			for (i = 0; i < n; i++) {
				while ((j > 0) && (input[i] != p[j])) {// 매칭중인 상태에서 틀린문자가 나왔을 때
					j = table[j - 1];
				}
				if (input[i] == p[j]) {// 같다면 인덱스 증가
					if (j == (m - 1)) {// 패턴이 전부 같다면 패턴이 존재하는 것
						fw.write("텍스트의 " + (i - m + 1) + "번째부터 나타남\r\n");
						j = table[j];
					} else {
						j++;
					}
				}
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static int[] compute(char[] p) {
		// 접두부 테이블 만들기
		int n = p.length;
		int[] table = new int[n];// 최대 접두부 테이블
		int i, j = 0;

		for (i = 1; i < n; i++) {
			while ((j > 0) && p[i] != p[j]) {// 매칭중인 상태에서 틀린문자가 나왔을 때
				j = table[j - 1];
			}
			if (p[i] == p[j]) {// suffix prefix같다면 증가
				table[i] = ++j;
			}
		}

		return table;
	}

	public static void main(String[] args) {
		// 보너스 매칭
		String input = "";
		String pattern = "";
		int i = 0;
		int q = 13;// prime number

		// try { // 문장 생성
		// File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\100000000.txt");
		// FileWriter fw = new FileWriter(file);
		// for (int j = 0; j < 100; j++) {//진행상황 체크
		// for (i = 0; i < 1000000; i++) { // 문장 랜덤 생성
		// int a = (int) (Math.random() * 10);
		// fw.write(Integer.toString(a));
		// }
		// System.out.println(j);
		// }
		// fw.close();
		// } catch (IOException e) {
		// System.out.println("파일을 못찾았습니다");
		// }

		for (i = 0; i < 30; i++) { // 패턴 랜덤 생성
			int a = (int) (Math.random() * 10);
			pattern = pattern + a;
		}
		// pattern = "101"; //확인을 위한 짧은 패턴
		System.out.println("패턴: " + pattern);
		char[] char_pattern = pattern.toCharArray();

		try {
			// 파일 객체 생성
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\100000000.txt");
			// 입력 스트림
			FileReader fr = new FileReader(file);
			// 입력 버퍼
			BufferedReader br = new BufferedReader(fr);
			input = br.readLine();

			char[] char_input = input.toCharArray();

			long start = System.currentTimeMillis();
			bruteforce(char_input, char_pattern);
			long end = System.currentTimeMillis();
			System.out.println("brute force: " + (end - start) + "ms");

			start = System.currentTimeMillis();
			rabin_karp(char_input, char_pattern, q);
			end = System.currentTimeMillis();
			System.out.println("rabin_karp: " + (end - start) + "ms");

			start = System.currentTimeMillis();
			kmp(char_input, char_pattern);
			end = System.currentTimeMillis();
			System.out.println("kmp: " + (end - start) + "ms");

			fr.close();

		} catch (FileNotFoundException e) {
			System.out.println("파일을 못찾았습니다");
		} catch (IOException e) {
			System.out.println("입출력 예외");
		}
	}

}
