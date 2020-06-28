import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//2015112232�����

public class B_palin {
	public static boolean palindrome(String word) {
		// ��� ȸ��
		if (word.length() <= 1) { // �ѱ��ڸ� ȸ���̴�
			return true;
		}
		if (word.charAt(0) != word.charAt(word.length() - 1)) {// �Ǿ� �ǵ� ���ڰ� �ٸ��� ȸ���� �ƴϴ�
			return false;
		}

		return palindrome(word.substring(1, word.length() - 1)); // �Ǿ� �ǵ��� ���ڸ� �ڸ� ���ڿ��� ����Ѵ�.
	}

	public static void main(String[] args) {
		try {
			File file = new File("E:\\school\\2_algorithm\\src\\exam_palindrome.txt"); // ���� �о����
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			List<String> ary = new ArrayList<String>(); // �ܾ����� list
			while ((line = br.readLine()) != null) { // ������ ���پ� �о ary�� ����
				ary.add(line);
			}
			for (int i = 0; i < ary.size(); i++) {
				System.out.println(palindrome(ary.get(i))); // �� �ܾ�� ȸ�� üũ
			}

		} catch (FileNotFoundException e) {// FileNotFoundException�� �߻��Ѵٸ� ���
			System.out.println("���Ͼ���");
		} catch (IOException e) { // IOException�� �߻��Ѵٸ� ���
			System.out.println("������̻�");
		}

	}

}
