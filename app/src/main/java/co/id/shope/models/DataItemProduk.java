package co.id.shope.models;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.SerializedName;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.annotation.Transient;

@Entity public class DataItemProduk implements Serializable {

	@SerializedName("gambar_utama")
	private String gambarUtama;

	@SerializedName("id_kategori")
	private String idKategori;

	@SerializedName("id_toko")
	private String idToko;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("stok")
	private String stok;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("gambar")
	private List<GambarItem> gambar;

	@SerializedName("nama")
	private String nama;

	@SerializedName("harga")
	private String harga;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("berat")
	private String berat;

	@SerializedName("updated_by")
	private String updatedBy;

//	@SerializedName("id")
	@Id(assignable = true)
	long id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("slug")
	private String slug;

	@SerializedName("status")
	private String status;


	@SerializedName("kategori")
	@Transient
	DataItemKategori kategori;

	@SerializedName("harga_diskon")
	private int hargaDiskon;

	public int getHargaDiskon() {
		return hargaDiskon;
	}

	public void setHargaDiskon(int hargaDiskon) {
		this.hargaDiskon = hargaDiskon;
	}

	private int amount;

	private boolean wishlist;

	private boolean cart;

	public boolean isWishlist() {
		return wishlist;
	}

	public void setWishlist(boolean wishlist) {
		this.wishlist = wishlist;
	}

	public boolean isCart() {
		return cart;
	}

	public void setCart(boolean cart) {
		this.cart = cart;
	}

	public DataItemKategori getKategori() {
		return kategori;
	}

	public void setKategori(DataItemKategori kategori) {
		this.kategori = kategori;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setGambarUtama(String gambarUtama){
		this.gambarUtama = gambarUtama;
	}

	public String getGambarUtama(){
		return gambarUtama;
	}

	public void setIdKategori(String idKategori){
		this.idKategori = idKategori;
	}

	public String getIdKategori(){
		return idKategori;
	}

	public void setIdToko(String idToko){
		this.idToko = idToko;
	}

	public String getIdToko(){
		return idToko;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setStok(String stok){
		this.stok = stok;
	}

	public String getStok(){
		return stok;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	public void setGambar(List<GambarItem> gambar){
		this.gambar = gambar;
	}

	public List<GambarItem> getGambar(){
		return gambar;
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

	public void setBerat(String berat){
		this.berat = berat;
	}

	public String getBerat(){
		return berat;
	}

	public void setUpdatedBy(String updatedBy){
		this.updatedBy = updatedBy;
	}

	public String getUpdatedBy(){
		return updatedBy;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
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

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataItemProduk{" +
			"gambar_utama = '" + gambarUtama + '\'' + 
			",id_kategori = '" + idKategori + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",stok = '" + stok + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",gambar = '" + gambar + '\'' + 
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