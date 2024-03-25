package com.dicoding.magame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.magame.ui.game.detail.DetailGameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val detailGameViewModel: DetailGameViewModel by viewModel()
    }
}