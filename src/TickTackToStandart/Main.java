package TickTackToStandart;

import TickTackToStandart.TicTacToe;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        TicTacToe a=new TicTacToe(3);
        do{
            int b = 0;

            System.out.println(a.toString());
            do {
                System.out.println("Spieler 1:");
                b = a.setZugSpieler1(scan.nextInt());
            }while (b!=0);

            System.out.println(a.toString());
            if(a.getGewonnen()!=0){
                System.out.println("Gratulation Spieler 1");
                break;
            }
            do {
                System.out.println("Spieler 2:");
                b = a.setZugSpieler2(scan.nextInt());
            }while (b!=0);
            if(a.getGewonnen()!=0){
                System.out.println("Gratulation Spieler 2");
                break;
            }
        }while (a.getEinerKannGewinnen());





    }
}
