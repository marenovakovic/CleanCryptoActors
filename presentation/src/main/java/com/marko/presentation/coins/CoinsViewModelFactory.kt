package com.marko.presentation.coins

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.domain.usecase.GetCoins

class CoinsViewModelFactory(
		private val getCoins: GetCoins
): ViewModelProvider.Factory {

	@Suppress("UNCHECKED_CAST")
	override fun <T : ViewModel?> create(modelClass: Class<T>): T {
		return CoinsViewModel(getCoins) as T
	}
}