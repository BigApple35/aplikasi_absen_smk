package com.example.aplikasiabsensiswa.guru.Absen;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Absen;
import com.example.aplikasiabsensiswa.Classes.Kelas;
import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;
import com.example.aplikasiabsensiswa.databinding.AbsenHistoryCardBinding;
import com.example.aplikasiabsensiswa.databinding.ActivityAbsenHistoryBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class AbsenHistory extends AppCompatActivity {
    private static final String SHARED_PREF_NAME = "user";

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Absen");
    DatabaseReference kelasRefrence = FirebaseDatabase.getInstance().getReference("Kelas");



    ActivityAbsenHistoryBinding binding;
    List<Absen> absenList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAbsenHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String nomorInduk = sharedPreferences.getString("nomorInduk", "null");
        AbsenHistory.AbsenHistoryAdapter absenAdapter = new AbsenHistory.AbsenHistoryAdapter(absenList);
        binding.absenHistoryRv.setAdapter(absenAdapter);
        binding.absenHistoryRv.setLayoutManager(new LinearLayoutManager(this));
        binding.absenHistoryRv.setHasFixedSize(true);



        databaseReference.orderByChild("pengabsen").equalTo(nomorInduk).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot absenSnapshot : dataSnapshot.getChildren()) {
                    Absen absen = absenSnapshot.getValue(Absen.class);
                    absenList.add(absen);
                }
                absenAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors.
            }
        });




    }
    class AbsenHistoryViewHolder extends RecyclerView.ViewHolder{
        TextView kelas;
        TextView tanggal;

        public AbsenHistoryViewHolder(View itemView) {
            super(itemView);
            this.kelas = itemView.findViewById(R.id.kelas_absen_history);
            this.tanggal = itemView.findViewById(R.id.tanggal_absen_history);
        }
    }

    class AbsenHistoryAdapter extends RecyclerView.Adapter<AbsenHistory.AbsenHistoryViewHolder>{
        private List<Absen> absenList;

        private Context context;

        public AbsenHistoryAdapter(List<Absen> absenList) {
            this.absenList = absenList;
        }

        @NonNull
        @Override
        public AbsenHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.absen_history_card, parent, false);
            return new AbsenHistoryViewHolder(view);
        }
        String jurursan;
        @Override
        public void onBindViewHolder(@NonNull AbsenHistoryViewHolder holder, int position) {
            Absen absen = absenList.get(position);
            kelasRefrence.child(absen.getKelas()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Kelas kelas = snapshot.getValue(Kelas.class);
                    jurursan = kelas.Jurusan;
                    holder.kelas.setText(jurursan);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            holder.tanggal.setText(absen.getTanggal());

        }

        @Override
        public int getItemCount() {
            return absenList.size();
        }
    }


}