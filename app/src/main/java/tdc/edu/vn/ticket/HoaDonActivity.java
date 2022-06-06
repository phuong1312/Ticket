package tdc.edu.vn.ticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class HoaDonActivity extends AppCompatActivity {

    private HoaDon hoaDon;
    private Button btn;
    private String nameMV, time, seet, date;
    private TextView txt1, txt2, txt3, txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);

        nameMV = getIntent().getBundleExtra("order").getString("nameMV");
        time = getIntent().getBundleExtra("order").getString("time");
        seet = getIntent().getBundleExtra("order").getString("seet");
        date = getIntent().getBundleExtra("order").getString("date");

        txt1 = (TextView) findViewById(R.id.tx1);
        txt2 = (TextView) findViewById(R.id.tx2);
        txt3 = (TextView) findViewById(R.id.tx3);
        txt4 = (TextView) findViewById(R.id.tx4);
        btn = findViewById(R.id.btnHoanTat);

        txt1.setText("Tên Bộ Phim: " + nameMV);
        txt2.setText("Thời Gian Chiếu: " + time);
        txt3.setText("Ghế Ngồi: " + seet);
        txt4.setText("Thời Gian Đặt: " + date);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hoaDon = new HoaDon(nameMV, time,seet,date);
                FirebaseDatabase.getInstance().getReference().child("Hóa đơn").push().setValue(hoaDon);

//                Toast.makeText(HoaDonActivity.this, "" + a, Toast.LENGTH_LONG).show();
            }
        });
    }
}