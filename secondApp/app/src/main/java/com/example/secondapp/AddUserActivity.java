package com.example.secondapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AddUserActivity extends AppCompatActivity {

    Button insertUserBtn, replaceUserBtn, deleteUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    TextView textView;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        //
        insertUserBtn = findViewById(R.id.insertUserBtn);
        replaceUserBtn = findViewById(R.id.replaceUserBtn);
        deleteUserBtn = findViewById(R.id.deleteUserBtn);
        //
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        //
        textView = findViewById(R.id.textViewCaption);
        //
        actionCRUD(getIntent().getStringExtra("actionCRUD"));
        //
        insertUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextName.getText().length()==0) {
                    Toast.makeText(AddUserActivity.this,"Поле 'Имя' обязательно для заполнения! ", Toast.LENGTH_LONG).show();
                    return;
                }
                Users users = Users.get(AddUserActivity.this);
                User user = new User();
                user.setUserName(editTextName.getText().toString());
                user.setUserLastName(editTextLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                users.addUser(user);
                onBackPressed();
            }
        });
        //
        replaceUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(AddUserActivity.this);
                UserFragment.iuser.setUserName(editTextName.getText().toString());
                UserFragment.iuser.setUserLastName(editTextLastName.getText().toString());
                UserFragment.iuser.setPhone(editTextPhone.getText().toString());
                users.updateUser(UserFragment.iuser);
                Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });
        //
        deleteUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users users = Users.get(AddUserActivity.this);
                users.deleteUser(UserFragment.iuser);
                Intent intent = new Intent(AddUserActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void actionCRUD(String actionCRUD) {
        switch (actionCRUD) {
            case "add": {
                insertUserBtn.setVisibility(View.VISIBLE);
                replaceUserBtn.setVisibility(View.INVISIBLE);
                deleteUserBtn.setVisibility(View.INVISIBLE);
                textView.append("добавления пользователя");
                break;}

            case "update": {
                insertUserBtn.setVisibility(View.INVISIBLE);
                replaceUserBtn.setVisibility(View.VISIBLE);
                deleteUserBtn.setVisibility(View.INVISIBLE);
                textView.append("редактирования пользователя");
                initUserFields();
                break;}

            case "delete": {
                insertUserBtn.setVisibility(View.INVISIBLE);
                replaceUserBtn.setVisibility(View.INVISIBLE);
                deleteUserBtn.setVisibility(View.VISIBLE);
                textView.append("удаления пользователя");
                initUserFields();
                break;}

        }
    }
    private void initUserFields(){
        editTextName.setText(UserFragment.iuser.getUserName());
        editTextLastName.setText(UserFragment.iuser.getUserLastName());
        editTextPhone.setText(UserFragment.iuser.getPhone());
    }

}