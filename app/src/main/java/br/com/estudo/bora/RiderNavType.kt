package br.com.estudo.bora

import android.os.Bundle
import androidx.navigation.NavType

class RiderNavType : NavType<RideEstimateResponse>(false) {

    override fun get(bundle: Bundle, key: String): RideEstimateResponse? {
        TODO("Not yet implemented")
    }

    override fun parseValue(value: String): RideEstimateResponse {
        TODO("Not yet implemented")
    }

    override fun put(bundle: Bundle, key: String, value: RideEstimateResponse) {
        TODO("Not yet implemented")
    }
}