package vn.ed.fpt.doannv.duanmau.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.ed.fpt.doannv.duanmau.R;

public class TheLoaiHolder extends RecyclerView.ViewHolder {
    public TextView tvTheLoaiId;
    public TextView tvTheLoai;
    public TextView tvSoLuong;
    public ImageView imgUpdateTheLoai;
    public ImageView imgDeleteTheLoai;
    public TheLoaiHolder(View itemView) {
        super( itemView );
        tvTheLoaiId =  itemView.findViewById( R.id.tvTheLoaiId);
        tvTheLoai =  itemView.findViewById(R.id.tvTheLoai);
        tvSoLuong = itemView.findViewById(R.id.tvSoLuong);
        imgUpdateTheLoai =  itemView.findViewById(R.id.imgUpdateTheLoai);
        imgDeleteTheLoai = itemView.findViewById(R.id.imgDeleteTheLoai);
    }
}

