package tdc.edu.vn.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DatChoActivity extends AppCompatActivity {

    private Button btnA1, btnA2, btnA3, btnA4, btnB1, btnB2, btnB3, btnB4, btnC1, btnC2, btnC3, btnC4, btnD1, btnD2, btnD3, btnD4 , btnDatVe;
    private String date;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seet_layout);


        List<String> list = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        date = cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);

        String nameMV = getIntent().getBundleExtra("data").getString("nameMV");
        String time = getIntent().getBundleExtra("data").getString("time");

        btnA1 = findViewById(R.id.btnA1);
        btnA2 = findViewById(R.id.btnA2);
        btnA3 = findViewById(R.id.btnA3);
        btnA4 = findViewById(R.id.btnA4);
        btnB1 = findViewById(R.id.btnB1);
        btnB2 = findViewById(R.id.btnB2);
        btnB3 = findViewById(R.id.btnB3);
        btnB4 = findViewById(R.id.btnB4);
        btnC1 = findViewById(R.id.btnC1);
        btnC2 = findViewById(R.id.btnC2);
        btnC3 = findViewById(R.id.btnC3);
        btnC4 = findViewById(R.id.btnC4);
        btnD1 = findViewById(R.id.btnD1);
        btnD2 = findViewById(R.id.btnD2);
        btnD3 = findViewById(R.id.btnD3);
        btnD4 = findViewById(R.id.btnD4);
        btnDatVe = findViewById(R.id.btnDatVe);



        btnA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnA1.getText().toString());
                btnA1.setEnabled(false);
            }
        });
        btnA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnA2.getText().toString());
                btnA2.setEnabled(false);
            }
        });
        btnA3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnA3.getText().toString());
                btnA3.setEnabled(false);
            }
        });
        btnA4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnA4.getText().toString());
                btnA4.setEnabled(false);
            }
        });
        btnB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnB1.getText().toString());
                btnB1.setEnabled(false);
            }
        });
        btnB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnB2.getText().toString());
                btnB2.setEnabled(false);
            }
        });
        btnB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnB3.getText().toString());
                btnB3.setEnabled(false);
            }
        });
        btnB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnB4.getText().toString());
                btnB4.setEnabled(false);
            }
        });
        btnC1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnC1.getText().toString());
                btnC1.setEnabled(false);
            }
        });
        btnC2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnC2.getText().toString());
                btnC2.setEnabled(false);
            }
        });
        btnC3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnC3.getText().toString());
                btnC3.setEnabled(false);
            }
        });
        btnC4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnC4.getText().toString());
                btnC4.setEnabled(false);
            }
        });
        btnD1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnD1.getText().toString());
                btnD1.setEnabled(false);
            }
        });
        btnD2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnD2.getText().toString());
                btnD2.setEnabled(false);
            }
        });
        btnD3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnD3.getText().toString());
                btnD3.setEnabled(false);
            }
        });
        btnD4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(btnD4.getText().toString());
                btnD4.setEnabled(false);
            }
        });

        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DatChoActivity.this, HoaDonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameMV", nameMV);
                bundle.putString("time", time);
                bundle.putString("seet", list.toString());
                bundle.putString("date", date);
                intent.putExtra("order", bundle);
                startActivity(intent);
            }
        });
    }
}
