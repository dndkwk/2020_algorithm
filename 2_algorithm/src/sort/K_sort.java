package sort;

//2015112232�����

import java.util.Stack;

//system �Լ� ���

public class K_sort {
	public static void quick(int[] ary, int left, int right) {
		Stack<Integer> lstack = new Stack<Integer>();
		Stack<Integer> rstack = new Stack<Integer>();

		lstack.push(left);
		rstack.push(right);

		while (lstack.isEmpty() != true) {// lstack�� ��������� �����.
			left = lstack.pop();
			int pl = left;// ���� Ŀ��
			right = rstack.pop();
			int pr = right; // ������ Ŀ��
			int x = ary[(left + right) / 2]; // �ǹ�

			do {
				while (ary[pl] > x)
					pl++; // ���������� ���� ���ʿ� ū ������ ���´�.
				while (ary[pr] < x)
					pr--;// ���������� ���� �����ʿ� ���� ������ ���´�.
				if (pl <= pr) { // ���� ������ Ŀ���� ��ġ�ų� �����Ǹ�
					int temp = ary[pl]; // swap�Ѵ�.
					ary[pl] = ary[pr];
					ary[pr] = temp;
					pl++;
					pr--;
				}
			} while (pl <= pr);

			if (left < pr) {
				lstack.push(left); // ���� �׷� ������
				rstack.push(pr); // �ε����� Ǫ���մϴ�.
			}
			if (pl < right) {
				lstack.push(pl); // ������ �׷� ������
				rstack.push(right); // �ε����� Ǫ���մϴ�.
			}
		}
	}

	public static void bubble(int[] arr, int n) {
		if (n == 1) // �� ���ڸ� ������ �����Ѵ�.
			return;

		for (int i = 0; i < n - 1; i++) {
			if (arr[i] < arr[i + 1]) { // ���������� ���� index���� ���� �� �۴ٸ�
				int temp = arr[i]; // swap�Ѵ�
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}

		bubble(arr, n - 1); // n-1�� ������ �ڸ��� �����ش�.
	}

	public static void main(String[] args) {
		// K�迭 ����ð����ϱ�
		int[] B_rand = new int[1000];
		for (int i = 0; i < 1000; i++) {
			B_rand[i] = (int) (Math.random() * 10000) + 1;
		}

		long B_start = System.currentTimeMillis(); // ���� ���۽ð�

		bubble(B_rand, B_rand.length);

		long B_end = System.currentTimeMillis(); // ���� ������ �ð�

		System.out.println("���� ����ð�: " + (B_end - B_start));

		int[] q_rand = new int[1000];

		for (int i = 0; i < 1000; i++) {
			q_rand[i] = (int) (Math.random() * 10000) + 1;
		}

		long Q_start = System.currentTimeMillis(); // �� ���۽ð�
		quick(q_rand, 0, q_rand.length - 1);
		long Q_end = System.currentTimeMillis(); // �� ������ �ð�
		System.out.println("�� ����ð�: " + (Q_end - Q_start));

	}

}
