package com.example.aplikasiabsensiswa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aplikasiabsensiswa.Classes.Sekretaris;
import com.example.aplikasiabsensiswa.Classes.User;
import com.example.aplikasiabsensiswa.databinding.ActivityLoginBinding;
import com.example.aplikasiabsensiswa.databinding.ActivityLoginSiswaBinding;
import com.example.aplikasiabsensiswa.guru.MenuGuru;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<String> role = new ArrayList<>();

        role.add("Guru");
        role.add("Sekretaris");
        role.add("Siswa");

        binding.spinnerLogin.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, role));
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("users");
        DatabaseReference sekretarisRef = FirebaseDatabase.getInstance().getReference("userSekretaris");


        binding.loginButton.setOnClickListener(v -> {
            String NIS = binding.etNisLogin.getText().toString();
            String Password = binding.etPasswordLogin.getText().toString();
            String Role = binding.spinnerLogin.getSelectedItem().toString();

            if (NIS.isEmpty() || Password.isEmpty()) {
               Toast.makeText(this, "NIS atau Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
               return;
           }

            if(Role.equals("Sekretaris")){
               sekretarisRef.orderByChild("nis").equalTo(NIS).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {

                       for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                           Sekretaris user = dataSnapshot.getValue(Sekretaris.class);
                           if (user.getPassword().equals(Password) && user.getNis().equals(NIS)) {
                               saveData(user.getNis(), user.getNama(), user.getPassword(), "sekretaris");
                               Intent intent = new Intent(Login.this, MenuGuru.class);
                               startActivity(intent);
                           } else {
                               Toast.makeText(getApplicationContext(),"Login Gagal:(", Toast.LENGTH_SHORT).show();
                           }
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
               return;
           }

           if (Role.equals("Guru")) {
               database.orderByChild("nip").equalTo(NIS).addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {

                       for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                           User user = dataSnapshot.getValue(User.class);
                           if (user.getPassword().equals(Password) && user.getNip().equals(NIS)) {
                               Intent intent = new Intent(Login.this, MenuGuru.class);
                               saveData(user.getNip(), user.getNama(), user.getPassword(), "guru");
                               startActivity(intent);
                           } else {
                               Toast.makeText(getApplicationContext(),"Login Gagal:(", Toast.LENGTH_SHORT).show();
                           }
                       }
                   }
                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
           }
        });

    }

    public void saveData(String nomorInduk, String nama, String password, String role){
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nomorInduk", nomorInduk);
        editor.putString("nama", nama);
        editor.putString("password", password);
        editor.putString("role", role);
        editor.apply();
    }

}