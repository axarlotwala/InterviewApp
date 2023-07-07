package com.example.interviewapp.viewmodwel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewapp.model.DataListModel

class DataViewModel : ViewModel() {

    private val postMutableData = MutableLiveData<ArrayList<DataListModel>>()
    val getFilterUserData: MutableLiveData<ArrayList<DataListModel>> = postMutableData

    fun postData(dataList: ArrayList<DataListModel>) {
        postMutableData.postValue(dataList)
    }
}