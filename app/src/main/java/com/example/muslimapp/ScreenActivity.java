package com.example.muslimapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenActivity extends AppCompatActivity {
    UserModel userModel;
    SaveShared saveShared;
    TextView tvTime, tvTanggal, tvUsername;
    ImageView ivBackground;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);

        tvTime = findViewById(R.id.tvClock);
        tvTanggal = findViewById(R.id.tvCalender);
        tvUsername = findViewById(R.id.tvUsername);

        ivBackground = findViewById(R.id.ivBg);

        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite three = new ThreeBounce();
        progressBar.setIndeterminateDrawable(three);

        saveShared = new SaveShared(this);
        userModel = new UserModel();
        userModel = saveShared.getUser();
        tvUsername.setText("Hi, "+userModel.getName());

        showDynamisTime();
    }
    private void showDynamisTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyy");
        tvTanggal.setText(simpleDateFormat.format(new Date()));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                userModel = saveShared.getUser();
                userModel.setStatusLogin(false);
                saveShared.setUser(userModel);
                startActivity(new Intent(ScreenActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.accountSetting:
                startActivity(new Intent(ScreenActivity.this, SettingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void asmaulHusna(View view) {
        startActivity(new Intent(ScreenActivity.this, AsmaulHusna.class));
    }

    public void praySchedule(View view) {
        startActivity(new Intent(ScreenActivity.this, JadwalActivity.class));
    }

    public void tasbeeh(View view) {
        startActivity(new Intent(ScreenActivity.this, Tasbeeh.class));
    }

    public void Qibla(View view) {
        startActivity(new Intent(ScreenActivity.this, QiblatActivity.class));
    }

    public void DzikrPagi(View view) {
        Intent intent = new Intent(ScreenActivity.this, DetailDzikir.class);
        intent.putExtra("KEY","Pagi");
        startActivity(intent);

    }

    public void DzikirPetang(View view) {
        Intent intent = new Intent(ScreenActivity.this, DetailDzikir.class);
        intent.putExtra("KEY","Petang");
        startActivity(intent);
    }
}
