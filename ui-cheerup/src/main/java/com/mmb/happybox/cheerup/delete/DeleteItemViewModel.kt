package com.mmb.happybox.cheerup.delete

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmb.happybox.domain.usecases.DeleteHappyThingUseCase
import com.mmb.happybox.shared.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class DeleteItemViewModel @Inject constructor(
    private val deleteHappyThingUseCase: DeleteHappyThingUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private var itemId = -1L

    private val _onCloseDialog = MutableSharedFlow<Unit>(replay = 0)
    val onCloseDialog: SharedFlow<Unit> get() = _onCloseDialog

    init {
        savedStateHandle.get<Long>("id")?.let {
            itemId = it
        }
    }

    fun onDeleteItemClicked() {
        viewModelScope.launch {
            deleteHappyThingUseCase(itemId)
                .onSuccess {
                    _onCloseDialog.emit(Unit)
                }
        }
    }

}