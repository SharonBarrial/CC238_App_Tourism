package com.example.upc_pre_202401_cc238_ea.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea.R
import com.example.upc_pre_202401_cc238_ea.adapter.PackageAdapter
import com.example.upc_pre_202401_cc238_ea.database.AppDatabase
import com.example.upc_pre_202401_cc238_ea.model.Package

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

class FavoriteActivity: AppCompatActivity(), PackageAdapter.OnItemClickListener {

    private lateinit var rvFavorite : RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)

        setSupportActionBar(findViewById(R.id.toolbar2))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        rvFavorite = findViewById(R.id.rvFavorites)
    }

    override fun onResume() {
        super.onResume()

        loadPack { pack ->
            rvFavorite.adapter = PackageAdapter(pack, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun loadPack(onComplete: (List<Package>)-> Unit) {
        val dao = AppDatabase.getInstance(this).getDao()

        onComplete(dao.getAll())
    }

    override fun onItemClick(pack: Package) {
        val dao = AppDatabase.getInstance(this).getDao()

        dao.delete(pack)

        Toast.makeText(this, "Person  "+pack.nombre+" deleted from favorites", Toast.LENGTH_SHORT).show()

        loadPack { packFav ->
            rvFavorite.adapter = PackageAdapter(packFav, this)
            rvFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }
}