package stringmatch;
//2015112232백근주

public class stringmatch {

	public static void bruteforce(char[] input, char[] p) {
		int n = input.length;
		int m = p.length;
		int i, j;

		if ((n == 0) || (m == 0)) { // 문장 혹은 패턴이 없다면
			System.out.println("패턴 또는 문장이 없습니다.");
			return;
		}

		for (i = 0; i <= n - m; i++) {
			for (j = 0; j < m; j++) {
				if (input[i + j] != p[j]) { // 패턴이 다르다면
					break;// for문 탈출
				}
			}
			if (j == m) {// 패턴 글자가 전부 같다면 출력
				System.out.println("텍스트의 " + i + "번째부터 나타남");
			}
		}

	}

	public static void rabin_karp(char[] input, char[] p, int q) {
		int d = 128; // 아스키 코드
		int n = input.length;
		int m = p.length;
		int i, j;
		int v_p = 0;
		int v_input = 0;
		int h = 1;

		if ((n == 0) || (m == 0)) {// 문장 혹은 패턴이 없다면
			System.out.println("패턴 또는 문장이 없습니다.");
			return;
		}

		for (i = 0; i < m - 1; i++) { // mod연산
			h = (h * d) % q;
		}

		for (i = 0; i < m; i++) {// 패턴의 해시값과 input의 첫번째 값 해시값
			v_p = (d * v_p + p[i]) % q;
			v_input = (d * v_input + input[i]) % q;
		}

		for (i = 0; i <= n - m; i++) {

			if (v_p == v_input) {// 해시값이 같다면
				for (j = 0; j < m; j++) {// 패턴의 글자와 하나씩 비교
					if (input[i + j] != p[j]) {
						break;
					}
				}
				if (j == m) {
					System.out.println("텍스트의 " + i + "번째부터 나타남");
				}
			}
			if (i < n - m) {// 다음 번째의 해시값 구하기
				v_input = (d * (v_input - input[i] * h) + input[i + m]) % q;

				if (v_input < 0) {// 음수는 양수로 바꾼다
					v_input = (v_input + q);
				}

			}
		}
	}

	public static void kmp(char[] input, char[] p) {
		int[] table = compute(p);
		int n = input.length;
		int m = p.length;
		int i, j = 0;

		if ((n == 0) || (m == 0)) {// 문장 혹은 패턴이 없다면
			System.out.println("패턴 또는 문장이 없습니다.");
			return;
		}

		for (i = 0; i < n; i++) {
			while ((j > 0) && (input[i] != p[j])) {// 매칭중인 상태에서 틀린문자가 나왔을 때
				j = table[j - 1];
			}
			if (input[i] == p[j]) {// 같다면 인덱스 증가
				if (j == (m - 1)) {// 패턴이 전부 같다면 패턴이 존재하는 것
					System.out.println("텍스트의 " + (i - m + 1) + "번째부터 나타남");
					j = table[j];
				} else {
					j++;
				}
			}
		}
	}

	public static int[] compute(char[] p) {
		// 접두부 테이블 만들기
		int n = p.length;
		int[] table = new int[n];// 최대 접두부 테이블
		int i, j = 0;

		for (i = 1; i < n; i++) {
			while ((j > 0) && p[i] != p[j]) {// 매칭중인 상태에서 틀린문자가 나왔을 때
				j = table[j - 1];
			}
			if (p[i] == p[j]) {// suffix prefix같다면 증가
				table[i] = ++j;
			}
		}

		return table;
	}

