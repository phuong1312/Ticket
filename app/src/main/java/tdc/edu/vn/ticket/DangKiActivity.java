package tdc.edu.vn.ticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DangKiActivity extends AppCompatActivity {
    private EditText edtTaiKhoan, edtMatKhau, edtEmail, edtSdt;
    private Button btnDangKi;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dang_ki_layout);
        auth = FirebaseAuth.getInstance();

        edtTaiKhoan = (EditText) findViewById(R.id.edtTaiKhoan);
        edtMatKhau = (EditText) findViewById(R.id.edtMatKhau);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtSdt = (EditText) findViewById(R.id.edtSDT);

        btnDangKi = (Button) findViewById(R.id.btnDangKi);

        btnDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKi();
            }
        });
    }

    private void dangKi() {
        String taiKhoan, matKhau, email, sdt;

        taiKhoan = edtTaiKhoan.getText().toString();
        matKhau = edtMatKhau.getText().toString();
        email =edtEmail.getText().toString();
        sdt = edtSdt.getText().toString();

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
        if (taiKhoan.isEmpty()){
            edtTaiKhoan.setError("Vui lòng nhập tên tài khoản");
            edtTaiKhoan.requestFocus();
            return;
        }
        if (sdt.isEmpty()){
            edtSdt.setError("Vui lòng nhập số điện thoại");
            edtSdt.requestFocus();
            return;
        }

        auth.createUserWithEmailAndPassword(email, matKhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(taiKhoan, matKhau, email, sdt);

                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(DangKiActivity.this, "Đăng kí thành công", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(DangKiActivity.this, DangNhapActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(DangKiActivity.this, "Đăng kí không thành công", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(DangKiActivity.this, "Đăng kí khôngg thành công", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}