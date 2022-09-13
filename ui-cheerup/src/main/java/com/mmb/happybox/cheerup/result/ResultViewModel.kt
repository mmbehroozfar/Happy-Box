package com.mmb.happybox.cheerup.result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.happybox.domain.usecases.GetRandomHappyThingUseCase
import com.mmb.happybox.shared.onError
import com.mmb.happybox.shared.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val getRandomHappyThingUseCase: GetRandomHappyThingUseCase,
) : ViewModel() {

    private val _happyThing = MutableStateFlow("")
    val happyThing: StateFlow<String> get() = _happyThing

    private val _onCloseDialog = MutableSharedFlow<Unit>(replay = 0)
    val onCloseDialog: SharedFlow<Unit> get() = _onCloseDialog

    init {
        handleGetHappyThing()
    }

    private fun handleGetHappyThing() {
        viewModelScope.launch {
            getRandomHappyThingUseCase()
                .onSuccess {
                    _happyThing.emit(it.name)
                }
                .onError {
                    _onCloseDialog.emit(Unit)
                }
        }
    }

    fun onDoThatClicked() {
        viewModelScope.launch {
            _onCloseDialog.emit(Unit)
        }
    }

}