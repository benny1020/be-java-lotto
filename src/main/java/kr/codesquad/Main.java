package kr.codesquad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {

	static int MATCH_THREE_PRICE = 5000;
	static int MATCH_FOUR_PRICE = 50000;
	static int MATCH_FIVE_PRICE = 1500000;
	static int MATCH_SIX_PRICE = 2000000000;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("구매금액을 입력해 주세요");

		Integer price = Integer.valueOf(br.readLine());

		int cnt_lotto = price / 1000;

		System.out.println(cnt_lotto + "개를 구매했습니다.");

		List<List<Integer>> lottoList = new ArrayList<>();
		List<Integer> collectLottoNumberList = new ArrayList<>();

		int[] result = new int[7];
		Arrays.fill(result, 0);

		getLottoList(br, lottoList, cnt_lotto);
		getCollectNumberList(br, collectLottoNumberList);
		getResult(lottoList, collectLottoNumberList, result);
		String resultProfit = getResultProfit(result, price);
		printResult(result, resultProfit);

	}

	private static String getResultProfit(int[] result, int price) {
		int resultProfit = MATCH_THREE_PRICE * result[3] + MATCH_FOUR_PRICE * result[4]
				+ MATCH_FIVE_PRICE * result[5] + MATCH_SIX_PRICE * result[6];

		double profitPercentage = (double)(resultProfit - price) / price * 100;

		return String.format("%.2f", profitPercentage);
	}

	private static void printResult(int[] result, String resultProfit) {
		System.out.println("당첨 통계");
		System.out.println("----------");
		System.out.println("3개 일치 (" + MATCH_THREE_PRICE + "원) - " + result[3] + "개");
		System.out.println("4개 일치 (" + MATCH_FOUR_PRICE + "원) - " + result[4] + "개");
		System.out.println("5개 일치 (" + MATCH_FIVE_PRICE + "원) - " + result[5] + "개");
		System.out.println("6개 일치 (" + MATCH_SIX_PRICE + "원) - " + result[6] + "개");
		System.out.println("총 수익률은 " + resultProfit + "%입니다.");
	}

	private static void getResult(List<List<Integer>> lottoList, List<Integer> collectLottoNumberList, int[] result) {
		for (int i = 0; i < lottoList.size(); i++) {
			List<Integer> myLottoNumberList = lottoList.get(i);

			int cnt = 0;

			for (int v = 0; v < 6; v++) {
				if (myLottoNumberList.contains(collectLottoNumberList.get(v))) {
					cnt++;
				}
			}
			result[cnt]++;
		}
	}

	private static void getCollectNumberList(BufferedReader br, List<Integer> collectLottoNumberList) throws IOException {

		System.out.println("당첨 번호를 입력해 주세요.");

		StringTokenizer st = new StringTokenizer(br.readLine(), ",");
		for (int i = 0; i < 6; i++) {
			int num = Integer.valueOf(st.nextToken().trim());
			collectLottoNumberList.add(num);
			Collections.sort(collectLottoNumberList);
		}
	}

	private static void getLottoList(BufferedReader br, List<List<Integer>> lottoList, int cnt_lotto) throws IOException {

		List<Integer> lottoNumberList = generateLottoNumberList();

		getLottoNumberList(lottoList, cnt_lotto, lottoNumberList);
	}

	private static void getLottoNumberList(List<List<Integer>> lottoList, int cnt_lotto, List<Integer> lottoNumberList) {
		for (int i = 0; i < cnt_lotto; i++) {

			Collections.shuffle(lottoNumberList);

			List<Integer> myLottoNumberList = lottoNumberList.subList(0, 6);
			Collections.sort(myLottoNumberList);

			lottoList.add(myLottoNumberList);
		}
	}

	private static List<Integer> generateLottoNumberList() {
		List<Integer> lottoNumberList = new ArrayList<>();

		for (int i = 1; i < 46; i++) {
			lottoNumberList.add(i);
		}
		return lottoNumberList;
	}
}