package lam.fpoly.adminmanager.manhinhchoAdmin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import lam.fpoly.adminmanager.MainActivity;
import lam.fpoly.adminmanager.R;

public class ActivityDangNhap extends AppCompatActivity {
    EditText edUserName, edPassWord;
    TextView btnDangNhap;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edUserName = findViewById(R.id.edUserName);
        edPassWord = findViewById(R.id.edPassword);
        btnDangNhap = findViewById(R.id.btnDangNhap);

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edUserName.getText().toString();
                String pass = edPassWord.getText().toString();

                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pass)){
                    AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityDangNhap.this);
                    builder.setTitle("Thông báo!");
                    builder.setMessage("Không được để trống");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                    return;
                }

                if (name.equals("admin") && pass.equals("admin123")){
                    Toast.makeText(ActivityDangNhap.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(ActivityDangNhap.this, MainActivity.class);
                    startActivity(i);
                }else {
                    AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ActivityDangNhap.this);
                    builder.setTitle("Thông báo!");
                    builder.setMessage("Sai tài khoản hoặc mật khẩu");
                    builder.setPositiveButton("OK", null);
                    builder.show();
                }

            }
        });
    }
}