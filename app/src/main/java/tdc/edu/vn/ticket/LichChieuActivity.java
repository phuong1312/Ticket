package tdc.edu.vn.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LichChieuActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private List<String> listmv;
    private Map<String, List<String>> map;
    private ExpandableListViewAdapter expandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lich_chieu_layout);

        expandableListView = findViewById(R.id.exlView);
        showList();

        expandableListViewAdapter = new ExpandableListViewAdapter(this, listmv, map);
        expandableListView.setAdapter(expandableListViewAdapter);
        Click_child();
    }

    private void Click_child() {
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

                Intent intent = new Intent(LichChieuActivity.this, DatChoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameMV", listmv.get(i));
                bundle.putString("time", map.get(listmv.get(i)).get(i1));
                intent.putExtra("data", bundle);
                startActivity(intent);
                return false;
            }
        });
    }

    private void showList() {
        listmv = new ArrayList<>();
        map = new HashMap<>();

        listmv.add("Bóng Đè");
        listmv.add("Chị Mười Ba");
        listmv.add("Mẹ ma than khóc");
        listmv.add("Trái tim quái vật");
        listmv.add("Siêu sao ngố");
        listmv.add("Thần sấm 2");

        List<String> movieList1 = new ArrayList<>();
        movieList1.add("8:00");
        movieList1.add("12:00");
        movieList1.add("16:00");
        movieList1.add("19:00");

        List<String> movieList2 = new ArrayList<>();
        movieList2.add("6:00");
        movieList2.add("10:00");
        movieList2.add("15:00");
        movieList2.add("18:00");

        List<String> movieList3 = new ArrayList<>();
        movieList3.add("9:00");
        movieList3.add("13:00");
        movieList3.add("19:00");
        movieList3.add("20:00");

        List<String> movieList4 = new ArrayList<>();
        movieList4.add("5:00");
        movieList4.add("12:00");
        movieList4.add("16:00");
        movieList4.add("19:45");

        List<String> movieList5 = new ArrayList<>();
        movieList5.add("8:30");
        movieList5.add("10:10");
        movieList5.add("15:40");
        movieList5.add("19:00");

        List<String> movieList6 = new ArrayList<>();
        movieList6.add("8:00");
        movieList6.add("12:20");
        movieList6.add("16:30");
        movieList6.add("20:20");

        map.put(listmv.get(0), movieList1);
        map.put(listmv.get(1), movieList2);
        map.put(listmv.get(2), movieList3);
        map.put(listmv.get(3), movieList4);
        map.put(listmv.get(4), movieList5);
        map.put(listmv.get(5), movieList6);

        FirebaseDatabase.getInstance().getReference().child("Lịch Chiếu").setValue(map);
    }

}