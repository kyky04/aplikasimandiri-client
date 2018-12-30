package co.id.shope.models;

import java.io.Serializable;

public class ProdukOrder implements Serializable {
    int id_produk;
    double harga;
    int quantity;
    String nama;
    String gambar;

    public ProdukOrder(int id_produk, double harga, int quantity, String nama, String gambar) {
        this.id_produk = id_produk;
        this.harga = harga;
        this.quantity = quantity;
        this.nama = nama;
        this.gambar = gambar;
    }

    public ProdukOrder(int id_produk, double harga, int quantity) {
        this.id_produk = id_produk;
        this.harga = harga;
        this.quantity = quantity;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public int getId_produk() {
        return id_produk;
    }

    public void setId_produk(int id_produk) {
        this.id_produk = id_produk;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "id "+id_produk + " harga " + harga + " quantity " + quantity;
    }
}
