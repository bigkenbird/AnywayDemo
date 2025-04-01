package org.example.anywaydemo.calculator;

import lombok.RequiredArgsConstructor;
import org.example.anywaydemo.calculator.listener.CalculatorListener;
import org.example.anywaydemo.calculator.listener.RecordActionListener;
import org.example.anywaydemo.calculator.service.CalculatorService;
import org.example.anywaydemo.calculator.service.RecordService;
import org.example.anywaydemo.calculator.share.SharedDataBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.*;

@Component
public class CalculatorAWT extends Frame {

    @Autowired
    public CalculatorAWT(CalculatorService calculatorService, RecordService recordService, SharedDataBean sharedDataBean) {
        TextField textField = new TextField();
        CalculatorListener calculatorListener = new CalculatorListener(calculatorService,sharedDataBean, textField);
        RecordActionListener recordActionListener = new RecordActionListener(recordService, textField);
        setTitle("AWT Calculator");
        setSize(300, 400);
        setLayout(new BorderLayout());

        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        Panel panel = new Panel();
        panel.setLayout(new GridLayout(4, 4));
        String[] recordButtons = { "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "+", "-", "*", "/","undo", "redo" };
        String[] calculatorButtons = { "=", "C" };

        for(String numberButton: recordButtons) {
            Button button = new Button(numberButton);
            button.addActionListener(recordActionListener);
            panel.add(button);
        }

        for (String operatorButton : calculatorButtons) {
            Button button = new Button(operatorButton);
            button.addActionListener(calculatorListener);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
