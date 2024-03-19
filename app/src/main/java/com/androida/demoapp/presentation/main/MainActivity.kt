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
import com.androida.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private val eventBusViewModel: EventBusViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoAppTheme {
                val events = eventBusViewModel.events.collectAsState()
                AppEventsHandler(events.value)
                HomeScreen()
            }
        }
    }
}


@Composable
private fun AppEventsHandler(
    events: EventsViewState
) {
//    events.navigationEvent.peekValue {
//        Log.d("aaaa","bbbb")
//    }
//    events.showLoader.peekValue {
//
//    }
//    events.showWarning.peekValue {
//
//    }
}
