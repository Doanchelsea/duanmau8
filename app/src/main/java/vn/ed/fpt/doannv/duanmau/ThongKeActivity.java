package vn.ed.fpt.doannv.duanmau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import vn.ed.fpt.doannv.duanmau.database.DatabaseHelper;
import vn.ed.fpt.doannv.duanmau.sqliteDAO.StaticDAO;

import static vn.ed.fpt.doannv.duanmau.database.Constant.D_DAY;
import static vn.ed.fpt.doannv.duanmau.database.Constant.M_MONTH;
import static vn.ed.fpt.doannv.duanmau.database.Constant.Y_YEAR;

public class ThongKeActivity extends AppCompatActivity {

    private TextView tvStaticsMonth;
    private TextView tvStaticsDay;
    private TextView tvStaticsYear;

    private StaticDAO staticDAO;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_thong_ke );
        intivity();

        databaseHelper = new DatabaseHelper (this);
        staticDAO = new StaticDAO (databaseHelper);

        double day = staticDAO.getStatisticsByDate(D_DAY);
        double month = staticDAO.getStatisticsByDate(M_MONTH);
        double year = staticDAO.getStatisticsByDate(Y_YEAR);

        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String day1 = fmt.format(day);
        String month1 = fmt.format(month);
        String year1 = fmt.format(year);

        tvStaticsDay.setText ("Số tiền trong ngày: "+day1);
        tvStaticsMonth.setText ("Số tiền trong tháng: "+month1);
        tvStaticsYear.setText ("Số tiền trong năm: "+year1);
    }

    private void intivity() {
        tvStaticsMonth = findViewById(R.id.tvStaticsMonth);
        tvStaticsDay = findViewById(R.id.tvStaticsDay);
        tvStaticsYear = findViewById(R.id.tvStaticsYear);

    }
}
