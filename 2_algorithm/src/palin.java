import java.io.*;
import java.util.ArrayList;
import java.util.List;

//2015112232백근주

public class palin {
	public static boolean palindrome(String word) {
		//비재귀회문
		boolean check = true; // 회문 여부
		for (int i = 0; i < word.length() / 2; i++) { // 회문이므로 글자 길이의 반만 확인
			if (word.length() <= 1) {// 한글자면 회문
				check = true;
				break;
			}
			if (word.charAt(i) == word.charAt(word.length() - 1 - i)) { // 단어가 같다면 skip 다르다면 회문이 아니다.
				// skip
			} else {
				check = false;
				break;
			}

		}

		return check;
	}

	public static void main(String[] args) {
		try {// 파일 입출력 예외 대비 try-catch
			File file = new File("E:\\school\\2_algorithm\\src\\exam_palindrome.txt"); // 파일 읽어오기
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = ""; //
			List<String> ary = new ArrayList<String>(); // 단어 저장 list
			while ((line = br.readLine()) != null) { // 파일을 한줄씩 읽어서 ary에 저장
				ary.add(line);
			}
			for (int i = 0; i < ary.size(); i++) {
				System.out.println(palindrome(ary.get(i))); // 각 단어당 회문 체크
			}

		} catch (FileNotFoundException e) { // FileNotFoundException이 발생한다면 출력
			System.out.println("파일없음");
		} catch (IOException e) { // IOException이 발생한다면 출력
			System.out.println("입출력이상");
		}

	}

}
