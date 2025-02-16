package com.example.core.domain

sealed class UiComponentsState {
    object Show : UiComponentsState()
    object Hide : UiComponentsState()

}