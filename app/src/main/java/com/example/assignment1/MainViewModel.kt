package com.example.assignment1

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(context: Context): ViewModel() {

    var data :LiveData<List<Expense>>
    val  expenseDatabase = ExpenseDatabase.getInstance(context.applicationContext)

    init {
        data = expenseDatabase.expenseDao().getallExpense()
    }

    fun insertData(expense: Expense){
        viewModelScope.launch {
            expenseDatabase.expenseDao().insertExpense(expense)
            data = expenseDatabase.expenseDao().getallExpense()
        }
    }
    fun deleteData(expense: Expense){
        viewModelScope.launch {
            expenseDatabase.expenseDao().deleteExpense(expense)
            data = expenseDatabase.expenseDao().getallExpense()
        }
    }

}