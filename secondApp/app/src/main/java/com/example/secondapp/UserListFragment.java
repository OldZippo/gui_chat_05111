package com.example.secondapp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserListFragment extends Fragment {
    Button addUser, updateUser, deleteUser, insertUserBtn, replaceUserBtn, deleteUserBtn;
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user_list, viewGroup, false);
        //
        addUser = view.findViewById(R.id.addUser);
       /* updateUser = view.findViewById(R.id.updateUser);
        deleteUser = view.findViewById(R.id.deleteUser);*/
        //
        insertUserBtn = view.findViewById(R.id.insertUserBtn);
        replaceUserBtn = view.findViewById(R.id.replaceUserBtn);
        deleteUserBtn = view.findViewById(R.id.deleteUserBtn);
        //
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                intent.putExtra("actionCRUD", "add");
                startActivity(intent);
            }
        });
       /* //
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
        });*/
        return view;
    }
    //
    private void recyclerViewInit(){
        Users users = Users.get(getActivity());
        List<User> userList = users.getUserList();
        List<String> userNameList = new ArrayList<>();
        for (User user:userList) {
            userNameList.add(user.getUserName());
        }
        userAdapter = new UserAdapter(userNameList);
        recyclerView.setAdapter(userAdapter);
    }
    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
        Log.d("SYSTEM INFO: ", "?????????? onResume() ??????????????");
    }
    // UserHolder ???????????????? ???? ???????????? ?????????????? ???????????? ???? ??????????????????????
    // ???????????? ?????????? ???? ?????????? ?????????????????? ???????? activity_item ??????????????????
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        String userName;
        int position;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.activity_item,viewGroup,false));
            //itemView - ?????????????? layout activity_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }
        public void bind(String userName, int position){
            this.userName = userName;
            this.position = position;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(),userName+" - ????????!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), UserActivity.class);
            intent.putExtra("position",position);
            startActivity(intent);
        }
     }
    // UserAdapter ?????????? ?????? ????????, ?????????? ???????????????????? ???????????????? ???? RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        List<String> userList;
        public UserAdapter(List<String> userList) {
            this.userList = userList;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            // inflater - ??????????????????????
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {
            String userName = userList.get(position);
            userHolder.bind(userName, position);
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }
}