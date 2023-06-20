package com.example.pokedex.ui.base

import androidx.activity.ComponentActivity

abstract class BaseActivity<T : ViewState> : ComponentActivity() {

    abstract val viewModel: BaseViewModel<T>


    abstract fun render(viewState: T)
}
