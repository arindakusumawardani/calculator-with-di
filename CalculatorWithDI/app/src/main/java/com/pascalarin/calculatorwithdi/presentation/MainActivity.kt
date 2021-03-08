package com.pascalarin.calculatorwithdi.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.pascalarin.calculatorwithdi.R
import com.pascalarin.calculatorwithdi.data.repository.CalculatorRepositoryPostImpl
import com.pascalarin.calculatorwithdi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        binding.apply {
            calcButton.setOnClickListener {
                viewModel.doCalculatorCount().observe(this@MainActivity, {
                    Log.d("calc", it)
                })
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val getRepo = CalculatorRepositoryPostImpl()
                return MainActivityViewModel(getRepo) as T
            }
        }).get(MainActivityViewModel::class.java)
    }
}