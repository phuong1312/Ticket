package tdc.edu.vn.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;
import tdc.edu.vn.ticket.Slider.Photo;
import tdc.edu.vn.ticket.Slider.PhotoAdapter;
import tdc.edu.vn.ticket.Slider.ZoomOutPageTransformer;

public class MainActivity extends AppCompatActivity {
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

//        // Write a message to the database
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("message");//Key hiển thị ở RealTime DataBase trên FireBase
//
//        myRef.setValue("heloo");//Giá trị lưu
//
//        // Read from the database
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Log.d("fb", "Value is: " + value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w("fb", "Failed to read value.", error.toException());
//            }
//        });
    }
    
    // nếu ảnh hiện tại bằng ảnh cuối cùng trong list thì sẽ chuyển về ảnh đầu tiên
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
}