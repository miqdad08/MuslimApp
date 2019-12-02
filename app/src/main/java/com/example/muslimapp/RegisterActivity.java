package com.example.muslimapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout tInputName, tInputEmail, tInputPass, tInputConfirmPass;
    AutoCompleteTextView etName, etEmail, etPass, etConfirmPass;
    RadioButton rbMale, rbFemale;
    RadioGroup rgGender;
    Button btnRegister;
    CheckBox cbAggre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPassword);
        etConfirmPass = findViewById(R.id.etPasswordConfirm);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rgGender = findViewById(R.id.rgGender);
        btnRegister = findViewById(R.id.btnRegister);
        cbAggre = findViewById(R.id.cbAggre);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnRegister) {
            String nama = etName.getText().toString();
            String email = etEmail.getText().toString();
            String pass = etPass.getText().toString();
            String kelamin = String.valueOf(rgGender.getCheckedRadioButtonId());
            String aggre = cbAggre.getText().toString();
            String confirm = etConfirmPass.getText().toString();

            if (TextUtils.isEmpty(nama)) {
                etName.setError("Nama Tidak Boleh Kosong");
                return;
            }
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email Tidak Boleh Kosong");
                return;
            }
            if (TextUtils.isEmpty(pass)) {
                etPass.setError("Password Tidak Boleh Kosong");
                return;
            }
            if (TextUtils.isEmpty(confirm)) {
                etConfirmPass.setError("Confirm Password Tidak Boleh Kosong");
                return;
            }
            if (!pass.equals(confirm)) {
                Toast.makeText(this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                return;
            }
            invalidUser(nama, email, pass, kelamin, aggre);
        }
    }

    private void invalidUser(String name, String email, String password, String kelamin, String aggre) {

        SaveShared userShare = new SaveShared(this);
        UserModel userModel = userShare.getUser();

        String saveEmail = userModel.getEmail();
        if (email.equals(saveEmail)) {
            Toast.makeText(this, "Email Sudah Terdaftar, Silahkan Gunakan Email Yang Lain!", Toast.LENGTH_SHORT).show();
        } else {
            saveUser(name, email, password, kelamin, aggre);
        }
    }

    private void saveUser(String name, String email, String password, String kelamin, String aggre) {
        SaveShared userShared = new SaveShared(this);
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setKelamin(kelamin);
        userModel.setAggre(aggre);

        userShared.setUser(userModel);
        AlertDialog.Builder alert = new AlertDialog.Builder(RegisterActivity.this);
        alert.setTitle(getString(R.string.succesRegister));
        alert.setTitle(getString(R.string.cautionSucces));
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            }
        });
        alert.show();
    }
}
