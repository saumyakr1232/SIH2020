package com.example.sih

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_main.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signUp.setOnClickListener{
            val intent= Intent(this,SignUp::class.java)
            startActivity(intent)
        }

        next.setOnClickListener{

            val intent =Intent(this,MainPage::class.java)
            startActivity(intent)
        }
    }
}

