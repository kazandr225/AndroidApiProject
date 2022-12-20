package com.example.apiproject;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListItem {
    Connection connect;
    String ConnectionResult = "";
    Boolean isSuccess  = false;

    public List<Map<String,String>> getList(String qu){
        List<Map<String,String>> data = null;
        data = new ArrayList<Map<String,String>>();
        try{
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionClass();
            if(connect != null)
            {

                Statement statement = connect.createStatement();
                ResultSet resultSet = statement.executeQuery(qu);
                while(resultSet.next())
                {
                    Map<String,String> dtname = new HashMap<String, String>();
                    dtname.put("id_employee",resultSet.getString("id_employee"));
                    dtname.put("Surname",resultSet.getString("Surname"));
                    dtname.put("Name",resultSet.getString("Name"));
                    dtname.put("Patronymic",resultSet.getString("Patronymic"));
                    data.add(dtname);
                }
                ConnectionResult = "Success";
                isSuccess = true;
                connect.close();
            }
            else {
                ConnectionResult = "Failed";
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  data;
    }
}
