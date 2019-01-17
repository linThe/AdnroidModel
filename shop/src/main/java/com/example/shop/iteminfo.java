package com.example.shop;

import java.io.Serializable;

public class iteminfo implements Serializable { //标记，系统会自动序列化该内容
    private String name ;
    private int life ;
    private int attack ;
    private  int agile ;

    public iteminfo(String name,int life,int attack,int agile) {
        this.name = name;
        this.life =  life ;
        this.attack =attack ;
        this.agile = agile ;
    }


    @Override
    public String toString() {
        return "iteminfo{" +
                "name='" + name + '\'' +
                ", life=" + life +
                ", attack=" + attack +
                ", agile=" + agile +
                '}';
    }

    public int getAgile() {
        return agile;
    }

    public void setAgile(int agile) {
        this.agile = agile;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
