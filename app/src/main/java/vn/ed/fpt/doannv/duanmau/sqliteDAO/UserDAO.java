package vn.ed.fpt.doannv.duanmau.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import vn.ed.fpt.doannv.duanmau.database.Constant;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.common.Constants;
import vn.ed.fpt.doannv.duanmau.database.model.User;

public class UserDAO implements Constant {
    private DatabaseHelper databaseHelper;
    public UserDAO(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public long insertUser(User user){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( TB_COLUMN_ID, user.id );
        contentValues.put( TB_COLUMN_NAME, user.name );
        contentValues.put( TB_COLUMN_PHONE, user.phone );

         long result = sqLiteDatabase.insert( TABLE_USER,null  , contentValues );
         if(Constants.isDEBUG) Log.e( "insertUser", "insertUser : "+result );

         sqLiteDatabase.close();
         return result;
    }

    public long deleteUser(String typeID){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete( TABLE_USER, TB_COLUMN_ID + "=?", new String[]{typeID} );

        sqLiteDatabase.close();
        return result;
    }
    public long updateUser(User user){
       // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( TB_COLUMN_ID, user.id );
        contentValues.put( TB_COLUMN_NAME, user.name );
        contentValues.put( TB_COLUMN_PHONE, user.phone );
        long result = sqLiteDatabase.update( TABLE_USER,contentValues,
                TB_COLUMN_ID + "=?", new String[]{user.id} );
               sqLiteDatabase.close();
                    return result;
    }
      public List<User> getAllUser(){
                List<User> users = new ArrayList<>(  );

          // xin ban quyen
          SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

           // viet cau lenh select all
          String SELECT_ALL_USER = "SELECT * FROM " + TABLE_USER;
          Cursor cursor = sqLiteDatabase.rawQuery( SELECT_ALL_USER, null );

          if (cursor.moveToFirst()){
              do {
                  String id = cursor.getString( cursor.getColumnIndex( TB_COLUMN_ID ) );
                  String name = cursor.getString( cursor.getColumnIndex( TB_COLUMN_NAME ) );
                  String phone = cursor.getString( cursor.getColumnIndex( TB_COLUMN_PHONE ) );
                  User user = new User();

                  user.id = id;
                  user.name = name;
                  user.phone = phone;

                  users.add( user );
              }while (cursor.moveToNext());
          }
          sqLiteDatabase.close();
          return users;
      }
      public User getUserByID(String typeID){
        User user = null;
          // xin ban quyen
          SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

          Cursor cursor = sqLiteDatabase.query(TABLE_USER,
                  new String[]{TB_COLUMN_ID, TB_COLUMN_NAME, TB_COLUMN_PHONE},
                  TB_COLUMN_ID + "=?", new String[]{typeID},
                  null, null, null);



          if (cursor !=null && cursor.moveToFirst()){
              String id = cursor.getString( cursor.getColumnIndex( TB_COLUMN_ID ) );
              String name = cursor.getString( cursor.getColumnIndex( TB_COLUMN_NAME ) );
              String phone = cursor.getString( cursor.getColumnIndex( TB_COLUMN_PHONE ) );
              user  = new User();

              user.id = id;
              user.name = name;
              user.phone = phone;
          }
          return user;
      }
}
