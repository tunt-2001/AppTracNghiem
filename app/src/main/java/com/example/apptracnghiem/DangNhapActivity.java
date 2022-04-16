package com.example.apptracnghiem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class DangNhapActivity extends AppCompatActivity {
    Button btn_DangNhap, btn_NextDangKy;
    CheckBox ckb_GhiNho;
    TextInputEditText editTextUser_DN,editTextPass_DN;
    DAO_Login dao_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        btn_DangNhap = findViewById(R.id.btn_DangNhap);
        btn_NextDangKy = findViewById(R.id.btn_NextDangKy);
        ckb_GhiNho = findViewById(R.id.ckb_GhiNho);
        editTextUser_DN = findViewById(R.id.ed_user);
        editTextPass_DN = findViewById(R.id.ed_password);
        dao_login = new DAO_Login(this);



        btn_NextDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangNhapActivity.this, DangKyActivity.class));
            }
        });

        DangNhap();
    }
    public void DangNhap(){
        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editTextUser_DN.getText().toString();
                String pass = editTextPass_DN.getText().toString();
                boolean kt = dao_login.testLogIn(user,pass);
                if (editTextUser_DN.getText().toString().isEmpty() && editTextPass_DN.getText().toString().isEmpty()){
                    Toast.makeText(DangNhapActivity.this,"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_LONG).show();
                }else if(kt){
                    Toast.makeText(DangNhapActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                    finish();
                }else{
                    Toast.makeText(DangNhapActivity.this,"Tên đăng nhập hoặc mật khẩu không chính xác!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}