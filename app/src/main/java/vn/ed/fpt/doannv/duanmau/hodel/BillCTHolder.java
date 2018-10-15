package vn.ed.fpt.doannv.duanmau.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vn.ed.fpt.doannv.duanmau.R;

public class BillCTHolder extends RecyclerView.ViewHolder {
    public TextView tvinfoct;
    public Button btndeletect;


    public BillCTHolder(View itemView) {
        super( itemView );
        tvinfoct = itemView.findViewById( R.id.tvinfoct );
        btndeletect = itemView.findViewById( R.id.btndeletect );
    }
}
