package sort;

//2015112232�����

public class bubble_sort {

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
		// ��� ����

		int[] rand = new int[10];
		for (int i = 0; i < 10; i++) { // ���� ���� 10���� �迭�� ����
			rand[i] = (int) (Math.random() * 10000) + 1;
		}

		bubble(rand, rand.length);// �������� �ѹ����ư��� ���� ���ڸ��� �ڸ��� �������Ƿ� n�������Ѵ�.

		for (int i = 0; i < 10; i++) { // ���
			System.out.println(rand[i]);
		}
	}

}
