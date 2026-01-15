package com.example.historyrepublic.ui.herolist

import androidx.lifecycle.ViewModel
import com.example.historyrepublic.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroListViewModel @Inject constructor(repository: Repository): ViewModel() {
}