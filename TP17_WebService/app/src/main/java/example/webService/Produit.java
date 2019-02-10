package example.webService;

public class Produit {
    private String des,ref;

    public Produit(String des, String ref) {
        this.des = des;
        this.ref = ref;
    }

    public String getDes() {
        return des;
    }

    public String getRef() {
        return ref;
    }
}
