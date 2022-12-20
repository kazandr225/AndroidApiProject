package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Button btn, btnAdd;

    Connection connection;
    String ConnectionResult = "";

    TextView id, Name, Patronymic, Surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnNext);
        btn.setOnClickListener(this);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    public void GetData(View v)
    {

        try
        {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connection = connectionHelper.connectionClass();

            if (connection != null)
            {
                String query = "Select * from Persons";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);

                while (resultSet.next())
                {
                    id.setText(resultSet.getString(1));
                    Name.setText(resultSet.getString(2));
                    Patronymic.setText(resultSet.getString(3));
                    Surname.setText(resultSet.getString(4));
                }
                connection.close();

            }

        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void onClick(View vi)
    {
        switch (vi.getId())
        {
            case R.id.btnNext:
                startActivity(new Intent(this, ShowData.class));
                break;
            case R.id.btnAdd:
                startActivity(new Intent(this, AddEmployee.class));
                break;
        }
    }
}