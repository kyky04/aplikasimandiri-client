package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity public class GambarItem implements Serializable{

	@SerializedName("id_produk")
	private String idProduk;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	@Id
	long id;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("created_by")
	private String createdBy;

	public GambarItem(String gambar) {
		this.gambar = gambar;
	}

	public void setIdProduk(String idProduk){
		this.idProduk = idProduk;
	}

	public String getIdProduk(){
		return idProduk;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUpdatedBy(int updatedBy){
		this.updatedBy = updatedBy;
	}

	public int getUpdatedBy(){
		return updatedBy;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setGambar(String gambar){
		this.gambar = gambar;
	}

	public String getGambar(){
		return gambar;
	}

	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}

	public String getCreatedBy(){
		return createdBy;
	}

	@Override
 	public String toString(){
		return 
			"GambarItem{" + 
			"id_produk = '" + idProduk + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",gambar = '" + gambar + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			"}";
		}
}