package com.mmb.happybox.cheerup.happythingslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.mmb.happybox.domain.usecases.ObservePagedHappyThingsUseCase
import com.mmb.happybox.model.HappyThing
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class HappyThingsListViewModel @Inject constructor(
    observePagedHappyThingsUseCase: ObservePagedHappyThingsUseCase,
) : ViewModel() {

    private val _happyThings = observePagedHappyThingsUseCase()
    val happyThings: Flow<PagingData<HappyThing>> get() = _happyThings

    private val _navigateToHappyThingScreen = MutableSharedFlow<Unit>(replay = 0)
    val navigateToHappyThingScreen: SharedFlow<Unit> get() = _navigateToHappyThingScreen

    fun onAddItemClicked() {
        viewModelScope.launch {
            _navigateToHappyThingScreen.emit(Unit)
        }
    }
}