package com.example.assignment1

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Expense::class], version = 1)
abstract class ExpenseDatabase : RoomDatabase() {

    abstract fun expenseDao() : ExpenseDao

    companion object{

        private var INSTANCE: ExpenseDatabase? = null

        fun getInstance(context: Context): ExpenseDatabase {
            @Volatile
            if(INSTANCE == null){
                synchronized(this){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        ExpenseDatabase::class.java,
                        "Expense"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}