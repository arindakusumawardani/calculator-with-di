package com.pascalarin.calculatorwithdi.data.repository

import com.enigmacamp.myhiltcalculator.data.model.CalculatorRequest
import com.enigmacamp.myhiltcalculator.data.model.CalculatorResponse
import com.pascalarin.calculatorwithdi.data.api.RetrofitInstance

class CalculatorRepositoryPostImpl : CalculatorRepository {
    override suspend fun doCount(calculatorRequest: CalculatorRequest): CalculatorResponse =
        RetrofitInstance.calculatorApi.postCalculatorProcess(calculatorRequest)
}