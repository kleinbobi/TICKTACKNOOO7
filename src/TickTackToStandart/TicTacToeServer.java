package TickTackToStandart;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.util.Scanner;

public class TicTacToeServer extends TicTacToe {

    private java.net.Socket clientSocket;
    private static int FELDGROESSE;
    private static int PORT;
    private java.net.ServerSocket server;

    public TicTacToeServer(int feldgroesse,int port) throws java.io.IOException{

        super(feldgroesse);
        this.PORT=port;
        this.FELDGROESSE=feldgroesse;

        server = new ServerSocket(PORT);



    }

    public static void main(java.lang.String[] args){
        TicTacToeServer a=null;
        try {
            a = new TicTacToeServer(3,1025);
        }catch (IOException e){
            e.printStackTrace();
            System.exit(0);
        }

        do{
            int b = 0;

            System.out.println(a.toString());
            do {
                System.out.println("Spieler 1 ist am Zug");
                try{
                    b = a.getGegnerZug();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }while (b!=0);

            System.out.println(a.toString());
            if(a.getGewonnen()!=0){
                System.out.println("Gratulation Spieler 1");
                try {
                    a.close();
                }catch (IOException e){}
                break;
            }
            do {

                try{
                    b = a.setMeinZug(readInt("Server:"));
                }catch (IOException e) {

                }
            }while (b!=0);
            //try {
              //  a.close();
            //}catch (IOException e){}


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


    public void close() throws IOException {
            server.close();
            clientSocket.close();
    }

    private static int readInt(String s){

        Scanner scann = new Scanner(System.in);
        System.out.print(s+" ");
        return scann.nextInt();
    }

    public int getGegnerZug() throws IOException{
        clientSocket = server.accept();
        InputStream in = clientSocket.getInputStream();
        return setZugSpieler1(in.read());
    }

    public int setMeinZug(int zug) throws IOException{
        int i =setZugSpieler2(zug);

        if(i==0) {
            OutputStream out = clientSocket.getOutputStream();
            out.write(zug);
            clientSocket.close();
        }

        return i;


    }




}
