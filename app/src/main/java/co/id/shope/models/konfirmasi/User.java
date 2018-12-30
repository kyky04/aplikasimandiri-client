package co.id.shope.models.konfirmasi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

    @SerializedName("id_toko")
    private int idToko;

    @SerializedName("android_token")
    private String androidToken;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("admin")
    private boolean admin;

    @SerializedName("saldo")
    private String saldo;

    @SerializedName("created_by")
    private Object createdBy;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("foto")
    private Object foto;

    @SerializedName("nomor_telepon")
    private Object nomorTelepon;

    @SerializedName("name")
    private String name;

    @SerializedName("updated_by")
    private Object updatedBy;

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("status")
    private int status;

    public void setIdToko(int idToko) {
        this.idToko = idToko;
    }

    public int getIdToko() {
        return idToko;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }

    public String getAndroidToken() {
        return androidToken;
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

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }

    public String getSaldo() {
        return saldo;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public Object getFoto() {
        return foto;
    }

    public void setNomorTelepon(Object nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public Object getNomorTelepon() {
        return nomorTelepon;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
                "User{" +
                        "id_toko = '" + idToko + '\'' +
                        ",android_token = '" + androidToken + '\'' +
                        ",created_at = '" + createdAt + '\'' +
                        ",admin = '" + admin + '\'' +
                        ",saldo = '" + saldo + '\'' +
                        ",created_by = '" + createdBy + '\'' +
                        ",updated_at = '" + updatedAt + '\'' +
                        ",foto = '" + foto + '\'' +
                        ",nomor_telepon = '" + nomorTelepon + '\'' +
                        ",name = '" + name + '\'' +
                        ",updated_by = '" + updatedBy + '\'' +
                        ",id = '" + id + '\'' +
                        ",email = '" + email + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}