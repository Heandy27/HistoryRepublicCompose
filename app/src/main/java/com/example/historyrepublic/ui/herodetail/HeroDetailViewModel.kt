package com.example.historyrepublic.ui.herodetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyrepublic.data.Repository
import com.example.historyrepublic.domain.HeroDetail
import com.example.historyrepublic.ui.heroviews.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroDetailViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    // Detail Heroe

    private val _stateDetail =
        MutableStateFlow<UIState<HeroDetail>>(UIState.Loading)

    val stateDetail: StateFlow<UIState<HeroDetail>> =
        _stateDetail.asStateFlow()

    fun fetchHeroById(id: String) {
        viewModelScope.launch {

            _stateDetail.value = UIState.Loading

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.fetchHeroById(id)
                }
            }

            result.onSuccess { hero ->
                _stateDetail.value = UIState.Success(hero)
            }

            result.onFailure { error ->
                _stateDetail.value =
                    UIState.Error(error.message ?: "Unknown error")
            }
        }
    }

}