package com.pascalarin.calculatorwithdi.presentation

import android.util.Log
import androidx.lifecycle.*
import com.enigmacamp.myhiltcalculator.data.model.CalculatorRequest
import com.pascalarin.calculatorwithdi.data.repository.CalculatorRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withTimeout

class MainActivityViewModel(private val repository: CalculatorRepository) : ViewModel() {

//    private var _mutableResult = MutableLiveData<String>()
//    val mutableResult: LiveData<String>
//        get() {
//            return _mutableResult
//        }

    fun calculatorCount(calculatorRequest: CalculatorRequest) =
            liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                withTimeout(2000) {
                    try {
//                        emit("Please Wait !")
                        val response = repository.doCount(calculatorRequest)
                        emit(response.result).toString()
                    } catch (e: Exception) {
                        Log.d("Calc", e.toString())
                        emit("Error")
                    }
                }
            }

    fun validation(request: CalculatorRequest, ) =
            when (request.operator) {
                "+", "-", "*", "/" -> calculatorCount(request)
                else -> liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
                    withTimeout(1000) {
                        try {
                            emit("Wrong Input")
                        } catch (e: Exception) {
                            Log.d("Calc", e.toString())
                            emit("error")
                        }
                    }
                }
            }
}
