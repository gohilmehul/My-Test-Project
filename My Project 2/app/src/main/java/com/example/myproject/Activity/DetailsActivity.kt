package com.example.myproject.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myproject.R
import com.example.myproject.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity(R.layout.activity_details) {


    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _init()
    }

    private fun _init() {

        val actionbar = supportActionBar
        actionbar!!.title = getString(R.string.detail)
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)


        binding.tvName.text = intent.getStringExtra("Name").toString()
        binding.tvDose.text = intent.getStringExtra("Dose").toString()
        binding.tvStrength.text = intent.getStringExtra("Strength").toString()
    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}