package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.CaseMap;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    int puntosPlayer = 0;
    int puntosMaquina = 0;
    int player = -1;
    TextView PuntosPlayer;
    TextView PuntosMaquina;
    TextView Empate;
    Button btnPiedra;
    Button btnPapel;
    Button btnTijera;
    ImageView ImagePlayer;
    ImageView ImageEnemy;
    int repetido;
    MediaPlayer mp3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        PuntosPlayer = findViewById(R.id.puntos_player);
        PuntosMaquina = findViewById(R.id.PuntosMaquina);
        Empate = findViewById(R.id.Empate);
        ImagePlayer = findViewById(R.id.IconPlayer);
        ImageEnemy = findViewById(R.id.IconEnemy);
        mp3 = MediaPlayer.create(MainActivity2.this, R.raw.aa);
        mp3.start();


        btnPiedra = findViewById(R.id.Piedra);
        btnPiedra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player = 0;
                ImagePlayer.setImageResource(R.drawable.piedra);
                clearEmpate();
                result();
            }
        });

        btnPapel = findViewById(R.id.Papel);
        btnPapel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player = 1;
                ImagePlayer.setImageResource(R.drawable.pepel);
                clearEmpate();
                result();


            }

        });
        btnTijera = findViewById(R.id.Tijera);
        btnTijera.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                player = 2;
                ImagePlayer.setImageResource(R.drawable.tijerablue);
                clearEmpate();
                result();

            }
        });


    }

    @Override
    protected void onPause() {
        mp3.pause();
        super.onPause();

    }

    @Override
    protected void onStart() {

        super.onStart();
        mp3.start();
        mp3.setLooping(true);


    }


    protected void result() {
        int enemy;

        do {
            enemy = (int) Math.round(Math.random() * 2);
        } while (enemy == repetido);

        setImageEnemy(enemy);


        if ((player == 2 && enemy == 1) || (player == 1 && enemy == 0) || (player == 0 && enemy == 2)) {
            puntosPlayer++;
            PuntosPlayer.setText(String.valueOf(puntosPlayer));
        } else if (player == enemy) {
            Empate.setText("Empate");
        } else {
            puntosMaquina++;
            PuntosMaquina.setText(String.valueOf(puntosMaquina));
        }

        if (puntosPlayer == 3) {
            Empate.setText("Ganaste");
            showAlertDialog("Ganaste");
        }

        if (puntosMaquina == 3) {
            Empate.setText("Perdiste");
            showAlertDialog("Perdiste");
        }

        repetido = enemy;


    }

    public void clearEmpate() {
        Empate.setText("");
    }

    public void setImageEnemy(int enemy) {
        switch (enemy) {
            case 0:
                ImageEnemy.setImageResource(R.drawable.piedra);
                break;
            case 1:
                ImageEnemy.setImageResource(R.drawable.pepel);
                break;
            case 2:
                ImageEnemy.setImageResource(R.drawable.tijeraenemy);
        }

    }




    public void showAlertDialog(String title) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("quieres volver a jugar?");
        alert.setTitle(title);
        alert.setCancelable(false);
        alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                Toast.makeText(MainActivity2.this, "suerte", Toast.LENGTH_SHORT).show();
                puntosMaquina = 0;
                puntosPlayer = 0;
                PuntosMaquina.setText(String.valueOf(puntosMaquina));
                PuntosPlayer.setText(String.valueOf(puntosPlayer));


            }
        });

        alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity2.this, "Vuelve pronto", Toast.LENGTH_SHORT).show();
                finish();
                mp3.stop();
            }
        });
        alert.create().show();

    }


}