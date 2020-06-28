package MTP_DP;

//2015112232백근주

public class DP_MTP {

	static MTP_MAP[][] map = new MTP_MAP[5][5]; // 미리 맵 선언

	// 맵 만들기
	public static void makemap() {
		map[0][0] = new MTP_MAP(3, 1);
		map[0][1] = new MTP_MAP(2, 0);
		map[0][2] = new MTP_MAP(4, 2);
		map[0][3] = new MTP_MAP(0, 4);
		map[0][4] = new MTP_MAP(0, 3);

		map[1][0] = new MTP_MAP(3, 4);
		map[1][1] = new MTP_MAP(2, 6);
		map[1][2] = new MTP_MAP(4, 5);
		map[1][3] = new MTP_MAP(1, 2);
		map[1][4] = new MTP_MAP(0, 1);

		map[2][0] = new MTP_MAP(0, 4);
		map[2][1] = new MTP_MAP(7, 4);
		map[2][2] = new MTP_MAP(3, 5);
		map[2][3] = new MTP_MAP(4, 2);
		map[2][4] = new MTP_MAP(0, 1);

		map[3][0] = new MTP_MAP(3, 5);
		map[3][1] = new MTP_MAP(3, 6);
		map[3][2] = new MTP_MAP(0, 8);
		map[3][3] = new MTP_MAP(2, 5);
		map[3][4] = new MTP_MAP(0, 3);

		map[4][0] = new MTP_MAP(1, 0);
		map[4][1] = new MTP_MAP(3, 0);
		map[4][2] = new MTP_MAP(2, 0);
		map[4][3] = new MTP_MAP(2, 0);
		map[4][4] = new MTP_MAP(0, 0);
	}

	public static int max_value(int i, int j) {
		// dynamic programming 사용
		int temp = 0;
		if (i >= 1 && j >= 1) {// 시작점 도달할때 까지
			temp = Math.max(max_value(i, j - 1) + map[i][j - 1].right, max_value(i - 1, j) + map[i - 1][j].bottom); // 도달하는 경우 가장 큰 값을 저장
		} else if (i == 0 && j >= 1) { // 0행일 경우
			temp = max_value(i, j - 1) + map[i][j - 1].right;
		} else if (j == 0 && i >= 1) { // 0열일 경우
			temp = max_value(i - 1, j) + map[i - 1][j].bottom;
		} else {// 시작점 도달
			temp = 0;
		}
		map[i][j].value = temp;
		return temp;
	}

	public static void find_road(int i, int j) {
		// 길찾기 재귀 사용
		if (i == 0 && j == 0) { // 시작점
			System.out.print("Map[" + i + "]" + "[" + j + "]=" + map[i][j].value);
		} else if (i == 0) { // 0행일때
			find_road(i, j - 1);
			System.out.print(",Map[" + i + "]" + "[" + j + "]=" + map[i][j].value);
		} else if (j == 0) { // 0열일때
			find_road(i - 1, j);
			System.out.print(",Map[" + i + "]" + "[" + j + "]=" + map[i][j].value);
		} else if (map[i][j].value == map[i - 1][j].value + map[i - 1][j].bottom) { // 위쪽에서 오는 경우
			find_road(i - 1, j);
			System.out.print(",Map[" + i + "]" + "[" + j + "]=" + map[i][j].value);
		} else if (map[i][j].value == map[i][j - 1].value + map[i][j - 1].right) { // 왼쪽에서 오는 경우
			find_road(i, j - 1);
			System.out.print(",Map[" + i + "]" + "[" + j + "]=" + map[i][j].value);
		}
	}

	public static void main(String[] args) {

		makemap();// 맵만들기
		for (int i = 0; i < 5; i++) { // 모든 value값을 출력
			for (int j = 0; j < 5; j++) {
				System.out.print(max_value(i, j) + " ");
			}
			System.out.println("");
		}
		find_road(4, 4);// end까지의 길 찾기
	}

}
