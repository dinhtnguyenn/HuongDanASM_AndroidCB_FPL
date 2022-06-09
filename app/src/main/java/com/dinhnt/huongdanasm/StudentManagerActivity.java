package com.dinhnt.huongdanasm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.dinhnt.huongdanasm.dao.ClassesDAO;
import com.dinhnt.huongdanasm.model.Classes;

import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        //ánh xạ
        Spinner spnClasses = findViewById(R.id.spnClasses);
        Button btnAddStudent = findViewById(R.id.btnAddStudent);

        //data
        ClassesDAO classesDAO = new ClassesDAO(StudentManagerActivity.this);
        ArrayList<Classes> list = classesDAO.getAll();

        //adapter
        ArrayList<HashMap<String, String>> listSPN = new ArrayList<>();
        for(Classes classes : list){
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", classes.getId());
            hashMap.put("name", classes.getName());
            listSPN.add(hashMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(StudentManagerActivity.this,
                                    listSPN,
                                    R.layout.item_spinner,
                                    new String[]{"id", "name"},
                                    new int[]{R.id.txtID, R.id.txtName});

        //add adpater spinner
        spnClasses.setAdapter(adapter);

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> hashMap = (HashMap<String, String>) spnClasses.getSelectedItem();
                String idLop = hashMap.get("id");
                String nameLop = hashMap.get("name");

                Toast.makeText(StudentManagerActivity.this, "" + idLop + " - " + nameLop, Toast.LENGTH_SHORT).show();
            }
        });
    }
}