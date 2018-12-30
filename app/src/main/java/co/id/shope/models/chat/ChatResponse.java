package co.id.shope.models.chat;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChatResponse{

	@SerializedName("data")
	private List<DataItemChat> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemChat> data){
		this.data = data;
	}

	public List<DataItemChat> getData(){
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
			"ChatResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}