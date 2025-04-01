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
