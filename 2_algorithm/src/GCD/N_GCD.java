package GCD;

//2015112232백근주

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class N_GCD {
	public static int GCD(int a, int b) {
		// a를 b로 나눈 나머지를 r이라 하면 a와 b최대공약수는 b와 r최대공약수와 같다.
		if (b == 0) {
			return a;
		}
		return GCD(b, a % b);
	}

	public static void main(String[] args) {
		// N개 최대공약수
		System.out.println("숫자를 입력해 주세요");
		Scanner input = new Scanner(System.in);
		String str = input.nextLine();
		int[] strnum = Stream.of(str.split("\\s+")).mapToInt(Integer::parseInt).toArray(); // 받은 문자열을 정수형으로 자르고 배열에 넣는다.
		int gcd = strnum[strnum.length - 1]; // 가장 끝에 있는 정수를 변수에 저장
		for (int i = strnum.length - 1; i >= 0; --i) { // 두개의 정수에서 나온 최대공약수를 구하고 그 최대공약수와 다른 수의 최대공약수를 구한다.
			int x, y;
			if (gcd > strnum[i]) {
				x = gcd;
				y = strnum[i];
			} else {
				x = strnum[i];
				y = gcd;
			}
			gcd = GCD(x, y); // 두 정수의 최대공약수를 구한다.
		}
		System.out.println(gcd);
		input.close();
	}
}
