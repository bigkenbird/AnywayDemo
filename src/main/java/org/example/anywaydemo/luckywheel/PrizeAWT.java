package org.example.anywaydemo.luckywheel;

import org.example.anywaydemo.luckywheel.listener.AddEventListener;
import org.example.anywaydemo.luckywheel.listener.DrawEventListener;
import org.example.anywaydemo.luckywheel.service.PrizeService;
import org.example.anywaydemo.luckywheel.share.SharedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.*;


@Component
public class PrizeAWT extends JFrame{


    private PrizeService prizeService;

    private final SharedBean sharedBean;

    private final JTextArea resultArea;
    private final JTextField prize1NameInput;
    private final JTextField prize2NameInput;
    private final JTextField prize3NameInput;
    private final JTextField prize1QuantityInput;
    private final JTextField prize2QuantityInput;
    private final JTextField prize3QuantityInput;

    private final JTextField prize1ProbabilityInput;
    private final JTextField prize2ProbabilityInput;
    private final JTextField prize3ProbabilityInput;


    @Autowired
    public PrizeAWT(PrizeService prizeService,SharedBean sharedBean) {
        this.prizeService = prizeService;
        this.sharedBean = sharedBean;
        setTitle("抽獎系統");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 顯示結果區域
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // 控制面板
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 3));

        prize1NameInput = new JTextField();
        prize2NameInput = new JTextField();
        prize3NameInput = new JTextField();
        prize1QuantityInput = new JTextField();
        prize2QuantityInput = new JTextField();
        prize3QuantityInput = new JTextField();
        prize1ProbabilityInput = new JTextField();
        prize2ProbabilityInput = new JTextField();
        prize3ProbabilityInput = new JTextField();

        controlPanel.add(new JLabel("獎品1名稱:"));
        controlPanel.add(prize1NameInput);
        controlPanel.add(new JLabel("獎品1數量:"));
        controlPanel.add(prize1QuantityInput);
        controlPanel.add(new JLabel("獎品1機率 (0~1):"));
        controlPanel.add(prize1ProbabilityInput);

        controlPanel.add(new JLabel("獎品2名稱:"));
        controlPanel.add(prize2NameInput);
        controlPanel.add(new JLabel("獎品2數量:"));
        controlPanel.add(prize2QuantityInput);
        controlPanel.add(new JLabel("獎品2機率 (0~1):"));
        controlPanel.add(prize2ProbabilityInput);

        controlPanel.add(new JLabel("獎品3名稱:"));
        controlPanel.add(prize3NameInput);
        controlPanel.add(new JLabel("獎品3數量:"));
        controlPanel.add(prize3QuantityInput);
        controlPanel.add(new JLabel("獎品3機率 (0~1):"));
        controlPanel.add(prize3ProbabilityInput);

        JButton addButton = new JButton("加入");

        AddEventListener addEventListener = new AddEventListener(
                prize1NameInput,prize2NameInput,prize3NameInput,
                prize1QuantityInput,prize2QuantityInput,prize3QuantityInput,
                prize1ProbabilityInput,prize2ProbabilityInput,prize3ProbabilityInput,this.sharedBean);
        addButton.addActionListener(addEventListener);

        add(controlPanel, BorderLayout.NORTH);
        add(addButton, BorderLayout.EAST);


        // 抽獎按鈕
        JButton drawButton = new JButton("抽獎");

        DrawEventListener drawEventListener = new DrawEventListener(resultArea,prizeService);

        drawButton.addActionListener(drawEventListener);
        add(drawButton, BorderLayout.SOUTH);
    }
}
