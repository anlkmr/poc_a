package com.cesta.poc_a.concurrency.thread;

public class ExampleThread extends Thread{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName());
    }
}
