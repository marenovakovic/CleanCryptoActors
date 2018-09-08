package com.marko.cleancryptoboomcoroutines.injection.coins

import com.marko.domain.repository.CoinsRepository
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.coins.CoinsViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class CoinsModule {

	@Provides
	fun provideGetAllCoinsUseCase(coinsRepository: CoinsRepository): GetCoins {
		return GetCoins(coinsRepository)
	}

	@Provides
	fun provideCoinsViewModelFactory(getCoins: GetCoins): CoinsViewModelFactory {
		return CoinsViewModelFactory(getCoins)
	}
}