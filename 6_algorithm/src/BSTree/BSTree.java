package BSTree;
//2015112232�����
import java.util.Scanner;

public class BSTree {
	public static void main(String[] args) {
		System.out.println("Command: ");
		System.out.println("  +key : Insert (or update) element ");
		System.out.println("  ?key : Retrieve element ");
		System.out.println("  -key : Remove element ");
		System.out.println("  Q    : Quit the test program ");

		Scanner sc = new Scanner(System.in);
		String input;
		
		BS tree = new BS();
		tree.show();
		
		do {
			System.out.print("Command: ");
			input = sc.next();
			
			if(input.charAt(0)=='+') {
				System.out.println("Insert : key = "+input.substring(1));
				int element = Integer.parseInt(input.substring(1));
				tree.insert(element);
				tree.show();
			}else if(input.charAt(0)=='?') {
				System.out.println("Retrieved : key = "+input.substring(1));
				int element = Integer.parseInt(input.substring(1));
				tree.retrieve(element);
				tree.show();
			}else if(input.charAt(0)=='-') {
				System.out.println("-"+input.substring(1));
				int element = Integer.parseInt(input.substring(1));
				tree.remove(element);
				tree.show();
			}else if((input.charAt(0)=='Q') && (input.length() == 1)){
				System.out.println("�ý����� �����մϴ�.");
			}else {
				System.out.println("�ٽ� �Է��ϼ���.");	
			}
		} while (!((input.charAt(0)=='Q') && (input.length() == 1)));
		
		sc.close();
	}
}
