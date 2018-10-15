package vn.ed.fpt.doannv.duanmau.database;

public  interface Constant {

    boolean isDEBUG = true;

    String D_DAY = "\"%Y-%m-%d\"";
    String M_MONTH = "\"%Y-%m\"";
    String Y_YEAR = "\"%Y\"";

    String TABLE_USER = "User";
    String TB_COLUMN_ID = "Id";
    String TB_COLUMN_NAME = "Name";
    String TB_COLUMN_PHONE = "PhoneNumber";

    String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_USER + "(" +
            ""+ TB_COLUMN_ID + " PRIMARY KEY NOT NULL," +
            ""+ TB_COLUMN_NAME + " NVARCHAR(50)," +
            ""+ TB_COLUMN_PHONE + " INT" +
            ")";

    // loai sach
    String TABLE_BOOK = "Book";
    String TB_COLUMN_BOOK_ID = "Id";
    String TB_COLUMN_BOOK_NAME = "Name";
    String TB_COLUMN_BOOK_THE_LOAI = "TheLoai";

    String CREATE_TABLE_BOOK = "CREATE TABLE " + TABLE_BOOK + "(" +
            ""+ TB_COLUMN_BOOK_ID + " PRIMARY KEY NOT NULL," +
            ""+ TB_COLUMN_BOOK_NAME + " NVARCHAR(50)," +
            ""+ TB_COLUMN_BOOK_THE_LOAI + " INT" +
            ")";

// the loai
    String TABLE_THELOAI = "TheLoai";
    String TB_COLUMN_THELOAI_ID = "Id";
    String TB_COLUMN_THELOAI_NAME = "Name";
    String TB_COLUMN_THELOAI_THE_LOAI = "Soluong";

    String CREATE_TABLE_THELOAI = "CREATE TABLE " + TABLE_THELOAI + "(" +
            ""+ TB_COLUMN_THELOAI_ID + " PRIMARY KEY NOT NULL," +
            ""+ TB_COLUMN_THELOAI_NAME + " NVARCHAR(50)," +
            ""+ TB_COLUMN_THELOAI_THE_LOAI + " INT" +
            ")";


      String TABLE_BILL = "Bill";
      String B_ID = "MaHoaDon";
      String B_DATE = "NgayMua";

      String CREATE_TABLE_BILL = "CREATE TABLE " + TABLE_BILL + "("+
              "" + B_ID +" NCHAR(7) PRIMARY KEY NOT NULL," +
              "" + B_DATE +" LONG NUT NULL" +
              ")";

    String TABLE_BILL_CT = "BillCT";
    String B_ID_CT = "MaHoaDon";
    String B_DATE_CT = "NgayMua";
    String B_TEN_CT = "Name";
    String B_SOLUONG_CT = "SoLuong";
    String B_GIA_CT = "Gia";
    String CREATE_TABLE_BILL_CT = "CREATE TABLE " + TABLE_BILL_CT + "("+
            "" + B_ID_CT +" NCHAR(7) PRIMARY KEY NOT NULL," +
            "" + B_DATE_CT +" LONG NUT NULL," +
            "" + B_TEN_CT+" NVARCHAR(50)," +
            "" + B_SOLUONG_CT +"  INT," +
            "" + B_GIA_CT +" INT " +
            ")";
}
