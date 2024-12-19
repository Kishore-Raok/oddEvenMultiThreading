package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Printer printer = new Printer();

        Thread oddThread = new Thread(() -> printer.printOdd(), "OddThread");
        Thread evenThread = new Thread(() -> printer.printEven(), "EvenThread");

        oddThread.start();
        evenThread.start();
    }
}