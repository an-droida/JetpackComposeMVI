package com.androida.demoapp.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.androida.demoapp.ui.theme.DemoAppTheme
import com.androida.eventbus.presentation.EventBusViewModel
import com.androida.eventbus.utils.EventsViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val eventBusViewModel: EventBusViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val events = eventBusViewModel.events.collectAsState()
            DemoAppTheme {
                AppEventsHandler(events.value)
            }
        }
    }
}


@Composable
private fun AppEventsHandler(
    events: EventsViewState
) {
    events.navigationEvent.peekValue {

    }
    events.showLoader.peekValue {

    }
    events.showWarning.peekValue {

    }
}

@Composable
fun GreetingPreview() {
    DemoAppTheme {

    }
}