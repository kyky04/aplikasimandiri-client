package co.id.shope.models.order;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("id_address")
	private String idAddress;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_toko")
	private String idToko;

	@SerializedName("kode")
	private String kode;

	@SerializedName("cod")
	private String cod;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("id")
	private int id;

	@SerializedName("created_by")
	private String createdBy;

	@SerializedName("status")
	private int status;

	public void setIdAddress(String idAddress){
		this.idAddress = idAddress;
	}

	public String getIdAddress(){
		return idAddress;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdToko(String idToko){
		this.idToko = idToko;
	}

	public String getIdToko(){
		return idToko;
	}

	public void setKode(String kode){
		this.kode = kode;
	}

	public String getKode(){
		return kode;
	}

	public void setCod(String cod){
		this.cod = cod;
	}

	public String getCod(){
		return cod;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
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
			"DataItemToko{" +
			"id_address = '" + idAddress + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",kode = '" + kode + '\'' + 
			",cod = '" + cod + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",id = '" + id + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}