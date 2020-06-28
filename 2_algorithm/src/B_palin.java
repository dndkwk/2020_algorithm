import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//2015112232백근주

public class B_palin {
	public static boolean palindrome(String word) {
		// 재귀 회문
		if (word.length() <= 1) { // 한글자면 회문이다
			return true;
		}
		if (word.charAt(0) != word.charAt(word.length() - 1)) {// 맨앞 맨뒤 글자가 다르면 회문이 아니다
			return false;
		}

		return palindrome(word.substring(1, word.length() - 1)); // 맨앞 맨뒤의 글자를 자른 문자열을 재귀한다.
	}

	public static void main(String[] args) {
		try {
			File file = new File("E:\\school\\2_algorithm\\src\\exam_palindrome.txt"); // 파일 읽어오기
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			List<String> ary = new ArrayList<String>(); // 단어저장 list
			while ((line = br.readLine()) != null) { // 파일을 한줄씩 읽어서 ary에 저장
				ary.add(line);
			}
			for (int i = 0; i < ary.size(); i++) {
				System.out.println(palindrome(ary.get(i))); // 각 단어당 회문 체크
			}

		} catch (FileNotFoundException e) {// FileNotFoundException이 발생한다면 출력
			System.out.println("파일없음");
		} catch (IOException e) { // IOException이 발생한다면 출력
			System.out.println("입출력이상");
		}

	}

}
