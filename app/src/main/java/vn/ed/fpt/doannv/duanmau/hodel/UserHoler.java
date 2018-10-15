package vn.ed.fpt.doannv.duanmau.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.ed.fpt.doannv.duanmau.R;

public class UserHoler extends RecyclerView.ViewHolder{
    public ImageView imgavatar;
    public TextView tvId;
    public TextView tvTen;
    public TextView tvSdt;
    public ImageView imgsua;
    public ImageView imgxoa;



    public UserHoler(View itemView) {
        super( itemView );

        imgavatar = itemView.findViewById( R.id.imgavatar);
        tvTen = itemView.findViewById(R.id.tvTen);
        tvId = itemView.findViewById(R.id.tvId);
        tvSdt = itemView.findViewById(R.id.tvSdt);
        imgsua = itemView.findViewById(R.id.imgsua);
        imgxoa = itemView.findViewById(R.id.imgxoa);
    }
}
