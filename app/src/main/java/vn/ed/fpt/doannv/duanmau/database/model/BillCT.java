package vn.ed.fpt.doannv.duanmau.database.model;

public class BillCT {
    public String idct;
    public long datect;
    public String tenct;
    public int soluong;
    public int gia;

    public BillCT(String idct, long datect, String tenct, int soluong, int gia) {
        this.idct = idct;
        this.datect = datect;
        this.tenct = tenct;
        this.soluong = soluong;
        this.gia = gia;
    }
}
