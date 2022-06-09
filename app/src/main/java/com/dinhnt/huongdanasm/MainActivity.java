package com.dinhnt.huongdanasm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dinhnt.huongdanasm.dao.ClassesDAO;
import com.dinhnt.huongdanasm.model.Classes;

public class MainActivity extends AppCompatActivity {

    private ClassesDAO classesDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ánh xạ
        Button btnAddClass = findViewById(R.id.btnAddClass);
        Button btnClassManager = findViewById(R.id.btnClassManager);
        Button btnStudentManager = findViewById(R.id.btnStudentManager);

        classesDAO = new ClassesDAO(MainActivity.this);

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //custom dialog
                showDialogAddClass();
            }
        });

        btnClassManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ClassesManagerActivity.class));
            }
        });

        btnStudentManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, StudentManagerActivity.class));
            }
        });
    }

    private void showDialogAddClass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.dialog_add_class, null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.show();

        //ánh xạ
        EditText edtClassID = view.findViewById(R.id.edtClassID);
        EditText edtClassName = view.findViewById(R.id.edtClassName);
        Button btnClear = view.findViewById(R.id.btnClear);
        Button btnAddClass = view.findViewById(R.id.btnAdd);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtClassID.setText("");
                edtClassName.setText("");
            }
        });

        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //... DAO.insertLop();
                String idClass = edtClassID.getText().toString();
                String nameClass = edtClassName.getText().toString();
                Classes classes = new Classes(idClass, nameClass);
                if(classesDAO.insertClasses(classes)){
                    Toast.makeText(MainActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Thất bại gồi", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });

    }
}