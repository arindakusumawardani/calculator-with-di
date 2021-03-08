package com.pascalarin.calculatorwithdi.data.api

import com.enigmacamp.myhiltcalculator.data.model.CalculatorRequest
import com.enigmacamp.myhiltcalculator.data.model.CalculatorResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface CalculatorApi {

    @POST("calculate")
    suspend fun postCalculatorProcess(
        @Body calculatorRequest: CalculatorRequest
    ) : CalculatorResponse
}