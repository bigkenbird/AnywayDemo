package org.example.anywaydemo.calculator.service;

import lombok.RequiredArgsConstructor;
import org.example.anywaydemo.calculator.share.SharedDataBean;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final SharedDataBean sharedDataBean;

    public void addCount(String countData) {
        sharedDataBean.countSave.add(countData);
    }

    public void undo() {
        if(!sharedDataBean.countSave.isEmpty()) {
                String undo = sharedDataBean.countSave.removeLast();
                sharedDataBean.undoSave.add(undo);
            }
    }

    public String redo() {
        if(!sharedDataBean.undoSave.isEmpty()){
            String redoData = sharedDataBean.undoSave.pop();
            sharedDataBean.countSave.add(redoData);
            return redoData;
        }
        return "";
    }

    public String showCount() {
        return CollectionUtils.isEmpty(sharedDataBean.countSave) ? "" :
                String.join("", sharedDataBean.countSave);

    }

    public void clean(){
        sharedDataBean.countSave.clear();
        sharedDataBean.undoSave.clear();
    }
}
