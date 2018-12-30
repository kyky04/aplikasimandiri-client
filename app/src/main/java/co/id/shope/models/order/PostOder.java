package co.id.shope.models.order;

import com.google.gson.annotations.SerializedName;

public class PostOder{

	@SerializedName("data")
	private DataItemOrder data;

	@SerializedName("status")
	private boolean status;

	public void setData(DataItemOrder data){
		this.data = data;
	}

	public DataItemOrder getData(){
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
			"PostOder{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}