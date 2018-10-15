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

import vn.ed.fpt.doannv.duanmau.adapter.AdapterBook;
import vn.ed.fpt.doannv.duanmau.adapter.AdapterTheLoai;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.Book;
import vn.ed.fpt.doannv.duanmau.database.model.TheLoai;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BookDAO;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.TheLoaiDAO;

public class TheLoaiSachActivity extends AppCompatActivity {

    private FloatingActionButton faTheLoai;
    private RecyclerView lvTheLoai;

    private AdapterTheLoai adapterBook;
    private List<TheLoai> typeBooks;
    private DatabaseHelper databaseHelper;
    private TheLoaiDAO typeBookDAO;
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_the_loai_sach );
        faTheLoai = findViewById(R.id.faTheLoai);


        faTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TheLoaiSachActivity.this);
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dig_log_the_loai);
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
                        TheLoai book = typeBookDAO.getTheLoaiByID(edbookId.getText().toString().trim() );

                        if (book == null){
                            book = new TheLoai();
                            String bookid = edbookId.getText().toString().trim();
                            String bookname = edbookName.getText().toString().trim();
                            String booktheloai = edbookTheLoai.getText().toString().trim();

                            book.theloaiid = bookid;
                            book.theloai = bookname;
                            book.soluong = booktheloai;


                            long result = typeBookDAO.insertTheLoai( book );

                            if (result > 0){
                                typeBooks.add( 0, book );

                                adapterBook.notifyDataSetChanged();
                                dialog.cancel();
                            }else {
                                Toast.makeText( TheLoaiSachActivity.this, "Thong Bao Co Loi", Toast.LENGTH_SHORT ).show();
                            }

                        }else {
                            Toast.makeText( TheLoaiSachActivity.this, "ID giong nhau", Toast.LENGTH_SHORT ).show();
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
        typeBookDAO = new TheLoaiDAO(databaseHelper);

        typeBooks = typeBookDAO.getAllTheLoai();

        lvTheLoai = findViewById(R.id.lvTheLoai);

        adapterBook = new AdapterTheLoai(this,typeBooks,typeBookDAO);

        lvTheLoai.setAdapter(adapterBook);
        linearLayoutManager = new LinearLayoutManager(this);
        lvTheLoai.setLayoutManager(linearLayoutManager);

    }
}

