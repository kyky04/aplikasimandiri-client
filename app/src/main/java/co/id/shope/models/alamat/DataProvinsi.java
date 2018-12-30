package co.id.shope.models.alamat;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataProvinsi{

	@SerializedName("data")
	private List<DataItemProvinsi> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemProvinsi> data){
		this.data = data;
	}

	public List<DataItemProvinsi> getData(){
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
			"DataProvinsi{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}