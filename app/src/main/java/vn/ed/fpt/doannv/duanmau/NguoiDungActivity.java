package vn.ed.fpt.doannv.duanmau;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import vn.ed.fpt.doannv.duanmau.adapter.AdapterUser;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.User;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.UserDAO;

public class NguoiDungActivity extends AppCompatActivity {

    private FloatingActionButton faNguoiDung;
    private RecyclerView lvListUser;

    private AdapterUser adapterUser;
    private List<User> typeUsers;
    private DatabaseHelper databaseHelper;
    private UserDAO typeUserDAO;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nguoi_dung);
        faNguoiDung = findViewById(R.id.faNguoiDung);


        faNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(NguoiDungActivity.this);
                dialog.requestWindowFeature( Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.activity_dig_log_nguoi_dung);
               final EditText edId;
                final EditText edTen;
                final EditText edsdt;
                Button btThemNguoiDung;
                Button btHuyNguoiDung;

                edId = dialog.findViewById( R.id.edId );
                edsdt = dialog.findViewById( R.id.edSdt );
                edTen = dialog.findViewById( R.id.edTen );
                btThemNguoiDung = dialog.findViewById( R.id.btnThemNguoiDung );
                btHuyNguoiDung = dialog.findViewById( R.id.btnHuyNguoiDung );
             btThemNguoiDung.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e( "btThemNguoiDung", "btThemNguoiDung" );
                    User user = typeUserDAO.getUserByID(edId.getText().toString().trim() );

                    if (user == null){
                      user = new User();
                      String id = edId.getText().toString().trim();
                        String name = edTen.getText().toString().trim();
                        String phone = edsdt.getText().toString().trim();

                        user.id = id;
                        user.name = name;
                        user.phone = phone;


                        long result = typeUserDAO.insertUser( user );

                        if (result > 0){
                            typeUsers.add( 0, user );

                            adapterUser.notifyDataSetChanged();
                            dialog.cancel();
                        }else {
                            Toast.makeText( NguoiDungActivity.this, "Thong Bao Co Loi", Toast.LENGTH_SHORT ).show();
                        }

                    }else {
                        Toast.makeText( NguoiDungActivity.this, "ID giong nhau", Toast.LENGTH_SHORT ).show();
                    }

                }
            } );
                btHuyNguoiDung.setOnClickListener( new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                } );
                dialog.show();
            }
        });
        databaseHelper = new DatabaseHelper(this);
        typeUserDAO = new UserDAO(databaseHelper);

        typeUsers = typeUserDAO.getAllUser();
        lvListUser = findViewById(R.id.lvNguoiDung);

        adapterUser = new AdapterUser(this, typeUsers, typeUserDAO);

        lvListUser.setAdapter(adapterUser);
        linearLayoutManager = new LinearLayoutManager(this);
        lvListUser.setLayoutManager(linearLayoutManager);

    }

}
