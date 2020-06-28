package heap;

//2015112232백근주

public class h_s {

	public static void heap(int[] arr, int n) {
		// 힙 구현
		int index = 0;
		for (int i = 0; i < n; i++) {
			index = i;
			while (index > 0) { // 최상위 부모 노드가 될때 까지 반복
				if (arr[index] > arr[(index - 1) / 2]) { // 부모 노드와 자식노드 크기 비교
					int temp = arr[index]; // swap
					arr[index] = arr[(index - 1) / 2];
					arr[(index - 1) / 2] = temp;
				}
				index = (index - 1) / 2;
			}
		}
	}

	public static void heapsort(int[] arr) {
		// 힙정렬
		for (int i = 1; i <= arr.length; i++) {

			heap(arr, arr.length - (i - 1)); // 힙 구현 (위치 정해진 인덱스 제외)

			for (int j = 0; j < arr.length; j++) { //출력
				System.out.print(arr[j] + " ");
			}
			System.out.println("");

			int temp = arr[0];			//제일 큰 숫자의 위치 정해주기.
			arr[0] = arr[arr.length - i];
			arr[arr.length - i] = temp;
		}
	}

	public static void main(String[] args) {
		// 힙생성
		int[] A = { 4, 1, 3, 2, 16, 9, 10, 14, 8, 7 };
		heapsort(A);
		
		System.out.println("결과");
		
		for (int j = 0; j < A.length; j++) { //출력
			System.out.print(A[j] + " ");
		}
		
		
	}

}
