package com.marko.cleancryptoboomcoroutines.injection.activity

import com.marko.cleancryptoboomcoroutines.MainActivity
import com.marko.cleancryptoboomcoroutines.injection.coins.CoinsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [CoinsModule::class])
	abstract fun bindMainActivity(): MainActivity
}