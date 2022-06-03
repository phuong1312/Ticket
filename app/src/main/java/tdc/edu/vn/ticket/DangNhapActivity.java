package tdc.edu.vn.ticket;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DangNhapActivity extends AppCompatActivity {
    private EditText edtEmail, edtMatKhau;
    private Button btnDangki, btnDangNhap;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap_layout);
        auth= FirebaseAuth.getInstance();

        edtEmail = (EditText)findViewById(R.id.edtEmail);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        btnDangki = (Button) findViewById(R.id.btnDangKi);
        btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangNhap();
            }
        });
        btnDangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKi();
            }
        });
    }

    private void dangKi() {
        Intent intent = new Intent(DangNhapActivity.this, DangKiActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        startActivity(intent);
    }

    private void dangNhap() {
        String email, matKhau;

        email = edtEmail.getText().toString().trim();
        matKhau = edtMatKhau.getText().toString().trim();

        if (email.isEmpty()){
            edtEmail.setError("Vui lòng nhập email");
            edtEmail.requestFocus();
            return;
        }
       if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
           edtEmail.setError("Email không hợp lệ");
           edtEmail.requestFocus();
           return;
       }
        if (matKhau.isEmpty()){
            edtMatKhau.setError("Vui lòng nhập mật khẩu");
            edtMatKhau.requestFocus();
            return;
        }
        if (matKhau.length() <6){
            edtMatKhau.setError("Mật khẩu phải có ít nhất 6 kí tự");
            edtMatKhau.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email, matKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DangNhapActivity.this, ThongTinTaiKhoanActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
