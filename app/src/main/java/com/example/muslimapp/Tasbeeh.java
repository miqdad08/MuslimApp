package com.example.muslimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Tasbeeh extends AppCompatActivity {
    TextView tvDigit;
    Button btnPress, btnTahmid, btnReset;
    private  int tambahAngka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasbeeh);



        tvDigit = findViewById(R.id.tv_digit);
        btnPress = findViewById(R.id.btn_prees);
        btnTahmid = findViewById(R.id.btn_tasbih);
        btnReset = findViewById(R.id.btn_reset);


        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                tambahAngka = 0;
                tvDigit.setText(String.valueOf(tambahAngka));
            }
        });


        btnTahmid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tasbeeh.this,TahmidActivity.class));
            }
        });


        btnPress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvDigit.setText(String.valueOf(tambahAngka));
                if (tambahAngka >= 33){
                    Toast.makeText(Tasbeeh.this,"Succes di tekan",Toast.LENGTH_SHORT).show();
                    btnTahmid.setVisibility(View.VISIBLE);
                }else{
                    tambahAngka = tambahAngka+1;
                    btnTahmid.setVisibility(View.GONE);
                }
                tvDigit.setText(String.valueOf(tambahAngka));
            }
        });



    }
}
