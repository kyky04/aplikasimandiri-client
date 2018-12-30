package co.id.shope.models.chat;

import com.google.gson.annotations.SerializedName;

public class DataItemChat {

	@SerializedName("id_chat")
	private int idChat;

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("deleted")
	private int deleted;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("updated_by")
	private Object updatedBy;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("id_user")
	private int idUser;

	@SerializedName("created_by")
	private Object createdBy;

	@SerializedName("user")
	private User user;

	public void setIdChat(int idChat){
		this.idChat = idChat;
	}

	public int getIdChat(){
		return idChat;
	}

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
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

	public void setIdUser(int idUser){
		this.idUser = idUser;
	}

	public int getIdUser(){
		return idUser;
	}

	public void setCreatedBy(Object createdBy){
		this.createdBy = createdBy;
	}

	public Object getCreatedBy(){
		return createdBy;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"DataItemChat{" +
			"id_chat = '" + idChat + '\'' + 
			",pesan = '" + pesan + '\'' + 
			",deleted = '" + deleted + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",updated_by = '" + updatedBy + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",id_user = '" + idUser + '\'' + 
			",created_by = '" + createdBy + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}