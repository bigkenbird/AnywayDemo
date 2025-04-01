package org.example.anywaydemo.calculator.listener;

import lombok.RequiredArgsConstructor;
import org.example.anywaydemo.calculator.service.RecordService;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecordActionListener implements ActionListener {

    private RecordService recordService;

    private TextField field;

    public RecordActionListener(RecordService recordService, TextField field) {
        this.recordService = recordService;
        this.field = field;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "undo":
                recordService.undo();
                field.setText(recordService.showCount());
                break;
            case "redo":
                String redoData = recordService.redo();
                if("=".equals(redoData.substring(0,1))){
                    field.setText(redoData.substring(1));
                }
                else{
                    field.setText(recordService.showCount());
                }
                break;
            default:
                recordService.addCount(command);
                field.setText(recordService.showCount());
        }
    }
}
