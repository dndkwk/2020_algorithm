package minmax;
//2015112232�����
public class min_max {

	public static int Minimum(int[] arr, int n) {
		//min�� ���ϱ�
		int i, temp;
		temp = arr[0];

		for (i = 1; i < n; i++) {
			if (temp > arr[i]) { // ���� ���� temp�� �ִ´�.
				temp = arr[i];
			}
		}
		return temp;
	}

	public static int Maximum(int[] arr, int n) {
		//max�� ���ϱ�
		int i, temp;
		temp = arr[0];

		for (i = 1; i < n; i++) {
			if (temp < arr[i]) {// ū ���� temp�� �ִ´�.
				temp = arr[i];
			}
		}

		return temp;
	}

	public static void main(String[] args) {

		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = (int) (Math.random() * 100000) + 1; // ���� �迭 ����
		}
		System.out.println("�ּҰ�: " + Minimum(arr, arr.length));
		System.out.println("�ִ밪: " + Maximum(arr, arr.length));

	}
}
