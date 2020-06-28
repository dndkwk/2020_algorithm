package coin_DP;

//2015112232�����

public class DP_coin {

	public static void main(String[] args) {

		int M = 11;
		int[] dp = new int[M + 1];
		int[] coin = { 1, 4, 7 };
		for (int i = 1; i <= M; i++) { // ū������ ��� �迭�� �ʱ�ȭ �Ѵ�.
			dp[i] = 100000;
		}

		for (int i = 0; i < coin.length; i++) { // 1���� ���vs1,4�� ���vs 1,4,7�� ��� ��
			for (int j = coin[i]; j <= M; j++) {
				dp[j] = Math.min(dp[j], dp[j - coin[i]] + 1); // ��츦 ���Ͽ� �������� ����
			}
		}
		System.out.println("���");
		for (int i = 0; i < dp.length; i++) { // ���
			System.out.println("dp[" + i + "]: " + dp[i]);
		}
	}
}
