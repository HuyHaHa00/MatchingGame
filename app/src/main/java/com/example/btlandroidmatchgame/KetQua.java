package com.example.btlandroidmatchgame;

public class KetQua {
    String name;
    String flipCount;

    public KetQua(){

    }
    public KetQua(String name, String flipCount){
        this.name = name;
        this.flipCount = flipCount;
    }

    public String getName(){
        return name;
    }

    public String getFlipCount(){
        return flipCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFlipCount(String flipCount) {
        this.flipCount = flipCount;
    }
}


