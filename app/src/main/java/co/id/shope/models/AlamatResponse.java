package co.id.shope.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class AlamatResponse{

	@SerializedName("data")
	private List<DataItemAlamat> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemAlamat> data){
		this.data = data;
	}

	public List<DataItemAlamat> getData(){
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
			"AlamatResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}