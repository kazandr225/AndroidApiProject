package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddEmployee extends AppCompatActivity implements View.OnClickListener
{
    EditText AddName, AddSurname, AddPatronymic;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        AddName = findViewById(R.id.AddName);
        AddSurname = findViewById(R.id.AddSurname);
        AddPatronymic = findViewById(R.id.AddPatronymic);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public  void onClick(View vi)
    {
        switch (vi.getId())
        {
            case R.id.btnAdd:

                String Name = AddName.getText().toString();
                String Surname = AddSurname.getText().toString();
                String Patronymic = AddPatronymic.getText().toString();

                addEmployee(vi, Name, Surname, Patronymic);

                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }

    protected void addEmployee(View v, String Name, String Surname, String Patronymic)
    {
        ConnectionHelper CH = new ConnectionHelper();
        Connection connection = CH.connectionClass();
        try {
            Log.w("Run: ", "Connection open!");

            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Employees(Name, Surname, Patronymic) VALUES ('"+Name+"',  '"+Patronymic+"', '"+Surname+"')");
            stmt.close();
            connection.close();
            Log.w("Run: ", "Connection close!");

            Toast.makeText(getBaseContext(),"Запись добавлена", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException ex)
        {
            Log.w("SQLException error: ", ex.getMessage());
        }
        catch (Exception ex)
        {
            Log.w("Exception error: ", ex.getMessage());
        }
    }
}