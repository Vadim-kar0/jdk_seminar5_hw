package org.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

public class Philosopher extends Thread{

    private Fork leftFork;
    private Fork rightFork;
    private CountDownLatch satiety = new CountDownLatch(3);


    public Philosopher(String name) {
        super(name);
    }

    public void eating() throws InterruptedException {
        System.out.println("Philosopher №" + getName() +
                    " уплетает вермишель, используя вилки:" + leftFork + " и " + rightFork);
        Thread.sleep((long) (Math.random()*1000L));

    }

    public void returnForks() throws InterruptedException {
        System.out.println("Philosopher №" + getName() +
                " покушал, можно и помыслить. Не забыв при этом вернуть вилки: " + leftFork + " и " + rightFork);
        Thread.sleep((long) (Math.random()*1000L));
    }
    public void thinking() throws InterruptedException {
        System.out.println("Philosopher №" + getName() +
                " размышляет перед спагетти");
        Thread.sleep((long) (Math.random()*1000L));
    }


    public void takeLeftFork() {
        if (Integer.parseInt(getName()) == 0) {
            if (Table.table.get(Table.table.size() - 1) != null) {
                leftFork = Table.table.get(Table.table.size() - 1);
            }
        } else if (Table.table.get(Integer.parseInt(getName()) - 1) != null) {
            leftFork = Table.table.get(Integer.parseInt(getName()) - 1);
        }

    }

    public void takeRightFork(){

        if(Integer.parseInt(getName()) == 4){
            if(Table.table.get(Integer.parseInt(getName())) != null){
                rightFork = Table.table.get(Integer.parseInt(getName()));
            }
        } else if(Table.table.get(Integer.parseInt(getName())) != null){
            rightFork = Table.table.get(Integer.parseInt(getName()));
        }
    }


    @Override
    public void run() {
        try {
            Thread.sleep((long) (Math.random()*1000L));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        takeLeftFork();
        takeRightFork();
        while (satiety.getCount() > 0){
            synchronized (leftFork){
                synchronized (rightFork){
                    try {
                        eating();
                        returnForks();
                        satiety.countDown();
                        sleep(1000/(satiety.getCount() + 1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            if(satiety.getCount() != 0){
                try {
                    thinking();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

        }
        if(satiety.getCount() == 0){
            System.out.println("Philosopher №" + getName() + " наелся");
        }
    }
}
