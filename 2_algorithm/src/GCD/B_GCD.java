package GCD;

//2015112232�����


import java.util.Scanner;

public class B_GCD {

	public static void main(String[] args) {
		//����� �ִ�����
		System.out.println("���� �ΰ��� �Է��ϼ���: "); //�ΰ� ���� �Է¹ޱ�
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		while (b != 0) {     //a�� b�� ���� �������� r�̶� �ϸ� a�� b�ִ������� b�� r�ִ������� ����.
			int r = a % b;
			a = b;
			b = r;
		}
		System.out.println("�ִ�����: " + a);
		sc.close();
		
	}

}
