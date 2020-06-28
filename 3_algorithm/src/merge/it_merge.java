package merge;

//2015112232백근주

public class it_merge {
	// 분할
	public static void mergeSort(int arr[], int n) {

		int i;
		int left;

		for (i = 1; i < n; i = 2 * i) { // 이 만큼 나눠야 1개의 원소가 된다.
			for (left = 0; left < n - 1; left += 2 * i) {
				int mid = Math.min(left + i - 1, n - 1);       //중간정하기
				int right = Math.min(left + 2 * i - 1, n - 1); //오른쪽정하기
				merge(arr, left, mid, right);
			}
		}
	}

	public static void merge(int arr[], int l, int m, int r) {
		// 합병
		int i, j, k;
		int n1 = m - l + 1; // 왼쪽 원소 개수
		int n2 = r - m; // 오른쪽 원소 개수

		int L[] = new int[n1]; // 왼쪽 배열
		int R[] = new int[n2]; // 오른쪽 배열

		

		for (i = 0; i < n1; i++) // 왼쪽 배열 넣기
			L[i] = arr[l + i];
		for (j = 0; j < n2; j++) // 오른쪽 배열 넣기
			R[j] = arr[m + 1 + j];

		i = 0;
		j = 0;
		k = l;

		while (i < n1 && j < n2) { // 각각 배열의 끝에 도달하지 않았으면
			if (L[i] <= R[j]) { // 작은 순서대로 넣는다.
				arr[k] = L[i];
				i++;
			} else {
				arr[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) { // 왼쪽 배열의 남아있는 원소 넣기
			arr[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) { // 오른쪽 배열의 남아있는 원소 넣기
			arr[k] = R[j];
			j++;
			k++;
		}
		
		for (int a = l; a <= r; a++) { // 배열 정렬 상황 출력
			System.out.print(arr[a] + " ");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		// 비재귀 합병

		int[] A = { 30, 20, 40, 35, 5, 50, 45, 10, 25, 15 };
		int n = A.length;

		mergeSort(A, n);
		System.out.println("결과");
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
	}
}
