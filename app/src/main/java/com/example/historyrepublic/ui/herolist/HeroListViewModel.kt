package com.example.historyrepublic.ui.herolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyrepublic.data.Repository
import com.example.historyrepublic.domain.Hero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {
    val heros = generateHeros()

    private val _state: MutableStateFlow<HeroListState> = MutableStateFlow(HeroListState.Loading)
    val state: StateFlow<HeroListState> = _state.asStateFlow()

    fun getHeros() {
        viewModelScope.launch {
            val result = withContext(dispatcher) {

            }
        }
    }

}

private fun generateHeros() = (0 until 10).map { Hero("id$it", "Name$it", "Title$it", "Information$it","image$it", "Url$it") }