package kr.codesquad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class LottoGame {

    private final static int LOTTO_PRICE = 1000;
    private final static int LOTTO_NUMBER_COUNT = 6;

    private static ArrayList<Integer> result = new ArrayList<>(LOTTO_NUMBER_COUNT);
    private static int firstWin = 0;
    private static int secondWin = 0;
    private static int thirdWin = 0;
    private static int fourthWin = 0;

    public List<Integer> lottoNumbers;

    public void start() {
        double purchaseAmount = getPurchaseAmount();
        int purchaseLottoCount = getLottoCount(purchaseAmount);
        ArrayList<Lotto> purchasedLottoList = purchaseLottoList(purchaseLottoCount);
        Lotto winningLotto = createWinningLottery();
        compareLotto(purchasedLottoList, winningLotto);
        printResult(purchaseLottoCount);
    }

    public LottoGame() {
        lottoNumbers = new ArrayList<>();
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }
    }

    public void confirmLotto(Lotto purchasedLotto, Lotto winningLotto) {

        int matchNum = 0;
        for (int num : purchasedLotto.getNumbers()) {
            matchNum = setMatchNum(winningLotto, matchNum, num);
        }
        if (matchNum == LOTTO_NUMBER_COUNT) {
            firstWin += 1;
        }
        if (matchNum == LOTTO_NUMBER_COUNT - 1) {
            secondWin += 1;
        }
        if (matchNum == LOTTO_NUMBER_COUNT - 2) {
            thirdWin += 1;
        }
        if (matchNum == LOTTO_NUMBER_COUNT - 3) {
            fourthWin += 1;
        }
    }

    private static int setMatchNum(Lotto winningLotto, int matchNum, int num) {
        if (winningLotto.getNumbers().contains(num)) {
            matchNum += 1;
        }
        return matchNum;
    }

    public void printResult(int purchaseAmount) {
        System.out.println("당첨 통계");
        System.out.println("-----");
        System.out.println("3개 일치 (5000원) - " + fourthWin);
        System.out.println("4개 일치 (50000원) - " + thirdWin);
        System.out.println("5개 일치 (1500000원) - " + secondWin);
        System.out.println("6개 일치 (2000000000원) - " + firstWin);
        double output = fourthWin * 5000 + thirdWin * 50000 + secondWin * 1500000 + firstWin * 2000000000;
        double input = purchaseAmount * 1000;

        System.out.println(String.format("%.2f",((output - input) / input) * 100)+"%");
    }

    public void compareLotto(List<Lotto> purchasedLottoList, Lotto winningLotto) {
        for (int i = 0; i < purchasedLottoList.size(); i++) {
            System.out.println(purchasedLottoList.get(i));
            confirmLotto(purchasedLottoList.get(i), winningLotto);
        }
    }

    public Lotto getNewLotto() {
        Collections.shuffle(lottoNumbers);
        List<Integer> newLottoNumbers = new ArrayList<>(lottoNumbers.subList(0, LOTTO_NUMBER_COUNT));
        Collections.sort(newLottoNumbers);
        return new Lotto(newLottoNumbers);
    }

    public double getPurchaseAmount() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextDouble();
    }

    public int getLottoCount(double purchaseAmount) {
        int lottoCount = (int) (purchaseAmount / LOTTO_PRICE);
        System.out.println(lottoCount + "개를 구입하였습니다.");

        return lottoCount;
    }

    public ArrayList<Lotto> purchaseLottoList(int lottoCount) {
        ArrayList<Lotto> lottoList = new ArrayList<Lotto>();
        for (int i = 0; i < lottoCount; i++) {
            lottoList.add(getNewLotto());
            System.out.println(lottoList.get(i));
        }

        return lottoList;
    }

    public Lotto createWinningLottery() {
        System.out.println("당첨 번호를 입력해주세요");
        Scanner scanner = new Scanner(System.in);
        String numbers = scanner.nextLine();
        List<Integer> lottoNumbers = new ArrayList<Integer>();
        for (String number : numbers.split(", ")) {
            lottoNumbers.add(Integer.parseInt(number));
        }

        return new Lotto(lottoNumbers);
    }
}
