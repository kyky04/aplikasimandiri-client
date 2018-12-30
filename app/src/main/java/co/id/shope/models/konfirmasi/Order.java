package co.id.shope.models.konfirmasi;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Order  implements Serializable {

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("id_address")
	private int idAddress;

	@SerializedName("deleted")
	private int deleted;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode")
	private String kode;

	@SerializedName("updated_by")
	private Object updatedBy;

	@SerializedName("id_voucher")
	private Object idVoucher;

	@SerializedName("cod")
	private boolean cod;

	@SerializedName("bayar_saldo")
	private Object bayarSaldo;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private int status;

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

	public void setIdAddress(int idAddress){
		this.idAddress = idAddress;
	}

	public int getIdAddress(){
		return idAddress;
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

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
	}

	public void setIdVoucher(Object idVoucher){
		this.idVoucher = idVoucher;
	}

	public Object getIdVoucher(){
		return idVoucher;
	}

	public void setCod(boolean cod){
		this.cod = cod;
	}

	public boolean isCod(){
		return cod;
	}

	public void setBayarSaldo(Object bayarSaldo){
		this.bayarSaldo = bayarSaldo;
	}

	public Object getBayarSaldo(){
		return bayarSaldo;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"Order{" + 
			"id_toko = '" + idToko + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",id_address = '" + idAddress + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",kode = '" + kode + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id_voucher = '" + idVoucher + '\'' + 
			",cod = '" + cod + '\'' + 
			",bayar_saldo = '" + bayarSaldo + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}