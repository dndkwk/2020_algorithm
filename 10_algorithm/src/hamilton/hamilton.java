package hamilton;
//2015112232�����

public class hamilton {

	public static void combination(int[][] matrix, int row, String[] array) {
		// �����ϱ�
		int count = 1; // return������ ���� ����
		String s = array[row];// ó�� ���ڸ� ����
		for (int i = 0; i < matrix.length; i++) {
			if (matrix[row][i] != 0) { // ����Ǵ� ���ڸ� ������
				subcombination(matrix, i, array, s, count + 1);
			}
		}
	}

	public static void subcombination(int[][] matrix, int row, String[] array, String s, int count) {
		// �����ϴ� �Լ�
		if (count == matrix.length) { // ���� ��� row���� �� ��������
			s = s + array[row].substring(array[0].length() - 1);
			if (Iscorrect(array, s)) { // �ߺ��� ����ִ��� üũ
				System.out.println(s); // �����ϰ� ���
			}
		} else {
			s = s + array[row].substring(array[0].length() - 1); // ���� ������ ������ ���ڸ� �������ش�.
			for (int i = 0; i < matrix.length; i++) {
				if ((matrix[row][i] != 0)) { // ����Ǵ� ���� �߰�
					subcombination(matrix, i, array, s, count + 1);
				}
			}
		}
	}

	public static boolean Iscorrect(String[] array, String s) {
		// �� ���ڿ��� �ߺ����ڰ� ������� Ȯ��
		int n = array.length;
		String[] s_array = new String[n];
		boolean[] check = new boolean[n];

		for (int i = 0; i < n; i++) { // �ߺ�üũ �ʱ�ȭ
			check[i] = false;
		}

		for (int i = 0; i < n; i++) {
			s_array[i] = s.substring(i, i + array[0].length()); // s�� ���ڿ��� spectrumó�� �ڸ���.
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (array[i].equals(s_array[j]) && (check[i] == false)) { // üũ�� �ȵǾ��ְ� ����ִ°� Ȯ�εǸ�
					check[i] = true; // ���Ǿ��ٰ� üũ
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (check[i] == false) { // �ϳ��� ���ȵȰ� Ȯ�εǸ�
				return false; // �� ���ڿ��� Ʋ����
			}
		}

		return true; // s�� ��� array�� ���ڿ��� �������
	}

	public static void spectrum(String[] s) {
		// �ع��� path�� �ذ��ϱ�
		int n = s.length;
		int[][] matrix = new int[n][n];
		int l = s[0].length();

		// �ع��� path �����
		// �ڱ� �ڽ��� �ƴ϶� ���� ���Ͽ� 1~l������ ���ڰ� ������ 0~l-1���ڿ� ���ٸ� �� ������ �ε����� �Է�
		for (int row = 0; row < n; row++) {
			for (int column = 0; column < n; column++) {
				if (s[row].substring(1).equals(s[column].substring(0, l - 1)) && (row != column)) {
					matrix[row][column] = 1;
				} else {
					matrix[row][column] = 0;
				}
			}
		}

		// for (int i = 0; i < n; i++) { // matrix ���
		// for (int j = 0; j < n; j++) {
		// System.out.print(String.format("%3d", matrix[i][j]));
		// }
		// System.out.println("");
		// }

		for (int i = 0; i < n; i++) { // ��� ��������� �����غ���
			combination(matrix, i, s);
		}
	}

	public static void main(String[] args) {
		String[] array1 = { "AGT", "AAA", "ACT", "AAC", "CTT", "GTA", "TTT", "TAA" };
		String[] array2 = { "ATG", "AGG", "TGC", "TCC", "GTC", "GGT", "GCA", "CAG" };
		String[] array3 = { "ATG", "TGG", "TGC", "GTG", "GGC", "GCA", "GCG", "CGT" };
		String[] array4 = { "ATGC", "TGCG", "GCGG", "CGGC", "GGCT", "GCTG", "CTGT", "TGTA", "GTAT", "TATG", "ATGG",
				"TGGT", "GGTG" };
		String[] array5 = { "ABA", "BAB", "ABC", "BCB", "CBA" }; // �������� spectrum
		String[] array6 = { "TAT", "ATG", "TGG", "GGT", "GTG", "TGC" }; // ppt spectrum
		String[] array7 = { "ATG", "GGT", "GTG", "TAT", "TGC", "TGG" };

		
		System.out.println("array1 ���ü� �ִ� ��� ���:");
		spectrum(array1);
		System.out.println("array2 ���ü� �ִ� ��� ���:");
		spectrum(array2);
		System.out.println("array3 ���ü� �ִ� ��� ���:");
		spectrum(array3);
		System.out.println("array4 ���ü� �ִ� ��� ���:");
		spectrum(array4);
		System.out.println("array5 ���ü� �ִ� ��� ���:");
		spectrum(array5);
		System.out.println("array6 ���ü� �ִ� ��� ���:");
		spectrum(array6);
		System.out.println("array7 ���ü� �ִ� ��� ���:");
		spectrum(array7);
		
	}
}
