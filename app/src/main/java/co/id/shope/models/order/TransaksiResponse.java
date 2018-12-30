package co.id.shope.models.order;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class TransaksiResponse implements Serializable {

	@SerializedName("data")
	private List<DataItemOrder> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemOrder> data){
		this.data = data;
	}

	public List<DataItemOrder> getData(){
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
			"TransaksiResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}