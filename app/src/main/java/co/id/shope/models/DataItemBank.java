package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataItemBank implements Serializable {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("nomor_rekening")
	private String noRekening;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("nama_bank")
	private String namaBank;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("atas_nama")
	private String atasNama;

	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setIdToko(int idToko){
		this.idToko = idToko;
	}

	public int getIdToko(){
		return idToko;
	}

	public void setUpdatedBy(int updatedBy){
		this.updatedBy = updatedBy;
	}

	public int getUpdatedBy(){
		return updatedBy;
	}

	public void setNoRekening(String noRekening){
		this.noRekening = noRekening;
	}

	public String getNoRekening(){
		return noRekening;
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

	public void setNamaBank(String namaBank){
		this.namaBank = namaBank;
	}

	public String getNamaBank(){
		return namaBank;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setAtasNama(String atasNama){
		this.atasNama = atasNama;
	}

	public String getAtasNama(){
		return atasNama;
	}

	@Override
 	public String toString(){
		return 
			"DataItemBank{" +
			"updated_at = '" + updatedAt + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",no_rekening = '" + noRekening + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",nama_bank = '" + namaBank + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",atas_nama = '" + atasNama + '\'' + 
			"}";
		}
}