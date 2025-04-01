package org.example.anywaydemo;

import jakarta.annotation.PostConstruct;
import org.example.anywaydemo.luckywheel.Prize;
import org.example.anywaydemo.luckywheel.service.PrizeService;
import org.example.anywaydemo.luckywheel.share.SharedBean;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootTest
class AnyWayDemoApplicationTests {

    @Autowired
    private PrizeService prizeService;

    @Autowired
    private SharedBean sharedBean;


    @BeforeEach
    public void init() {
        Prize prize1 = new Prize();
        Prize prize2 = new Prize();
        Prize prize3 = new Prize();

        Prize noprize = new Prize();


        prize1.setName("a");
        prize1.setQuantity(10);
        prize1.setProbability(0.5);

        prize2.setName("b");
        prize2.setQuantity(10);
        prize2.setProbability(0.2);

        prize3.setName("c");
        prize3.setQuantity(10);
        prize3.setProbability(0.1);

        noprize.setName("鳴謝惠顧");
        noprize.setQuantity(Integer.MAX_VALUE);
        noprize.setProbability(0.2);

        this.sharedBean.prizes.add(prize1);
        this.sharedBean.prizes.add(prize2);
        this.sharedBean.prizes.add(prize3);
        this.sharedBean.prizes.add(noprize);

    }

    @Test
    void contextLoads() {

        ExecutorService executor = Executors.newFixedThreadPool(100); // 建立一個大小為 100 的執行緒池
        for (int i = 0; i < 100; i++) {
            Future<?> future = executor.submit(() -> {
                try {
                    Prize result = prizeService.drawTestPrize();
                    System.out.println(result);
                } catch (Exception e) {
                    System.err.println("Error drawing prize: " + e.getMessage());
                }
            });
        }


    }

}
