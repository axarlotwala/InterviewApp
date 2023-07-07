package com.example.interviewapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.interviewapp.databinding.RowUserItemBinding
import com.example.interviewapp.model.DataListModel

class DataListAdapter(var context: Context, val dataList: ArrayList<DataListModel>) :
    RecyclerView.Adapter<DataListAdapter.DataListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowUserItemBinding.inflate(inflater, parent, false)
        return DataListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataListViewHolder, position: Int) {
        val data = dataList[position]
        holder.binding.apply {
            textViewName.text = "UserName: ${data.name}"
            textViewAge.text = "Age: ${data.age}"
            textViewCity.text = "City: ${data.city}"
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    inner class DataListViewHolder(val binding: RowUserItemBinding) :
        RecyclerView.ViewHolder(binding.root) {}

}