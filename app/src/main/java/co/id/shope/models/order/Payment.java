package co.id.shope.models.order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Payment implements Serializable {

	@SerializedName("id_order")
	private int idOrder;

	@SerializedName("deleted")
	private int deleted;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("jenis")
	private String jenis;

	@SerializedName("updated_by")
	private int updatedBy;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("nomor_akun")
	private String nomorAkun;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("akun")
	private String akun;

	public void setIdOrder(int idOrder){
		this.idOrder = idOrder;
	}

	public int getIdOrder(){
		return idOrder;
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

	public void setJenis(String jenis){
		this.jenis = jenis;
	}

	public String getJenis(){
		return jenis;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNomorAkun(String nomorAkun){
		this.nomorAkun = nomorAkun;
	}

	public String getNomorAkun(){
		return nomorAkun;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setAkun(String akun){
		this.akun = akun;
	}

	public String getAkun(){
		return akun;
	}

	@Override
 	public String toString(){
		return 
			"Payment{" + 
			"id_order = '" + idOrder + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",nomor_akun = '" + nomorAkun + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",akun = '" + akun + '\'' + 
			"}";
		}
}