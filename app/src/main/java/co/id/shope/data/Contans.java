package co.id.shope.data;

public class Contans {
    public static final String WEB_URL = "http://aplikasimandiri.com/";
//    public static final String WEB_URL = "http://192.168.1.10/shope/public/";
    public static final String WEB_URL_API = WEB_URL + "api/";
    public static final String WEB_URL_STORAGE = WEB_URL + "storage/";
    public static final String RESET_PASS = WEB_URL + "/password/reset/";
    //    public static final String WEB_URL_API = "http://192.168.1.4/khsonline/public/api/";
    public static final String PRODUK = WEB_URL_API + "backend/produk";
    public static final String KATEGORI = WEB_URL_API + "backend/master/kategori";
    public static final String SLIDER = WEB_URL_API + "backend/master/slider";
    public static final String LOGIN = WEB_URL_API + "login";
    public static final String REGISTER_MEMBER = WEB_URL_API + "register/user";
    public static final String MEMBER = WEB_URL_API + "backend/member";
    public static final String TOKO = WEB_URL_API + "backend/toko";

    public static final String ALAMAT = WEB_URL_API + "backend/alamat";
    public static final String SHIP = WEB_URL_API + "backend/pengiriman/ongkir";
    public static final String BANK = WEB_URL_API + "backend/master/bank";
    public static final String NEWS = WEB_URL_API + "backend/master/news";

    public static final String CHAT_ROOM = WEB_URL_API + "backend/chat/room";
    public static final String CHAT_USER = WEB_URL_API + "backend/chat/send-to-user";
    public static final String CHAT_TOKO = WEB_URL_API + "backend/chat/send-to-toko";

    public static final String TOKEN = WEB_URL_API + "tokenize";

    public static final String ORDER = WEB_URL_API + "backend/transaksi/order";
    public static final String ORDER_PARAM = "id_toko";
    public static final String ORDER_RELATION = "detail.produk,address,user,shipping,payment";

    public static final String KONFIRMASI = WEB_URL_API + "backend/transaksi/konfirmasi";
    public static final String KONFIRMASI_RELATION = "user,order";

    public static final String TOPUP = WEB_URL_API + "backend/transaksi/topup";
    public static final String TOPUP_RELATION = "user";


}
