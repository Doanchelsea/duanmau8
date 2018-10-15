package vn.ed.fpt.doannv.duanmau.hodel;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.ed.fpt.doannv.duanmau.R;

public class BillHolder extends RecyclerView.ViewHolder {
    public TextView tvinfo;
    public Button btndelete;
    public CardView cvBill;

    public BillHolder(View itemView) {
        super( itemView );

        cvBill = itemView.findViewById( R.id.cvBill );
        tvinfo = itemView.findViewById( R.id.tvinfo );
        btndelete = itemView.findViewById( R.id.btndelete );
    }
}
