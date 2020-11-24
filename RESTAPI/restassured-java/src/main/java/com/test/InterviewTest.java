package com.test;


//Given the total number of Legs and Head of Rabbits and Pigeons.
// The task is to calculate the number of Rabbits and Pigeons. 
// Examples: Input: Heads = 200, Legs = 540
// Output: Rabbits = 70, Pigeons = 130

// Rabbit: 4 legs 1 head
// pigeon: 2 legs and 1 head

// legs = 70*4 + 130*2 = 280+260 = 540
// heads = 70+130 = 200

// nr * 4 + np*2 = nl
// 2* nr + 2*np = 2* nh

// nr + np = nh

// 2 * nr = nl - 2nh
// nr = (nl-2h)/2




public class InterviewTest {

    public static void main(String[] args) {

        getRabbitsPigeons(100, 300);


    }


    public static void getRabbitsPigeons(int heads , int legs) {

        int numRabbits =  (legs-2*heads)/2;
        int numPigeons = (legs - 4*numRabbits)/2;

        System.out.println("No of Rabbits: " +numRabbits );
        System.out.println("No of Pigeons: " +numPigeons );



    }





}
