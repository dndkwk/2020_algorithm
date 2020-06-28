package GCD;

//2015112232�����

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class N_GCD {
	public static int GCD(int a, int b) {
		// a�� b�� ���� �������� r�̶� �ϸ� a�� b�ִ������� b�� r�ִ������� ����.
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}

	public static void main(String[] args) {
		// N�� �ִ�����
		System.out.println("���ڸ� �Է��� �ּ���");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		int[] strnum = Stream.of(str.split("\\s+")).mapToInt(Integer::parseInt).toArray(); // ���� ���ڿ��� ���������� �ڸ��� �迭�� �ִ´�.
		int gcd = strnum[strnum.length - 1]; // ���� ���� �ִ� ������ ������ ����
		for (int i = strnum.length - 1; i >= 0; --i) { // �ΰ��� �������� ���� �ִ������� ���ϰ� �� �ִ������� �ٸ� ���� �ִ������� ���Ѵ�.
			int x, y;
			if (gcd > strnum[i]) {
				x = gcd;
				y = strnum[i];
			} else {
				x = strnum[i];
				y = gcd;
			}
			gcd = GCD(x, y); // �� ������ �ִ������� ���Ѵ�.
		}
		System.out.println(gcd);
		input.close();
	}
}
