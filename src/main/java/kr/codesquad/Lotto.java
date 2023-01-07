package kr.codesquad;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Lotto {

    private List<Integer> numList;

    public Lotto() {

        List<Integer> allNumList = new ArrayList<>();
        numList = new ArrayList<>();

        for (int i = 1; i < 46; i++) {
            allNumList.add(i);
        }

        Collections.shuffle(allNumList);
        this.numList = allNumList.subList(0, 6);

        System.out.println(this.numList);
    }

    public List<Integer> getNumList() {
        return this.numList;
    }
}
