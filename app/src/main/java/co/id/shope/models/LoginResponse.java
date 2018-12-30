package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("data")
	private DataUser data;

	@SerializedName("status")
	private boolean status;

	@SerializedName("message")
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(DataUser data){
		this.data = data;
	}

	public DataUser getData(){
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
			"LoginResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}