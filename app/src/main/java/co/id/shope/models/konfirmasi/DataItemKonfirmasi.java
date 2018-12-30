package co.id.shope.models.konfirmasi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItemKonfirmasi implements Serializable {

	@SerializedName("bank_pengirim")
	private String bankPengirim;

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("rekening_pengirim")
	private String rekeningPengirim;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("rekening_tujuan")
	private String rekeningTujuan;

	@SerializedName("id_order")
	private int idOrder;

	@SerializedName("nama_pengirim")
	private String namaPengirim;

	@SerializedName("deleted")
	private int deleted;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("bank_tujuan")
	private String bankTujuan;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("id")
	private int id;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("jumlah_bayar")
	private String jumlahBayar;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private int status;

	@SerializedName("order")
	private Order order;

	public void setBankPengirim(String bankPengirim){
		this.bankPengirim = bankPengirim;
	}

	public String getBankPengirim(){
		return bankPengirim;
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

	public void setRekeningPengirim(String rekeningPengirim){
		this.rekeningPengirim = rekeningPengirim;
	}

	public String getRekeningPengirim(){
		return rekeningPengirim;
	}

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setRekeningTujuan(String rekeningTujuan){
		this.rekeningTujuan = rekeningTujuan;
	}

	public String getRekeningTujuan(){
		return rekeningTujuan;
	}

	public void setIdOrder(int idOrder){
		this.idOrder = idOrder;
	}

	public int getIdOrder(){
		return idOrder;
	}

	public void setNamaPengirim(String namaPengirim){
		this.namaPengirim = namaPengirim;
	}

	public String getNamaPengirim(){
		return namaPengirim;
	}

	public void setDeleted(int deleted){
		this.deleted = deleted;
	}

	public int getDeleted(){
		return deleted;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setBankTujuan(String bankTujuan){
		this.bankTujuan = bankTujuan;
	}

	public String getBankTujuan(){
		return bankTujuan;
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

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setJumlahBayar(String jumlahBayar){
		this.jumlahBayar = jumlahBayar;
	}

	public String getJumlahBayar(){
		return jumlahBayar;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	public void setOrder(Order order){
		this.order = order;
	}

	public Order getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"DataItemKonfirmasi{" +
			"bank_pengirim = '" + bankPengirim + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",rekening_pengirim = '" + rekeningPengirim + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",gambar = '" + gambar + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",rekening_tujuan = '" + rekeningTujuan + '\'' + 
			",id_order = '" + idOrder + '\'' + 
			",nama_pengirim = '" + namaPengirim + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",bank_tujuan = '" + bankTujuan + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id = '" + id + '\'' + 
			",tanggal = '" + tanggal + '\'' + 
			",jumlah_bayar = '" + jumlahBayar + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}