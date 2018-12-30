package co.id.shope.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataUser {

	@SerializedName("id_toko")
	private int idToko;

	@SerializedName("created_at")
	private Object createdAt;

	@SerializedName("admin")
	private boolean admin;

	@SerializedName("toko")
	private Toko toko;

	@SerializedName("saldo")
	private String saldo;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("nomor_telepon")
	private String nomorTelepon;

	@SerializedName("name")
	private String name;

	@SerializedName("updated_by")
	private Object updatedBy;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("status")
	private int status;

	@SerializedName("order")
	private List<OrderItem> order;

	public void setIdToko(int idToko){
		this.idToko = idToko;
	}

	public int getIdToko(){
		return idToko;
	}

	public void setCreatedAt(Object createdAt){
		this.createdAt = createdAt;
	}

	public Object getCreatedAt(){
		return createdAt;
	}

	public void setAdmin(boolean admin){
		this.admin = admin;
	}

	public boolean isAdmin(){
		return admin;
	}

	public void setToko(Toko toko){
		this.toko = toko;
	}

	public Toko getToko(){
		return toko;
	}

	public void setSaldo(String saldo){
		this.saldo = saldo;
	}

	public String getSaldo(){
		return saldo;
	}

	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}

	public Object getCreatedBy(){
		return createdBy;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setNomorTelepon(String nomorTelepon){
		this.nomorTelepon = nomorTelepon;
	}

	public String getNomorTelepon(){
		return nomorTelepon;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setUpdatedBy(Object updatedBy){
		this.updatedBy = updatedBy;
	}

	public Object getUpdatedBy(){
		return updatedBy;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	public void setOrder(List<OrderItem> order){
		this.order = order;
	}

	public List<OrderItem> getOrder(){
		return order;
	}

	@Override
 	public String toString(){
		return 
			"DataUser{" +
			"id_toko = '" + idToko + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",admin = '" + admin + '\'' + 
			",toko = '" + toko + '\'' + 
			",saldo = '" + saldo + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",nomor_telepon = '" + nomorTelepon + '\'' + 
			",name = '" + name + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",status = '" + status + '\'' + 
			",order = '" + order + '\'' + 
			"}";
		}
}