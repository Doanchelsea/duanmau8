package vn.ed.fpt.doannv.duanmau.hodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import vn.ed.fpt.doannv.duanmau.R;

public class BookHolder extends RecyclerView.ViewHolder{

    public TextView tvBookId;
    public TextView tvBookName;
    public TextView tvBookTheloai;
    public ImageView imgUpdateBook;
    public ImageView imgDeleteBook;


    public BookHolder(View itemView) {
        super( itemView );
        tvBookId = itemView.findViewById( R.id.tvBookId);
        tvBookName = itemView.findViewById(R.id.tvBookName);
        tvBookTheloai = itemView.findViewById(R.id.tvBookTheloai);
        imgUpdateBook = itemView.findViewById(R.id.imgUpdateBook);
        imgDeleteBook = itemView.findViewById(R.id.imgDeleteBook);


    }
}
