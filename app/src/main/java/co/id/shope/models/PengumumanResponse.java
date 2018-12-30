package co.id.shope.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PengumumanResponse{

	@SerializedName("data")
	private List<DataItemPengumuman> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemPengumuman> data){
		this.data = data;
	}

	public List<DataItemPengumuman> getData(){
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
			"PengumumanResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}