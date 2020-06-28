package merge;

//2015112232�����

public class it_merge {
	// ����
	public static void mergeSort(int arr[], int n) {

		int i;
		int left;

		for (i = 1; i < n; i = 2 * i) { // �� ��ŭ ������ 1���� ���Ұ� �ȴ�.
			for (left = 0; left < n - 1; left += 2 * i) {
				int mid = Math.min(left + i - 1, n - 1);       //�߰����ϱ�
				int right = Math.min(left + 2 * i - 1, n - 1); //���������ϱ�
				merge(arr, left, mid, right);
			}
		}
	}

	public static void merge(int arr[], int l, int m, int r) {
		// �պ�
		int i, j, k;
		int n1 = m - l + 1; // ���� ���� ����
		int n2 = r - m; // ������ ���� ����

		int L[] = new int[n1]; // ���� �迭
		int R[] = new int[n2]; // ������ �迭

		

		for (i = 0; i < n1; i++) // ���� �迭 �ֱ�
			L[i] = arr[l + i];
		for (j = 0; j < n2; j++) // ������ �迭 �ֱ�
			R[j] = arr[m + 1 + j];

		i = 0;
		j = 0;
		k = l;

		while (i < n1 && j < n2) { // ���� �迭�� ���� �������� �ʾ�����
			if (L[i] <= R[j]) { // ���� ������� �ִ´�.
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) { // ���� �迭�� �����ִ� ���� �ֱ�
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) { // ������ �迭�� �����ִ� ���� �ֱ�
			arr[k] = R[j];
			j++;
			k++;
		}
		
		for (int a = l; a <= r; a++) { // �迭 ���� ��Ȳ ���
			System.out.print(arr[a] + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		// ����� �պ�

		int[] A = { 30, 20, 40, 35, 5, 50, 45, 10, 25, 15 };
		int n = A.length;

		mergeSort(A, n);
		System.out.println("���");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
	}
}
