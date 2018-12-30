package co.id.shope.models;

import java.util.List;

public class SliderResponse{
	private List<DataItemSlider> data;
	private boolean status;

	public void setData(List<DataItemSlider> data){
		this.data = data;
	}

	public List<DataItemSlider> getData(){
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
			"SliderResponse{" + 
			"data = '" + data + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}