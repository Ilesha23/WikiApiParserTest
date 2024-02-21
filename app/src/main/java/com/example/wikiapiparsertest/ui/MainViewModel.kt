package com.example.wikiapiparsertest.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wikiapiparsertest.data.repository.WikiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WikiRepository
) : ViewModel() {

    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    private val _rawTextState = MutableStateFlow("")
    val rawTextState = _rawTextState.asStateFlow()

    private val _parsedTextState = MutableStateFlow("")
    val parsedTextState = _parsedTextState.asStateFlow()

    fun getRawData() {
        viewModelScope.launch {
            _state.update { "Loading" }
            repository.getFromWiki()
                .collectLatest { result ->
                    if (result.isSuccess) {
                        _rawTextState.update {
                            result.getOrNull() ?: "Null"
                        }
                    } else {
                        _rawTextState.update {
                            result.toString()
                        }
                    }
                    _state.update { _rawTextState.value }
                }
        }
    }

    fun parse() {
        viewModelScope.launch {
            _state.update { "Parsing" }
            _parsedTextState.update {
                repository.parse(_rawTextState.value)
            }
            _state.update { _parsedTextState.value }
        }
    }

    fun clear() {
        _state.update { "" }
        _rawTextState.update { "" }
        _parsedTextState.update { "" }
    }

}