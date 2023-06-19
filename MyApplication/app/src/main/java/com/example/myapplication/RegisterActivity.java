package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.model.Account;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
       Button btnadd = findViewById(R.id.createAccountBtn);
        ImageButton backBtn = findViewById(R.id.backBtn);

       DatabaseClient db_client=DatabaseClient.getDB(this);
       btnadd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               EditText userName=findViewById(R.id.username);
               EditText email=findViewById(R.id.email);
               EditText pass=findViewById(R.id.password);
               db_client.account_dao().register(new Account(email.getText().toString(),userName.getText().toString(),pass.getText().toString()));

               AlertDialog alert=new AlertDialog.Builder(RegisterActivity.this).create();
               alert.setTitle("Successful");
               alert.setMessage("Account created");
               alert.setButton(AlertDialog.BUTTON_POSITIVE, "Ok", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.dismiss();
                   }
               });
               alert.show();
           }
       });

       backBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
               startActivity(intent);
               finish();
           }
       });
    }
}