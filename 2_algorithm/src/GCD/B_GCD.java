package GCD;

//2015112232백근주


import java.util.Scanner;

public class B_GCD {

	public static void main(String[] args) {
		//비재귀 최대공약수
		System.out.println("숫자 두개를 입력하세요: "); //두개 숫자 입력받기
		Scanner sc = new Scanner(System.in);

		int a = sc.nextInt();
		int b = sc.nextInt();
		while (b != 0) {     //a를 b로 나눈 나머지를 r이라 하면 a와 b최대공약수는 b와 r최대공약수와 같다.
			int r = a % b;
			a = b;
			b = r;
		}
		System.out.println("최대공약수: " + a);
		sc.close();
		
	}

}
