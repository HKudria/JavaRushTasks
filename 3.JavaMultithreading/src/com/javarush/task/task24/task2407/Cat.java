package com.javarush.task.task24.task2407;

/*
В работе вам иногда будет нужно закастить класс к какому-нибудь интерфейсу (тут Sayable),
который не реализован в текущем классе
 */
public class Cat implements Pet {
    private String name;

    public Cat(String name) {
        this.name = name;
    }

    public Sayable toSayable(final int i) {
        class CatPet implements Sayable{
            @Override
            public String say() {
                if (i < 1) return Cat.this.name + " спит.";
               
                    String countIA = "";
                    for (int j = 0; j < i; j++) {
                        countIA += "я";
                    }
                    return Cat.this.name + " говорит м" + countIA + "у!";
                
            }
        }
        return new CatPet();
    }
}