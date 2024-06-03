package com.example.aplikasiabsensiswa.guru.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplikasiabsensiswa.Classes.Siswa;
import com.example.aplikasiabsensiswa.R;

import java.util.List;

class AbsenSiswaViewHolder extends RecyclerView.ViewHolder {
    public TextView nama;

    public Spinner spinner;


    public AbsenSiswaViewHolder(View itemView) {
        super(itemView);
        nama = itemView.findViewById(R.id.nama_siswa_absen);
        spinner = itemView.findViewById(R.id.spinner);
    }
}

public class AbsenSiswaAdapter extends RecyclerView.Adapter<AbsenSiswaViewHolder> {
    private List<Siswa> siswaList;

    public AbsenSiswaAdapter(List<Siswa> siswaList) {
        this.siswaList = siswaList;
    }

    @NonNull
    @Override
    public AbsenSiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.absen_card, parent, false);
        return new AbsenSiswaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AbsenSiswaViewHolder holder, int position) {
        Siswa siswa = siswaList.get(position);
        Spinner spinner = holder.spinner;
        holder.nama.setText(siswa.getNama());
        String[] textSpinner = new String[] {"masuk", "alpha", "izin", "sakit"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>( holder.itemView.getContext(), android.R.layout.simple_spinner_item, textSpinner);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                siswa.setCheked(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaList.size();
    }
}
