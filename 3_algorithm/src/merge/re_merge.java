package merge;

//2015112232�����

public class re_merge {

	// ����
	public static void mergeSort(int[] arr) {
		if (arr.length > 1) { // �迭 ���̰� 2�̻��϶�
			int[] left = new int[arr.length / 2]; // ���� ���ҵ��� ����� left�迭
			int[] right = new int[arr.length - left.length]; // ���� ���ҵ��� ����� right�迭

			for (int i = 0; i < left.length; i++)
				left[i] = arr[i];
			for (int i = 0; i < right.length; i++)
				right[i] = arr[i + left.length];

			mergeSort(left); // �������� ����
			mergeSort(right);// �������� ����
			merge(left, right, arr); // �պ�

			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();

		} else {
			return;
		}
	}

	// �պ�
	public static void merge(int[] left, int[] right, int[] arr) {
		// left�迭�� right�迭�� ��ģ��
		int leftIdx = 0;
		int rightIdx = 0;
		int arrIdx = 0;

		while (leftIdx < left.length) { // left�迭 ������ ��� ���������� �ݺ��Ѵ�.
			if (rightIdx < right.length) { // right�迭�� �����ִ� ���� ��
				if (left[leftIdx] < right[rightIdx]) { // left right ���� ���Ͽ� ���������� �ִ´�.
					arr[arrIdx] = left[leftIdx];
					leftIdx++; // ���� left�迭 ���� ��
				} else {
					arr[arrIdx] = right[rightIdx];
					rightIdx++; // ���� right�迭 ���� ��
				}
				arrIdx++;
			} else { // right�迭 ���Ұ� ���ٸ� left�� �����ִ� ���Ҹ� ��� �ִ´�.
				while (leftIdx < left.length) {
					arr[arrIdx] = left[leftIdx];
					leftIdx++;
					arrIdx++;
				}
			}
		}

		while (rightIdx < right.length) { // �����ִ� right�迭�� ��� �ִ´�.
			arr[arrIdx] = right[rightIdx];
			rightIdx++;
			arrIdx++;
		}

	}

	public static void main(String[] args) {
		// ��� �պ�

		int[] A = { 30, 20, 40, 35, 5, 50, 45, 10, 25, 15 };

		mergeSort(A);
		System.out.println("���");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
}
