package com.company.samuel.gameeasy;

import android.app.Activity;
import android.graphics.drawable.Icon;
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ImageView ca1,ca2,ca3,cb1,cb2,cb3,cc1,cc2,cc3;
    private ImageView [][] casillas;
    private RadioGroup radioG;
    private Button onePlayerBtn,twoPlayerBtn;
    private int player;
    private int dificult;
    private Match match;
    /*private int matriz[][];
    private RadioButton fac,med,dif;
    private int turno;
    private boolean ganador = false;
    private int moviments;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instanciarCasillas();
        casillas = new ImageView[3][3];
        llenarCasillas();
        radioG = (RadioGroup)findViewById(R.id.rgBtn);
        onePlayerBtn = (Button) findViewById(R.id.btn1Player);
        twoPlayerBtn = (Button) findViewById(R.id.btn2Player);
        /*
        matriz = new int[3][3];
        fac = (RadioButton) findViewById(R.id.rbtnF);
        med = (RadioButton) findViewById(R.id.rbtnM);
        dif = (RadioButton) findViewById(R.id.rbtnD);
        turno =1;
        moviments = 0;
        */
    }

    public void playGame(View view){
        resetGame();
        player = 1;
        if(view.getId()==R.id.btn2Player){
            player = 2;
        }
        int idBtnG = radioG.getCheckedRadioButtonId();
        dificult = 0;
        if(idBtnG == R.id.rbtnM)dificult = 1;
        else if(idBtnG == R.id.rbtnD)dificult = 2;
        match = new Match(dificult);
        onePlayerBtn.setEnabled(false);
        twoPlayerBtn.setEnabled(false);
        radioG.setEnabled(false);

    }


    public void touchCasilla(View view) {

        if(match == null)return;
        int []arr = new int[2];
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (view.getId() == casillas[i][j].getId()) {
                    arr = new int[]{i,j};
                    break;
                }
            }
        }
        if(match.isGoodPosition(arr)) {
            marcar(casillas[arr[0]][arr[1]]);
        }
        else return;
        int res = match.turno();
        if(res >0){
            terminar(res);
            return;
        }


        if(player==1) {
            arr = match.ia();
            while (!match.isGoodPosition(arr)) arr = match.ia();
            marcar(casillas[arr[0]][arr[1]]);
            res = match.turno();
            if (res > 0) terminar(res);
        }
    }

    public void terminar(int res){
        if(res ==1) Toast.makeText(this,R.string.aspaGana,Toast.LENGTH_LONG).show();
        else if( res == 2)Toast.makeText(this,R.string.circuloGana,Toast.LENGTH_LONG).show();
        else Toast.makeText(this,R.string.empate,Toast.LENGTH_LONG).show();
        onePlayerBtn.setEnabled(true);
        twoPlayerBtn.setEnabled(true);
        radioG.setEnabled(true);
        match = null;
    }

    private void marcar(ImageView img){
        if(match.getPlayer()==1){
            img.setImageResource(R.drawable.aspa);
        }
        else{
            img.setImageResource(R.drawable.circulo);
        }
    }

    private void resetGame(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                casillas[i][j].setImageResource(R.drawable.casilla);
            }
        }
    }

    private void instanciarCasillas(){
        ca1 = (ImageView) findViewById(R.id.ca1);
        ca2 = (ImageView) findViewById(R.id.ca2);
        ca3 = (ImageView) findViewById(R.id.ca3);

        cb1 = (ImageView) findViewById(R.id.cb1);
        cb2 = (ImageView) findViewById(R.id.cb2);
        cb3 = (ImageView) findViewById(R.id.cb3);

        cc1 = (ImageView) findViewById(R.id.cc1);
        cc2 = (ImageView) findViewById(R.id.cc2);
        cc3 = (ImageView) findViewById(R.id.cc3);
    }

    private void llenarCasillas(){
        casillas[0][0]= ca1;
        casillas[0][1]= ca2;
        casillas[0][2]= ca3;
        casillas[1][0]= cb1;
        casillas[1][1]= cb2;
        casillas[1][2]= cb3;
        casillas[2][0]= cc1;
        casillas[2][1]= cc2;
        casillas[2][2]= cc3;
    }
    /*
    public void playGame(View view){
        Op.setEnabled(false);
        Tp.setEnabled(false);
        moviments = 0;
        ganador = false;
        turno = 1;
        matriz = new int[3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                casillas[i][j].setImageResource(R.drawable.casilla);
            }
        }

    }

    public void touchCasilla(View view) {
        if (player == 1) {

        if (fac.isChecked()) {
        if (marcar(view)) {
        int arr[] = getJugadaFacil();
        matriz[arr[0]][arr[1]] = turno;
        casillas[arr[0]][arr[1]].setImageResource(R.drawable.circulo);
        turno = 1;
        }
        }
        if (med.isChecked()) {

        }
        if (dif.isChecked()) {

        }
        } else if (player == 2) {
        marcar(view);
        ganador = buscarGanador();
        if (ganador) {
        if(moviments==9){
        Toast.makeText(this, R.string.empate, Toast.LENGTH_LONG).show();
        Op.setEnabled(true);
        Tp.setEnabled(true);
        }
        else {
        if (turno == 2) {
            Toast.makeText(this, R.string.aspaGana, Toast.LENGTH_LONG).show();
        } else
            Toast.makeText(this, R.string.circuloGana, Toast.LENGTH_LONG).show();
        }
        }
        }

    }

    private boolean marcar(View view){
        boolean res = false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(view.getId() == casillas[i][j].getId()){
                    if(matriz[i][j]==0){
                        moviments++;
                        matriz[i][j] = turno;
                        if(turno == 1){
                            casillas[i][j].setImageResource(R.drawable.aspa);
                            turno = 2;
                        }
                        else {
                            casillas[i][j].setImageResource(R.drawable.circulo);
                            turno =1;
                        }
                        res = true;
                    }
                    else{
                        Toast.makeText(this,"This selected space has already been marked",Toast.LENGTH_LONG).show();
                    }
                    return  res;
                }
            }
        }
        return res;
    }

    private int [] getJugadaFacil(){
        int arr[] = new int[2];
        for(int i=0;i<3;i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == 0) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
        return arr;
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
    }*/
}
