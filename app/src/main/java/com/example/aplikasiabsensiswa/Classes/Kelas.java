package com.example.aplikasiabsensiswa.Classes;

import android.content.Intent;

public class Kelas {
    public String Jurusan;
    public Integer Kelas;

    public Long studentCount;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String banner;

    private String kelasKey;

    // Make sure the 's' in 'studentCount' is lowercase
    public void setStudentCount(Long studentCount) {
        this.studentCount = studentCount;
    }

    // Add a getter for studentCount
    public Long getStudentCount() {
        return this.studentCount;
    }

    public void setKelasKey(String kelasKey){this.kelasKey = kelasKey;}

    public String getKelasKey(){return  this.kelasKey;}

    public Kelas(){

    }

    public Kelas(String Jurusan, Integer Kelas, String banner){
        this.Jurusan = Jurusan;
        this.Kelas = Kelas;
        this.banner = banner;
    }

}
