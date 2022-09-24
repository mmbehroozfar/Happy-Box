package com.mmb.happybox.cheerup.happything

import android.text.Editable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.happybox.android_test_shared.AppIdleResource
import com.mmb.happybox.domain.usecases.GetHappyThingUseCase
import com.mmb.happybox.domain.usecases.SaveHappyThingUseCase
import com.mmb.happybox.model.HappyThing
import com.mmb.happybox.shared.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HappyThingViewModel @Inject constructor(
    private val getHappyThingUseCase: GetHappyThingUseCase,
    private val saveHappyThingUseCase: SaveHappyThingUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _happyThing = MutableStateFlow(HappyThing())
    val happyThing: StateFlow<HappyThing> get() = _happyThing

    private val _navigateUp = MutableSharedFlow<Unit>(replay = 0)
    val navigateUp: SharedFlow<Unit> get() = _navigateUp

    init {
        savedStateHandle.get<Long>("id").takeIf { it != 0L }?.let {
            AppIdleResource.increment()
            handleGetHappyThing(it)
        }
    }

    private fun handleGetHappyThing(id: Long) {
        viewModelScope.launch {
            getHappyThingUseCase(id)
                .onSuccess {
                    _happyThing.emit(it)
                    AppIdleResource.decrement()
                }
        }
    }

    fun onNameChanged(value: Editable?) {
        AppIdleResource.increment()
        viewModelScope.launch {
            _happyThing.emit(
                _happyThing.value.copy(name = value.toString())
            )
            AppIdleResource.decrement()
        }
    }

    fun onSaveClicked() {
        AppIdleResource.increment()
        viewModelScope.launch {
            saveHappyThingUseCase(happyThing.value)
                .onSuccess {
                    _navigateUp.emit(Unit)
                    AppIdleResource.decrement()
                }
        }
    }

}