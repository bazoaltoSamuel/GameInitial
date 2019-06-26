package com.company.samuel.gameeasy;

import java.util.Random;

public class Match {

    public final int dificult;
    private int player;
    private int [][]matriz;

    public Match(int dificult){
        this.dificult = dificult;
        player = 1;
        matriz = new int[3][3];
    }

    public boolean isGoodPosition(int arr[]){
        boolean res = false;
        if(matriz[arr[0]][arr[1]]==0){
            matriz[arr[0]][arr[1]] = player;
            res = true;
        }
        return res;
    }

    public int getPlayer(){
        return player;
    }

    public int[] ia(){
        int arr [] = new int[2];
        arr = dosEnRaya(2);
        if(arr[0] != -1)return  arr;

        if(dificult>0){
            arr = dosEnRaya(1);
            if(arr[0]!=-1)return arr;
        }
        if(dificult==2){
            if(matriz[1][1] ==0)return arr =new int[]{1,1};
            if(matriz[0][0] ==0)return arr =new int[]{0,0};
            if(matriz[0][2] ==0)return arr =new int[]{0,2};
            if(matriz[2][0] ==0)return arr =new int[]{2,0};
            if(matriz[2][2] ==0)return arr =new int[]{2,2};
        }

        Random random = new Random();
        arr[0] = random.nextInt(3);
        arr[1] = random.nextInt(3);
        return arr;
    }

    public int turno(){
        int res =0;
        if(buscarEmpate()){
            res =3;
            return res;
        }

        if(buscarGanador()){
            if(player ==1) res = 1;
            else res =2;
        }
        if(player == 1) player = 2;
        else player =1;
        return res;
    }

    private boolean buscarEmpate(){
        boolean res = true;
        for(int i=0;i<3;i++){
            for(int j =0;j<3;j++){
                if(matriz[i][j]==0)res = false;
            }
        }
        return res;
    }

    private boolean buscarGanador(){
        boolean res = false;
        for(int i=0;i<3;i++) {
            if((matriz[i][0]==1 && matriz[i][1]==1 && matriz[i][2]==1) ||
                    (matriz[i][0]==2 && matriz[i][1]==2 && matriz[i][2]==2)){
                res = true;
            }
            if((matriz[0][i]==1 && matriz[1][i]==1 && matriz[2][i]==1) ||
                    (matriz[0][i]==2 && matriz[1][i]==2 && matriz[2][i]==2)){
                res = true;
            }
        }

        if((matriz[0][0]==1 && matriz[1][1]==1 && matriz[2][2]==1) ||
                (matriz[0][0]==2 && matriz[1][1]==2 && matriz[2][2]==2)){
            res = true;
        }

        if((matriz[0][2]==1 && matriz[1][1]==1 && matriz[2][0]==1) ||
                (matriz[0][2]==2 && matriz[1][1]==2 && matriz[2][0]==2)){
            res = true;
        }
        return res;
    }

    public int[] dosEnRaya(int playerAct){
        int arr [] = new int[]{-1,-1};
        for(int i=0;i<3;i++) {
            if((matriz[i][0]==playerAct && matriz[i][1]==playerAct && matriz[i][2]==0) ||
                    (matriz[i][0]==0 && matriz[i][1]==playerAct && matriz[i][2]==playerAct) ||
                            (matriz[i][0]==playerAct && matriz[i][1]==0 && matriz[i][2]==playerAct )){
                if(matriz[i][0] ==0)arr = new int []{i,0};
                else if(matriz[i][1] ==0)arr = new int []{i,1};
                else arr = new int []{i,2};
                return arr;
            }
            if((matriz[0][i]==playerAct && matriz[1][i]==playerAct && matriz[2][i]==0) ||
                    (matriz[0][i]==0 && matriz[1][i]==playerAct && matriz[2][i]==playerAct) ||
                            (matriz[0][i]==playerAct && matriz[1][i]==0 && matriz[2][i]==playerAct)){
                if(matriz[0][i] ==0)arr = new int []{0,i};
                else if(matriz[1][i] ==0)arr = new int []{1,i};
                else arr = new int []{2,i};
                return  arr;
            }
        }

        if((matriz[0][0]==playerAct && matriz[1][1]==playerAct && matriz[2][2]==0) ||
                (matriz[0][0]==0 && matriz[1][1]==playerAct && matriz[2][2]==playerAct) ||
                        (matriz[0][0]==playerAct && matriz[1][1]==0 && matriz[2][2]==playerAct)){
            if(matriz[0][0] ==0)arr = new int []{0,0};
            else if(matriz[1][1] ==0)arr = new int []{1,1};
            else arr = new int []{2,2};
            return arr;
        }

        if((matriz[0][2]==playerAct && matriz[1][1]==playerAct && matriz[2][0]==playerAct) ||
                (matriz[0][2]==playerAct && matriz[1][1]==playerAct && matriz[2][0]==playerAct) ||
                        (matriz[0][2]==playerAct && matriz[1][1]==playerAct && matriz[2][0]==playerAct)){
            if(matriz[0][2] ==0)arr = new int []{0,2};
            else if(matriz[1][1] ==0)arr = new int []{1,1};
            else arr = new int []{2,0};
            return arr;
        }
        return arr;
    }
}
