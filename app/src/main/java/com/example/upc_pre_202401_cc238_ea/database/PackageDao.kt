package com.example.upc_pre_202401_cc238_ea.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.upc_pre_202401_cc238_ea.model.Package


/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

@Dao
interface PackageDao {
    //CRUD
    //Room just works with List
    @Insert
    fun insert(pkg: Package)

    @Query("SELECT * FROM package")
    fun getAll(): List<Package>

    @Delete
    fun delete(pkg: Package)

    @Update
    fun update(pkg: Package)

}