package br.com.estudo.bora.home

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import br.com.estudo.bora.Option
import br.com.estudo.bora.RideEstimateResponse
import br.com.estudo.bora.TextFieldDefault
import br.com.estudo.bora.ui.theme.BoraTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onSuccess: (RideEstimateResponse) -> Unit,
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val current = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.event.collect {
            when (it) {
                is HomeEvents.ShowErrorMessage -> {
                    Toast.makeText(current, "ERROR", Toast.LENGTH_LONG).show()
                }

                is HomeEvents.GoToConfirm -> onSuccess(it.riders)
            }
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Surface(
            modifier = Modifier.padding(innerPadding),
        ) {
            HomeScreen(
                viewState = viewState,
                onContinuedClick = { idText, fromText, toText ->
                    viewModel.rideEstimate(
                        idText = idText,
                        fromText = fromText,
                        toText = toText
                    )
                }
            )
        }
    }
}

@Composable
private fun HomeScreen(
    viewState: HomeViewState,
    onContinuedClick: (String, String, String) -> Unit
) {
    var idText by remember { mutableStateOf("") }
    var fromText by remember { mutableStateOf("") }
    var toText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(24.dp))
        IdBar(
            value = idText,
            onValueChange = { idText = it }
        )
        Spacer(Modifier.height(100.dp))
        SearchBarFrom(
            value = fromText,
            onValueChange = { fromText = it })
        Spacer(Modifier.height(16.dp))
        SearchBarTo(
            value = toText,
            onValueChange = { toText = it }
        )
        Spacer(Modifier.weight(1f))
        if (viewState.isLoading) {
            CircularProgressIndicator(
                color = Color.DarkGray,
            )
        } else {
            ButtonNavigation(onContinuedClicked = {
                onContinuedClick(idText, fromText, toText)
            })
        }

    }
}

@Composable
fun SearchBarFrom(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextFieldDefault(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
        },
        label = { Text(text = "Origem: ") },
    )
}

@Composable
fun SearchBarTo(
    modifier: Modifier = Modifier,
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {
    TextFieldDefault(
        value = value,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        label = { Text(text = "Destino:") },
    )
}


@Composable
fun IdBar(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit
) {
    TextFieldDefault(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "ID: ") },
    )

}


@Composable
fun ButtonNavigation(
    onContinuedClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            shape = CircleShape,
            onClick = onContinuedClicked
        ) {
            Text(text = "Vamos lá?")
        }
    }
}

@Preview
@Composable
private fun SearchBarFromPreview() {
    BoraTheme {
        SearchBarFrom(
            modifier = Modifier.padding(8.dp),
            value = "",
            onValueChange = {})
    }
}


@Preview
@Composable
private fun SearchBarToPreview() {
    BoraTheme { SearchBarTo(modifier = Modifier.padding(8.dp)) }
}

@Preview
@Composable
private fun IdBarPreviwe() {
    BoraTheme {
        IdBar(modifier = Modifier.padding(8.dp),
            value = "",
            onValueChange = { })
    }
}

@Preview
@Composable
private fun ButtonNavigationPreview() {
    BoraTheme { ButtonNavigation(onContinuedClicked = {}) }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    BoraTheme {
        HomeScreen(
            viewState = HomeViewState(),
            onContinuedClick = { id, from, to ->

            }
        )
    }
}