package org.example.anywaydemo.luckywheel.listener;

import org.example.anywaydemo.luckywheel.Prize;
import org.example.anywaydemo.luckywheel.service.PrizeService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawEventListener implements ActionListener {

    private final PrizeService prizeService;

    private final JTextArea jTextArea;


    public DrawEventListener(JTextArea jTextArea, PrizeService prizeService) {
        this.jTextArea = jTextArea;
        this.prizeService = prizeService;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Prize result = prizeService.drawPrize();
        jTextArea.append(result.toString()+"\n");
    }
}
