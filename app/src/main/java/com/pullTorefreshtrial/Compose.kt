package com.pullTorefreshtrial

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pullTorefreshtrial.ui.theme.PullToRefreshTrialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySample(modifier: Modifier = Modifier) {

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = "My Numbers List") })
        },
        content = { paddingValues ->
            SwipeRefreshSample(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues)
            )
        })
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeRefreshSample(
    modifier: Modifier = Modifier,
    viewModel: NumberViewModel = viewModel()
) {
    val numbers by viewModel.numbers.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    val state = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = { viewModel.refreshNumbers() }
    )

    Box(modifier = modifier.pullRefresh(state)) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth()
        ) {
            items(numbers) { number ->
                Text(
                    text = "Number: $number",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 10.dp),
                )
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = state,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SwipeRefreshSamplePreview() {
    PullToRefreshTrialTheme {
        SwipeRefreshSample()
    }
}