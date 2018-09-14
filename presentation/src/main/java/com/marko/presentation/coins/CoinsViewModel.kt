package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entity.Coin
import com.marko.presentation.mapper.CoinsPresentationMapper
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.launch

class CoinsViewModel(
	private val getCoins: GetCoins
) : BaseViewModel() {

	private val _coins = MutableLiveData<List<Coin>>()
	val coins: LiveData<List<Coin>>
		get() = _coins

	private val coinsActor = scope.actor<CoinActorMessage> {
		for (message in channel) {
			when (message) {
				is GetAllCoins -> {
					println("Started on ${Thread.currentThread().name}")

					val coins = asyncScope.async {
						println("Getting coins on ${Thread.currentThread().name}")

						CoinsPresentationMapper.mapFromEntity(getCoins(Unit))
					}

					_coins.value = coins.await()

					println("Back on ${Thread.currentThread().name}")
				}
				is GetCoin -> Unit
				is InvalidateCache -> Unit
			}
		}
	}

	fun fetch() {
		scope.launch { coinsActor.send(GetAllCoins) }
	}

	fun fetch(id: Int) {
		scope.launch { coinsActor.send(GetCoin(id)) }
	}

	private fun deleteCoins() {
		scope.launch { coinsActor.send(InvalidateCache) }
	}
}

sealed class CoinActorMessage

object GetAllCoins : CoinActorMessage()
object InvalidateCache : CoinActorMessage()
data class GetCoin(val id: Int) : CoinActorMessage()