import java.io.*;
import java.util.ArrayList;
import java.util.List;

//2015112232�����

public class palin {
	public static boolean palindrome(String word) {
		//�����ȸ��
		boolean check = true; // ȸ�� ����
		for (int i = 0; i < word.length() / 2; i++) { // ȸ���̹Ƿ� ���� ������ �ݸ� Ȯ��
			if (word.length() <= 1) {// �ѱ��ڸ� ȸ��
				check = true;
				break;
			}
			if (word.charAt(i) == word.charAt(word.length() - 1 - i)) { // �ܾ ���ٸ� skip �ٸ��ٸ� ȸ���� �ƴϴ�.
				// skip
			} else {
				check = false;
				break;
			}

		}

		return check;
	}

	public static void main(String[] args) {
		try {// ���� ����� ���� ��� try-catch
			File file = new File("E:\\school\\2_algorithm\\src\\exam_palindrome.txt"); // ���� �о����
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = ""; //
			List<String> ary = new ArrayList<String>(); // �ܾ� ���� list
			while ((line = br.readLine()) != null) { // ������ ���پ� �о ary�� ����
				ary.add(line);
			}
			for (int i = 0; i < ary.size(); i++) {
				System.out.println(palindrome(ary.get(i))); // �� �ܾ�� ȸ�� üũ
			}

		} catch (FileNotFoundException e) { // FileNotFoundException�� �߻��Ѵٸ� ���
			System.out.println("���Ͼ���");
		} catch (IOException e) { // IOException�� �߻��Ѵٸ� ���
			System.out.println("������̻�");
		}

	}

}
