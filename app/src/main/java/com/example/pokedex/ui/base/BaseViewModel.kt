package com.example.pokedex.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<T : ViewState> : ViewModel() {

    @Suppress("PropertyName")
    abstract val _viewData: MutableStateFlow<T>
    val viewData: StateFlow<T>
        get() = _viewData

    abstract fun setStateEvent(stateEvent: StateEvent)

}
