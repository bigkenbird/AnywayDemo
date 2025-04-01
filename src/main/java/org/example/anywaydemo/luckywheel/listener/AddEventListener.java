package org.example.anywaydemo.luckywheel.listener;

import org.example.anywaydemo.luckywheel.share.SharedBean;
import org.example.anywaydemo.luckywheel.Prize;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEventListener implements ActionListener {

    private SharedBean sharedBean;

    private JTextField prize1NameInput;
    private JTextField prize2NameInput;
    private JTextField prize3NameInput;
    private JTextField prize1QuantityInput;
    private JTextField prize2QuantityInput;
    private JTextField prize3QuantityInput;
    private JTextField prize1ProbabilityInput;
    private JTextField prize2ProbabilityInput;
    private JTextField prize3ProbabilityInput;


    public AddEventListener(JTextField prize1NameInput, JTextField prize2NameInput, JTextField prize3NameInput
    , JTextField prize1QuantityInput, JTextField prize2QuantityInput, JTextField prize3QuantityInput,
     JTextField prize1ProbabilityInput, JTextField prize2ProbabilityInput,JTextField prize3ProbabilityInput,SharedBean sharedBean) {
        this.prize1NameInput = prize1NameInput;
        this.prize2NameInput = prize2NameInput;
        this.prize3NameInput = prize3NameInput;
        this.prize1QuantityInput = prize1QuantityInput;
        this.prize2QuantityInput = prize2QuantityInput;
        this.prize3QuantityInput = prize3QuantityInput;
        this.prize1ProbabilityInput = prize1ProbabilityInput;
        this.prize2ProbabilityInput = prize2ProbabilityInput;
        this.prize3ProbabilityInput = prize3ProbabilityInput;
        this.sharedBean = sharedBean;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Prize prize1 = new Prize();
        Prize prize2 = new Prize();
        Prize prize3 = new Prize();
        Prize noPrize = new Prize();

        prize1.setName(prize1NameInput.getText());
        prize1.setQuantity(Integer.valueOf(prize1QuantityInput.getText()));
        prize1.setProbability(Double.valueOf(prize1ProbabilityInput.getText()));

        prize2.setName(prize2NameInput.getText());
        prize2.setQuantity(Integer.valueOf(prize2QuantityInput.getText()));
        prize2.setProbability(Double.valueOf(prize2ProbabilityInput.getText()));

        prize3.setName(prize3NameInput.getText());
        prize3.setQuantity(Integer.valueOf(prize3QuantityInput.getText()));
        prize3.setProbability(Double.valueOf(prize3ProbabilityInput.getText()));

        if(!CollectionUtils.isEmpty(this.sharedBean.prizes)){
            this.sharedBean.prizes.clear();
        }

        noPrize.setName("鳴謝惠顧");
        noPrize.setQuantity(Integer.MAX_VALUE);
        noPrize.setProbability(Double.parseDouble("1")-prize1.getProbability()-prize2.getProbability()-prize3.getProbability());

        this.sharedBean.noPrize = noPrize;

        this.sharedBean.prizes.add(prize1);
        this.sharedBean.prizes.add(prize2);
        this.sharedBean.prizes.add(prize3);
        this.sharedBean.prizes.add(noPrize);
    }
}
