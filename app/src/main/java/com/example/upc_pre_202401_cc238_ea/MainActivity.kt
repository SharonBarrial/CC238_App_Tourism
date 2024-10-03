package com.example.upc_pre_202401_cc238_ea

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.upc_pre_202401_cc238_ea.activities.FavoriteActivity
import com.example.upc_pre_202401_cc238_ea.activities.PackageActivity

class MainActivity : AppCompatActivity() {
    private lateinit var findBtn: Button
    private lateinit var favoriteBtn: Button

    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        findBtn = findViewById(R.id.btFind)
        favoriteBtn = findViewById(R.id.btFavorite)

        findBtn.setOnClickListener {
            val intent = Intent(this, PackageActivity::class.java)
            startActivity(intent)
        }

        favoriteBtn.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}