package com.example.wikiapiparsertest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wikiapiparsertest.ui.theme.WikiApiParserTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = hiltViewModel<MainViewModel>()
            var textFieldState by remember { mutableStateOf("") }
            val textState = viewModel.state.collectAsState().value

            WikiApiParserTestTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Row {
                            TextField(value = textFieldState, onValueChange = {
                                textFieldState = it
                            })
                        }
                        Row {
                            Button(onClick = {
                                viewModel.getRawData(textFieldState)
                            }) {
                                Text(text = "Get data")
                            }
                            Button(onClick = {
                                viewModel.parse()
                            }) {
                                Text(text = "Parse")
                            }
                            Button(onClick = {
                                viewModel.clear()
                            }) {
                                Text(text = "Clear")
                            }
                        }
                        Text(text = textState)
                    }
                }
            }
        }
    }
}