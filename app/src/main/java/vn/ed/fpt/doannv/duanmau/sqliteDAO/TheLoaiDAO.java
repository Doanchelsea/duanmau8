package vn.ed.fpt.doannv.duanmau.sqliteDAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import vn.ed.fpt.doannv.duanmau.database.Constant;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.common.Constants;
import vn.ed.fpt.doannv.duanmau.database.model.Book;
import vn.ed.fpt.doannv.duanmau.database.model.TheLoai;

public class TheLoaiDAO implements Constant {
    private DatabaseHelper databaseHelper;
    public TheLoaiDAO(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public long insertTheLoai(TheLoai book){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( TB_COLUMN_THELOAI_ID, book.theloaiid );
        contentValues.put( TB_COLUMN_THELOAI_NAME, book.theloai );
        contentValues.put( TB_COLUMN_THELOAI_THE_LOAI, book.soluong );

        long result = sqLiteDatabase.insert( TABLE_THELOAI,null  , contentValues );
        if(Constants.isDEBUG) Log.e( "insertTheLoai", "insertTheLoai : "+result );

        sqLiteDatabase.close();
        return result;
    }

    public long deleteTheLoai(String typeID){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete( TABLE_THELOAI, TB_COLUMN_THELOAI_ID + "=?", new String[]{typeID} );

        sqLiteDatabase.close();
        return result;
    }
    public long updateTheLoai(TheLoai book){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( TB_COLUMN_THELOAI_ID, book.theloaiid );
        contentValues.put( TB_COLUMN_THELOAI_NAME, book.theloai );
        contentValues.put( TB_COLUMN_THELOAI_THE_LOAI, book.soluong );
        long result = sqLiteDatabase.update( TABLE_THELOAI,contentValues,
                TB_COLUMN_THELOAI_ID + "=?", new String[]{book.theloaiid} );
        sqLiteDatabase.close();
        return result;
    }
    public List<TheLoai> getAllTheLoai(){
        List<TheLoai> books = new ArrayList<>(  );

        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all
        String SELECT_ALL_THELOAI = "SELECT * FROM " + TABLE_THELOAI;
        Cursor cursor = sqLiteDatabase.rawQuery( SELECT_ALL_THELOAI, null );

        if (cursor.moveToFirst()){
            do {
                String theloaiid = cursor.getString( cursor.getColumnIndex( TB_COLUMN_THELOAI_ID ) );
                String theloai = cursor.getString( cursor.getColumnIndex( TB_COLUMN_THELOAI_NAME ) );
                String soluong = cursor.getString( cursor.getColumnIndex( TB_COLUMN_THELOAI_THE_LOAI ) );
                TheLoai book = new TheLoai();

                book.theloaiid = theloaiid;
                book.theloai = theloai;
                book.soluong = soluong;

                books.add( book );
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return books;
    }
    public TheLoai getTheLoaiByID(String typeID){
        TheLoai book = null;
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_THELOAI,
                new String[]{TB_COLUMN_THELOAI_ID, TB_COLUMN_THELOAI_NAME, TB_COLUMN_THELOAI_THE_LOAI},
                TB_COLUMN_THELOAI_ID + "=?", new String[]{typeID},
                null, null, null);



        if (cursor !=null && cursor.moveToFirst()){
            String bookid = cursor.getString( cursor.getColumnIndex( TB_COLUMN_THELOAI_ID ) );
            String bookname = cursor.getString( cursor.getColumnIndex( TB_COLUMN_THELOAI_NAME ) );
            String booktheloai = cursor.getString( cursor.getColumnIndex( TB_COLUMN_THELOAI_THE_LOAI ) );
            book  = new TheLoai();

            book.theloaiid = bookid;
            book.theloai = bookname;
            book.soluong = booktheloai;
        }
        return book;
    }
}
