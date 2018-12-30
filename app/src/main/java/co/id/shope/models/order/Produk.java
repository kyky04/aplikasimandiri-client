package co.id.shope.models.order;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Produk implements Serializable {

	@SerializedName("gambar_utama")
	private String gambarUtama;

	@SerializedName("id_kategori")
	private int idKategori;

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("stok")
	private int stok;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("deleted")
	private int deleted;

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private String harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("berat")
	private int berat;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("slug")
	private String slug;

	@SerializedName("status")
	private int status;

	public void setGambarUtama(String gambarUtama){
		this.gambarUtama = gambarUtama;
	}

	public String getGambarUtama(){
		return gambarUtama;
	}

	public void setIdKategori(int idKategori){
		this.idKategori = idKategori;
	}

	public int getIdKategori(){
		return idKategori;
	}

	public void setIdToko(int idToko){
		this.idToko = idToko;
	}

	public int getIdToko(){
		return idToko;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setStok(int stok){
		this.stok = stok;
	}

	public int getStok(){
		return stok;
	}

	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}

	public Object getCreatedBy(){
		return createdBy;
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

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setBerat(int berat){
		this.berat = berat;
	}

	public int getBerat(){
		return berat;
	}

	public void setUpdatedBy(int updatedBy){
		this.updatedBy = updatedBy;
	}

	public int getUpdatedBy(){
		return updatedBy;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setSlug(String slug){
		this.slug = slug;
	}

	public String getSlug(){
		return slug;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Produk{" + 
			"gambar_utama = '" + gambarUtama + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",stok = '" + stok + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",nama = '" + nama + '\'' + 
			",harga = '" + harga + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",berat = '" + berat + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id = '" + id + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",slug = '" + slug + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}