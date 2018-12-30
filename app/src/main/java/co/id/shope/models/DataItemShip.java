package co.id.shope.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class DataItemShip {

	@SerializedName("costs")
	private List<CostsItem> costs;

	@SerializedName("code")
	private String code;

	@SerializedName("name")
	private String name;

	public void setCosts(List<CostsItem> costs){
		this.costs = costs;
	}

	public List<CostsItem> getCosts(){
		return costs;
	}

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	@Override
 	public String toString(){
		return 
			"DataItemKota{" +
			"costs = '" + costs + '\'' + 
			",code = '" + code + '\'' + 
			",name = '" + name + '\'' + 
			"}";
		}
}