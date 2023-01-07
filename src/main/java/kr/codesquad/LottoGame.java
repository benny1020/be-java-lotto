package kr.codesquad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class LottoGame {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final int LOTTO_PRICE = 1000;
    private static int[] match_price = {0, 0, 0, 5000, 50000, 1500000, 2000000000};

    private List<Lotto> lottoList;
    private List<Integer> collectNumberList;
    private int amountOfLotto;
    private int[] match_cnt;


    public LottoGame() throws IOException {

        purchaseLotto();
        generateLotto();
        getCollectNumber();
        for (Lotto lotto : lottoList) {
            getResult(lotto);
        }
    }

    private void purchaseLotto() throws IOException {
        System.out.println("구매금액을 입력해 주세요.");
        Integer purchasePrice = Integer.valueOf(br.readLine());

        this.amountOfLotto = purchasePrice / LOTTO_PRICE;

        System.out.println(this.amountOfLotto + "개를 구매했습니다.");
    }

    public void generateLotto() {

        lottoList = new ArrayList<>();

        for (int i = 0; i < this.amountOfLotto; i++) {
            Lotto lotto = new Lotto();
            lottoList.add(lotto);
        }

        match_cnt = new int[7];
    }

    public void getResult(Lotto lotto) throws IOException {

        int cnt = 0;
        for (Integer collectNumber : collectNumberList) {
            cnt = getCnt(lotto, cnt, collectNumber);
        }
        match_cnt[cnt]++;

        printResult();
    }

    private void printResult() {
        System.out.println("당첨 통계");
        System.out.println("----------");

        int resultProfit = 0;

        for (int i = 3; i <= 6; i++) {
            System.out.println(i + "개 일치 (" + match_price[i] + "원) - " + match_cnt[i] + "개");
            resultProfit += match_price[i] * match_cnt[i];
        }

        int purchasePrice = this.amountOfLotto * LOTTO_PRICE;
        double profitPercentage = (double)(resultProfit - purchasePrice) / purchasePrice * 100;

        System.out.println("총 수익률은 " + profitPercentage + "%입니다.");
    }

    private static int getCnt(Lotto lotto, int cnt, Integer collectNumber) {
        if (lotto.getNumList().contains(collectNumber)) {
            cnt++;
        }
        return cnt;
    }

    private void getCollectNumber() throws IOException {

        collectNumberList = new ArrayList<>();

        System.out.println("당첨 번호를 입력해 주세요.");

        StringTokenizer st = new StringTokenizer(br.readLine(), ",");

        for (int i = 0; i < 6; i++) {
            int num = Integer.valueOf(st.nextToken().trim());
            this.collectNumberList.add(num);
        }
    }

}
