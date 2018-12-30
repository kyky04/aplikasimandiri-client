package co.id.shope.models.toko;

import com.google.gson.annotations.SerializedName;

public class ShowToko {

	@SerializedName("data")
	private DataItemToko data;

	@SerializedName("status")
	private boolean status;

	public void setData(DataItemToko data){
		this.data = data;
	}

	public DataItemToko getData(){
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
			"ShowToko{" +
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}