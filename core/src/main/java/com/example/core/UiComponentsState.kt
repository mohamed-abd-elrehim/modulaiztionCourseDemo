package com.example.core

sealed class UiComponentsState {
    object Show : UiComponentsState()
    object Hide : UiComponentsState()

}