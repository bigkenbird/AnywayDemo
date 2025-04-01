package org.example.anywaydemo.calculator.listener;

import org.example.anywaydemo.calculator.service.CalculatorService;
import org.example.anywaydemo.calculator.share.SharedDataBean;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class CalculatorListener implements ActionListener {

    private CalculatorService calculatorService;

    private SharedDataBean sharedDataBean;

    private TextField textField;

    public CalculatorListener(CalculatorService calculatorService,SharedDataBean sharedDataBean,TextField textField) {
        this.calculatorService = calculatorService;
        this.sharedDataBean = sharedDataBean;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if("=".equals(command)) {
            BigDecimal result = calculatorService.amount();
            this.textField.setText(String.valueOf(result));
            this.sharedDataBean.countSave.add("=" + result);
        }
        else if("C".equals(command)) {
            this.sharedDataBean.countSave.clear();
            this.sharedDataBean.undoSave.clear();
            this.textField.setText("");
        }
    }
}
