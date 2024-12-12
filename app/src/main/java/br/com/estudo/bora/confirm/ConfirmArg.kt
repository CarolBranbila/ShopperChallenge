package br.com.estudo.bora.confirm

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConfirmArg(
    val rider: List<RiderArg>
) : Parcelable

@Parcelize
data class RiderArg(
    val name: String,
    val description: String,
    val price: String,
    val reputation: String,
    val car: String,
) : Parcelable