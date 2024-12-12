package br.com.estudo.bora.confirm

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.estudo.bora.ui.theme.BoraTheme
import com.google.maps.android.compose.GoogleMap

@Composable
fun ConfirmScreen() {
    Scaffold { paddingValues ->
        ConfirmScreenContent(
            modifier = Modifier.padding(paddingValues),
            viewState = ConfirmViewState()
        )
    }
}

@Composable
private fun ConfirmScreenContent(
    modifier: Modifier,
    viewState: ConfirmViewState,
) {
    Column(
        modifier = modifier
    ) {
        GoogleMap(
            modifier = Modifier
                .weight(0.5f)
                .fillMaxWidth()
        )

        LazyColumn(
            modifier = Modifier
                .weight(0.5f)
        ) {
            items(viewState.riderList) {
                DriverItem(
                    name = it.name,
                    description = it.description,
                    ridePrice = it.price,
                    reputation = it.reputation,
                    car = it.car,
                    onConfirm = {}
                )
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun ConfirmScreenContentPreview() {
    BoraTheme {
        ConfirmScreenContent(
            modifier = Modifier,
            viewState = ConfirmViewState(
                riderList = buildList {
                    repeat(3) {
                        add(
                            RiderItemState(
                                id = "maximus",
                                name = "Dorothy McCarthy",
                                price = "10",
                                reputation = "10",
                                car = "hb20",
                                description = "rider description"
                            )
                        )
                    }
                }
            )
        )
    }
}

@Composable
private fun DriverItem(
    name: String,
    description: String,
    ridePrice: String,
    reputation: String,
    car: String,
    onConfirm: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    Text(
                        text = "Ride Price: $ridePrice",
                        style = MaterialTheme.typography.labelMedium,
                    )

                    Text(
                        text = "Avaliation: $reputation/10",
                        style = MaterialTheme.typography.labelMedium,
                    )

                    Text(
                        text = "Car: $car",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }

                Button(
                    onClick = onConfirm,
                ) {
                    Text(
                        text = "Confirm",
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
@PreviewLightDark
private fun DriverItemPreview() {
    BoraTheme {
        DriverItem(
            name = "Raquel Warner",
            description = "atqui",
            ridePrice = "20",
            reputation = "10",
            car = "sadipscing",
            onConfirm = {}

        )
    }
}