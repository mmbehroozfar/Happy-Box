package com.mmb.happybox.cheerup.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.happybox.domain.usecases.GetHappyThingsNamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHappyThingsNamesUseCase: GetHappyThingsNamesUseCase,
) : ViewModel() {

    private val _happyThings = MutableStateFlow("")
    val happyThings: StateFlow<String> get() = _happyThings

    private val _navigateToFaqScreen = MutableSharedFlow<Unit>(replay = 0)
    val navigateToFaqScreen: SharedFlow<Unit> get() = _navigateToFaqScreen

    private val _navigateToHappyThingsListScreen = MutableSharedFlow<Unit>(replay = 0)
    val navigateToHappyThingsListScreen: SharedFlow<Unit> get() = _navigateToHappyThingsListScreen

    init {
        handleGetHappyThings()
    }

    private fun handleGetHappyThings() {
        getHappyThingsNamesUseCase()
            .onEach {
                _happyThings.emit(it)
            }
            .launchIn(viewModelScope)
    }

    fun onFaqClicked() {
        viewModelScope.launch {
            _navigateToFaqScreen.emit(Unit)
        }
    }

    fun onHappyThingsListClicked() {
        viewModelScope.launch {
            _navigateToHappyThingsListScreen.emit(Unit)
        }
    }

}