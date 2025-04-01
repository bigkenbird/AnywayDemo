package org.example.anywaydemo.luckywheel.share;

import org.example.anywaydemo.luckywheel.Prize;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SharedBean {

    public List<Prize> prizes;

    public List<Prize> testPrizes;

    public Prize noPrize;

    public Prize noPrizeTest;

    public SharedBean(){
        prizes = new ArrayList<>();
        testPrizes = new ArrayList<>();

        Prize prize1 = new Prize();
        Prize prize2 = new Prize();
        Prize prize3 = new Prize();
        noPrizeTest = new Prize();


        prize1.setName("a");
        prize1.setQuantity(5);
        prize1.setProbability(0.5);

        prize2.setName("b");
        prize2.setQuantity(5);
        prize2.setProbability(0.2);

        prize3.setName("c");
        prize3.setQuantity(5);
        prize3.setProbability(0.1);

        noPrizeTest.setName("鳴謝惠顧");
        noPrizeTest.setQuantity(Integer.MAX_VALUE);
        noPrizeTest.setProbability(0.2);

        testPrizes.add(prize1);
        testPrizes.add(prize2);
        testPrizes.add(prize3);
        testPrizes.add(noPrizeTest);
    }

}
