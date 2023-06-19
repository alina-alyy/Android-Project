package com.example.myapplication.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class UserList {
    @SerializedName("page")
    public Integer page;
    @SerializedName("per_page")
    public Integer perPage;
    @SerializedName("total")
    public Integer total;
    @SerializedName("total_pages")
    public Integer totalPages;
    @SerializedName("data")
        public ArrayList<User> users = new ArrayList();

    public class User {


        @SerializedName("id")
        public Integer id;
        @SerializedName("first_name")
        public String first_name;
        @SerializedName("last_name")
        public String last_name;

        @SerializedName("email")
        public String email;
        @SerializedName("avatar")
        public String avatar;

        public String getFullName(){
            return first_name + " " + last_name;
        }

    }
}
