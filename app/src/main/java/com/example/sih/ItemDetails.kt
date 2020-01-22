package com.example.sih

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.item_details.*

class ItemDetails : AppCompatActivity() {

    var categoryId=0
    var db:SQLiteDatabase?=null
    var cursor :Cursor?=null
    var itemAdapter:ItemAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_details)

        categoryId=intent.extras!!.get("CATEGORY_ID").toString().toInt()
        Toast.makeText(this,categoryId.toString(),Toast.LENGTH_LONG).show()

        //read Data base

        val myNotesDatabaseHelper=CategorySQliteOpenHelper(this)
        db=myNotesDatabaseHelper.readableDatabase

        cursor = db!!.query(""+
                "lists", arrayOf("list"),"category_id=?", arrayOf(categoryId.toString()),
            null,null,null)
        val lists = mutableListOf<String>()

        while(cursor!!.moveToNext()==true)
        {
            val list = cursor!!.getString(0)
            lists.add(list)
        }

        //Adapter object


        //Use Layout manager

        val layoutManager = LinearLayoutManager(this)

        recyclerViewItems.adapter=itemAdapter
        recyclerViewItems.layoutManager=layoutManager
    }
    override fun onDestroy()
    {
        super.onDestroy()
        db!!.close()
        cursor!!.close()
    }
}