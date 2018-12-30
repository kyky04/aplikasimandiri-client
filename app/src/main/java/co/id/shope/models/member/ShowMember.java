package co.id.shope.models.member;

import com.google.gson.annotations.SerializedName;

import co.id.shope.models.DataItemMember;

public class ShowMember{

	@SerializedName("data")
	private DataItemMember data;

	@SerializedName("status")
	private boolean status;

	public void setData(DataItemMember data){
		this.data = data;
	}

	public DataItemMember getData(){
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
			"ShowMember{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}