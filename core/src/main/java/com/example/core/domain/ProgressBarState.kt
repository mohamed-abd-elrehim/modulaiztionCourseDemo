package com.example.core.domain

sealed class ProgressBarState {
    object Loading : ProgressBarState()
    object Idle : ProgressBarState()

}
//Loading → When the progress bar is actively showing.
//Idle → When the progress bar is hidden or not in use.
