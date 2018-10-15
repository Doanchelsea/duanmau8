package vn.ed.fpt.doannv.duanmau.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import vn.ed.fpt.doannv.duanmau.R;
import vn.ed.fpt.doannv.duanmau.database.model.Book;
import vn.ed.fpt.doannv.duanmau.database.model.TheLoai;
import vn.ed.fpt.doannv.duanmau.hodel.BookHolder;
import vn.ed.fpt.doannv.duanmau.hodel.TheLoaiHolder;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BookDAO;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.TheLoaiDAO;

public class AdapterTheLoai extends RecyclerView.Adapter<TheLoaiHolder> {
    private Context context;
    private List<TheLoai> books;

    public AdapterTheLoai(Context context, List<TheLoai> books, TheLoaiDAO bookDAO) {
        this.context = context;
        this.books = books;
        this.bookDAO = bookDAO;
    }

    private TheLoaiDAO bookDAO;
    @NonNull
    @Override
    public TheLoaiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_the_loai,parent,false );
        return new TheLoaiHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiHolder holder, final int position) {
        final TheLoai book = books.get( position );
        holder.tvTheLoaiId.setText( book.theloaiid );
        holder.tvTheLoai.setText( book.theloai );
        holder.tvSoLuong.setText( book.soluong );
        holder.imgDeleteTheLoai.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = bookDAO.deleteTheLoai( book.theloaiid );
                if (result >0){
                    books.remove( position );
                    notifyDataSetChanged();
                }else{
                    //khong thanh cong
                    Toast.makeText( context, "Xóa Không Thành Công", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        holder.imgUpdateTheLoai.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog( context );
                dialog.setContentView( R.layout.dig_log_the_loai );
                final EditText edBookID;
                final EditText edBookName;
                final EditText edBookTheLoai;
                Button btThemSach;
                Button btHuySach;

                edBookID = dialog.findViewById( R.id.edbookID );
                edBookID.setVisibility( View.GONE );
                edBookName = dialog.findViewById( R.id.edBookName );
                edBookTheLoai = dialog.findViewById( R.id.edBookTheLoai );
                btThemSach = dialog.findViewById( R.id.btnThemSach );
                btHuySach = dialog.findViewById( R.id.btnHuySach );
                btThemSach.setText( "Sửa" );

                edBookTheLoai.setText( book.soluong );
                edBookName.setText( book.theloai );
                btThemSach.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TheLoai book1 = new TheLoai();
                        String bookname = edBookName.getText().toString().trim();
                        String booktheloai = edBookTheLoai.getText().toString().trim();

                        book1.theloaiid = book.theloaiid;
                        book1.theloai = bookname;
                        book1.soluong = booktheloai;

                        long result = bookDAO.updateTheLoai( book );
                        if (result > 0) {
                            books.get( position ).theloaiid = book.theloaiid;
                            books.get( position ).theloai = bookname;
                            books.get( position ).soluong = booktheloai;
                            notifyDataSetChanged();
                            dialog.cancel();
                        } else {
                            // update ko thanh cong
                        }
                    }
                } );
                btHuySach.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                } );
                dialog.show();
            }

        } );
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
