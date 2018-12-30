package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

public class BankItem{

	@SerializedName("updated_at")
	private Object updatedAt;

	@SerializedName("nomor_rekening")
	private String nomorRekening;

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("updated_by")
	private Object updatedBy;

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

	public void setUpdatedAt(Object updatedAt){
		this.updatedAt = updatedAt;
	}

	public Object getUpdatedAt(){
		return updatedAt;
	}

	public void setNomorRekening(String nomorRekening){
		this.nomorRekening = nomorRekening;
	}

	public String getNomorRekening(){
		return nomorRekening;
	}

	public void setIdToko(int idToko){
		this.idToko = idToko;
	}

	public int getIdToko(){
		return idToko;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
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
			"BankItem{" + 
			"updated_at = '" + updatedAt + '\'' + 
			",nomor_rekening = '" + nomorRekening + '\'' + 
			",id_toko = '" + idToko + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",nama_bank = '" + namaBank + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",atas_nama = '" + atasNama + '\'' + 
			"}";
		}
}