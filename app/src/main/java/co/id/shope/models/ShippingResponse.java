package co.id.shope.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class ShippingResponse{

	@SerializedName("data")
	private List<DataItemShip> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemShip> data){
		this.data = data;
	}

	public List<DataItemShip> getData(){
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
			"ShippingResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}