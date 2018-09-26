package com.marko.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.experimental.CoroutineScope
import kotlinx.coroutines.experimental.Dispatchers
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.Main

abstract class BaseViewModel : ViewModel() {

	private val job = Job()

	protected val scope = CoroutineScope(Dispatchers.IO + job)

	override fun onCleared() {
		super.onCleared()
		job.cancel()
	}
}