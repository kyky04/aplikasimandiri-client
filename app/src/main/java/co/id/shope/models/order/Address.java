package co.id.shope.models.order;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Address implements Serializable {

	@SerializedName("nama_provinsi")
	private String namaProvinsi;

	@SerializedName("latitude")
	private String latitude;

	@SerializedName("kode_pos")
	private String kodePos;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_kota")
	private int idKota;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("id_provinsi")
	private int idProvinsi;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("utama")
	private boolean utama;

	@SerializedName("deleted")
	private int deleted;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("nomor_telepon")
	private String nomorTelepon;

	@SerializedName("updated_by")
	private String updatedBy;

	@SerializedName("id")
	private int id;

	@SerializedName("nama_kota")
	private String namaKota;

	@SerializedName("longitude")
	private String longitude;

	public void setNamaProvinsi(String namaProvinsi){
		this.namaProvinsi = namaProvinsi;
	}

	public String getNamaProvinsi(){
		return namaProvinsi;
	}

	public void setLatitude(String latitude){
		this.latitude = latitude;
	}

	public String getLatitude(){
		return latitude;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdKota(int idKota){
		this.idKota = idKota;
	}

	public int getIdKota(){
		return idKota;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setIdProvinsi(int idProvinsi){
		this.idProvinsi = idProvinsi;
	}

	public int getIdProvinsi(){
		return idProvinsi;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setUtama(boolean utama){
		this.utama = utama;
	}

	public boolean isUtama(){
		return utama;
	}

	public void setDeleted(int deleted){
		this.deleted = deleted;
	}

	public int getDeleted(){
		return deleted;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setNomorTelepon(String nomorTelepon){
		this.nomorTelepon = nomorTelepon;
	}

	public String getNomorTelepon(){
		return nomorTelepon;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNamaKota(String namaKota){
		this.namaKota = namaKota;
	}

	public String getNamaKota(){
		return namaKota;
	}

	public void setLongitude(String longitude){
		this.longitude = longitude;
	}

	public String getLongitude(){
		return longitude;
	}

	@Override
 	public String toString(){
		return 
			"Address{" + 
			"nama_provinsi = '" + namaProvinsi + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",kode_pos = '" + kodePos + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_kota = '" + idKota + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",id_provinsi = '" + idProvinsi + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",utama = '" + utama + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",nama = '" + nama + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",nomor_telepon = '" + nomorTelepon + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id = '" + id + '\'' + 
			",nama_kota = '" + namaKota + '\'' + 
			",longitude = '" + longitude + '\'' + 
			"}";
		}
}