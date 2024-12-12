package br.com.estudo.bora

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RideEstimateRequest(
    @SerialName("customer_id")
    val customerId: String,
    val origin: String,
    val destination: String
)