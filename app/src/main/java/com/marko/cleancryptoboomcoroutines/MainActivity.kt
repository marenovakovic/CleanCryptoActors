package com.marko.cleancryptoboomcoroutines

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.cleancryptoboomcoroutines.base.BaseActivity
import com.marko.cleancryptoboomcoroutines.coins.CoinsAdapter
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.coins.CoinsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

	@Inject
	lateinit var factory: CoinsViewModelFactory

	private val viewModel: CoinsViewModel by lazy {
		ViewModelProviders.of(this, factory).get(CoinsViewModel::class.java)
	}

	private val recyclerAdapter = CoinsAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel.fetch()
		viewModel.coins.observe(this, Observer { coins ->
			coins?.let { recyclerAdapter.coins = it }
		})

		coinsRecyclerView.adapter = recyclerAdapter
		coinsRecyclerView.layoutManager = LinearLayoutManager(this)
	}
}
