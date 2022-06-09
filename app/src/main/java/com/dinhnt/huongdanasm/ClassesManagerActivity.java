package com.dinhnt.huongdanasm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.dinhnt.huongdanasm.adapter.ClassesAdapter;
import com.dinhnt.huongdanasm.dao.ClassesDAO;
import com.dinhnt.huongdanasm.model.Classes;

import java.util.ArrayList;

public class ClassesManagerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes_manager);

        //ánh xạ
        ListView listClasses = findViewById(R.id.listClasses);

        //data
        ClassesDAO classesDAO = new ClassesDAO(ClassesManagerActivity.this);
        ArrayList<Classes> list = classesDAO.getAll();

        //adapter
        ClassesAdapter adapter = new ClassesAdapter(list, ClassesManagerActivity.this);

        //set adaptere
        listClasses.setAdapter(adapter);
    }
}