package com.mmtzj;


public class ThreadTest implements Runnable {


    public void run() {
        int j = 0;
        while (true) {

            try {
                synchronized (this) {
                    if (j == 5) {
                        j = 0;
                        Tmp.getA().setOnoff(true);
                        Tmp.getA().Notify();
                        wait();
                    }
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("XX+++" + j);
            j++;
        }
    }


    public synchronized void Notify() {
        notify();
    }

    public static void main(String[] args) {

        ThreadA A = new ThreadA();
        Thread testA = new Thread(A);
        testA.start();

        ThreadTest B = new ThreadTest();
        Thread testB = new Thread(B);
        testB.start();

        Tmp tmp = new Tmp();
        tmp.setB(B);
        tmp.setA(A);


    }


}


class Tmp {
    private static ThreadTest B;
    private static ThreadA A;

    public static ThreadA getA() {
        return A;
    }


    public static void setA(ThreadA a) {
        A = a;
    }


    public static ThreadTest getB() {
        return B;
    }


    public static void setB(ThreadTest b) {
        B = b;
    }
}


class ThreadA implements Runnable {


    boolean Onoff = false;


    public boolean setOnoff(boolean LnKai) {
        return Onoff = LnKai;
    }


    public synchronized void Notify() {
        notify();
    }


    public void run() {
        int j = 0;
        while (true) {

            while (Onoff) {

                try {
                    synchronized (this) {
                        if (j == 5) {
                            j = 0;
                            Onoff = false;
                            Tmp.getB().Notify();
                            wait();
                        }
                        Thread.sleep(500);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("YY---" + j);
                j++;

            }
        }


    }
}