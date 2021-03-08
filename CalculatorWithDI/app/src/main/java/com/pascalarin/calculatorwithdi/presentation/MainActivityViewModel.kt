package com.pascalarin.calculatorwithdi.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.enigmacamp.myhiltcalculator.data.model.CalculatorRequest
import com.pascalarin.calculatorwithdi.data.repository.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout

class MainActivityViewModel(private val repository : CalculatorRepository) : ViewModel() {

    fun doCalculatorCount() = liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
        withTimeout(2000) {
            try {
                emit("Please Wait !")
                val response = repository.doCount(CalculatorRequest(number1 = "8", number2 = "2", operator = "/"))
                emit(response.result).toString()
            }catch (e:Exception) {
                Log.d("Calc", e.toString())
                emit("Error")
            }finally {
                emit("Done")
            }
        }
    }
}