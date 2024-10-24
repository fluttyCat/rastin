package com.nexu.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.nexu.android.core.data.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    application: Application,
) : AndroidViewModel(application) {

}

sealed interface MainActivityUiState {
    data object Loading : MainActivityUiState
    data class Success(
        val userData: UserData
    ) : MainActivityUiState
}
