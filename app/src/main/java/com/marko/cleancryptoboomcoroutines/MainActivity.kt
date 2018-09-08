package com.marko.cleancryptoboomcoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.marko.cleancryptoboomcoroutines.coins.CoinsAdapter
import com.marko.presentation.coins.CoinsViewModel
import com.marko.presentation.coins.CoinsViewModelFactory
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

	@Inject
	lateinit var factory: CoinsViewModelFactory

	private val viewModel: CoinsViewModel by lazy {
		ViewModelProviders.of(this, factory).get(CoinsViewModel::class.java)
	}

	private val recyclerAdapter = CoinsAdapter()

	override fun onCreate(savedInstanceState: Bundle?) {
		AndroidInjection.inject(this)
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
