package com.marko.cleancryptoboomcoroutines.common

import android.app.Activity
import android.app.Application
import com.marko.cleancryptoboomcoroutines.injection.application.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


class App: Application(), HasActivityInjector {

	@Inject
	lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

	override fun activityInjector(): AndroidInjector<Activity> {
		return dispatchingAndroidInjector
	}

	override fun onCreate() {
		super.onCreate()

		DaggerApplicationComponent.builder()
				.application(this)
				.build()
				.inject(this)
	}
}