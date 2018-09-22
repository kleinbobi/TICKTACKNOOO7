package TickTackToStandart;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class TicTacToeClient extends TicTacToe{

    private Socket client;
    private static int FELDGROESSE;
    private static String ipAdresse;
    private static int PORT;

    public TicTacToeClient(int feldgroesse){
        super(feldgroesse);





    }


    public static void main(java.lang.String[] args){
        if(args[0]==null)
            System.exit(0);
        System.out.println(args[0]+" "+args[1]);
        ipAdresse=args[0];
        PORT=Integer.parseInt(args[1]);



        TicTacToeClient a= new TicTacToeClient(3);

        do{
            int b = 0;

            System.out.println(a.toString());
            do {

                try{
                    b = a.setMeinZug(readInt("Server:"));
                }catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    try {
                       // a.close();
                    }catch (Exception e){}
                }
            }while (b!=0);
            b=0;
            System.out.println(a.toString());
            if(a.getGewonnen()!=0){
                System.out.println("Gratulation Spieler 1");
                try {
                    a.close();
                }catch (IOException e){}
                break;
            }
            System.out.println("Spieler 1:");
            do {

                try{
                    b = a.getGegnerZug();
                }catch (IOException e) {
                    b=3;
                }
            }while (b!=0);
            try {
                a.close();
            }catch (IOException e){}

            if(a.getGewonnen()!=0){
                System.out.println("Gratulation Spieler 2");
                try {
                    a.close();
                }catch (IOException e){}
                break;
            }
        }while (a.getEinerKannGewinnen());
        try {
            a.close();
        }catch (IOException e){}






    }



    public void close() throws IOException{
        client.close();
    }


    private static int readInt(String s){

        Scanner scann = new Scanner(System.in);
        System.out.print(s+" ");
        return scann.nextInt();
    }

    public int setMeinZug(int zug) throws IOException, UnknownHostException{
        int i = setZugSpieler1(zug);
        if(i == 0){
            client = new Socket(ipAdresse,PORT);
            OutputStream out= client.getOutputStream();
            out.write(zug);
            return 0;
        }
        return i;
    }


    public int getGegnerZug() throws IOException{
        InputStream in=null;
        try {
           in = client.getInputStream();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return setZugSpieler2(in.read());
    }





}
