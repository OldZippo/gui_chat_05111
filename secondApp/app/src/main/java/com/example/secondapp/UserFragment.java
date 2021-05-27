package com.example.secondapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class UserFragment extends Fragment {
    User user;
    TextView userInfo;
    Button updateUser, deleteUser;
    public static User iuser;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int position = getActivity().getIntent().getIntExtra("position",1);
        user = Users.get(getActivity()).getUserList().get(position);
        iuser = user;
    //
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user, viewGroup, false);
        //
        updateUser = view.findViewById(R.id.updateUser);
        deleteUser = view.findViewById(R.id.deleteUser);
        //
        userInfo = view.findViewById(R.id.userInfo);
        userInfo.setText(user.getUserName()+" "+user.getUserLastName()+"\n"+user.getPhone());
        //
        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                intent.putExtra("actionCRUD", "update");
                startActivity(intent);
            }
        });
        //
        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                intent.putExtra("actionCRUD", "delete");
                startActivity(intent);
            }
        });
        return view;
    }

}
