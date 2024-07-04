package com.jpp.usercentricschallenge.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun HomeScreen(viewModel: HomeScreenViewModel) {
    val viewState by viewModel.state.collectAsStateWithLifecycle()

    HomeScreenInternal(
        mainActionEnabled = viewState.collectButtonEnabled,
        cost = viewState.cost.toString(),
        onActionClicked = { viewModel.collectConsent() },
    )
}

@Composable
private fun HomeScreenInternal(
    mainActionEnabled: Boolean,
    cost: String,
    onActionClicked: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ConsentScore(
            cost = cost,
            modifier =
                Modifier
                    .weight(1f)
                    .fillMaxSize(),
        )
        Button(
            onClick = onActionClicked,
            enabled = mainActionEnabled,
            modifier =
                Modifier
                    .fillMaxWidth(),
        ) {
            Text(text = "Show Consent Banner")
        }
    }
}

@Composable
private fun ConsentScore(
    modifier: Modifier,
    cost: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = cost,
            fontSize = 120.sp,
        )
        Text(text = "Consent Score", fontSize = 24.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenInternal(
        mainActionEnabled = true,
        cost = "2",
        onActionClicked = { },
    )
}
