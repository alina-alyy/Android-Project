package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.API.APIClient;
import com.example.myapplication.API.APIInterface;
import com.example.myapplication.API.UserList;
import com.example.myapplication.adapters.LIstViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListUserFragment extends Fragment {
    ListView list_view;
    APIInterface apiInterface;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<UserList> userListReq = apiInterface.doGetUserList("1");
        list_view = view.findViewById(R.id.UserListView);
//        responseText = view.findViewById(R.id.listUserResponse);

        userListReq.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {

                if(response.isSuccessful()) {
                    UserList userList = response.body();


                    ArrayList<UserList.User> usersList = userList.users;

                    LIstViewAdapter listViewAdapter =  new LIstViewAdapter(getContext(),R.layout.list_item, usersList);
                    list_view.setAdapter(listViewAdapter);

//                    String displayResponse = "";
//
//                    for (UserList.User user : usersList) {
//                        displayResponse += "id : " + user.id + " name: " + user.first_name + " " + user.last_name + " avatar: " + user.avatar;
//                    }
//
//                    responseText.setText(displayResponse);
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
                call.cancel();
            }
        });

    }
}