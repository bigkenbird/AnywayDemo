package org.example.anywaydemo.luckywheel.service;

import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.example.anywaydemo.luckywheel.Prize;
import org.example.anywaydemo.luckywheel.share.SharedBean;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class PrizeService {

    private final SharedBean sharedBean;

    @Synchronized
    public Prize drawPrize() {
        Prize result = null;
        Random random = new Random();
        double randomNumber = random.nextDouble();
        Double sumNumber = Double.valueOf("0");
        for(Prize prize: sharedBean.prizes) {
            sumNumber += prize.getProbability();

            if (randomNumber <= sumNumber) {
                if(prize.getQuantity()==0){
                    result = this.sharedBean.noPrize;
                }
                else{
                    prize.minusQuantity();
                    result = prize;
                }

                break;
            }
        }
        return result;
    }

    @Synchronized
    public Prize drawTestPrize() {
        Prize result = null;
        Random random = new Random();
        double randomNumber = random.nextDouble();
        Double sumNumber = Double.valueOf("0");
        for(Prize prize: sharedBean.testPrizes) {
            sumNumber += prize.getProbability();

            if (randomNumber <= sumNumber) {
                if(prize.getQuantity()==0){
                    result = this.sharedBean.noPrizeTest;
                }
                else{
                    prize.minusQuantity();
                    result = prize;
                }
                break;
            }
        }
        return result;
    }
}
