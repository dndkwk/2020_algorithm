package BWT;
//2015112232백근주

public class BWT {

	public static String[] rest(String T, int[] ar_number) {
		// Fill the rest
		// $ 붙이기

		T = T + '$'; // $ 붙이기
		String[] T_array = new String[T.length()];

		for (int i = 0; i < T.length(); i++) {
			T_array[i] = T.substring(T.length() - i - 1) + T.substring(0, T.length() - i - 1); // 문자열 구하기
			ar_number[i] = T.length() - i - 1; // ar_number 초기화
		}

		for (int i = 0; i < T_array.length; i++) {// 출력
			System.out.println(T_array[i]);
		}

		return T_array;
	}

	public static String[] rest_sort(String[] T_array, int[] ar_number) {
		String temp;
		int n = 0;

		for (int i = 0; i < T_array.length; i++) {
			for (int j = 0; j < T_array.length; j++) {
				// i번째 문자열이 순서상 더 크다면 j번쨰 문자열과 바꾼다.
				if (T_array[i].compareTo(T_array[j]) < 0) {
					temp = T_array[i];
					T_array[i] = T_array[j];
					T_array[j] = temp;

					n = ar_number[i];// ar_number위치 바꾸기
					ar_number[i] = ar_number[j];
					ar_number[j] = n;
				}
			}
		}

		for (int i = 0; i < T_array.length; i++) { // 출력
			System.out.println(T_array[i]);
		}

		return T_array;
	}

	public static String bwt(String[] T_array) {
		// bwt 구하기
		String bwt = "";
		for (int i = 0; i < T_array.length; i++) {
			// 마지막 문자들을 모두 합친다.
			bwt = bwt + T_array[i].substring(T_array.length - 1);
		}
		System.out.println(bwt); // 출력
		return bwt;
	}

	public static String pre_bwt(String[] T_array) {
		// pre_bwt 구하기
		String pre_bwt = "";
		for (int i = 0; i < T_array.length; i++) {
			// 첫번째 문자들을 모두 합친다.
			pre_bwt = pre_bwt + T_array[i].substring(0, 1);
		}
		System.out.println(pre_bwt); // 출력
		return pre_bwt;
	}

	public static void show_number(int[] ar_number) {
		for (int i = 0; i < ar_number.length; i++) { // ar_number 출력
			System.out.print(ar_number[i] + " ");
		}

	}

	public static void restore(String bwt, int[] ar_number) {
		// 문자열 복원
		String t = "";
		char[] temp = bwt.toCharArray();

		for (int i = ar_number.length - 1; i > 0; i--) {
			for (int j = 0; j < ar_number.length; j++) {
				if (i == ar_number[j]) { // ar_numbr의 값이 내가찾는 순서번호와 같다면 그 인덱스의 char값을 합침
					t = Character.toString(temp[j]) + t;
					System.out.println(t);
				}
			}
		}
	}

	public static void main(String[] args) {
		String T = "acaacg";
		String[] T_array;
		String bwt;
		String pre_bwt;
		int[] ar_number = new int[T.length() + 1];

		// fill the rest
		System.out.println("fill the rest>>");
		T_array = rest(T, ar_number);
		System.out.println("");
		// sort
		System.out.println("sort>>");
		T_array = rest_sort(T_array, ar_number);
		System.out.println("");
		// bwt
		System.out.print("bwt = ");
		bwt = bwt(T_array);
		// pre_bwt
		System.out.print("pre_twt = ");
		pre_bwt = pre_bwt(T_array);
		// ar_number
		System.out.print("ar_number = ");
		show_number(ar_number);
		System.out.println("");

		System.out.println("");
		System.out.println("문자열 복원작업 시작");
		restore(bwt, ar_number);
		System.out.println("복원 완료");

	}

}
