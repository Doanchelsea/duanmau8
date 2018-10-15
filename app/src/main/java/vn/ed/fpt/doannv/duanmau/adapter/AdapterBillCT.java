package vn.ed.fpt.doannv.duanmau.adapter;

import android.content.Context;
import android.content.Loader;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import vn.ed.fpt.doannv.duanmau.R;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.Bill;
import vn.ed.fpt.doannv.duanmau.database.model.BillCT;
import vn.ed.fpt.doannv.duanmau.hodel.BillCTHolder;
import vn.ed.fpt.doannv.duanmau.hodel.BillHolder;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BillCTDAO;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BillDAO;

public class AdapterBillCT extends RecyclerView.Adapter<BillCTHolder> {
    private Context context;
    private List<BillCT> billCTS;
    private BillCTDAO billCTDAO;

    public AdapterBillCT(Context context, List<BillCT> billCTS, BillCTDAO billCTDAO) {
        this.context = context;
        this.billCTS = billCTS;
        this.billCTDAO = billCTDAO;
    }

    @NonNull
    @Override
    public BillCTHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_bill_ct,parent,false );
        return new BillCTHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull BillCTHolder holder, final int position) {
        final BillCT billCT = billCTS.get( position );
        String date = new Date( billCT.datect ).toString();

        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String tong = fmt.format( billCT.soluong * billCT.gia );


        holder.tvinfoct.setText( "Mã Hóa Đơn: "+ billCT.idct
                                 + "\n"  +"Ngày Mua Hàng: "+date
                                 + "\n"  +"Tên Sách: "+billCT.tenct
                                 + "\n"  +"Số Lượng: "+billCT.soluong
                                 + "\n"  + "Giá Tiền: "+billCT.gia
                                 + "\n"  +"Thanh Toán: "+(tong));

        holder.btndeletect.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BillCTDAO billCTDAO = new BillCTDAO( new DatabaseHelper( context ) );
                billCTDAO.deleteBillCT( billCT.idct );
                billCTS.remove( position );
                notifyDataSetChanged();
            }
        } );

    }

    @Override
    public int getItemCount() {
        return billCTS.size();
    }
}
