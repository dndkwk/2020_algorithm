package BWT;
//2015112232�����

public class BWT {

	public static String[] rest(String T, int[] ar_number) {
		// Fill the rest
		// $ ���̱�

		T = T + '$'; // $ ���̱�
		String[] T_array = new String[T.length()];

		for (int i = 0; i < T.length(); i++) {
			T_array[i] = T.substring(T.length() - i - 1) + T.substring(0, T.length() - i - 1); // ���ڿ� ���ϱ�
			ar_number[i] = T.length() - i - 1; // ar_number �ʱ�ȭ
		}

		for (int i = 0; i < T_array.length; i++) {// ���
			System.out.println(T_array[i]);
		}

		return T_array;
	}

	public static String[] rest_sort(String[] T_array, int[] ar_number) {
		String temp;
		int n = 0;

		for (int i = 0; i < T_array.length; i++) {
			for (int j = 0; j < T_array.length; j++) {
				// i��° ���ڿ��� ������ �� ũ�ٸ� j���� ���ڿ��� �ٲ۴�.
				if (T_array[i].compareTo(T_array[j]) < 0) {
					temp = T_array[i];
					T_array[i] = T_array[j];
					T_array[j] = temp;

					n = ar_number[i];// ar_number��ġ �ٲٱ�
					ar_number[i] = ar_number[j];
					ar_number[j] = n;
				}
			}
		}

		for (int i = 0; i < T_array.length; i++) { // ���
			System.out.println(T_array[i]);
		}

		return T_array;
	}

	public static String bwt(String[] T_array) {
		// bwt ���ϱ�
		String bwt = "";
		for (int i = 0; i < T_array.length; i++) {
			// ������ ���ڵ��� ��� ��ģ��.
			bwt = bwt + T_array[i].substring(T_array.length - 1);
		}
		System.out.println(bwt); // ���
		return bwt;
	}

	public static String pre_bwt(String[] T_array) {
		// pre_bwt ���ϱ�
		String pre_bwt = "";
		for (int i = 0; i < T_array.length; i++) {
			// ù��° ���ڵ��� ��� ��ģ��.
			pre_bwt = pre_bwt + T_array[i].substring(0, 1);
		}
		System.out.println(pre_bwt); // ���
		return pre_bwt;
	}

	public static void show_number(int[] ar_number) {
		for (int i = 0; i < ar_number.length; i++) { // ar_number ���
			System.out.print(ar_number[i] + " ");
		}

	}

	public static void restore(String bwt, int[] ar_number) {
		// ���ڿ� ����
		String t = "";
		char[] temp = bwt.toCharArray();

		for (int i = ar_number.length - 1; i > 0; i--) {
			for (int j = 0; j < ar_number.length; j++) {
				if (i == ar_number[j]) { // ar_numbr�� ���� ����ã�� ������ȣ�� ���ٸ� �� �ε����� char���� ��ħ
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
		System.out.println("���ڿ� �����۾� ����");
		restore(bwt, ar_number);
		System.out.println("���� �Ϸ�");

	}

}
