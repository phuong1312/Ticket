package tdc.edu.vn.ticket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import tdc.edu.vn.ticket.Phim.Phim;
import tdc.edu.vn.ticket.Slider.Photo;
import tdc.edu.vn.ticket.Slider.PhotoAdapter;
import tdc.edu.vn.ticket.Slider.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {
    private DatabaseReference reference;
    private ViewPager2 viewPager2;
    private CircleIndicator3 circleIndicator3;
    private List<Photo> photoList;
    private Handler handler= new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int CurrentPosition = viewPager2.getCurrentItem();
            // nếu ảnh hiện tại bằng ảnh cuối cùng trong list thì sẽ chuyển về ảnh đầu tiên
            if (CurrentPosition == photoList.size() -1){
                viewPager2.setCurrentItem(0);
            }
            else {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        reference = FirebaseDatabase.getInstance().getReference();
        //Button btnSave = (Button)findViewById(R.id.btnSave);

        ImageView anh1 = (ImageView)findViewById(R.id.img_photo1);
        ImageView anh2 = (ImageView)findViewById(R.id.img_photo2);
        ImageView anh3 = (ImageView)findViewById(R.id.img_photo3);
        ImageView anh4 = (ImageView)findViewById(R.id.img_photo4);
        ImageView anh5 = (ImageView)findViewById(R.id.img_photo5);
        ImageView anh6 = (ImageView)findViewById(R.id.img_photo6);

        viewPager2 = findViewById(R.id.view_pager2);
        circleIndicator3 = findViewById(R.id.circle_indicator3);
        photoList = getListPhoto();

        PhotoAdapter adapter = new PhotoAdapter(this,photoList);
        viewPager2.setAdapter(adapter);
        circleIndicator3.setViewPager(viewPager2);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable, 4000);
            }
        });

        viewPager2.setPageTransformer(new ZoomOutPageTransformer());

//        btnSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                byte[] mangHinh = ImageView_To_Byte(anh6);
//                String chuoiHinh = Base64.encodeToString(mangHinh, Base64.DEFAULT);
//                //reference.child("pictural").setValue(chuoiHinh);
//               Phim phim = new Phim(6, chuoiHinh, "Thor 2", "Đây là 1 bộ phim về ....", "Hành động, siêu nhiên", "300 phút", "Tiếng Anh");
//                reference.child("Phim").push().setValue(phim);
//            }
//        });

        reference.child("Phim").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Phim phim = snapshot.getValue(Phim.class);
//                Toast.makeText(MainActivity.this, ""+photo.id, Toast.LENGTH_LONG).show();

                if (phim.getId() == 1){
                    byte[] mangHinh = Base64.decode(phim.str_img.toString(), Base64.DEFAULT);
                    Log.d("mangbyte", "onChildAdded: "+ mangHinh);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                    anh1.setImageBitmap(bitmap);
                }
                if (phim.getId() == 2){
                    byte[] mangHinh = Base64.decode(phim.str_img.toString(), Base64.DEFAULT);
                    Log.d("mangbyte", "onChildAdded: "+ mangHinh);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                    anh2.setImageBitmap(bitmap);
                }
                if (phim.getId() == 3){
                    byte[] mangHinh = Base64.decode(phim.str_img.toString(), Base64.DEFAULT);
                    Log.d("mangbyte", "onChildAdded: "+ mangHinh);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                    anh3.setImageBitmap(bitmap);
                }
                if (phim.getId() == 4){
                    byte[] mangHinh = Base64.decode(phim.str_img.toString(), Base64.DEFAULT);
                    Log.d("mangbyte", "onChildAdded: "+ mangHinh);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                    anh4.setImageBitmap(bitmap);
                }
                if (phim.getId() == 5){
                    byte[] mangHinh = Base64.decode(phim.str_img.toString(), Base64.DEFAULT);
                    Log.d("mangbyte", "onChildAdded: "+ mangHinh);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                    anh5.setImageBitmap(bitmap);
                }
                if (phim.getId() == 6){
                    byte[] mangHinh = Base64.decode(phim.str_img.toString(), Base64.DEFAULT);
                    Log.d("mangbyte", "onChildAdded: "+ mangHinh);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(mangHinh, 0, mangHinh.length);
                    anh6.setImageBitmap(bitmap);
                }
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

        reference.child("Phim").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Phim phim = snapshot.getValue(Phim.class);
                if (phim.getId()==1){
                    anh1.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(MainActivity.this, ChiTietPhim.class);
                            Bundle data = new Bundle();
                            data.putString("IMG", phim.str_img);
                            data.putString("TENPHIM", phim.tenPhim);
                            data.putString("MOTANGAN", phim.thongTinPhim.moTaNgan);
                            data.putString("NGONNGU", phim.thongTinPhim.theLoai);
                            data.putString("THOILUONG", phim.thongTinPhim.thoiLuong);
                            data.putString("THELOAI", phim.thongTinPhim.ngonNgu);
                            intent.putExtra("DATA", data);
                            startActivity(intent);
                        }
                    });
                }
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
    
    // lấy ảnh trong drawable
    private List<Photo> getListPhoto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.anh));
        list.add(new Photo(R.drawable.anh1));
        list.add(new Photo(R.drawable.anh2));
        list.add(new Photo(R.drawable.anh3));
        list.add(new Photo(R.drawable.anh4));
        return list;
    }
    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 4000);
    }

    // hàm chuyển ảnh thành mảng
    public byte[] ImageView_To_Byte(ImageView v){
        //Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.chomuc);
        BitmapDrawable drawable = (BitmapDrawable) v.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}