package com.ltq.undertow.math;

import java.util.HashMap;
import java.util.Random;

public class Acount implements Cal {
    public String name = "";
    public double ten;
    public int percent;
    public static String[] number = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十" };
    public static HashMap<String, Integer> numberMap = new HashMap();

    static {
        for (int i = 0; i < number.length; i++) {
            numberMap.put(number[i], i);
        }
    }

    public enum NameType {
        ZHE, CHENG
    }

    public Acount(NameType aType) {
        Random rand = new Random();
        percent = rand.nextInt(100);

        if (aType == NameType.ZHE) {
            if (percent < 10) {
                name = number[percent] + "折";
            } else {
                name = number[percent / 10] + number[percent % 10] + "折";
            }
        } else {
            if (percent < 10) {
                name = number[percent] + "成";
            } else {
                name = number[percent / 10] + "成" + number[percent % 10];
            }
        }
        set();
    }

    public Acount(String name) {
        this.name = name;
        set();
    }

    public void set() {
        String temp = name;
        temp = temp.replaceAll("成", "");
        temp = temp.replaceAll("折", "");
        if (temp.length() == 1) {
            percent = numberMap.get(temp.substring(0, 1)) * 10;
            ten = numberMap.get(temp.substring(0, 1));
        }
        if (temp.length() == 2) {
            percent = numberMap.get(temp.substring(0, 1)) * 10 + numberMap.get(temp.substring(1, 2));
            ten = numberMap.get(temp.substring(0, 1)) + numberMap.get(temp.substring(1, 2)) / 10;
        }
    }

    @Override
    public String getQues() {
        // 一折 1/10 10%
        return String.format("%s＝(  )/10＝(  )", name) + "%";
    }

    @Override
    public String getAnswer() {
        return ten + "," + percent;
    }

    public static void main(String[] args) {
        Acount ac = new Acount(NameType.ZHE);
        System.out.println(ac.getQues() + ":" + ac.getAnswer());
        ac = new Acount(NameType.CHENG);
        System.out.println(ac.getQues() + ":" + ac.getAnswer());
    }
}