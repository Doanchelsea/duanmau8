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
import vn.ed.fpt.doannv.duanmau.database.model.User;

public class BookDAO implements Constant {

    private DatabaseHelper databaseHelper;
    public BookDAO(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public long insertBook(Book book){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( TB_COLUMN_BOOK_ID, book.bookid );
        contentValues.put( TB_COLUMN_BOOK_NAME, book.bookname );
        contentValues.put( TB_COLUMN_BOOK_THE_LOAI, book.booktheloai );

        long result = sqLiteDatabase.insert( TABLE_BOOK,null  , contentValues );
        if(Constants.isDEBUG) Log.e( "insertBook", "insertBook : "+result );

        sqLiteDatabase.close();
        return result;
    }

    public long deleteBook(String typeID){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete( TABLE_BOOK, TB_COLUMN_BOOK_ID + "=?", new String[]{typeID} );

        sqLiteDatabase.close();
        return result;
    }
    public long updateBook(Book book){
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( TB_COLUMN_BOOK_ID, book.bookid );
        contentValues.put( TB_COLUMN_BOOK_NAME, book.bookname );
        contentValues.put( TB_COLUMN_BOOK_THE_LOAI, book.bookname );
        long result = sqLiteDatabase.update( TABLE_BOOK,contentValues,
                TB_COLUMN_BOOK_ID + "=?", new String[]{book.bookid} );
        sqLiteDatabase.close();
        return result;
    }
    public List<Book> getAllBook(){
        List<Book> books = new ArrayList<>(  );

        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all
        String SELECT_ALL_BOOK = "SELECT * FROM " + TABLE_BOOK;
        Cursor cursor = sqLiteDatabase.rawQuery( SELECT_ALL_BOOK, null );

        if (cursor.moveToFirst()){
            do {
                String bookid = cursor.getString( cursor.getColumnIndex( TB_COLUMN_BOOK_ID ) );
                String bookname = cursor.getString( cursor.getColumnIndex( TB_COLUMN_BOOK_NAME ) );
                String booktheloai = cursor.getString( cursor.getColumnIndex( TB_COLUMN_BOOK_THE_LOAI ) );
                Book book = new Book();

                book.bookid = bookid;
                book.bookname = bookname;
                book.booktheloai = booktheloai;

                books.add( book );
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return books;
    }
    public Book getBookByID(String typeID){
        Book book = null;
        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.query(TABLE_BOOK,
                new String[]{TB_COLUMN_BOOK_ID, TB_COLUMN_BOOK_NAME, TB_COLUMN_BOOK_THE_LOAI},
                TB_COLUMN_BOOK_ID + "=?", new String[]{typeID},
                null, null, null);



        if (cursor !=null && cursor.moveToFirst()){
            String bookid = cursor.getString( cursor.getColumnIndex( TB_COLUMN_BOOK_ID ) );
            String bookname = cursor.getString( cursor.getColumnIndex( TB_COLUMN_BOOK_NAME ) );
            String booktheloai = cursor.getString( cursor.getColumnIndex( TB_COLUMN_BOOK_THE_LOAI ) );
            book  = new Book();

            book.bookid = bookid;
            book.bookname = bookname;
            book.booktheloai = booktheloai;
        }
        return book;
    }
}
