package com.pascalarin.calculatorwithdi.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enigmacamp.myhiltcalculator.data.model.CalculatorRequest
import com.pascalarin.calculatorwithdi.R
import com.pascalarin.calculatorwithdi.data.repository.CalculatorRepositoryPostImpl
import com.pascalarin.calculatorwithdi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var calculateRequest : CalculatorRequest? = null
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.apply {
            calculateRequest?.apply {
                countBtn.text = "POST"

                number1Tiet.setText(number1)
                number2Tiet.setText(number2)
                operatorTiet.setText(operator)
            }
            countBtn.setOnClickListener{
                calculateRequest = CalculatorRequest(
                    number1 = number1Et.editText?.text.toString(),
                    number2 = number2Et.editText?.text.toString(),
                    operator = operatorEt.editText?.text.toString()
                )
                viewModel.validation(calculateRequest!!).observe(this@MainActivity, Observer{
                    binding.hasil.text = it
                })
            }
        }
        setContentView(binding.root)
        initViewModel()
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