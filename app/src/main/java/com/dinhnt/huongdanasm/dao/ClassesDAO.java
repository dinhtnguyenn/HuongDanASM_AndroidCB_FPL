package com.dinhnt.huongdanasm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.dinhnt.huongdanasm.database.StudentDatabase;
import com.dinhnt.huongdanasm.model.Classes;

import java.util.ArrayList;

public class ClassesDAO {
    StudentDatabase studentDatabase;

    public ClassesDAO(Context context) {
        studentDatabase = new StudentDatabase(context);
    }

    public ArrayList<Classes> getAll() {
        SQLiteDatabase database = studentDatabase.getReadableDatabase();
        ArrayList<Classes> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM CLASSES", null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            do {
                list.add(new Classes(cursor.getString(0), cursor.getString(1)));
            } while (cursor.moveToNext());
        }

        return list;
    }

    public boolean insertClasses(Classes classes){
        try {
            SQLiteDatabase database = studentDatabase.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("id", classes.getId());
            contentValues.put("name", classes.getName());
            long value = database.insert("CLASSES", null, contentValues);
            if(value == -1){
                return false;
            }else {
                return true;
            }
        }catch (Exception ex){
            return false;
        }
    }

}
