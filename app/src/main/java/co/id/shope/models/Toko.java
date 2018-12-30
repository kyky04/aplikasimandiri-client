package co.id.shope.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Toko{

	@SerializedName("whatsapp")
	private String whatsapp;

	@SerializedName("nama_provinsi")
	private String namaProvinsi;

	@SerializedName("latitude")
	private Object latitude;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("id_kota")
	private int idKota;

	@SerializedName("instagram")
	private String instagram;

	@SerializedName("id_provinsi")
	private int idProvinsi;

	@SerializedName("twitter")
	private String twitter;

	@SerializedName("bank")
	private List<BankItem> bank;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("pos")
	private boolean pos;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("longitude")
	private Object longitude;

	@SerializedName("jne")
	private boolean jne;

	@SerializedName("tiki")
	private boolean tiki;

	@SerializedName("facebook")
	private String facebook;

	@SerializedName("kode_pos")
	private String kodePos;

	@SerializedName("bbm")
	private String bbm;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("alamat")
	private String alamat;

	@SerializedName("biaya_cod")
	private String biayaCod;

	@SerializedName("nama")
	private String nama;

	@SerializedName("nomor_telepon")
	private String nomorTelepon;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("cod")
	private boolean cod;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("nama_kota")
	private String namaKota;

	public void setWhatsapp(String whatsapp){
		this.whatsapp = whatsapp;
	}

	public String getWhatsapp(){
		return whatsapp;
	}

	public void setNamaProvinsi(String namaProvinsi){
		this.namaProvinsi = namaProvinsi;
	}

	public String getNamaProvinsi(){
		return namaProvinsi;
	}

	public void setLatitude(Object latitude){
		this.latitude = latitude;
	}

	public Object getLatitude(){
		return latitude;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setIdKota(int idKota){
		this.idKota = idKota;
	}

	public int getIdKota(){
		return idKota;
	}

	public void setInstagram(String instagram){
		this.instagram = instagram;
	}

	public String getInstagram(){
		return instagram;
	}

	public void setIdProvinsi(int idProvinsi){
		this.idProvinsi = idProvinsi;
	}

	public int getIdProvinsi(){
		return idProvinsi;
	}

	public void setTwitter(String twitter){
		this.twitter = twitter;
	}

	public String getTwitter(){
		return twitter;
	}

	public void setBank(List<BankItem> bank){
		this.bank = bank;
	}

	public List<BankItem> getBank(){
		return bank;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPos(boolean pos){
		this.pos = pos;
	}

	public boolean isPos(){
		return pos;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setLongitude(Object longitude){
		this.longitude = longitude;
	}

	public Object getLongitude(){
		return longitude;
	}

	public void setJne(boolean jne){
		this.jne = jne;
	}

	public boolean isJne(){
		return jne;
	}

	public void setTiki(boolean tiki){
		this.tiki = tiki;
	}

	public boolean isTiki(){
		return tiki;
	}

	public void setFacebook(String facebook){
		this.facebook = facebook;
	}

	public String getFacebook(){
		return facebook;
	}

	public void setKodePos(String kodePos){
		this.kodePos = kodePos;
	}

	public String getKodePos(){
		return kodePos;
	}

	public void setBbm(String bbm){
		this.bbm = bbm;
	}

	public String getBbm(){
		return bbm;
	}

	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}

	public Object getCreatedBy(){
		return createdBy;
	}

	public void setAlamat(String alamat){
		this.alamat = alamat;
	}

	public String getAlamat(){
		return alamat;
	}

	public void setBiayaCod(String biayaCod){
		this.biayaCod = biayaCod;
	}

	public String getBiayaCod(){
		return biayaCod;
	}

	public void setNama(String nama){
		this.nama = nama;
	}

	public String getNama(){
		return nama;
	}

	public void setNomorTelepon(String nomorTelepon){
		this.nomorTelepon = nomorTelepon;
	}

	public String getNomorTelepon(){
		return nomorTelepon;
	}

	public void setUpdatedBy(int updatedBy){
		this.updatedBy = updatedBy;
	}

	public int getUpdatedBy(){
		return updatedBy;
	}

	public void setCod(boolean cod){
		this.cod = cod;
	}

	public boolean isCod(){
		return cod;
	}

	public void setDeskripsi(String deskripsi){
		this.deskripsi = deskripsi;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setNamaKota(String namaKota){
		this.namaKota = namaKota;
	}

	public String getNamaKota(){
		return namaKota;
	}

	@Override
 	public String toString(){
		return 
			"Toko{" + 
			"whatsapp = '" + whatsapp + '\'' + 
			",nama_provinsi = '" + namaProvinsi + '\'' + 
			",latitude = '" + latitude + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_kota = '" + idKota + '\'' + 
			",instagram = '" + instagram + '\'' + 
			",id_provinsi = '" + idProvinsi + '\'' + 
			",twitter = '" + twitter + '\'' + 
			",bank = '" + bank + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",pos = '" + pos + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",longitude = '" + longitude + '\'' + 
			",jne = '" + jne + '\'' + 
			",tiki = '" + tiki + '\'' + 
			",facebook = '" + facebook + '\'' + 
			",kode_pos = '" + kodePos + '\'' + 
			",bbm = '" + bbm + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",alamat = '" + alamat + '\'' + 
			",biaya_cod = '" + biayaCod + '\'' + 
			",nama = '" + nama + '\'' + 
			",nomor_telepon = '" + nomorTelepon + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",cod = '" + cod + '\'' + 
			",deskripsi = '" + deskripsi + '\'' + 
			",nama_kota = '" + namaKota + '\'' + 
			"}";
		}
}