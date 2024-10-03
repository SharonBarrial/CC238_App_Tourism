package com.example.upc_pre_202401_cc238_ea.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.upc_pre_202401_cc238_ea.model.Package

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

@Database(entities = [Package::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getDao(): PackageDao

    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room
                    .databaseBuilder(context, AppDatabase::class.java, "Packages.db")
                    .allowMainThreadQueries()
                    .build()
            }
            //return INSTANCE as AppDatabase => Cambio de as por !! para evitar errores
            return INSTANCE!!
        }
    }

}