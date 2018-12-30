package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity public class DataItemKategori implements Serializable{

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("nama")
	private String nama;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_toko")
	private String idToko;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	@Id(assignable = true)
	long id;

	@SerializedName("gambar")
	private String gambar;

	@SerializedName("created_by")
	private String createdBy;

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
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

	public void setIdToko(String idToko){
		this.idToko = idToko;
	}

	public String getIdToko(){
		return idToko;
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
				"DataItemShip{" +
						"keterangan = '" + keterangan + '\'' +
						",nama = '" + nama + '\'' +
						",updated_at = '" + updatedAt + '\'' +
						",id_toko = '" + idToko + '\'' +
						",updated_by = '" + updatedBy + '\'' +
						",created_at = '" + createdAt + '\'' +
						",id = '" + id + '\'' +
						",gambar = '" + gambar + '\'' +
						",created_by = '" + createdBy + '\'' +
						"}";
	}
}