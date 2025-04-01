package org.example.anywaydemo.calculator.service;

import lombok.RequiredArgsConstructor;
import org.example.anywaydemo.calculator.CalculatorAWT;
import org.example.anywaydemo.calculator.share.SharedDataBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.BiFunction;

@Service
@RequiredArgsConstructor
public class CalculatorService {

    private final SharedDataBean sharedDataBean;

    public BigDecimal amount() {
        StringBuilder num1 = new StringBuilder();
        StringBuilder num2 = new StringBuilder();
        BiFunction<BigDecimal, BigDecimal, BigDecimal> operator = null;
        for(String count:sharedDataBean.countSave){
            if(sharedDataBean.operators.containsKey(count)){
                operator = sharedDataBean.operators.get(count);
            }
            else{
                if(operator != null){
                    num2.append(count);
                }
                else{
                    num1.append(count);
                }
            }
        }
        return operator!=null ?
            operator.apply(new BigDecimal(String.valueOf(num1)),new BigDecimal(String.valueOf(num2))):
            null;
    }

}
