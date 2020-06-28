package minmax;
//2015112232백근주
public class min_max {

	public static int Minimum(int[] arr, int n) {
		//min값 구하기
		int i, temp;
		temp = arr[0];

		for (i = 1; i < n; i++) {
			if (temp > arr[i]) { // 작은 값을 temp에 넣는다.
				temp = arr[i];
			}
		}
		return temp;
	}

	public static int Maximum(int[] arr, int n) {
		//max값 구하기
		int i, temp;
		temp = arr[0];

		for (i = 1; i < n; i++) {
			if (temp < arr[i]) {// 큰 값을 temp에 넣는다.
				temp = arr[i];
			}
		}

		return temp;
	}

	public static void main(String[] args) {

		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = (int) (Math.random() * 100000) + 1; // 난수 배열 생성
		}
		System.out.println("최소값: " + Minimum(arr, arr.length));
		System.out.println("최대값: " + Maximum(arr, arr.length));

	}
}
