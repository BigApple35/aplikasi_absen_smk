package com.example.aplikasiabsensiswa.Classes;

public class User {
    private String nip;
    private String email;
    private String password;

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    private String nama;

    public User(String nip, String email, String password, String nama) {
        this.nip = nip;
        this.email = email;
        this.password = password;
        this.nama = nama;
    }

    public User() {

    }



}
