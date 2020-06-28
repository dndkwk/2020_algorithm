package heap;

//2015112232�����

public class h_s {

	public static void heap(int[] arr, int n) {
		// �� ����
		int index = 0;
		for (int i = 0; i < n; i++) {
			index = i;
			while (index > 0) { // �ֻ��� �θ� ��尡 �ɶ� ���� �ݺ�
				if (arr[index] > arr[(index - 1) / 2]) { // �θ� ���� �ڽĳ�� ũ�� ��
					int temp = arr[index]; // swap
					arr[index] = arr[(index - 1) / 2];
					arr[(index - 1) / 2] = temp;
				}
				index = (index - 1) / 2;
			}
		}
	}

	public static void heapsort(int[] arr) {
		// ������
		for (int i = 1; i <= arr.length; i++) {

			heap(arr, arr.length - (i - 1)); // �� ���� (��ġ ������ �ε��� ����)

			for (int j = 0; j < arr.length; j++) { //���
				System.out.print(arr[j] + " ");
			}
			System.out.println("");

			int temp = arr[0];			//���� ū ������ ��ġ �����ֱ�.
			arr[0] = arr[arr.length - i];
			arr[arr.length - i] = temp;
		}
	}

	public static void main(String[] args) {
		// ������
		int[] A = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		heapsort(A);
		
		System.out.println("���");
		
		for (int j = 0; j < A.length; j++) { //���
			System.out.print(A[j] + " ");
		}
		
		
	}

}
