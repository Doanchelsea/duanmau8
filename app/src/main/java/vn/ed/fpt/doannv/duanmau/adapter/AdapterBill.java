package vn.ed.fpt.doannv.duanmau.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Date;
import java.util.List;

import vn.ed.fpt.doannv.duanmau.R;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.Bill;
import vn.ed.fpt.doannv.duanmau.hodel.BillHolder;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BillDAO;

public class AdapterBill extends RecyclerView.Adapter<BillHolder> {
    private Context context;
    private List<Bill> bills;
    private BillDAO billDAO;

    public AdapterBill(Context context, List<Bill> bills, BillDAO billDAO) {
        this.context = context;
        this.bills = bills;
        this.billDAO = billDAO;
    }

    @NonNull
    @Override
    public BillHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_bill,parent,false );
        return new BillHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull BillHolder holder, final int position) {
           final Bill bill = bills.get( position );
           String date = new Date( bill.date ).toString();
           holder.tvinfo.setText( bill.id + "\n"  +date );

           holder.btndelete.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {
         BillDAO billDAO = new BillDAO( new DatabaseHelper( context ) );
         billDAO.deleteBill( bill.id );
               bills.remove( position );
               notifyDataSetChanged();
               }
           } );
           holder.cvBill.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View v) {

               }
           } );

    }

    @Override
    public int getItemCount() {
        return bills.size();
    }
}
