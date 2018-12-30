package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItemMember implements Serializable {

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("id_toko")
    private int idToko;

    @SerializedName("nomor_telepon")
    private String nomorTelepon;

    @SerializedName("name")
    private String name;

    @SerializedName("updated_by")
    private String updatedBy;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("admin")
    private boolean admin;

    @SerializedName("id")
    private int id;

    @SerializedName("saldo")
    private String saldo;

    @SerializedName("created_by")
    private String createdBy;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private int status;

    @SerializedName("android_token")
    private String android_token;

    @SerializedName("foto")
    private String foto;

    public String getAndroid_token() {
        return android_token;
    }

    public void setAndroid_token(String android_token) {
        this.android_token = android_token;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setIdToko(int idToko) {
        this.idToko = idToko;
    }

    public int getIdToko() {
        return idToko;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return
                "DataItemMember{" +
                        "updated_at = '" + updatedAt + '\'' +
                        ",id_toko = '" + idToko + '\'' +
                        ",nomor_telepon = '" + nomorTelepon + '\'' +
                        ",name = '" + name + '\'' +
                        ",updated_by = '" + updatedBy + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",admin = '" + admin + '\'' +
                        ",id = '" + id + '\'' +
                        ",saldo = '" + saldo + '\'' +
                        ",created_by = '" + createdBy + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}