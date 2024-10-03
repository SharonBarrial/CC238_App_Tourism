package com.example.upc_pre_202401_cc238_ea.activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.upc_pre_202401_cc238_ea.R
import com.example.upc_pre_202401_cc238_ea.adapter.PackageAdapter
import com.example.upc_pre_202401_cc238_ea.database.AppDatabase
import com.example.upc_pre_202401_cc238_ea.database.PackageDao
import com.example.upc_pre_202401_cc238_ea.model.Package
import com.example.upc_pre_202401_cc238_ea.network.RandomPackageApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author Sharon Antuanet Ivet Barrial Marin
 * @version 1.0
 */

class PackageActivity : AppCompatActivity(), PackageAdapter.OnItemClickListener {

    lateinit var packs: List<Package>
    lateinit var packageAdapter: PackageAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_packages) // Asegúrate de que esta línea esté presente

        // Initialize the adapter
        packageAdapter = PackageAdapter(emptyList(), this)

        val btSearch = findViewById<Button>(R.id.btSearch)

        // Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar2)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"))

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // RecyclerView setup
        val rvPackages = findViewById<RecyclerView>(R.id.rvResult)
        rvPackages.adapter = packageAdapter
        rvPackages.layoutManager = LinearLayoutManager(this)

        btSearch.setOnClickListener {
            searchPackages()
        }
    }

    override fun onItemClick(pack: Package) {
        //FavoriteActivity.addFavorites(this, pack)
        val dao = AppDatabase.getInstance(this).getDao()
        dao.insert(pack)

        Toast.makeText(this, "Person "+pack.nombre+" added to favorites", Toast.LENGTH_SHORT).show()
    }

    private fun searchPackages() {
        // Create the Retrofit object
        val retrofit = Retrofit.Builder()
            .baseUrl("https://dev.formandocodigo.com/ServicioTour/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val packageService = retrofit.create(RandomPackageApiService::class.java)

        val etSideId = findViewById<EditText>(R.id.etSite).text.toString()
        val etTypeId = findViewById<EditText>(R.id.etType).text.toString()

        val request = packageService.getPackages(etSideId, etTypeId)

        // Make the request to the server
        request.enqueue(object : Callback<List<Package>> {
            override fun onResponse(call: Call<List<Package>>, response: Response<List<Package>>) {
                if (response.isSuccessful && response.body() != null) {
                    packs = response.body()!!

                    val rvPack = findViewById<RecyclerView>(R.id.rvResult)

                    packageAdapter = PackageAdapter(packs, this@PackageActivity)
                    rvPack.adapter = packageAdapter

                    rvPack.layoutManager = LinearLayoutManager(this@PackageActivity)
                }
            }

            override fun onFailure(call: Call<List<Package>>, t: Throwable) {
                // Manage failure
                Log.e("PackagesActivity", "onFailure: ${t.message}")
                Toast.makeText(this@PackageActivity, "Failed to load packages: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> { // ID de la flecha de retroceso
                finish() // O puedes usar 'onBackPressed()' para volver a la actividad anterior
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}