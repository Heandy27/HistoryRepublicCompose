package com.example.historyrepublic.ui.heroviews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.historyrepublic.data.Repository
import com.example.historyrepublic.domain.Hero
import com.example.historyrepublic.domain.HeroDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    // Detail Heroe

    private val _stateDetail =
        MutableStateFlow<UIState<HeroDetail>>(UIState.Loading)

    val stateDetail: StateFlow<UIState<HeroDetail>> =
        _stateDetail.asStateFlow()

    // ✅ Lista original
    private val _heroes =
        MutableStateFlow<List<Hero>>(emptyList())


    // ✅ Texto buscado
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> =
        _searchQuery.asStateFlow()

    // ✅ Estado UI final (filtrado)
    val state: StateFlow<UIState<List<Hero>>> =
        combine(_heroes, _searchQuery) { heroes, query ->

            val filteredHeroes = heroes.filter {
                it.nameHero.contains(query, ignoreCase = true)
            }

            UIState.Success(filteredHeroes)

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            UIState.Loading
        )

    // ✅ Loading y Error aparte
    private val _uiState =
        MutableStateFlow<UIState<List<Hero>>>(UIState.Loading)

//    val state: StateFlow<UIState<List<Hero>>> =
//        _state.asStateFlow()

    init {
        getHeroes()
    }

    fun getHeroes() {
        viewModelScope.launch {

            _uiState.value = UIState.Loading

            val result = runCatching {
                withContext(Dispatchers.IO) {
                    repository.getHeroes()
                }
            }

            result.onSuccess { heroes ->
                _heroes.value = heroes
                _uiState.value = UIState.Success(heroes)
            }

            result.onFailure { error ->
                _uiState.value =
                    UIState.Error(error.message ?: "Unknown error")
            }
        }
    }

    // ✅ UI llama esto cuando escriben en search
    fun updateSearch(query: String) {
        _searchQuery.value = query
    }

    fun clearSearch() {
        _searchQuery.value = ""
    }


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

