package com.resatkarakaya.javafundamental;

public class Statements {
    public static void main(String[] args){
        //if is same with c programming for java
        //Switch
        int day=4;
        String dayString= "";
        switch (day){
            case 1:dayString = "Monday";break;
            case 2:dayString = "Tuesday";break;
            case 3:dayString = "Wednesday";break;
            case 4:dayString = "Thursday";break;
            case 5:dayString = "Friday";break;
            case 6:dayString = "Saturday";break;
            default:dayString = "Sunday";break;
        }
        System.out.println(dayString);


    }
}
