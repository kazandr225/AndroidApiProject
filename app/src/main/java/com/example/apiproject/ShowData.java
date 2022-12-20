package com.example.apiproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;;

public class ShowData extends AppCompatActivity implements  View.OnClickListener{

    RadioButton btnSortName, btnSortSurname;
    Button btnSearch;

    EditText etSearch;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_show_data);
    }

    TextView tvEmployeeId, tvSurname,tvName, tvPatronymic;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        tvEmployeeId = findViewById(R.id.tvChangeEmployeeID);
        tvName = findViewById(R.id.tvName);
        tvSurname = findViewById(R.id.tvSurname);
        tvPatronymic = findViewById(R.id.tvPatronymic);

        btnSortName = findViewById(R.id.btnSortName);
        btnSortName.setOnClickListener(this);

        btnSortSurname = findViewById(R.id.btnSortSurname);
        btnSortSurname.setOnClickListener(this);

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        etSearch = findViewById(R.id.etSearch);


        ListView listView = findViewById(R.id.listview1);
        List<String> list = GetList("select * from Employees");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tvSurname = view.findViewById(R.id.tvSurname);
                String surname = tvSurname.getText().toString();

                tvName = view.findViewById(R.id.tvName);
                String name = tvName.getText().toString();

                tvPatronymic = view.findViewById(R.id.tvPatronymic);
                String patronymic = tvPatronymic.getText().toString();

                tvEmployeeId = view.findViewById(R.id.tvChangeEmployeeID);
                String employeeID = tvEmployeeId.getText().toString();

                Intent intent = new Intent(ShowData.this, UpdateEmployee.class);

                intent.putExtra("Surname", surname);
                intent.putExtra("Name", name);
                intent.putExtra("Patronymic", patronymic);
                intent.putExtra("EmployeeID", employeeID);

                startActivity(intent);

            }
        });
    }

    SimpleAdapter ad;

    public List GetList(String qu)
    {
        ListView lstv = (ListView) findViewById(R.id.listview1);
        List<Map<String, String>> MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getList(qu);

        String[] Fromw = {"id_employee", "Surname", "Name", "Patronymic"};
        int[] Tow = {R.id.tvChangeEmployeeID, R.id.tvSurname, R.id.tvName, R.id.tvPatronymic};

        ad = new SimpleAdapter(ShowData.this, MyDataList, R.layout.list_template, Fromw, Tow);
        lstv.setAdapter(ad);

        return MyDataList;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.btnSortSurname:
                GetList("select * from Persons order by Surname");
                break;
            case R.id.btnSortName:
                GetList("select * from Persons order by Name");
                break;
            case R.id.btnSearch:

                String searchData = etSearch.getText().toString();
                GetList("select * from Employees where Name like '%" + searchData + "%' or Surname like '%" + searchData + "%'");
                break;
        }
    }
}