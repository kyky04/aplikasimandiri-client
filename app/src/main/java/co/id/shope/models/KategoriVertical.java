package co.id.shope.models;

import java.util.List;

public class KategoriVertical {

    private String title;
    private List<DataItemProduk> arrayList;

    public KategoriVertical(String title, List<DataItemProduk> arrayList) {
        this.title = title;
        this.arrayList = arrayList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DataItemProduk> getArrayList() {
        return arrayList;
    }

    public void setArrayList(List<DataItemProduk> arrayList) {
        this.arrayList = arrayList;
    }
}