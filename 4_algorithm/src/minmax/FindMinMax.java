package minmax;
//2015112232백근주
public class FindMinMax {

	public static int min, max;// 최대최소 저장 변수

	public static void Find_Min_Max(int[] arr, int n) {
		// 최대 최소를 동시에 찾는다.
		int i, small, large;
		min = arr[0];
		max = arr[0];

		for (i = 1; i < n - 1; i = i + 2) { // 두개씩 묶어서 비교
			if (arr[i] < arr[i + 1]) {
				small = arr[i]; // 둘 중 큰값은 max후보이고
				large = arr[i + 1];// 작은값은 min후보이다
			} else {
				small = arr[i + 1];
				large = arr[i];
			}
			if (small < min) { // 가장 작은 min
				min = small;
			}
			if (large > max) { // 가장 큰 max
				max = large;
			}
		}
	}

	public static void main(String[] args) {

		int[] arr = new int[1000];
		for (int i = 0; i < 1000; i++) {
			arr[i] = (int) (Math.random() * 100000) + 1;// 난수 배열 생성
		}

		Find_Min_Max(arr, arr.length); // 최대최소 정하기

		System.out.println("최소값: " + min);
		System.out.println("최대값: " + max);
	}

}
