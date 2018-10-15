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
import vn.ed.fpt.doannv.duanmau.database.model.User;
import vn.ed.fpt.doannv.duanmau.hodel.BookHolder;
import vn.ed.fpt.doannv.duanmau.hodel.UserHoler;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BookDAO;

public class AdapterBook extends RecyclerView.Adapter<BookHolder> {
    private Context context;
    private List<Book> books;
    private BookDAO bookDAO;

    public AdapterBook(Context context, List<Book> books, BookDAO bookDAO) {
        this.context = context;
        this.books = books;
        this.bookDAO = bookDAO;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_sach,parent,false );
        return new BookHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, final int position) {
        final Book book = books.get( position );
        holder.tvBookId.setText( book.bookid );
        holder.tvBookName.setText( book.bookname );
        holder.tvBookTheloai.setText( book.booktheloai );
        holder.imgDeleteBook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long result = bookDAO.deleteBook( book.bookid );
                if (result >0){
                    books.remove( position );
                    notifyDataSetChanged();
                }else{
                    //khong thanh cong
                    Toast.makeText( context, "Xóa Không Thành Công", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        holder.imgUpdateBook.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog( context );
                dialog.setContentView( R.layout.diglogsach );
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

                edBookTheLoai.setText( book.booktheloai );
                edBookName.setText( book.bookname );
                btThemSach.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Book book1 = new Book();
                        String bookname = edBookName.getText().toString().trim();
                        String booktheloai = edBookTheLoai.getText().toString().trim();

                        book1.bookid = book.bookid;
                        book1.bookname = bookname;
                        book1.booktheloai = booktheloai;

                        long result = bookDAO.updateBook( book );
                        if (result > 0) {
                            books.get( position ).bookid = book.bookid;
                            books.get( position ).bookname = bookname;
                            books.get( position ).booktheloai = booktheloai;
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
