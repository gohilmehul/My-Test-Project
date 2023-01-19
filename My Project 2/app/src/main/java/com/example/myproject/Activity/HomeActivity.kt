package com.example.myproject.Activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject.Adapter.DrugsAdapter
import com.example.myproject.Model.OfflineViewModel
import com.example.myproject.Model.OnlineViewModel
import com.example.myproject.Model.Problems
import com.example.myproject.Network.DataHandler
import com.example.myproject.R
import com.example.myproject.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.util.*
import javax.inject.Inject


@AndroidEntryPoint
class HomeActivity : AppCompatActivity(R.layout.activity_home) {
    private lateinit var tv_title: TextView
    lateinit var greeting: String

    val data = ArrayList<Problems>()
    lateinit var progress_bar: ProgressBar

    lateinit var recyclerview: RecyclerView;

    val viewModel: OnlineViewModel by viewModels()
    val viewModel1: OfflineViewModel by viewModels()

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var drugsAdapter: DrugsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _init();
        _listData();

    }

    private fun _listData() {


        viewModel.topHeadlines.observe(this) { dataHandler ->
            when (dataHandler) {
                is DataHandler.SUCCESS -> {

                    val elem =
                        JSONObject(dataHandler.data?.problems?.asJsonArray?.get(0).toString())


                    val Diabetes = elem.getJSONArray("Diabetes")
                    for (j in 0 until Diabetes.length()) {
                        val elem1: JSONObject = Diabetes.getJSONObject(j)
                        val medicationsClasses = elem1.getJSONArray("medications")
                        for (i in 0 until medicationsClasses.length()) {
                            val elem2: JSONObject = medicationsClasses.getJSONObject(i)
                            val className = elem2.getJSONArray("medicationsClasses")
                            for (i in 0 until className.length()) {
                                val elem3: JSONObject = className.getJSONObject(i)
                                val className = elem3.getJSONArray("className")
                                for (i in 0 until className.length()) {
                                    val elem4: JSONObject = className.getJSONObject(i)
                                    val associatedDrug = elem4.getJSONArray("associatedDrug")
                                    for (i in 0 until associatedDrug.length()) {
                                        val problems = Problems()
                                        problems.name = associatedDrug.getJSONObject(i).getString("name")
                                        problems.dose = associatedDrug.getJSONObject(i).getString("dose")
                                        problems.strength = associatedDrug.getJSONObject(i).getString("strength")
                                        data.add(problems)
                                        viewModel1.insertArticle(problems)
                                    }
                                    val associatedDrug2 = elem4.getJSONArray("associatedDrug#2")
                                    for (i in 0 until associatedDrug2.length()) {

                                        val problems = Problems()
                                        problems.name = associatedDrug2.getJSONObject(i).getString("name")
                                        problems.dose = associatedDrug2.getJSONObject(i).getString("dose")
                                        problems.strength = associatedDrug2.getJSONObject(i).getString("strength")
                                        data.add(problems)
                                        viewModel1.insertArticle(problems)

                                    }


                                }
                                val className2 = elem3.getJSONArray("className2")
                                for (i in 0 until className2.length()) {
                                    val elem5: JSONObject = className2.getJSONObject(i)
                                    val associatedDrug = elem5.getJSONArray("associatedDrug")
                                    for (i in 0 until associatedDrug.length()) {
                                        val problems = Problems()
                                        problems.name = associatedDrug.getJSONObject(i).getString("name")
                                        problems.dose = associatedDrug.getJSONObject(i).getString("dose")
                                        problems.strength = associatedDrug.getJSONObject(i).getString("strength")
                                        data.add(problems)
                                        viewModel1.insertArticle(problems)
                                    }
                                    val associatedDrug2 = elem5.getJSONArray("associatedDrug#2")
                                    for (i in 0 until associatedDrug2.length()) {
                                        val problems = Problems()
                                        problems.name = associatedDrug2.getJSONObject(i).getString("name")
                                        problems.dose = associatedDrug2.getJSONObject(i).getString("dose")
                                        problems.strength = associatedDrug2.getJSONObject(i).getString("strength")
                                        data.add(problems)
                                        viewModel1.insertArticle(problems)
                                    }
                                }
                            }
                        }

                    }

                    drugsAdapter.differ.submitList(data)
                    binding.listView.apply {
                        adapter = drugsAdapter
                        layoutManager = LinearLayoutManager(this@HomeActivity)
                    }
                    binding.frameLoading.visibility = View.GONE
                }
                is DataHandler.ERROR -> {
                    binding.frameLoading.visibility = View.GONE
                }
                is DataHandler.LOADING -> {
                    binding.frameLoading.visibility = View.VISIBLE

                }
            }

        }
        viewModel.getTopHeadlines()
        binding.frameLoading.visibility = View.GONE

    }


    private fun _init() {


        drugsAdapter.onArticleClicked {
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("Name",it.name)
            intent.putExtra("Dose",it.dose)
            intent.putExtra("Strength",it.strength)
            startActivity(intent)

        }

        val date = Date()
        val cal: Calendar = Calendar.getInstance()
        cal.setTime(date)
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)

        if (hour >= 12 && hour < 17) {
            greeting = " Good Afternoon";
        } else if (hour >= 17 && hour < 21) {
            greeting = " Good Evening";
        } else if (hour >= 21 && hour < 24) {
            greeting = " Good Night";
        } else {
            greeting = " Good Morning";
        }

        binding.tvTitle.setText("Hi, " + intent.getStringExtra("Email").toString() + greeting)
    }
}