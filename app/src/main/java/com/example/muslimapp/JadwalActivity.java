package com.example.muslimapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.muslimapp.Service.APIClient;
import com.example.muslimapp.Service.BaseAPIService;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.ThreeBounce;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {

    UserModel userModel;
    SaveShared saveShared;
    TextView tvFajr, tvSyuruk, tvDhuhr, tvAsr, tvMaghrib, tvIsya, tvTanggal, tvLocation, tvTime;
    TextView txtFajr, txtSunrise, txtDhuhr, txtAsr, txtMaghrib, txtIsya;
    ImageView ivBackground;
    BaseAPIService apiService;
    ProgressBar progressBar;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        tvFajr = findViewById(R.id.tvFajr);
        tvSyuruk = findViewById(R.id.tvSunrise);
        tvDhuhr = findViewById(R.id.tvDhuhr);
        tvAsr = findViewById(R.id.tvAsr);
        tvMaghrib = findViewById(R.id.tvMaghrib);
        tvIsya = findViewById(R.id.tvIsha);
        tvTanggal = findViewById(R.id.tvCalendar);
        tvLocation = findViewById(R.id.tvLocation);
        tvTime = findViewById(R.id.tvTime);
        ivBackground = findViewById(R.id.ivBg);
        scrollView = findViewById(R.id.svMain);


        //deklarasi untuk view textnya
        txtFajr = findViewById(R.id.fajr);
        txtSunrise = findViewById(R.id.sunrise);
        txtDhuhr = findViewById(R.id.dhuhr);
        txtAsr = findViewById(R.id.asr);
        txtMaghrib = findViewById(R.id.maghrib);
        txtIsya = findViewById(R.id.isha);

        progressBar = (ProgressBar) findViewById(R.id.spin_kit);
        Sprite three = new ThreeBounce();
        progressBar.setIndeterminateDrawable(three);

        apiService = APIClient.getJadwalService();
        getJadwalSholatMethod("Bekasi");

        Button btChange = findViewById(R.id.btOk);
        btChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText etCityName = new EditText(JadwalActivity.this);
                AlertDialog.Builder alert = new AlertDialog.Builder(JadwalActivity.this);
                alert.setTitle("City Name").setMessage("Masukan nama kota yang di inginkan").setView(etCityName);
                alert.setPositiveButton("Change City", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String city = etCityName.getText().toString();
                        getJadwalSholatMethod(city);
                    }
                });
                alert.show();
            }
        });

        showDynamisTime();
        saveShared = new SaveShared(this);
    }

    private void showDynamisTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyy");
        tvTanggal.setText(simpleDateFormat.format(new Date()));

        //setting dynamisnya, mengikuti waktu
        Calendar calendar = Calendar.getInstance();
        int time = calendar.get(Calendar.HOUR_OF_DAY);
        if (time>=4 && time<=5){
            tvTime.setText(getString(R.string.Fajr));
            ivBackground.setImageResource(R.drawable.morning);
            txtFajr.setTextColor(getResources().getColor(R.color.hijauTua));
            tvFajr.setTextColor(getResources().getColor(R.color.hijauTua));
        }else if (time>=5 && time<=6){
            tvTime.setText(getString(R.string.Sunrise));
            txtSunrise.setTextColor(getResources().getColor(R.color.hijauTua));
            tvSyuruk.setTextColor(getResources().getColor(R.color.hijauTua));
        }else if (time>=19 && time<=24){
            tvTime.setText(getString(R.string.isyaa));
            ivBackground.setImageResource(R.drawable.malam);
            txtIsya.setTextColor(getResources().getColor(R.color.hijauTua));
            tvIsya.setTextColor(getResources().getColor(R.color.hijauTua));
        }
    }

    private void getJadwalSholatMethod(String namaKota){
        final Call<ResponseBody> requestApi = apiService.getJadwalShalat(namaKota);
        requestApi.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        if (jsonObject.getString("status").equals("OK")){
                            progressBar.setVisibility(View.GONE);
                            scrollView.setVisibility(View.VISIBLE);
                            String fajr = jsonObject.getJSONObject("data").getString("Fajr");
                            String syuruk = jsonObject.getJSONObject("data").getString("Sunrise");
                            String dhuhr = jsonObject.getJSONObject("data").getString("Dhuhr");
                            String asr = jsonObject.getJSONObject("data").getString("Asr");
                            String maghrib = jsonObject.getJSONObject("data").getString("Maghrib");
                            String isya = jsonObject.getJSONObject("data").getString("Isha");
                            String address = jsonObject.getJSONObject("location").getString("address");


                            //set data to textview
                            tvFajr.setText(fajr+"AM");
                            tvSyuruk.setText(syuruk+"AM");
                            tvDhuhr.setText(dhuhr+"AM");
                            tvAsr.setText(asr+"PM");
                            tvMaghrib.setText(maghrib+"PM");
                            tvIsya.setText(isya+"PM");
                            tvLocation.setText(address);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(JadwalActivity.this,"Response Gagal!",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(JadwalActivity.this,"Bad Connection!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menuLogout:
                userModel = saveShared.getUser();
                userModel.setStatusLogin(false);
                saveShared.setUser(userModel);
                startActivity(new Intent(JadwalActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.accountSetting:
                startActivity(new Intent(JadwalActivity.this, SettingActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
