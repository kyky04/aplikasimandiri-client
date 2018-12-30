package co.id.shope.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class CostsItem{

	@SerializedName("cost")
	private List<CostItem> cost;

	@SerializedName("service")
	private String service;

	@SerializedName("description")
	private String description;

	private boolean selected;

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setCost(List<CostItem> cost){
		this.cost = cost;
	}

	public List<CostItem> getCost(){
		return cost;
	}

	public void setService(String service){
		this.service = service;
	}

	public String getService(){
		return service;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	@Override
 	public String toString(){
		return 
			"CostsItem{" + 
			"cost = '" + cost + '\'' + 
			",service = '" + service + '\'' + 
			",description = '" + description + '\'' + 
			"}";
		}
}