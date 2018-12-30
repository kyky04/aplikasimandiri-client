package co.id.shope.models;

public class ItemProduct {
    String url;
    String harga;
    String namaProduct;

    public ItemProduct(String url, String harga, String namaProduct) {
        this.url = url;
        this.harga = harga;
        this.namaProduct = namaProduct;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getNamaProduct() {
        return namaProduct;
    }

    public void setNamaProduct(String namaProduct) {
        this.namaProduct = namaProduct;
    }
}
