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
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import vn.ed.fpt.doannv.duanmau.R;
import vn.ed.fpt.doannv.duanmau.database.model.User;
import vn.ed.fpt.doannv.duanmau.hodel.UserHoler;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.UserDAO;

public class AdapterUser extends RecyclerView.Adapter<UserHoler> {
    private Context context;
    private List<User> users;
    private UserDAO userDAO;

    public AdapterUser(Context context, List<User> users, UserDAO userDAO) {
        this.context = context;
        this.users = users;
        this.userDAO = userDAO;
    }

    @NonNull
    @Override
    public UserHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from( context ).inflate( R.layout.item_nguoidung,parent,false );
        return new UserHoler( view );
    }

    @Override
    public void onBindViewHolder(@NonNull UserHoler holder, final int position) {
     final User user = users.get( position );
     holder.tvId.setText( user.id );
      holder.tvTen.setText( user.name );
      holder.tvSdt.setText( user.phone );
      holder.imgxoa.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             long result = userDAO.deleteUser( user.id );
             if (result >0){
           users.remove( position );
           notifyDataSetChanged();
             }else{
                 //khong thanh cong
                 Toast.makeText( context, "Xóa Không Thành Công", Toast.LENGTH_SHORT ).show();
             }
          }
      } );
      holder.imgsua.setOnClickListener( new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              final Dialog dialog = new Dialog( context );
              dialog.setContentView( R.layout.activity_dig_log_nguoi_dung );
              final EditText edId;
              final EditText edTen;
              final EditText edsdt;
              Button btThem;
              Button btHuy;

              edId = dialog.findViewById( R.id.edId );
              edId.setVisibility( View.GONE );
              edTen = dialog.findViewById( R.id.edTen );
              edsdt = dialog.findViewById( R.id.edSdt );
              btThem = dialog.findViewById( R.id.btnThemNguoiDung );
              btHuy = dialog.findViewById( R.id.btnHuyNguoiDung );
              btThem.setText( "Sửa" );

              edsdt.setText( user.phone );
              edTen.setText( user.name );
              btThem.setOnClickListener( new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      User user1 = new User();
                      String name = edTen.getText().toString().trim();
                      String phone = edsdt.getText().toString().trim();

                      user1.id = user.id;
                      user1.name = name;
                      user1.phone = phone;

                      long result = userDAO.updateUser( user );
                      if (result > 0) {
                          users.get( position ).id = user.id;
                          users.get( position ).name = name;
                          users.get( position ).phone = phone;
                          notifyDataSetChanged();
                          dialog.cancel();
                      } else {
                          // update ko thanh cong
                      }
                  }
              } );
              btHuy.setOnClickListener( new View.OnClickListener() {
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
        return users.size();
    }
}
