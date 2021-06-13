package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private static List<Horse> horses;
    static Hippodrome game = null;

    public static void main(String[] args) {
        horses = new ArrayList<>();
        horses.add(new Horse("horse1",3,0));
        horses.add(new Horse("horse2",3,0));
        horses.add(new Horse("horse3",3,0));
        game = new Hippodrome(horses);
        game.run();
    }

    public Hippodrome (List<Horse> horses){
        this.horses = horses;
    }
    
    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                move();
                print();
                Thread.sleep(500);
            }
        } catch (InterruptedException e){
            System.out.println("Error");
        }
    }

    public void move(){
        for (Horse s: horses) {
            s.move();
        }
    }

    public void print(){
        for (Horse s: horses) {
            s.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println("");
        }
    }
}
