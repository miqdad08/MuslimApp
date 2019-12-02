package com.example.muslimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TahmidActivity extends AppCompatActivity {
    TextView tvDigit1;
    Button btnTakbir, btnPress1, btnReset;
    private int tambahAngka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tahmid);

        tvDigit1 = findViewById(R.id.tv_digit1);
        btnTakbir = findViewById(R.id.btn_takbir);
        btnPress1 = findViewById(R.id.btn_prees1);
        btnReset= findViewById(R.id.btn_reset);

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tambahAngka = 0;
                tvDigit1.setText(String.valueOf(tambahAngka));
            }
        });


        btnTakbir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TahmidActivity.this,TakbirActivity.class));
            }
        });

        btnPress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvDigit1.setText(String.valueOf(tambahAngka));
                if (tambahAngka >= 33){
                    Toast.makeText(TahmidActivity.this,"Succes di tekan",Toast.LENGTH_SHORT).show();
                    btnTakbir.setVisibility(View.VISIBLE);
                }else{
                    tambahAngka = tambahAngka+1;
                    btnTakbir.setVisibility(View.GONE);


                }
                tvDigit1.setText(String.valueOf(tambahAngka));
            }
        });
    }
}

