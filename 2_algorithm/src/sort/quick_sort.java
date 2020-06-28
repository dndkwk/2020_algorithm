package sort;

//2015112232백근주

import java.util.Stack;

public class quick_sort {

	public static void quick(int[] ary, int left, int right) {
		Stack<Integer> lstack = new Stack<Integer>();
		Stack<Integer> rstack = new Stack<Integer>();

		lstack.push(left);
		rstack.push(right);

		while (lstack.isEmpty() != true) { // lstack이 비어있으면 멈춘다.
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
				if (pl <= pr) { // 왼쪽 오른쪽 커서가 만나기전
					int temp = ary[pl]; // swap한다.
					ary[pl] = ary[pr];
					ary[pr] = temp;
					pl++;
					pr--;
				}
			} while (pl <= pr);// 왼쪽 커서가 오른쪽 커서보다 크면 빠져나온다.

			if (left < pr) {
				lstack.push(left); // 왼쪽 그룹 범위의
				rstack.push(pr); // 인덱스를 푸시한다.
			}
			if (pl < right) {
				lstack.push(pl); // 오른쪽 그룹 범위의
				rstack.push(right); // 인덱스를 푸시한다.
			}
		}
	}

	public static void main(String[] args) {
		// 비재귀 퀵
		int[] rand = new int[10];
		for (int i = 0; i < 10; i++) { // 10개의 정수난수를 배열에 저장
			rand[i] = (int) (Math.random() * 10000) + 1;
		}

		quick(rand, 0, rand.length - 1);

		for (int i = 0; i < 10; i++) {
			System.out.println(rand[i]);
		}

	}
}
