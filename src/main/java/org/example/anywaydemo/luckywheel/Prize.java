package org.example.anywaydemo.luckywheel;

import lombok.Data;

@Data
public class Prize {
    String name;
    Double probability;
    Integer quantity;


    public void minusQuantity() {
        this.quantity--;
    }
}
