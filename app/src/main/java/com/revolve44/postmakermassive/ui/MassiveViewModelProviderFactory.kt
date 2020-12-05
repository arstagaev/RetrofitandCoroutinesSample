package com.revolve44.postmakermassive.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.revolve44.postmakermassive.repository.MassiveRepository

class MassiveViewModelProviderFactory(
    val app : Application,
    val repository: MassiveRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(app,repository) as T
    }
}