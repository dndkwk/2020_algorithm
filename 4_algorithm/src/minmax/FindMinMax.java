package minmax;
//2015112232�����
public class FindMinMax {

	public static int min, max;// �ִ��ּ� ���� ����

	public static void Find_Min_Max(int[] arr, int n) {
		// �ִ� �ּҸ� ���ÿ� ã�´�.
		int i, small, large;
		min = arr[0];
		max = arr[0];

		for (i = 1; i < n - 1; i = i + 2) { // �ΰ��� ��� ��
			if (arr[i] < arr[i + 1]) {
				small = arr[i]; // �� �� ū���� max�ĺ��̰�
				large = arr[i + 1];// �������� min�ĺ��̴�
			} else {
				small = arr[i + 1];
				large = arr[i];
			}
			if (small < min) { // ���� ���� min
				min = small;
			}
			if (large > max) { // ���� ū max
				max = large;
			}
		}
	}

	public static void main(String[] args) {

		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = (int) (Math.random() * 100000) + 1;// ���� �迭 ����
		}

		Find_Min_Max(arr, arr.length); // �ִ��ּ� ���ϱ�

		System.out.println("�ּҰ�: " + min);
		System.out.println("�ִ밪: " + max);
	}

}
