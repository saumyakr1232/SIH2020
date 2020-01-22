package com.example.sih

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CategorySQliteOpenHelper(context: Context):SQLiteOpenHelper(context,null,null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        // create two tables... for quotes categories and quotes
        db!!.execSQL("CREATE TABLE categories (" +
                "_id INTEGER PRIMARY KEY,"+
                "image_resource_id TEXT,"+
                "name TEXT" + ")")

        insertItemCategory(db,1,R.drawable.department,"Department")
        insertItemCategory(db,2,R.drawable.doctors,"Doctors")
        insertItemCategory(db,3,R.drawable.hospitals,"Hospitals")
        insertItemCategory(db,4,R.drawable.test,"Test")

        // table for quotes

        db.execSQL("CREATE TABLE items("+
                "item TEXT,"+
                "category_id INTEGER" +")")


    }

    fun insertList(db:SQLiteDatabase?,quote:String,categoryId:Int)
    {
        val contentValues=ContentValues()
        contentValues.put("quote",quote)
        contentValues.put("category_id",categoryId)

        db!!.insert("items",null,contentValues)
    }

    private fun insertItemCategory (db:SQLiteDatabase?, id:Int, resourceId:Int, name:String){
        val contentValues= ContentValues()
        contentValues.put("_id",id)
        contentValues.put("image_resource_id",resourceId)
        contentValues.put("name",name)

        db!!.insert("categories",null,contentValues)

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
}