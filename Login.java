package com.shia.atm7;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import javax.xml.transform.Result;

public class Login extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText ETuid = findViewById(R.id.ed_uid);
        SharedPreferences pre =getSharedPreferences("ATM_id_pw",MODE_PRIVATE);
        ETuid.setText(pre.getString("id",""));
    }



    public void login(View view){
        EditText ETuid = findViewById(R.id.ed_uid);
        EditText ETpw = findViewById(R.id.ed_pw);
        String uid = ETuid.getText().toString();
        String pw = ETpw.getText().toString();
        if (uid.equals("jack") && pw.equals("1234")){
            SharedPreferences pre = getSharedPreferences("ATM_id_pw",MODE_PRIVATE);
            pre.edit().putString("id",uid).apply();
            Toast.makeText(this,"OK",Toast.LENGTH_LONG).show();
            setResult(RESULT_OK);
            finish();
        }else {
            new AlertDialog.Builder(this).setMessage("帳號或密碼錯誤").setTitle("登入失敗").setNeutralButton("確定",null).show();
        }

    }
}
