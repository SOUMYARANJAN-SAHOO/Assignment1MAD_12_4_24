package com.example.assignment1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RecyclerAdapter
    lateinit var fab:FloatingActionButton

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, MainViewModelFactory(applicationContext)).get(MainViewModel::class.java)

        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

        viewModel.data.observe(this, Observer {data->
            adapter = RecyclerAdapter(data, viewModel)
            recyclerView.adapter = adapter
        })

        fab = findViewById(R.id.fab)
        fab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.alert_dialog,null)
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Insert Expense")
            alert.setView(view)
            alert.setPositiveButton("Add"){ _,_ ->
                val editText = view.findViewById<EditText>(R.id.EditTextTitle)
                val newText:String = editText.text.toString()
                viewModel.insertData(Expense(0,newText))
            }.show()

        }

    }

}