	public static void main(String[] args) {

		String input = "5614646515f12w651f654165f1w654f65ewf" + "f1w6f1w6f416ds31fzfasf2w1f3w1f65w1f65wf651f6qwf"
				+ "64620151122321654641wf161351f16f4lzfmelfkwef;wkegpoigf;las,fgf64qw"
				+ "f9613z2f46we41f6qwf123s1f35we1f3we1zlfkslfkwlfkweofjwqoff65we1f65we"
				+ "f1fe16f1we6f1w6f1we3f1sd6a1f65w1f3we1f6wqf16ew21f6s1f65we41f6"
				+ "sf16ef1q35fd13a131x3v1z3v46s4v0a1ew65f46s43f4e6f4w6f4wef"
				+ "534656420151122402646164531d1v32zx1v316v4wbcbfgnhukyky6f4fq4f"
				+ "s2af1w5f135zx1f3x1f6qw4fw64zxfasf1s32zf123f1ghukghkhgjk3ff9w8"
				+ "ㅁㄹ16ㅈ5ㄹ16ㅋ41ㄹ65ㅈ4ㄹ6ㅈ41ㄹ6ㅈㄹ13ㅁ1ryryryrdkghkhugkhugyrtㄹ6ㅈ"
				+ "af2fz54f56w4f9q4f6sa1f3szfd1fw65f46wf7494f9d1gf6we4g6wf"
				+ "saf4w1ef6w4q6fw1f654f6we4fq6f47qw92015112hkhjkjh232"
				+ "sf6a5few4f6qw4f6dgs4g45g31we56gf4we23f1qw65khjbjhkbkhvfs1f"
				+ "a34f65we59465164a1fs3f1f6wf4w56f46wf4yryekjkhjgkjhkry6wf64"
				+ "f1ew5f4q9f465z1f6ew4gf9q4f61f3as1fw6f7w9f79zkbvbkhbvfdsgskahakwf"
				+ "sffwfwfwfsf31sd3g16geg5se4g9eg7re9g4d3gz21g3jbvmbvnmbne4gew"
				+ "aw5f4we65f13zsf15w4f6w4f6s34g67g96tyreyryrembvnmbnmyd7hd"
				+ "5g1sd3g1sd3g1dg34red6gdf46g4654n0b4n6fg13jmbv1gfj"
				+ "y63u5ky64kgy54kyu31kyu54kmyu64kjy6u4k53yg4bnmbnmvbnmkygk"
				+ "hk65gj6t4u6r46y54s3yd1y3f21h6y4t6u4d6vjgjgjrurtu"
				+ "65j1g65j46g4i6ty7ity9kyt5j3h1x2liuopuohugikghkgf1h3"
				+ "dx1heh1ert3h13rt1h3rtd4j5tr4j3tururtuegjdrysryseyfrr4j34h"
				+ "xhqfwfwefd1f6z4fx67b6g7j98h7k9u7lu6i4i6y4jfgy64jfg"
				+ "x34htr654urt65u4r35u1tf31ud31utr3u4r6u4964gz53d1gsz"
				+ "g4e6y4se64y6r4u6r7ur6u4d53201511223sdrysysysdgdgdgsdgd"
				+ "gdgdsgreyrthkhgskgjkjhfbaekguenjoowjaklvj skvjas k2u1f3ryrdsyeryrsyf"
				+ "fwf1zf34w1f53w4f6541z32f1w53f1we3f13a1f3s1fwafwefkhwjkf"
				+ "kzjfhwkjfhwiufhwekfnaskfjofijewflewufiowejfowjf"
				+ "efkjhkfzhskfhwkfhwfiueyfiwqufkfksjfskfysdsdgsdgdsgdsghekfhkfjwef"
				+ "fkjgjoguogjslgmflkxvmc.vbmd2015112232baekguenjooblfkdbpkbrptkogjogjwog"
				+ "xglkxjzglxjglxgmdf.gkepgeig0ewugoirjgwlgmlsamflsajfwf"
				+ "esfklwejgoierjglrmg;erlgkerp2015112232wfzfsfwfefwf"
				+ "sfewjfkwjfljlfjljfelfjawlfwgieropgkldsgdgmdklgmdlgjre"
				+ "234641321646465476487256462565634632354534164654"
				+ "2651656546164646764515464644646461315316516wfwfwqf" + "f3f1w3f1z3sf1we31f6wf13e5w4fqw6f4w6f4qw"
				+ "z6f4sf1wa32f1wef321wf6ew4f64z687gd98b7d6b4df3hb1fdb1f66baekguenjoo" + "oow69";// 문장
		char[] char_input = input.toCharArray(); // 배열변환
		String p = "2015112232baekguenjoo";// 패턴
		char[] char_p = p.toCharArray();// 배열변환
		int q = 13;// prime number
		long start, end;
		long time;

		System.out.println("패턴: " + p);

		start = System.nanoTime();
		bruteforce(char_input, char_p);
		end = System.nanoTime();
		time = end - start;
		System.out.println("bruteforce: " + time + "ns"); // 나노 초로 구한다.

		start = System.nanoTime();
		rabin_karp(char_input, char_p, q);
		end = System.nanoTime();
		time = end - start;
		System.out.println("rabin karp: " + time + "ns");

		start = System.nanoTime();
		kmp(char_input, char_p);
		end = System.nanoTime();
		time = end - start;
		System.out.println("KMP: " + time + "ns");
	}

}