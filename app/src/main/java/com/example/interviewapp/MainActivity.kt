package com.example.interviewapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewapp.adapter.DataListAdapter
import com.example.interviewapp.databinding.ActivityMainBinding
import com.example.interviewapp.model.DataListModel
import com.example.interviewapp.viewmodwel.DataViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DataViewModel by viewModels()

    private lateinit var listUserData: ArrayList<DataListModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        listUserData = ArrayList()

        listUserData = createDummyDataList()
        viewModel.postData(listUserData)

        var linearLayoutManager = LinearLayoutManager(this@MainActivity)
        binding.userListView.layoutManager = linearLayoutManager

        viewModel.getFilterUserData.observe(this@MainActivity, Observer { listData ->
            var adapter = DataListAdapter(this@MainActivity, listData)
            binding.userListView.adapter = adapter
        })
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.itemByAge -> {
                Toast.makeText(this@MainActivity, "Toast", Toast.LENGTH_SHORT).show()
                var filterByAge = listUserData.apply {
                    sortBy { it.age }
                }
                viewModel.postData(filterByAge)
                return true
            }
            R.id.itemByName -> {
                Toast.makeText(this@MainActivity, "Toast", Toast.LENGTH_SHORT).show()
                var filterByName = listUserData.apply {
                    sortBy { it.name }
                }
                viewModel.postData(filterByName)
                return true
            }
            R.id.itemByCity -> {
                var filterByCity = listUserData.apply {
                    sortBy { it.city }
                }
                viewModel.postData(filterByCity)
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }


    fun createDummyDataList(): ArrayList<DataListModel> {
        val dataList = ArrayList<DataListModel>()
        for (i in 1..50) {
            val name = getRandomName()
            val age = (18..60).random()
            val city = getRandomCity()
            val data = DataListModel(name.toString(), age, city)
            dataList.add(data)
        }

        return dataList
    }

    fun getRandomCity(): String {
        val cities = listOf("New York", "London", "Paris", "Tokyo", "Sydney")
        return cities.random()
    }

    fun getRandomName(): String {
        var nameList =
            listOf("firstUser", "secondUser", "thirdUser", "yourUser", "oifthUser", "pixUser")
        return nameList.random()
    }
}