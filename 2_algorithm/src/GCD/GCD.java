package GCD;

//2015112232�����

import java.util.Scanner;

public class GCD {
	public static int GCD(int a, int b) {
		// a�� b�� ���� �������� r�̶� �ϸ� a�� b�ִ������� b�� r�ִ������� ����.
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b); //a�� a�� b�� ���� �������� ����Ѵ�.
	}

	public static void main(String[] args) {
		// ��� �ִ�����
		System.out.println("���� �ΰ��� �Է��ϼ���: ");
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();

		System.out.println("�ִ�����: " + GCD(a, b));
		sc.close();
	}

}
