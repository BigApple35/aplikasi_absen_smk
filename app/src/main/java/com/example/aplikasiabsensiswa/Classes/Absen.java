package com.example.aplikasiabsensiswa.Classes;

public class Absen {
    private Integer jamKe;

    private String kelas;
    private String pengabsen;
    private Integer sampaiKe;
    private String siswaTerabsen;

    private String siswaMasuk;

    public String getSiswaMasuk() {
        return siswaMasuk;
    }

    public void setSiswaMasuk(String siswaMasuk) {
        this.siswaMasuk = siswaMasuk;
    }

    public String getSiswaAlpha() {
        return siswaAlpha;
    }

    public void setSiswaAlpha(String siswaAlpha) {
        this.siswaAlpha = siswaAlpha;
    }

    public String getSiswaIzin() {
        return siswaIzin;
    }

    public void setSiswaIzin(String siswaIzin) {
        this.siswaIzin = siswaIzin;
    }

    public String getSiswaSakit() {
        return siswaSakit;
    }

    public void setSiswaSakit(String siswaSakit) {
        this.siswaSakit = siswaSakit;
    }

    private String siswaAlpha;
    private String siswaIzin;
    private String siswaSakit;

    private String tanggal;

    public Absen(Integer jamKe, String kelas, String pengabsen, Integer sampaiKe, String tanggal, String siswaTerabsen, String siswaMasuk, String siswaAlpha, String siswaIzin, String siswaSakit) {
        this.jamKe = jamKe;
        this.kelas = kelas;
        this.pengabsen = pengabsen;
        this.sampaiKe = sampaiKe;
        this.siswaTerabsen = siswaTerabsen;
        this.tanggal = tanggal;
        this.siswaMasuk = siswaMasuk;
        this.siswaAlpha = siswaAlpha;
        this.siswaIzin = siswaIzin;
        this.siswaSakit = siswaSakit;
    }

    public Absen() {

    }

    public Integer getJamKe() {
        return jamKe;
    }

    public void setJamKe(Integer jamKe) {
        this.jamKe = jamKe;
    }

    public String getKelas() {
        return kelas;
    }

    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    public String getPengabsen() {
        return pengabsen;
    }

    public void setPengabsen(String pengabsen) {
        this.pengabsen = pengabsen;
    }

    public Integer getSampaiKe() {
        return sampaiKe;
    }

    public void setSampaiKe(Integer sampaiKe) {
        this.sampaiKe = sampaiKe;
    }

    public String getSiswaTerabsen() {
        return siswaTerabsen;
    }

    public void setSiswaTerabsen(String siswaTerabsen) {
        this.siswaTerabsen = siswaTerabsen;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
