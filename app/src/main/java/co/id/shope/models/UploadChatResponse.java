package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import co.id.shope.models.chat.DataItemChat;

public class UploadChatResponse {

	@SerializedName("data")
	private DataItemChat data;

	@SerializedName("status")
	private boolean status;

	public void setData(DataItemChat data){
		this.data = data;
	}

	public DataItemChat getData(){
		return data;
	}

	public void setStatus(boolean status){
		this.status = status;
	}

	public boolean isStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"UploadKategoriResponse{" +
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}