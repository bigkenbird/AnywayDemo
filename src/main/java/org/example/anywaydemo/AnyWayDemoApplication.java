package org.example.anywaydemo;

import lombok.RequiredArgsConstructor;
import org.example.anywaydemo.calculator.CalculatorAWT;
import org.example.anywaydemo.luckywheel.Prize;
import org.example.anywaydemo.luckywheel.PrizeAWT;
import org.example.anywaydemo.luckywheel.service.PrizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@SpringBootApplication
public class AnyWayDemoApplication{

    public static void main(String[] args) {
        System.setProperty("java.awt.headless", "false");
        ApplicationContext context = SpringApplication.run(AnyWayDemoApplication.class, args);
        CalculatorAWT calculatorAWT = context.getBean(CalculatorAWT.class);
        PrizeAWT prizeAWT = context.getBean(PrizeAWT.class);
        calculatorAWT.setVisible(true);
        prizeAWT.setVisible(true);

        PrizeService prizeService = context.getBean(PrizeService.class);


        ExecutorService executor = Executors.newFixedThreadPool(100); // 建立一個大小為 100 的執行緒池
        List<Prize> testResults = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Future<?> future = executor.submit(() -> {
                try {
                    Prize result = prizeService.drawTestPrize();
                    testResults.add(result);
                } catch (Exception e) {
                    System.err.println("Error drawing prize: " + e.getMessage());
                }
            });
        }
        List<Prize> aPrizes = testResults.stream().filter(data-> "a".equals(data.getName())).toList();
        List<Prize> bPrizes = testResults.stream().filter(data-> "b".equals(data.getName())).toList();
        List<Prize> cPrizes = testResults.stream().filter(data-> "c".equals(data.getName())).toList();
        List<Prize> noPrizes = testResults.stream().filter(data-> "鳴謝惠顧".equals(data.getName())).toList();


        System.out.println("a獎品總共有5個, 最後抽出"+aPrizes.size()+"個");
        System.out.println("b獎品總共有5個, 最後抽出"+bPrizes.size()+"個");
        System.out.println("c獎品總共有5個, 最後抽出"+cPrizes.size()+"個");
        System.out.println("鳴謝惠顧, 最後抽出"+noPrizes.size()+"個");








    }
}
