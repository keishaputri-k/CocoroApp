package com.kei.cocoroapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kei.cocoroapp.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        tv_create.setOnClickListener{
            moveToRegister()
        }
    }

    private fun moveToRegister() {
        val intent = Intent (this,
            RegisterActivity::class.java)
        startActivity(intent)
    }
}