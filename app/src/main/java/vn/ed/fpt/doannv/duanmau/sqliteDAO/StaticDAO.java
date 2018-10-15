package vn.ed.fpt.doannv.duanmau.sqliteDAO;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import vn.ed.fpt.doannv.duanmau.database.Constant;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;

public class StaticDAO implements Constant {

    private DatabaseHelper databaseHelper;

    public StaticDAO(DatabaseHelper databaseHelper) {
        this.databaseHelper = databaseHelper;
    }

    public double getStatisticsByDate(String dateFormat) {

        double result = -1;

        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        String QUERY_DAY = "SELECT SUM(tongtien) FROM (" +
                "" + "SELECT SUM(TheLoai.SoLuong * BillCT.Gia) as 'tongtien'" +
                "" + " FROM " + TABLE_BILL +
                "" + " INNER JOIN " + TABLE_THELOAI + " ON " + " TheLoai.Name = BillCT.Name " +
                "" + " INNER JOIN " + TABLE_BILL_CT + " ON " + " Bill.MaHoaDon = BillCT.MaHoaDon " +
                "" + " WHERE  strftime(" + dateFormat + ", Bill.NgayMua / 1000 , 'unixepoch') = strftime(" + dateFormat + ",'now') " +
                "" + " GROUP BY BillCT.Name" +
                ")";

        Log.e("QUERY_DAY", QUERY_DAY);

        Cursor cursor = sqLiteDatabase.rawQuery(QUERY_DAY, null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();

            result = cursor.getDouble(0);
        }
        return result;
    }

}
