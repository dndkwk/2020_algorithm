package sort;

//2015112232백근주

public class bubble_sort {

	public static void bubble(int[] arr, int n) {
		if (n == 1) // 한 숫자만 남으면 리턴한다.
			return;

		for (int i = 0; i < n - 1; i++) {
			if (arr[i] < arr[i + 1]) { // 내림차순을 위해 index작은 값이 더 작다면
				int temp = arr[i]; // swap한다
				arr[i] = arr[i + 1];
				arr[i + 1] = temp;
			}
		}

		bubble(arr, n - 1); // n-1개 숫자의 자리를 정해준다.
	}

	public static void main(String[] args) {
		// 재귀 버블

		int[] rand = new int[10];
		for (int i = 0; i < 10; i++) { // 난수 정수 10개를 배열에 저장
			rand[i] = (int) (Math.random() * 10000) + 1;
		}

		bubble(rand, rand.length);// 버블정렬 한번돌아갈때 마다 한자리씩 자리가 정해지므로 n번돌게한다.

		for (int i = 0; i < 10; i++) { // 출력
			System.out.println(rand[i]);
		}
	}

}
