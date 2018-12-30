package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItemPengumuman implements Serializable {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_toko")
	private String idToko;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("judul")
	private String judul;

	@SerializedName("isi")
	private String isi;

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

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setJudul(String judul){
		this.judul = judul;
	}

	public String getJudul(){
		return judul;
	}

	public void setIsi(String isi){
		this.isi = isi;
	}

	public String getIsi(){
		return isi;
	}

	@Override
 	public String toString(){
		return 
			"DataItemPengumuman{" +
			"updated_at = '" + updatedAt + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",judul = '" + judul + '\'' + 
			",isi = '" + isi + '\'' + 
			"}";
		}
}