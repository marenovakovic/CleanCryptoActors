package com.marko.presentation.coins

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.marko.domain.Result
import com.marko.domain.entity.CoinEntity
import com.marko.domain.usecase.GetCoins
import com.marko.presentation.base.BaseViewModel
import com.marko.presentation.entity.Coin
import com.marko.presentation.mapper.CoinsPresentationMapper
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.actor
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


	private val coinsActor = scope.actor<CoinActorMessage> {
		for (message in channel) {
			when (message) {
				is GetAllCoins -> scope.launch {
					getCoins(Unit) { dispatchCoinsResult(it) }
				}
				is GetCoin -> Unit
				is InvalidateCache -> Unit
			}
		}
	}

	fun fetch() = scope.launch { coinsActor.send(GetAllCoins) }

	private suspend fun dispatchCoinsResult(channel: ReceiveChannel<Result<List<CoinEntity>>>) {
		scope.launch {
			for (result in channel) {
				when (result) {
					is Result.Loading -> {
						_showLoading.postValue(Unit)
					}
					is Result.Success -> _coins.postValue(
						CoinsPresentationMapper.mapFromEntity(
							result.data
						)
					)
					is Result.Error -> result.throwable.printStackTrace()
				}
			}
		}
	}
}

sealed class CoinActorMessage

object GetAllCoins : CoinActorMessage()
object InvalidateCache : CoinActorMessage()
data class GetCoin(val id: Int) : CoinActorMessage()