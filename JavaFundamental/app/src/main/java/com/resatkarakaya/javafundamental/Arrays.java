package com.resatkarakaya.javafundamental;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Arrays {
    public static void main(String[] args){
        //Array
        //1.Yol
        String[] myString = new String[3];

        myString[0] = "ist";
        myString[1] = "ank";
        myString[1] = "izm";

        System.out.println(myString[0]);

        //2.Yol
        int[] myInt ={0,1,2,3,4,5,6,7};

        System.out.println(myInt[0]);

        //List
        ArrayList<String> myMusicians = new ArrayList<String>();
        myMusicians.add("James");
        System.out.println(myMusicians.size());

        //Set
        HashSet<String> mySet = new HashSet<String>();
        mySet.add("James");
        mySet.add("James");

        System.out.println(mySet.size());

        //Map
        HashMap<String,String> myHashMap = new HashMap<String, String>();

        myHashMap.put("name","James");
        myHashMap.put("instrument","Guitar");

        System.out.println(myHashMap.get("name"));
        System.out.println(myHashMap.get("instrument"));

        HashMap<String,Integer> mySecondMap = new HashMap<>();
        mySecondMap.put("run",100);

        System.out.println(mySecondMap.get("run"));

    }
}
