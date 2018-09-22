package TickTackToStandart;



public class TicTacToe {


    private int[][] spielfeld;
    public static final int SPIELER1=-1;
    public static final int SPIELER2=-2;
    private int spielfeldgroessse;



    public TicTacToe(int spielfeldgroessse){
        if(spielfeldgroessse<3){
            this.spielfeldgroessse=3;
        }else this.spielfeldgroessse = spielfeldgroessse;

        spielfeld = new int[spielfeldgroessse][spielfeldgroessse];
        inFeled();


    }

    public int getSpielfeld(int zeilen, int spalte) {
        try {
            return spielfeld[zeilen][spalte];
        }catch (IndexOutOfBoundsException e){
            return -3;
        }
    }

    public static int getSPIELER1() {
        return SPIELER1;
    }

    public static int getSPIELER2() {
        return SPIELER2;
    }

    public int getSpielfeldgroessse() {
        return spielfeldgroessse;
    }

    private void inFeled(){
        int z=0;

        for(int i=0;i<spielfeld.length;i++) {
            for (int j = 0; j < spielfeld.length; j++) {
                spielfeld[i][j]=z;
                z++;
            }
        }
    }

    public String toString(){
        String ret="";

        for(int i=0;i<spielfeld.length;i++){
            for(int j=0;j<spielfeld.length;j++){
                ret=ret+" === ";
            }
            ret+="\n";
            for(int j=0;j<spielfeld.length;j++){
                if(spielfeld[i][j]<0){
                    if(spielfeld[i][j]==-1){
                        ret+="| X |";
                    }else{
                        ret+="| O |";
                    }
                }else{
                    if(spielfeld[i][j]<10) {
                        ret += "| " + spielfeld[i][j] + " |";
                    }else{
                        ret += "| " + spielfeld[i][j] + "|";
                    }
                }
            }
            ret+="\n";
        }
        for(int j=0;j<spielfeld.length;j++){
            ret=ret+" === ";
        }
        ret+="\n";
        return ret;

    }

    private int setZug(int zug, int spielernummer){
        if(zug > spielfeldgroessse*spielfeldgroessse || zug < 0){
            return -1;
        }
        for(int i = 0; i < spielfeldgroessse; i++){
            for(int j = 0; j < spielfeldgroessse; j++){
                if(spielfeld[i][j]==zug){
                    spielfeld[i][j]=spielernummer;
                    return 0;
                }
            }
        }
        return -2;
    }

    public int setZugSpieler1(int zug){
        return setZug(zug,SPIELER1);
    }

    public int setZugSpieler2(int zug){
        return setZug(zug,SPIELER2);
    }

    public int getGewonnen(){
        for (int i = -2; i < 0; i++){
            for(int j = 0; j < spielfeldgroessse; j++){

                int m = 0;
                int o = 0;
                for(int n = 0; n < spielfeldgroessse; n++) {
                    if (spielfeld[j][n]==i){
                        m++;
                    }
                    if (spielfeld[n][j]==i){
                        o++;
                    }
                    if(m==3||o==3){
                        return i;
                    }
                }
            }
            if(spielfeld[0][0]==i&&spielfeld[1][1]==i&&spielfeld[2][2]==i){
                return i;
            }

            if(spielfeld[0][2]==i&&spielfeld[1][1]==i&&spielfeld[2][0]==i){
                return i;
            }
        }
        return 0;
    }

    public boolean getEinerKannGewinnen(){

        for(int i = 0; i < spielfeldgroessse; i++){
            for(int j = 0; j < spielfeldgroessse; j++){
                if(spielfeld[i][j]<0)
                    return true;
            }
        }
        return false;
    }
}
