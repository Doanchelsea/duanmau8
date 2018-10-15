package vn.ed.fpt.doannv.duanmau;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import vn.ed.fpt.doannv.duanmau.adapter.AdapterBill;
import vn.ed.fpt.doannv.duanmau.adapter.AdapterUser;
import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.database.model.Bill;
import vn.ed.fpt.doannv.duanmau.database.model.User;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.BillDAO;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.UserDAO;

public class BillActivity extends AppCompatActivity {
    private EditText edmahoadon;
    private Button tvlich;
    private Button btnmahoadon;
    private RecyclerView lvBill;
    private long datePicker = -1;
    private CardView cvBill;

    private AdapterBill adapterBill;
    private List<Bill> typeBills;
    private DatabaseHelper databaseHelper;
    private BillDAO typeBillDAO;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_bill );
        edmahoadon =  findViewById(R.id.edmahoadon);
        tvlich =  findViewById(R.id.tvlich);
        btnmahoadon =  findViewById(R.id.btnmahoadon);
        lvBill = findViewById(R.id.lvBill);
        cvBill = findViewById( R.id.cvBill );

        databaseHelper = new DatabaseHelper(this);
        typeBillDAO = new BillDAO(databaseHelper);
        typeBills = typeBillDAO.getAllBill();

        adapterBill = new AdapterBill(this, typeBills, typeBillDAO);
        lvBill.setAdapter(adapterBill);
        linearLayoutManager = new LinearLayoutManager(this);
        lvBill.setLayoutManager(linearLayoutManager);
     btnmahoadon.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String id = edmahoadon.getText().toString().trim();
             long result = typeBillDAO.insertBill( new Bill(id ,datePicker ) );
             if (id.length()>7){
                 edmahoadon.setError(getString( R.string.notify_max_bill_id_length  ) );
                 return;
             }
             if (datePicker<0){
                 Toast.makeText( BillActivity.this, "PLS Choose Date first!!!", Toast.LENGTH_SHORT ).show();
                 return;
             }
             if (result<0){
                 Toast.makeText( BillActivity.this, "ID Trùng Nhau", Toast.LENGTH_SHORT ).show();
             }else {
                 adapterBill.notifyDataSetChanged();
                typeBills.add(0, new Bill( id,datePicker )  );

             }
         }
     } );

        tvlich.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              showDatePicker( tvlich );
            }
        } );
    }
    private void showDatePicker(final Button btnDate){
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog( this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set( year,month,dayOfMonth );
                BillActivity.this.datePicker = calendar.getTimeInMillis();
                btnDate.setText( new Date(calendar.getTimeInMillis()).toString() );
            }
        }, calendar.get( Calendar.YEAR ),calendar.get( Calendar.MONTH ),calendar.get( Calendar.DAY_OF_MONTH ));
        datePickerDialog.show();
    }
}
