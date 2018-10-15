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
import vn.ed.fpt.doannv.duanmau.database.model.BillCT;

public class BillCTDAO implements Constant {
    private DatabaseHelper databaseHelper;

    public BillCTDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public long insertBillCT(BillCT billCT) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put( B_ID_CT, billCT.idct );
        contentValues.put( B_DATE_CT, billCT.datect );
        contentValues.put( B_TEN_CT, billCT.tenct );
        contentValues.put( B_SOLUONG_CT, billCT.soluong );
        contentValues.put( B_GIA_CT, billCT.gia );

        long result = sqLiteDatabase.insert( TABLE_BILL_CT, null, contentValues );
        if (Constants.isDEBUG) Log.e( "insertBill", "insertBill : " + result );

        sqLiteDatabase.close();
        return result;
    }

    public long deleteBillCT(String id) {
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        long result = sqLiteDatabase.delete( TABLE_BILL_CT, B_ID_CT + "=?", new String[]{id} );
        sqLiteDatabase.close();
        return result;
    }

    public List<BillCT> getAllBillCT() {
        List<BillCT> billCTS = new ArrayList<>();

        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all
        String SELECT_ALL_BILL_CT = "SELECT * FROM " + TABLE_BILL_CT;
        Cursor cursor = sqLiteDatabase.rawQuery( SELECT_ALL_BILL_CT, null );

        if (cursor.moveToFirst()) {
            do {

                String id = cursor.getString( 0 );
                long date = cursor.getLong( 1 );
                String name = cursor.getString( 2 );
                int soluong = cursor.getInt( 3 );
                int gia = cursor.getInt( 4 );

                BillCT billCT = new BillCT( id, date, name, soluong, gia );

                billCTS.add( billCT );

            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return billCTS;
    }

    public BillCT getBillCTId(String id) {
        BillCT billCT = null;
        //        // xin ban quyen
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        // viet cau lenh select all
        Cursor cursor = sqLiteDatabase.query( TABLE_BILL_CT, new String[]{B_ID_CT,
                        B_DATE_CT,B_TEN_CT,B_SOLUONG_CT,B_GIA_CT}, B_ID_CT + " =? ",
                new String[]{id}, null, null, null );

        if (cursor.moveToFirst()) {
            do {

                String id1 = cursor.getString( 0 );
                long date = cursor.getLong( 1 );
                String name = cursor.getString( 2 );
                int soluong = cursor.getInt( 3 );
                int gia = cursor.getInt( 4 );
                billCT = new BillCT( id, date,name,soluong,gia);
                return billCT;
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return null;
    }
}
