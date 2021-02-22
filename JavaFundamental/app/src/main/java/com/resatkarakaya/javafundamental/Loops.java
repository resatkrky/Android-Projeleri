package com.resatkarakaya.javafundamental;

public class Loops {
    public static void main(String[] args){
        //for
        int[] myNum = {40,55,85,90,75};

        for(int i=0;i<myNum.length;i++){
            int x = myNum[i] *5 ;
            System.out.println(x);
        }

        for (int number : myNum){
            System.out.println(number / 5);
        }

        //While

        int j=0;
        while (j <10){
            System.out.println("test");
            j++;
        }

    }
}
