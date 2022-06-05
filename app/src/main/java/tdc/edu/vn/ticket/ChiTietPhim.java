package tdc.edu.vn.ticket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import tdc.edu.vn.ticket.Phim.Phim;

public class ChiTietPhim extends AppCompatActivity {
    private DatabaseReference reference;
    ListView listPhim;
    ArrayList<String> arrPhim;
    ArrayAdapter adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_phim_layout);

        reference = FirebaseDatabase.getInstance().getReference();
        //listPhim = (ListView)findViewById(R.id.lvThongTin);

        ImageView anhChiTiet = (ImageView)findViewById(R.id.img_chiTiet);

        EditText edtTenPhim = (EditText)findViewById(R.id.edtTenPhim);
        EditText edtMoTa= (EditText)findViewById(R.id.edtMoTa);
        EditText edtTheLoai = (EditText)findViewById(R.id.edtTheLoai);
        EditText edtThoiLuong = (EditText)findViewById(R.id.edtThoiLuong);
        EditText edtNgonNgu = (EditText)findViewById(R.id.edtNgonNgu);

        String img = getIntent().getBundleExtra("DATA").getString("IMG");
        String tenPhim = getIntent().getBundleExtra("DATA").getString("TENPHIM");
        String moTaNgan = getIntent().getBundleExtra("DATA").getString("MOTANGAN");
        String theLoai = getIntent().getBundleExtra("DATA").getString("THELOAI");
        String thoiLuong = getIntent().getBundleExtra("DATA").getString("THOILUONG");
        String ngonNgu = getIntent().getBundleExtra("DATA").getString("NGONNGU");

        edtTenPhim.setText(tenPhim);
        edtMoTa.setText(moTaNgan);
        edtTheLoai.setText(theLoai);
        edtThoiLuong.setText(thoiLuong);
        edtNgonNgu.setText(ngonNgu);

//        arrPhim = new ArrayList<String>();
//        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrPhim);
//        listPhim.setAdapter(adapter);

        //Toast.makeText(ChiTietPhim.this, tenPhim +"-"+moTaNgan+"-"+theLoai+"-"+thoiLuong+"-"+ngonNgu,Toast.LENGTH_LONG).show();

        reference.child("Phim").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Phim phim = snapshot.getValue(Phim.class);
                byte[] mangHinh = Base64.decode(img, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                anhChiTiet.setImageBitmap(bitmap);
//                arrPhim.add("Tên phim: "+ tenPhim + "\nMô tả ngắn: "+ moTaNgan +
//                        "\nThể loại: "+ theLoai + "\nThời lượng: "+ thoiLuong +"\nNgôn ngữ: "+ ngonNgu);
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}