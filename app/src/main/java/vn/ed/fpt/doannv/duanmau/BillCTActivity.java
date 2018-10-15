package vn.ed.fpt.doannv.duanmau;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import vn.ed.fpt.doannv.duanmau.adapter.AdapterBill;
import vn.ed.fpt.doannv.duanmau.adapter.AdapterBillCT;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.Bill;
import vn.ed.fpt.doannv.duanmau.database.model.BillCT;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BillCTDAO;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BillDAO;

public class BillCTActivity extends AppCompatActivity {

    private EditText edmahoadonct;
    private Button tvlichct;
    private Button btnmahoadonct;
    private RecyclerView lvBillct;
    private EditText edTenSachCT;
    private EditText edSoLuong;
    private EditText edGia;
    private long datePicker = -1;

    private AdapterBillCT adapterBillCT;
    private List<BillCT> typeBillCTs;
    private DatabaseHelper databaseHelper;
    private BillCTDAO typeBillCTDAO;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bill_ct );
        edmahoadonct =  findViewById(R.id.edmahoadonct);
        edTenSachCT =  findViewById(R.id.edtenCT);
        edSoLuong =  findViewById(R.id.edSoLuong);
        edGia =  findViewById(R.id.edGia);
        tvlichct =  findViewById(R.id.tvlichct);
        btnmahoadonct =  findViewById(R.id.btnmahoadonct);
        lvBillct = findViewById(R.id.lvBillct);

        databaseHelper = new DatabaseHelper(this);
        typeBillCTDAO = new BillCTDAO(databaseHelper);
        typeBillCTs = typeBillCTDAO.getAllBillCT();

        adapterBillCT = new AdapterBillCT(this, typeBillCTs, typeBillCTDAO);
        lvBillct.setAdapter(adapterBillCT);
        linearLayoutManager = new LinearLayoutManager(this);
        lvBillct.setLayoutManager(linearLayoutManager);
        btnmahoadonct.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edmahoadonct.getText().toString().trim();
                String name = edTenSachCT.getText().toString().trim();
                String soluong = edSoLuong.getText().toString().trim();
                 int soluong1 = Integer.parseInt( soluong );
                String gia = edGia.getText().toString().trim();
                 int gia1 = Integer.parseInt( gia );
                long result = typeBillCTDAO.insertBillCT( new BillCT(id ,datePicker,name,soluong1,gia1 ) );

                if (id.length()>7){
                    edmahoadonct.setError(getString( R.string.notify_max_bill_id_length  ) );
                    return;
                }

                if (datePicker<0){
                    Toast.makeText( BillCTActivity.this, "PLS Choose Date first!!!", Toast.LENGTH_SHORT ).show();
                    return;
                }
                if (result<0){
                    Toast.makeText( BillCTActivity.this, "ID Trùng Nhau", Toast.LENGTH_SHORT ).show();
                }else {
                    adapterBillCT.notifyDataSetChanged();
                    typeBillCTs.add(0, new BillCT( id,datePicker,name,soluong1,gia1 )  );

                }
            }
        } );

        tvlichct.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker( tvlichct );
            }
        } );
    }
    private void showDatePicker(final Button btnDate){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set( year,month,dayOfMonth );
                BillCTActivity.this.datePicker = calendar.getTimeInMillis();
                btnDate.setText( new Date(calendar.getTimeInMillis()).toString() );
            }
        }, calendar.get( Calendar.YEAR ),calendar.get( Calendar.MONTH ),calendar.get( Calendar.DAY_OF_MONTH ));
        datePickerDialog.show();
    }
}
