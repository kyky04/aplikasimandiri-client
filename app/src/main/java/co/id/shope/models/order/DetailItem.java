package co.id.shope.models.order;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import co.id.shope.models.DataItemProduk;


public class DetailItem implements Serializable {

    @SerializedName("id_produk")
    private int idProduk;

    @SerializedName("keterangan")
    private Object keterangan;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("created_by")
    private Object createdBy;

    @SerializedName("id_order")
    private int idOrder;

    @SerializedName("total")
    private String total;

    @SerializedName("deleted")
    private int deleted;

    @SerializedName("harga")
    private String harga;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("produk")
    private DataItemProduk produk;

    @SerializedName("qty")
    private int qty;

    @SerializedName("updated_by")
    private Object updatedBy;

    @SerializedName("id")
    private int id;

    public void setIdProduk(int idProduk) {
        this.idProduk = idProduk;
    }

    public int getIdProduk() {
        return idProduk;
    }

    public void setKeterangan(Object keterangan) {
        this.keterangan = keterangan;
    }

    public Object getKeterangan() {
        return keterangan;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getHarga() {
        return harga;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setProduk(DataItemProduk produk) {
        this.produk = produk;
    }

    public DataItemProduk getProduk() {
        return produk;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getQty() {
        return qty;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return
                "DetailItem{" +
                        "id_produk = '" + idProduk + '\'' +
                        ",keterangan = '" + keterangan + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",created_by = '" + createdBy + '\'' +
                        ",id_order = '" + idOrder + '\'' +
                        ",total = '" + total + '\'' +
                        ",deleted = '" + deleted + '\'' +
                        ",harga = '" + harga + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",produk = '" + produk + '\'' +
                        ",qty = '" + qty + '\'' +
                        ",updated_by = '" + updatedBy + '\'' +
                        ",id = '" + id + '\'' +
                        "}";
    }
}