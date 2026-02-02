package com.example.historyrepublic.ui.herodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyrepublic.data.Repository
import com.example.historyrepublic.data.network.model.SingleHeroResponse
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.ui.herolist.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _state =
        MutableStateFlow<UIState<SingleHeroResponse>>(UIState.Loading)

    val state: StateFlow<UIState<SingleHeroResponse>> =
        _state.asStateFlow()

    fun fetchHeroById(id: String) {
        viewModelScope.launch {

            _state.value = UIState.Loading

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.fetchHeroById(id)
                }
            }

            result.onSuccess { hero ->
                _state.value = UIState.Success(hero)
            }

            result.onFailure { error ->
                _state.value =
                    UIState.Error(error.message ?: "Unknown error")
            }
        }
    }
}
