package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.myapplication.model.Account;

public class LoginActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginBtn = findViewById(R.id.loginBtn);
        Button registerBtn = findViewById(R.id.registerBtn);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        DatabaseClient db_client=DatabaseClient.getDB(this);
        boolean remmember_me=sharedpreferences.getBoolean("rememberMeCheckbox",false);
        if (remmember_me){
            Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
            startActivity(intent);
            finish();
        }
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText userName = findViewById(R.id.usernameTxt);
                EditText password = findViewById(R.id.passwordTxt);
                CheckBox checkbox=findViewById(R.id.rememberMeCheckbox);

                Account foundAccount = db_client.account_dao().getAccount(userName.getText().toString(), password.getText().toString());
                SharedPreferences.Editor editor=sharedpreferences.edit();

                if(foundAccount != null){
                    if(checkbox.isChecked()){
                        editor.putBoolean("rememberMeCheckbox",true);
                        editor.commit();
                    }
                    Intent intent=new Intent(LoginActivity.this,NavigationActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    AlertDialog alert=new AlertDialog.Builder(LoginActivity.this).create();
                    alert.setTitle("Login Failed");
                    alert.setMessage("Incorrect Username or Password");
                    alert.setButton(AlertDialog.BUTTON_NEGATIVE, "Retry", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alert.show();
                }

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}