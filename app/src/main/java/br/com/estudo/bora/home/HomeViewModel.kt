package br.com.estudo.bora.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.estudo.bora.Option
import br.com.estudo.bora.RetrofitBuilder
import br.com.estudo.bora.RideEstimateRequest
import br.com.estudo.bora.RideEstimateResponse
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(HomeViewState())
    val viewState = _viewState.asStateFlow()

    private val _event = Channel<HomeEvents>(Channel.BUFFERED)
    val event: Flow<HomeEvents> = _event.receiveAsFlow()

    fun rideEstimate(idText: String, fromText: String, toText: String) {
        viewModelScope.launch {
            _viewState.update { it.copy(isLoading = true) }

            try {
                val response = RetrofitBuilder
                    .shopperTaxiAPI()
                    .rideEstimate(RideEstimateRequest(idText, fromText, toText))

                _event.send(
                    HomeEvents.GoToConfirm(
                        riders = response,
                    )
                )
            } catch (ex: Exception) {
                ex.printStackTrace()

                _event.send(
                    HomeEvents.ShowErrorMessage(message = ex.message.orEmpty())
                )
            }

            _viewState.update { it.copy(isLoading = false) }
        }
    }
}

data class HomeViewState(
    val isLoading: Boolean = false,
)

sealed interface HomeEvents {
    data class ShowErrorMessage(val message: String) : HomeEvents
    data class GoToConfirm(val riders: RideEstimateResponse): HomeEvents
}