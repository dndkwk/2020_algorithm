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

			if ((n == 0) || (m == 0)) { // ���� Ȥ�� ������ ���ٸ�
				System.out.println("���� �Ǵ� ������ �����ϴ�.");
				fw.close();
				return;
			}

			for (i = 0; i <= n - m; i++) {
				for (j = 0; j < m; j++) {
					if (input[i + j] != p[j]) { // ������ �ٸ��ٸ�
						break;// for�� Ż��
					}
				}
				if (j == m) {// ���� ���ڰ� ���� ���ٸ� ���
					fw.write("�ؽ�Ʈ�� " + i + "��°���� ��Ÿ��\r\n");
				}
			}

			fw.close();
		} catch (IOException e) {
			System.out.println("IOException");
		}
	}

	public static void rabin_karp(char[] input, char[] p, int q) {
		int d = 10; // 10����
		int n = input.length;
		int m = p.length;
		int i, j;
		int v_p = 0;
		int v_input = 0;
		int h = 1;

		try {
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\rabin_output.txt");
			FileWriter fw = new FileWriter(file);

			if ((n == 0) || (m == 0)) {// ���� Ȥ�� ������ ���ٸ�
				System.out.println("���� �Ǵ� ������ �����ϴ�.");
				fw.close();
				return;
			}

			for (i = 0; i < m - 1; i++) { // mod����
				h = (h * d) % q;
			}

			for (i = 0; i < m; i++) {// ������ �ؽð��� input�� ù��° �� �ؽð�
				v_p = (d * v_p + p[i]) % q;
				v_input = (d * v_input + input[i]) % q;
			}

			for (i = 0; i <= n - m; i++) {

				if (v_p == v_input) {// �ؽð��� ���ٸ�
					for (j = 0; j < m; j++) {// ������ ���ڿ� �ϳ��� ��
						if (input[i + j] != p[j]) {
							break;
						}
					}
					if (j == m) {
						fw.write("�ؽ�Ʈ�� " + i + "��°���� ��Ÿ��\r\n");
					}
				}
				if (i < n - m) {// ���� ��°�� �ؽð� ���ϱ�
					v_input = (d * (v_input - input[i] * h) + input[i + m]) % q;

					if (v_input < 0) {// ������ ����� �ٲ۴�
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

			if ((n == 0) || (m == 0)) {// ���� Ȥ�� ������ ���ٸ�
				System.out.println("���� �Ǵ� ������ �����ϴ�.");
				fw.close();
				return;
			}

			for (i = 0; i < n; i++) {
				while ((j > 0) && (input[i] != p[j])) {// ��Ī���� ���¿��� Ʋ�����ڰ� ������ ��
					j = table[j - 1];
				}
				if (input[i] == p[j]) {// ���ٸ� �ε��� ����
					if (j == (m - 1)) {// ������ ���� ���ٸ� ������ �����ϴ� ��
						fw.write("�ؽ�Ʈ�� " + (i - m + 1) + "��°���� ��Ÿ��\r\n");
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
		// ���κ� ���̺� �����
		int n = p.length;
		int[] table = new int[n];// �ִ� ���κ� ���̺�
		int i, j = 0;

		for (i = 1; i < n; i++) {
			while ((j > 0) && p[i] != p[j]) {// ��Ī���� ���¿��� Ʋ�����ڰ� ������ ��
				j = table[j - 1];
			}
			if (p[i] == p[j]) {// suffix prefix���ٸ� ����
				table[i] = ++j;
			}
		}

		return table;
	}

	public static void main(String[] args) {
		// ���ʽ� ��Ī
		String input = "";
		String pattern = "";
		int i = 0;
		int q = 13;// prime number

		// try { // ���� ����
		// File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\100000000.txt");
		// FileWriter fw = new FileWriter(file);
		// for (int j = 0; j < 100; j++) {//�����Ȳ üũ
		// for (i = 0; i < 1000000; i++) { // ���� ���� ����
		// int a = (int) (Math.random() * 10);
		// fw.write(Integer.toString(a));
		// }
		// System.out.println(j);
		// }
		// fw.close();
		// } catch (IOException e) {
		// System.out.println("������ ��ã�ҽ��ϴ�");
		// }

		for (i = 0; i < 30; i++) { // ���� ���� ����
			int a = (int) (Math.random() * 10);
			pattern = pattern + a;
		}
		// pattern = "101"; //Ȯ���� ���� ª�� ����
		System.out.println("����: " + pattern);
		char[] char_pattern = pattern.toCharArray();

		try {
			// ���� ��ü ����
			File file = new File("C:\\Users\\Baek Guen Joo\\Desktop\\100000000.txt");
			// �Է� ��Ʈ��
			FileReader fr = new FileReader(file);
			// �Է� ����
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
			System.out.println("������ ��ã�ҽ��ϴ�");
		} catch (IOException e) {
			System.out.println("����� ����");
		}
	}

}
