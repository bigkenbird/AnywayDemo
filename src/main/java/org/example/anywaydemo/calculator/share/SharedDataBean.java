package org.example.anywaydemo.calculator.share;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.BiFunction;

@Component
public class SharedDataBean {

    public Deque<String> countSave;
    public Stack<String> undoSave;
    public Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> operators;

    public SharedDataBean() {
        this.countSave = new LinkedList<>();
        this.undoSave = new Stack<>();
        this.operators = new HashMap<>();
        this.operators.put("+", BigDecimal::add);
        this.operators.put("-", BigDecimal::subtract);
        this.operators.put("*", BigDecimal::multiply);
        this.operators.put("/", (a, b) -> b.compareTo(BigDecimal.ZERO) == 0 ?
                BigDecimal.ZERO :
                a.divide(b, 10, RoundingMode.HALF_UP));
    }
}
