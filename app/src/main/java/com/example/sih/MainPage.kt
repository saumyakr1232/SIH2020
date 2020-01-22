package com.example.sih

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main_page.*

class MainPage : AppCompatActivity() {

    var db: SQLiteDatabase? = null
    var cursor: Cursor? = null
    var categoriesAdapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //read categories from database
        val myNotesDatabaseHelper = CategorySQliteOpenHelper(this)
        db = myNotesDatabaseHelper.readableDatabase

        cursor = db!!.query(
            "categories", arrayOf("_id", "image_resource_id", "name"),
            null, null, null, null, null
        )
        val listOfCategories = mutableListOf<Category>()

        while (cursor!!.moveToNext()) {
            val categoryId = cursor!!.getInt(0)
            val categoryResourceId = cursor!!.getInt(1)
            val categoryName = cursor!!.getString(2)

            val category = Category(categoryId, categoryResourceId, categoryName)
            listOfCategories.add(category)
        }
        categoriesAdapter = CategoryAdapter(this, listOfCategories) { categoryId ->
            val intent = Intent(this, MainPage::class.java)
            intent.putExtra("QUOTE_CATEGORY_ID", categoryId)
            startActivity(intent)
        }

        //using layout manager
        //val categoriesLayoutManager= LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)

        val categoryGridLayoutManager = GridLayoutManager(this, 2)
        recyclerViewQuoteCategories.adapter = categoriesAdapter
        recyclerViewQuoteCategories.layoutManager = categoryGridLayoutManager


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = resources.getString(R.string.home)
        loadFragment(HomeFragment())

        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {

                R.id.navigation_home -> {
                    title = resources.getString(R.string.home)
                    loadFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.navigation_myaccount -> {
                    title = resources.getString(R.string.person)
                    loadFragment(MyaccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_remainder -> {
                    title = resources.getString(R.string.remainder)
                    loadFragment(MyaccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.navigation_cart -> {
                    title = resources.getString(R.string.cart)
                    loadFragment(MyaccountFragment())
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false

        }
    }


        private fun loadFragment(fragment: Fragment) {
            // load fragment
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

    override fun onDestroy() {
        super.onDestroy()
        db!!.close()
        cursor!!.close()

    }

    }



