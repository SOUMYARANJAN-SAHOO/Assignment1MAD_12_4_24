package com.example.assignment1

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecyclerAdapter(private val expenseList: List<Expense>, private val viewModel: MainViewModel):RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>() {

    inner class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val deletebtn = itemView.findViewById<ImageButton>(R.id.btDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_single_row,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return expenseList.size
    }

    @SuppressLint("MissingInflatedId")
    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val item = expenseList[position]
        holder.title.text = item.title
        holder.deletebtn.setOnClickListener {
            viewModel.deleteData(item)
        }
    }
}