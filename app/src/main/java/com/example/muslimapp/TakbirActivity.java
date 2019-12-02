package com.example.muslimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TakbirActivity extends AppCompatActivity {

    TextView tvDigit2;
    Button btnPress2, btnTasbih, btnReset, btnMenu;
    private int tambahAngka;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_takbir);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        tvDigit2 = findViewById(R.id.tv_digit2);
        btnTasbih = findViewById(R.id.btn_tasbih);
        btnPress2 = findViewById(R.id.btn_prees2);
        btnReset=findViewById(R.id.btn_reset1);


        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tambahAngka = 0;
                tvDigit2.setText(String.valueOf(tambahAngka));
            }
        });




        btnTasbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TakbirActivity.this,Tasbeeh.class));
            }
        });

        btnPress2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvDigit2.setText(String.valueOf(tambahAngka));
                if (tambahAngka >= 33){
                    Toast.makeText(TakbirActivity.this,"Succes di tekan",Toast.LENGTH_SHORT).show();
                    btnTasbih.setVisibility(View.VISIBLE);
                }else{
                    tambahAngka = tambahAngka+1;
                    btnTasbih.setVisibility(View.GONE);


                }
                tvDigit2.setText(String.valueOf(tambahAngka));
            }
        });
    }
}

