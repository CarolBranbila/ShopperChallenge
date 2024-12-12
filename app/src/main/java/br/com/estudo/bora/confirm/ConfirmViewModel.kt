package br.com.estudo.bora.confirm

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class ConfirmViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(ConfirmViewState())
    val viewState = _viewState.asStateFlow()

    private val _event = Channel<ConfirmEvents>(Channel.BUFFERED)
    val event: Flow<ConfirmEvents> = _event.receiveAsFlow()

}

sealed interface ConfirmEvents {

}

data class ConfirmViewState(
    val riderList: List<RiderItemState> = emptyList()
)

data class RiderItemState(
    val id: String,
    val name: String,
    val description: String,
    val price: String,
    val reputation: String,
    val car: String,
)