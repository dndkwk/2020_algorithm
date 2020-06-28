package coin_DP;

//2015112232백근주

public class DP_coin {

	public static void main(String[] args) {

		int M = 11;
		int[] dp = new int[M + 1];
		int[] coin = { 1, 4, 7 };
		for (int i = 1; i <= M; i++) { // 큰값으로 모든 배열을 초기화 한다.
			dp[i] = 100000;
		}

		for (int i = 0; i < coin.length; i++) { // 1원만 사용vs1,4원 사용vs 1,4,7원 사용 비교
			for (int j = coin[i]; j <= M; j++) {
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1); // 경우를 비교하여 작은값을 저장
			}
		}
		System.out.println("결과");
		for (int i = 0; i < dp.length; i++) { // 출력
			System.out.println("dp[" + i + "]: " + dp[i]);
		}
	}
}
