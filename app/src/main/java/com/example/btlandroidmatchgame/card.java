package com.example.btlandroidmatchgame;

public class card {
    int card_img_res;
    boolean flipable = true;

    public card() {
    }

    public card(int card_img_res, boolean flipable) {
        this.card_img_res = card_img_res;
        this.flipable = flipable;
    }

    public int getCard_img_res() {
        return card_img_res;
    }

    public boolean isFlipable() {
        return flipable;
    }

    public void setFlipable(boolean flipable) {
        this.flipable = flipable;
    }
}


