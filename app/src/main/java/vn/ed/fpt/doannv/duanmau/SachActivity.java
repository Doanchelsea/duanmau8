package vn.ed.fpt.doannv.duanmau;

import android.app.Dialog;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import vn.ed.fpt.doannv.duanmau.adapter.AdapterBook;
import vn.ed.fpt.doannv.duanmau.adapter.AdapterUser;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.Book;
import vn.ed.fpt.doannv.duanmau.database.model.User;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BookDAO;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.UserDAO;

public class SachActivity extends AppCompatActivity {
    private FloatingActionButton faSach;
    private RecyclerView lvSach;

    private AdapterBook adapterBook;
    private List<Book> typeBooks;
    private DatabaseHelper databaseHelper;
    private BookDAO typeBookDAO;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_sach );
        faSach = findViewById(R.id.faSach);


        faSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SachActivity.this);
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.diglogsach);
                final EditText edbookId;
                final EditText edbookName;
                final EditText edbookTheLoai;
                Button btThemSach;
                Button btHuySach;

                edbookId = dialog.findViewById( R.id.edbookID );
                edbookName = dialog.findViewById( R.id.edBookName );
                edbookTheLoai = dialog.findViewById( R.id.edBookTheLoai );
                btThemSach = dialog.findViewById( R.id.btnThemSach );
                btHuySach = dialog.findViewById( R.id.btnHuySach );
                btThemSach.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.e( "btThemNguoiDung", "btThemNguoiDung" );
                        Book book = typeBookDAO.getBookByID(edbookId.getText().toString().trim() );

                        if (book == null){
                            book = new Book();
                            String bookid = edbookId.getText().toString().trim();
                            String bookname = edbookName.getText().toString().trim();
                            String booktheloai = edbookTheLoai.getText().toString().trim();

                            book.bookid = bookid;
                            book.bookname = bookname;
                            book.booktheloai = booktheloai;


                            long result = typeBookDAO.insertBook( book );

                            if (result > 0){
                                typeBooks.add( 0, book );

                                adapterBook.notifyDataSetChanged();
                                dialog.cancel();
                            }else {
                                Toast.makeText( SachActivity.this, "Thong Bao Co Loi", Toast.LENGTH_SHORT ).show();
                            }

                        }else {
                            Toast.makeText( SachActivity.this, "ID giong nhau", Toast.LENGTH_SHORT ).show();
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
        });
        databaseHelper = new DatabaseHelper(this);
        typeBookDAO = new BookDAO(databaseHelper);

        typeBooks = typeBookDAO.getAllBook();

        lvSach = findViewById(R.id.lvSach);

        adapterBook = new AdapterBook(this, typeBooks, typeBookDAO);

        lvSach.setAdapter(adapterBook);
        linearLayoutManager = new LinearLayoutManager(this);
        lvSach.setLayoutManager(linearLayoutManager);

    }
    }

