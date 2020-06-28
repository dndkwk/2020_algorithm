package sort;

//2015112232�����

import java.util.Stack;

public class quick_sort {

	public static void quick(int[] ary, int left, int right) {
		Stack<Integer> lstack = new Stack<Integer>();
		Stack<Integer> rstack = new Stack<Integer>();

		lstack.push(left);
		rstack.push(right);

		while (lstack.isEmpty() != true) { // lstack�� ��������� �����.
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
				if (pl <= pr) { // ���� ������ Ŀ���� ��������
					int temp = ary[pl]; // swap�Ѵ�.
					ary[pl] = ary[pr];
					ary[pr] = temp;
					pl++;
					pr--;
				}
			} while (pl <= pr);// ���� Ŀ���� ������ Ŀ������ ũ�� �������´�.

			if (left < pr) {
				lstack.push(left); // ���� �׷� ������
				rstack.push(pr); // �ε����� Ǫ���Ѵ�.
			}
			if (pl < right) {
				lstack.push(pl); // ������ �׷� ������
				rstack.push(right); // �ε����� Ǫ���Ѵ�.
			}
		}
	}

	public static void main(String[] args) {
		// ����� ��
		int[] rand = new int[10];
		for (int i = 0; i < 10; i++) { // 10���� ���������� �迭�� ����
			rand[i] = (int) (Math.random() * 10000) + 1;
		}

		quick(rand, 0, rand.length - 1);

		for (int i = 0; i < 10; i++) {
			System.out.println(rand[i]);
		}

	}
}
