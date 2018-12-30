package co.id.shope.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ProdukResponse{

	@SerializedName("data")
	private List<DataItemProduk> data;

	@SerializedName("status")
	private boolean status;

	public void setData(List<DataItemProduk> data){
		this.data = data;
	}

	public List<DataItemProduk> getData(){
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
			"ProdukResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}