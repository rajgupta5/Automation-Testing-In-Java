package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class test {

    public static void main(String[] args) {
        ArrayList<Integer> integerArrayList = new ArrayList<>(Arrays.asList(20,30,93,28,73));

        integerArrayList.add(30);

       for( int i=0; i< integerArrayList.size();i++) {
           System.out.println(integerArrayList.get(i));
       }

        System.out.println("=====");

        for (Integer n:
             integerArrayList) {
            System.out.println(n);
        }

        System.out.println("=====");

        Iterator<Integer> iterator = integerArrayList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        System.out.println("=====");

        ListIterator<Integer> integerListIterator = integerArrayList.listIterator();
        while (integerListIterator.hasNext()) {
            System.out.println(integerListIterator.next());
        }

        System.out.println("=====");

        integerArrayList.forEach(System.out::println);



    }
}
