package com.marko.cleancryptoboomcoroutines.coins

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marko.cleancryptoboomcoroutines.R
import com.marko.cleancryptoboomcoroutines.extensions.inflate
import com.marko.presentation.entity.Coin

class CoinsAdapter: RecyclerView.Adapter<CoinsAdapter.CoinHolder>() {

	var coins = listOf<Coin>()
	set(value) {
		field = value
		notifyDataSetChanged()
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder {
		val view = parent.inflate(R.layout.list_item_coin)
		return CoinHolder(view)
	}

	override fun onBindViewHolder(holder: CoinHolder, position: Int) {
		val coin = coins[position]
		holder.bind(coin)
	}

	override fun getItemCount(): Int = coins.size

	inner class CoinHolder(view: View): RecyclerView.ViewHolder(view) {

		private val coinName = view.findViewById<TextView>(R.id.coinName)

		fun bind(coin: Coin) {
			coinName.text = coin.name
		}
	}
}