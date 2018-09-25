package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.Result
import com.marko.domain.entity.CoinEntity
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entity.Coin
import com.marko.presentation.mapper.CoinsPresentationMapper
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.channels.actor
import kotlinx.coroutines.experimental.channels.consumeEach
import kotlinx.coroutines.experimental.launch

class CoinsViewModel(
	private val getCoins: GetCoins
) : BaseViewModel() {

	private val _coins = MutableLiveData<List<Coin>>()
	val coins: LiveData<List<Coin>>
		get() = _coins

	private val _showLoading = MutableLiveData<Unit>()
	val showLoading: LiveData<Unit>
		get() = _showLoading

	private val coinsChannel = Channel<Result<List<CoinEntity>>>()

	init {
		uiScope.launch {
			for (result in coinsChannel) {
				when (result) {
					is Result.Success -> _coins.value =
							CoinsPresentationMapper.mapFromEntity(result.data)
					is Result.Loading -> _showLoading.value = Unit
				}
			}
		}
	}

	private val coinsActor = uiScope.actor<CoinActorMessage> {
		for (message in channel) {
			when (message) {
				is GetAllCoins -> asyncScope.launch { getCoins(Unit, coinsChannel) }
				is GetCoin -> Unit
				is InvalidateCache -> Unit
			}
		}
	}

	fun fetch() {
		uiScope.launch { coinsActor.send(GetAllCoins) }
	}

	fun fetch(id: Int) {
		uiScope.launch { coinsActor.send(GetCoin(id)) }
	}

	private fun deleteCoins() {
		uiScope.launch { coinsActor.send(InvalidateCache) }
	}
}

sealed class CoinActorMessage

object GetAllCoins : CoinActorMessage()
object InvalidateCache : CoinActorMessage()
data class GetCoin(val id: Int) : CoinActorMessage()