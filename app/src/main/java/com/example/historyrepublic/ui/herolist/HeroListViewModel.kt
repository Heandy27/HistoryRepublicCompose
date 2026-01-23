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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: Repository,
): ViewModel() {

    private val _state: MutableStateFlow<HeroListState> = MutableStateFlow(HeroListState.Loading)
    val state: StateFlow<HeroListState> = _state.asStateFlow()

    init {
        getHeros()
    }

    fun getHeros() {
        viewModelScope.launch {
            _state.update { HeroListState.Loading }

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.getHeroes()
                }
            }
            if (result.isSuccess) {
                _state.update { HeroListState.Success(result.getOrThrow()) }
            } else {
                _state.update { HeroListState.Error(result.exceptionOrNull()?.message.orEmpty()) }
            }

        }
    }

}
