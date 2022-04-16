package com.example.apptracnghiem;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptracnghiem.model.LogIn;
import com.google.android.material.textfield.TextInputEditText;

public class DangKyActivity extends AppCompatActivity {
    TextView tv_nextDangNhap;
    TextInputEditText editText_user_dk, editText_pass_dk, editText_repass_dk;
    Button btn_DangKy;
    DAO_Login dao_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        tv_nextDangNhap = findViewById(R.id.tv_nextDangNhap);
        editText_user_dk = findViewById(R.id.ed_user);
        editText_pass_dk = findViewById(R.id.ed_password);
        editText_repass_dk = findViewById(R.id.ed_repassword);
        btn_DangKy = findViewById(R.id.btn_DangKy);
        dao_login = new DAO_Login(this);

        tv_nextDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));
            }
        });
        DangKy();


    }
    public void DangKy(){
        btn_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_dk = editText_user_dk.getText().toString();
                String pass_dk = editText_pass_dk.getText().toString();
                String repass_dk = editText_repass_dk.getText().toString();

                if(editText_user_dk.getText().toString().isEmpty() && editText_pass_dk.getText().toString().isEmpty() && editText_repass_dk.getText().toString().isEmpty()){
                    Toast.makeText(DangKyActivity.this,"Vui lòng điền đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }else if(repass_dk.equals(pass_dk)){
                    LogIn logIn = new LogIn(user_dk, pass_dk);
                    dao_login.ThemTK(logIn);
                    Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(DangKyActivity.this, DangNhapActivity.class));
                        }
                    },2000);
                }else{
                    Toast.makeText(DangKyActivity.this,"Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}