package br.com.estudo.bora

import retrofit2.http.Body
import retrofit2.http.POST

interface ShopperTaxiAPI {

    @POST("/ride/estimate")
    suspend fun rideEstimate(@Body rideEstimateRequest: RideEstimateRequest): RideEstimateResponse
}