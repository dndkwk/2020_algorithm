package merge;

//2015112232백근주

public class re_merge {

	// 분할
	public static void mergeSort(int[] arr) {
		if (arr.length > 1) { // 배열 길이가 2이상일때
			int[] left = new int[arr.length / 2]; // 분할 원소들이 저장될 left배열
			int[] right = new int[arr.length - left.length]; // 분할 원소들이 저장될 right배열

			for (int i = 0; i < left.length; i++)
				left[i] = arr[i];
			for (int i = 0; i < right.length; i++)
				right[i] = arr[i + left.length];

			mergeSort(left); // 조각조각 분할
			mergeSort(right);// 조각조각 분할
			merge(left, right, arr); // 합병

			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i] + " ");
			}
			System.out.println();

		} else {
			return;
		}
	}

	// 합병
	public static void merge(int[] left, int[] right, int[] arr) {
		// left배열과 right배열을 합친다
		int leftIdx = 0;
		int rightIdx = 0;
		int arrIdx = 0;

		while (leftIdx < left.length) { // left배열 원소을 모두 넣을때까지 반복한다.
			if (rightIdx < right.length) { // right배열의 남아있는 원소 비교
				if (left[leftIdx] < right[rightIdx]) { // left right 원소 비교하여 작은값부터 넣는다.
					arr[arrIdx] = left[leftIdx];
					leftIdx++; // 다음 left배열 원소 비교
				} else {
					arr[arrIdx] = right[rightIdx];
					rightIdx++; // 다음 right배열 원소 비교
				}
				arrIdx++;
			} else { // right배열 원소가 없다면 left의 남아있는 원소를 모두 넣는다.
				while (leftIdx < left.length) {
					arr[arrIdx] = left[leftIdx];
					leftIdx++;
					arrIdx++;
				}
			}
		}

		while (rightIdx < right.length) { // 남아있는 right배열을 모두 넣는다.
			arr[arrIdx] = right[rightIdx];
			rightIdx++;
			arrIdx++;
		}

	}

	public static void main(String[] args) {
		// 재귀 합병

		int[] A = { 30, 20, 40, 35, 5, 50, 45, 10, 25, 15 };

		mergeSort(A);
		System.out.println("결과");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
}
