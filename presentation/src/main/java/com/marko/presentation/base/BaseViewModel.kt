package com.marko.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.Job

abstract class BaseViewModel: ViewModel() {

	protected val job = Job()

	override fun onCleared() {
		super.onCleared()
		job.cancel()
	}
}