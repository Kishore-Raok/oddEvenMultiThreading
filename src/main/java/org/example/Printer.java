package org.example;

class Printer {
    private int num = 1;  // Shared counter
    private final Object lock = new Object();  // Lock for synchronization
    private boolean isOddTurn = true;  // Flag to indicate which thread's turn

    public void printOdd() {
        while (true) {
            synchronized (lock) {
                if(num > 100){
                    break;
                }
                while (!isOddTurn) {
                    try {
                        lock.wait();  // Wait until it is odd's turn
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if(num <= 100) {
                System.out.println("OddThread : " + num);
                num++;
                }
                isOddTurn = false;  // Toggle the turn
                lock.notifyAll();  // Wake up the other thread
            }
        }
    }

    public void printEven() {
        while (true) {
            synchronized (lock) {
                if(num > 100){
                    break;
                }
                while (isOddTurn) {
                    try {
                        lock.wait();  // Wait until it is even's turn
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                if(num <= 100) {
                    System.out.println("EvenThread : " + num);
                    num++;
                }
                isOddTurn = true;  // Toggle the turn
                lock.notifyAll();  // Wake up the other thread
            }
        }
    }
}