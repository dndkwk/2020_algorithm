package hamilton;
//2015112232백근주

public class hamilton {

	public static void combination(int[][] matrix, int row, String[] array) {
		// 조합하기
		int count = 1; // return조건을 위한 변수
		String s = array[row];// 처음 문자를 저장
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] != 0) { // 연결되는 문자를 만나면
				subcombination(matrix, i, array, s, count + 1);
			}
		}
	}

	public static void subcombination(int[][] matrix, int row, String[] array, String s, int count) {
		// 연산하는 함수
		if (count == matrix.length) { // 만일 모든 row들을 다 돌았으면
			s = s + array[row].substring(array[0].length() - 1);
			if (Iscorrect(array, s)) { // 중복이 들어있는지 체크
				System.out.println(s); // 조합하고 출력
			}
		} else {
			s = s + array[row].substring(array[0].length() - 1); // 다음 문자의 마지막 문자를 조합해준다.
			for (int i = 0; i < matrix.length; i++) {
				if ((matrix[row][i] != 0)) { // 연결되는 문자 발견
					subcombination(matrix, i, array, s, count + 1);
				}
			}
		}
	}

	public static boolean Iscorrect(String[] array, String s) {
		// 이 문자열에 중복문자가 사용됬는지 확인
		int n = array.length;
		String[] s_array = new String[n];
		boolean[] check = new boolean[n];

		for (int i = 0; i < n; i++) { // 중복체크 초기화
			check[i] = false;
		}

		for (int i = 0; i < n; i++) {
			s_array[i] = s.substring(i, i + array[0].length()); // s의 문자열을 spectrum처럼 자른다.
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (array[i].equals(s_array[j]) && (check[i] == false)) { // 체크가 안되어있고 들어있는게 확인되면
					check[i] = true; // 사용되었다고 체크
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (check[i] == false) { // 하나라도 사용안된게 확인되면
				return false; // 이 문자열은 틀린것
			}
		}

		return true; // s는 모든 array의 문자열을 사용했음
	}

	public static void spectrum(String[] s) {
		// 해밀턴 path로 해결하기
		int n = s.length;
		int[][] matrix = new int[n][n];
		int l = s[0].length();

		// 해밀턴 path 만들기
		// 자기 자신이 아니라 남과 비교하여 1~l까지의 문자가 상대방의 0~l-1문자와 같다면 그 문자의 인덱스를 입력
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				if (s[row].substring(1).equals(s[column].substring(0, l - 1)) && (row != column)) {
					matrix[row][column] = 1;
				} else {
					matrix[row][column] = 0;
				}
			}
		}

		// for (int i = 0; i < n; i++) { // matrix 출력
		// for (int j = 0; j < n; j++) {
		// System.out.print(String.format("%3d", matrix[i][j]));
		// }
		// System.out.println("");
		// }

		for (int i = 0; i < n; i++) { // 모든 출발점에서 시작해본다
			combination(matrix, i, s);
		}
	}

	public static void main(String[] args) {
		String[] array1 = { "AGT", "AAA", "ACT", "AAC", "CTT", "GTA", "TTT", "TAA" };
		String[] array2 = { "ATG", "AGG", "TGC", "TCC", "GTC", "GGT", "GCA", "CAG" };
		String[] array3 = { "ATG", "TGG", "TGC", "GTG", "GGC", "GCA", "GCG", "CGT" };
		String[] array4 = { "ATGC", "TGCG", "GCGG", "CGGC", "GGCT", "GCTG", "CTGT", "TGTA", "GTAT", "TATG", "ATGG",
				"TGGT", "GGTG" };
		String[] array5 = { "ABA", "BAB", "ABC", "BCB", "CBA" }; // 내가만든 spectrum
		String[] array6 = { "TAT", "ATG", "TGG", "GGT", "GTG", "TGC" }; // ppt spectrum
		String[] array7 = { "ATG", "GGT", "GTG", "TAT", "TGC", "TGG" };

		
		System.out.println("array1 나올수 있는 모든 경로:");
		spectrum(array1);
		System.out.println("array2 나올수 있는 모든 경로:");
		spectrum(array2);
		System.out.println("array3 나올수 있는 모든 경로:");
		spectrum(array3);
		System.out.println("array4 나올수 있는 모든 경로:");
		spectrum(array4);
		System.out.println("array5 나올수 있는 모든 경로:");
		spectrum(array5);
		System.out.println("array6 나올수 있는 모든 경로:");
		spectrum(array6);
		System.out.println("array7 나올수 있는 모든 경로:");
		spectrum(array7);
		
	}
}
