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
import vn.ed.fpt.doannv.duanmau.database.model.Bill;

public class BillDAO implements Constant {
    private DatabaseHelper databaseHelper;
    public BillDAO(DatabaseHelper databaseHelper){
        this.databaseHelper = databaseHelper;
    }

    public long insertBill(Bill bill){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( B_ID, bill.id );
        contentValues.put( B_DATE, bill.date );

        long result = sqLiteDatabase.insert( TABLE_BILL,null  , contentValues );
        if(Constants.isDEBUG) Log.e( "insertBill", "insertBill : "+result );

        sqLiteDatabase.close();
        return result;
    }
    public long updateBill(Bill bill){
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( B_ID, bill.id );
        contentValues.put( B_DATE, bill.date );

        long result = sqLiteDatabase.update( TABLE_BILL, contentValues,B_ID + "=?", new String[]{bill.id} );
        sqLiteDatabase.close();
        return result;
        }
        public  long deleteBill(String id){
            SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
            long result = sqLiteDatabase.delete( TABLE_BILL,B_ID + "=?", new String[]{id} );
            sqLiteDatabase.close();
            return result;
        }
    public List<Bill> getAllBill(){
        List<Bill> bills = new ArrayList<>(  );

        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all
        String SELECT_ALL_BILL = "SELECT * FROM " + TABLE_BILL;
        Cursor cursor = sqLiteDatabase.rawQuery( SELECT_ALL_BILL, null );

        if (cursor.moveToFirst()){
            do {

                String id = cursor.getString(0);
                long date = cursor.getLong( 1 );

                Bill bill = new Bill(id,date);

                  bills.add( bill );

            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return bills;
    }
    public Bill getBillId(String id){
        Bill bill = null;
        //        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all
        Cursor cursor = sqLiteDatabase.query( TABLE_BILL, new String[]{B_ID,B_DATE},B_ID + " =? ",
                new String[]{id},null,null,null);

        if (cursor.moveToFirst()){
            do {

                String id1 = cursor.getString(0);
                long date = cursor.getLong( 1 );
                bill = new Bill(id,date);
            return bill;
            }while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return null;
    }
}
