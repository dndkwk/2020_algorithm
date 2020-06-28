package GCD;

//2015112232백근주

import java.util.Scanner;

public class GCD {
	public static int GCD(int a, int b) {
		// a를 b로 나눈 나머지를 r이라 하면 a와 b최대공약수는 b와 r최대공약수와 같다.
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b); //a와 a를 b로 나눈 나머지를 재귀한다.
	}

	public static void main(String[] args) {
		// 재귀 최대공약수
		System.out.println("숫자 두개를 입력하세요: ");
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();

		System.out.println("최대공약수: " + GCD(a, b));
		sc.close();
	}

}
