package com.example.myproject.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.databinding.ActivityLoginsBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _onClick();
    }



    private fun _onClick() {
        binding.btnPassword.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("Email",binding.etEmail.text.toString())
            intent.putExtra("Password",binding.etPassword.text.toString())
            startActivity(intent)
            finish()
        }


        binding.btnOffline.setOnClickListener {
            val intent = Intent(this, OffLineActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}