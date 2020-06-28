package sort;

//2015112232백근주

import java.util.Stack;

//system 함수 사용

public class K_sort {
	public static void quick(int[] ary, int left, int right) {
		Stack<Integer> lstack = new Stack<Integer>();
		Stack<Integer> rstack = new Stack<Integer>();

		lstack.push(left);
		rstack.push(right);

		while (lstack.isEmpty() != true) {// lstack이 비어있으면 멈춘다.
			left = lstack.pop();
			int pl = left;// 왼쪽 커서
			right = rstack.pop();
			int pr = right; // 오른쪽 커서
			int x = ary[(left + right) / 2]; // 피벗

			do {
				while (ary[pl] > x)
					pl++; // 내림차순을 위해 왼쪽에 큰 정수만 놓는다.
				while (ary[pr] < x)
					pr--;// 내림차순을 위해 오른쪽에 작은 정수만 놓는다.
				if (pl <= pr) { // 왼쪽 오른쪽 커서가 겹치거나 역전되면
					int temp = ary[pl]; // swap한다.
					ary[pl] = ary[pr];
					ary[pr] = temp;
					pl++;
					pr--;
				}
			} while (pl <= pr);

			if (left < pr) {
				lstack.push(left); // 왼쪽 그룹 범위의
				rstack.push(pr); // 인덱스를 푸시합니다.
			}
			if (pl < right) {
				lstack.push(pl); // 오른쪽 그룹 범위의
				rstack.push(right); // 인덱스를 푸시합니다.
			}
		}
	}

	public static void bubble(int[] arr, int n) {
		if (n == 1) // 한 숫자만 남으면 리턴한다.
			return;

		for (int i = 0; i < n - 1; i++) {
			if (arr[i] < arr[i + 1]) { // 내림차순을 위해 index작은 값이 더 작다면
				int temp = arr[i]; // swap한다
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}

		bubble(arr, n - 1); // n-1개 숫자의 자리를 정해준다.
	}

	public static void main(String[] args) {
		// K배열 실행시간구하기
		int[] B_rand = new int[1000];
		for (int i = 0; i < 1000; i++) {
			B_rand[i] = (int) (Math.random() * 10000) + 1;
		}

		long B_start = System.currentTimeMillis(); // 버블 시작시간

		bubble(B_rand, B_rand.length);

		long B_end = System.currentTimeMillis(); // 버블 끝나는 시간

		System.out.println("버블 실행시간: " + (B_end - B_start));

		int[] q_rand = new int[1000];

		for (int i = 0; i < 1000; i++) {
			q_rand[i] = (int) (Math.random() * 10000) + 1;
		}

		long Q_start = System.currentTimeMillis(); // 퀵 시작시간
		quick(q_rand, 0, q_rand.length - 1);
		long Q_end = System.currentTimeMillis(); // 퀵 끝나는 시간
		System.out.println("퀵 실행시간: " + (Q_end - Q_start));

	}

}
