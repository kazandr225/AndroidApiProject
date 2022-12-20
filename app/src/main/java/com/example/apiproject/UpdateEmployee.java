package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateEmployee extends AppCompatActivity  implements  View.OnClickListener{

    TextView tvEmployeeID;
    EditText Name, Surname, Patronymic;
    Button btnChange, btnDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_employee);

        tvEmployeeID = findViewById(R.id.tvUpdateEmployeeID);
        Surname = findViewById(R.id.etUpdateSurname);
        Name = findViewById(R.id.etUpdateName);
        Patronymic = findViewById(R.id.etUpdatePatromymic);

        btnChange = findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);


        Bundle arguments = getIntent().getExtras();

        tvEmployeeID.setText(arguments.get("employeeID").toString());
        Surname.setText(arguments.get("SurnameEmployee").toString());
        Name.setText(arguments.get("NameEmployee").toString());
        Patronymic.setText(arguments.get("PatronymicEmployee").toString());
    }

    @Override
    public void onClick(View view) {

        String idEmployee;
        switch (view.getId()) {

            case R.id.btnChange:
                idEmployee = tvEmployeeID.getText().toString();
                String surname = Surname.getText().toString();
                String name = Name.getText().toString();
                String patronymic = Patronymic.getText().toString();

                changePerson(view, surname, name, patronymic, idEmployee);
                startActivity(new Intent(this, ShowData.class));
                break;
            case R.id.btnDelete:
                idEmployee = tvEmployeeID.getText().toString();
                deletePerson(view, idEmployee);
                startActivity(new Intent(this, ShowData.class));
                break;
        }
    }
    protected void changePerson (View v, String surname, String name, String patronymic, String idEmployee)
    {

        ConnectionHelper CH = new ConnectionHelper();
        Connection connection = CH.connectionClass();
        try {
            Log.w("Run: ", "Connection open!");

            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE Persons SET Surname = '"+surname+"', name = '"+name+"' , patronymic = '"+patronymic+"' WHERE id_employee = " + idEmployee);
            stmt.close();
            connection.close();
            Log.w("Run: ", "Connection close!");

            Toast.makeText(getBaseContext(),"Успешно изменено", Toast.LENGTH_SHORT).show();

        } catch (SQLException ex) {
            Log.w("SQLException error: ", ex.getMessage());
        } catch (Exception ex) {
            Log.w("Exception error: ", ex.getMessage());
        }
    }
    protected void deletePerson (View v, String idEmployee)
    {

        ConnectionHelper CH = new ConnectionHelper();
        Connection connection = CH.connectionClass();
        try {
            Log.w("Run: ", "Connection open!");

            Statement stmt = connection.createStatement();
            stmt.execute("DELETE Persons WHERE id_employee = " + idEmployee);
            stmt.close();
            connection.close();
            Log.w("Run: ", "Connection close!");

            Toast.makeText(getBaseContext(),"Успешно удалено", Toast.LENGTH_SHORT).show();

        } catch (SQLException ex) {
            Log.w("SQLException error: ", ex.getMessage());
        } catch (Exception ex) {
            Log.w("Exception error: ", ex.getMessage());
        }
    }
}