package co.id.shope.models.order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Shipping implements Serializable {

	@SerializedName("id_order")
	private int idOrder;

	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("ongkos_kirim")
	private String ongkosKirim;

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

	@SerializedName("kurir")
	private String kurir;

	@SerializedName("created_by")
	private int createdBy;

	@SerializedName("nomor_resi")
	private Object nomorResi;

	public void setIdOrder(int idOrder){
		this.idOrder = idOrder;
	}

	public int getIdOrder(){
		return idOrder;
	}

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setOngkosKirim(String ongkosKirim){
		this.ongkosKirim = ongkosKirim;
	}

	public String getOngkosKirim(){
		return ongkosKirim;
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

	public void setKurir(String kurir){
		this.kurir = kurir;
	}

	public String getKurir(){
		return kurir;
	}

	public void setCreatedBy(int createdBy){
		this.createdBy = createdBy;
	}

	public int getCreatedBy(){
		return createdBy;
	}

	public void setNomorResi(Object nomorResi){
		this.nomorResi = nomorResi;
	}

	public Object getNomorResi(){
		return nomorResi;
	}

	@Override
 	public String toString(){
		return 
			"Shipping{" + 
			"id_order = '" + idOrder + '\'' + 
			",keterangan = '" + keterangan + '\'' + 
			",ongkos_kirim = '" + ongkosKirim + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",jenis = '" + jenis + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",kurir = '" + kurir + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",nomor_resi = '" + nomorResi + '\'' + 
			"}";
		}
}