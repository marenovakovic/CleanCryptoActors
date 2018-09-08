package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entity.Coin
import com.marko.presentation.mapper.CoinsPresentationMapper
import kotlinx.coroutines.experimental.IO
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.launch

class CoinsViewModel(
		private val getCoins: GetCoins
): BaseViewModel() {

	private val _coins = MutableLiveData<List<Coin>>()
	val coins: LiveData<List<Coin>>
		get() = _coins

	private val coinsActor = actor<CoinActorMessage>(IO, parent = job) {
		for (event in channel) {
			when (event) {
				is GetAllCoins -> {
					val coins = CoinsPresentationMapper.mapFromEntity(getCoins(Unit))
					_coins.postValue(coins)
				}
				is GetCoin -> Unit
			}
		}
	}

	fun fetch() {
		launch(IO, parent = job) { coinsActor.send(GetAllCoins) }
	}
}

sealed class CoinActorMessage

object GetAllCoins: CoinActorMessage()
data class GetCoin(val id: Int): CoinActorMessage()