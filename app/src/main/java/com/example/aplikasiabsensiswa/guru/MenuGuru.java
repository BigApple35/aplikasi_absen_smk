package com.example.aplikasiabsensiswa.guru;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aplikasiabsensiswa.HalamanAwal;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.Riwayat;
import com.example.aplikasiabsensiswa.databinding.ActivityMenuGuruBinding;
import com.example.aplikasiabsensiswa.guru.Absen.AbsenHistory;
import com.example.aplikasiabsensiswa.guru.Absen.AbsenKelas;
import com.example.aplikasiabsensiswa.guru.Absen.AbsenSiswa;
import com.example.aplikasiabsensiswa.guru.Adapter.EditSekretarisAdapter;

public class MenuGuru extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "user";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_NIP = "nomorInduk";

    ActivityMenuGuruBinding binding;

    public void logOut(){
        sharedPreferences.edit().clear().apply();
    }

    @SuppressLint({"SetTextI18n", "ApplySharedPref"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuGuruBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        binding.textView5.setText("Halo , " + sharedPreferences.getString(KEY_NAMA, null) + " !");

        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);


        binding.buttonSekretaris.setOnClickListener(v -> {
            Intent sekretaris = new Intent(MenuGuru.this, EditSekretaris.class);
            startActivity(sekretaris);
        });

        binding.buttonKelas.setOnClickListener(v -> {
            if (!sharedPreferences.getString("role", null).equals("guru")){
                Toast.makeText(getApplicationContext(),"Edit kelas hanya bisa dilakukan oleh guru G :(", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent editkelas = new Intent(MenuGuru.this, EditKelas.class);
            startActivity(editkelas);
        });

        binding.buttonAbsenHistory.setOnClickListener(v -> {
            Intent riwayat = new Intent(MenuGuru.this, AbsenHistory.class);
            startActivity(riwayat);
        });

        binding.buttonAbsen.setOnClickListener(v -> {
            Intent absen = new Intent(MenuGuru.this, AbsenKelas.class);
            startActivity(absen);
        });

        binding.buttonLogout.setOnClickListener(v -> {
            logOut();
            Intent intent = new Intent(MenuGuru.this, HalamanAwal.class);
            startActivity(intent);
            finish();
        });




//        tvNama = findViewById(R.id.tvNama);
//        tvNIP = findViewById(R.id.tvNIP);
//        btnSekretaris = findViewById(R.id.btnSekretaris);
//        btnEditKelas = findViewById(R.id.btnKelas);
//        btnRiwayat = findViewById(R.id.btnRiwayat);
//        btnAbsen = findViewById(R.id.btnAbsen);
//
//
//        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
//        String nama = sharedPreferences.getString(KEY_NAMA,null);
//        String nip = sharedPreferences.getString(KEY_NIP,null);
//
//
//        if (nama != null && nip != null){
//            tvNama.setText(nama);
//            tvNIP.setText(nip);
//        }
//
//        btnSekretaris.setOnClickListener(v -> {
//            Intent sekretaris = new Intent(MenuGuru.this, EditSekretaris.class);
//            startActivity(sekretaris);
//        });
//        btnEditKelas.setOnClickListener(v -> {
//            Intent editkelas = new Intent(MenuGuru.this, EditKelas.class);
//            startActivity(editkelas);
//        });
//        btnRiwayat.setOnClickListener(v -> {
//            Intent riwayat = new Intent(MenuGuru.this, Riwayat.class);
//            startActivity(riwayat);
//        });
//        btnAbsen.setOnClickListener(v -> {
//            Intent absen = new Intent(MenuGuru.this, AbsenKelas.class);
//            startActivity(absen);
//        });


    }
}