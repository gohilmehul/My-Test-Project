package com.example.myproject.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myproject.Adapter.DrugsAdapter
import com.example.myproject.Model.OfflineViewModel
import com.example.myproject.Network.DataHandler
import com.example.myproject.R
import com.example.myproject.databinding.ActivityOfflineBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class OffLineActivity : AppCompatActivity(R.layout.activity_home) {


    val viewModel: OfflineViewModel by viewModels()

    @Inject
    lateinit var drugsAdapter: DrugsAdapter

    private lateinit var binding: ActivityOfflineBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOfflineBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        viewModel.article.observe(this, Observer { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {
                     drugsAdapter.differ.submitList(dataHandler.data)
                    binding.frameLoading.visibility = View.GONE
                }
                is DataHandler.ERROR -> {
                    binding.frameLoading.visibility = View.GONE
                }
                is DataHandler.LOADING -> {
                    binding.frameLoading.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun init() {

        drugsAdapter.onArticleClicked {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("Name", it.name)
            intent.putExtra("Dose", it.dose)
            intent.putExtra("Strength", it.strength)
            startActivity(intent)

        }
        binding.listView.apply {
            adapter = drugsAdapter
            layoutManager = LinearLayoutManager(this@OffLineActivity)
        }

    }

}