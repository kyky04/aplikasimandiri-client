package co.id.shope.models;

public class DataToko {
    int id_toko;
    int id_user;
    String nama_toko;
    String moto_toko;
    String warna_aplikasi;

    public int getId_toko() {
        return id_toko;
    }

    public void setId_toko(int id_toko) {
        this.id_toko = id_toko;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getMoto_toko() {
        return moto_toko;
    }

    public void setMoto_toko(String moto_toko) {
        this.moto_toko = moto_toko;
    }

    public String getWarna_aplikasi() {
        return warna_aplikasi;
    }

    public void setWarna_aplikasi(String warna_aplikasi) {
        this.warna_aplikasi = warna_aplikasi;
    }
}
