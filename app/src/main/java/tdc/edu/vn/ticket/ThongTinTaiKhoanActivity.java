package tdc.edu.vn.ticket;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class ThongTinTaiKhoanActivity extends AppCompatActivity {
    private TextView txTaiKhoan, txMatKhau, txEmail, txSdt;
    private Button btnSua1, btnHuy, btnSua2;
    private EditText edtTaiKhoan, edtMatKhau, edtEmail, edtSdt;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtintaikhoan_layout);
        txTaiKhoan = (TextView) findViewById(R.id.txTaiKhoan);
        txMatKhau = (TextView) findViewById(R.id.txMatKhau);
        txEmail = (TextView) findViewById(R.id.txEmail);
        txSdt = (TextView) findViewById(R.id.txSdt);
        btnSua1 = (Button) findViewById(R.id.btnSua1);

//        FirebaseDatabase.getInstance().getReference("Users")
//                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DataSnapshot> task) {
//                if (!task.isSuccessful()) {
//                    Log.e("firebase", "Error getting data", task.getException());
//                }
//                else {
//                    User user = task.getResult().getValue(User.class);
//                    txEmail.setText(user.getEmail());
//                    txTaiKhoan.setText(user.getTaiKhoan());
//                    txMatKhau.setText(user.getMatKhau());
//                    txSdt.setText(user.getSdt());
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                }
//            }
//        });

        FirebaseDatabase.getInstance().getReference("Users")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                txEmail.setText(user.getEmail());
                txTaiKhoan.setText(user.getTaiKhoan());
                txMatKhau.setText(user.getMatKhau());
                txSdt.setText(user.getSdt());
                Log.d("firebase", String.valueOf(snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnSua1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(Gravity.BOTTOM);

            }
        });
    }

    private void openDialog(int gravity){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.suataikhoan_layout);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);

        if (Gravity.BOTTOM == gravity) {
            dialog.setCancelable(true);
        } else {
            dialog.setCancelable(false);
        }

        edtTaiKhoan = (EditText) dialog.findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) dialog.findViewById(R.id.edtMatKhau);
        edtEmail = (EditText) dialog.findViewById(R.id.edtEmail);
        edtSdt = (EditText) dialog.findViewById(R.id.edtSDT);
        btnSua2 = (Button) dialog.findViewById(R.id.btnSua2);
        btnHuy = (Button) dialog.findViewById(R.id.btnHuy);

        edtEmail.setText(txEmail.getText().toString());
        edtTaiKhoan.setText(txTaiKhoan.getText().toString());
        edtSdt.setText(txSdt.getText().toString());
        edtMatKhau.setText(txMatKhau.getText().toString());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSua2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("email", edtEmail.getText().toString());
                map.put("matKhau", edtMatKhau.getText().toString());
                map.put("sdt", edtSdt.getText().toString());
                map.put("taiKhoan", edtTaiKhoan.getText().toString());

                FirebaseDatabase.getInstance().getReference("Users")
                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),"Sửa Thành Công", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });

            }
        });
        dialog.show();
    }
}
