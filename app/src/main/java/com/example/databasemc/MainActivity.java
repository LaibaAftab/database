package com.example.databasemc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button add,view;
    Switch enroll;
    EditText n,r;
    StudentModel studentModel;
    ListView listViewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add = (Button)findViewById(R.id.button2);
        view = (Button)findViewById(R.id.button);
        enroll = (Switch)findViewById(R.id.switch1);
        n =(EditText) findViewById(R.id.name);
        r =(EditText) findViewById(R.id.name2);

        listViewStudent = findViewById(R.id.listViewStudent);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    studentModel = new StudentModel(n.getText().toString(), Integer.parseInt(r.getText().toString()), enroll.isChecked());
                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBhelper dbHelper  = new DBhelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
            }
        });

      view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBhelper dbHelper = new DBhelper(MainActivity.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
                        (MainActivity.this, android.R.layout.simple_list_item_1,list);
                listViewStudent.setAdapter(arrayAdapter);
            }
        });
    }
}