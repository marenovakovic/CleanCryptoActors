package com.marko.cleancryptoboomcoroutines.injection.application

import android.app.Application
import com.marko.cleancryptoboomcoroutines.common.App
import com.marko.cleancryptoboomcoroutines.injection.activity.ActivityBindingModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
	AndroidInjectionModule::class,
	ApplicationModule::class,
	ActivityBindingModule::class
])
interface ApplicationComponent {

	@Component.Builder
	interface Builder {
		@BindsInstance
		fun application(application: Application): Builder
		fun build(): ApplicationComponent
	}

	fun inject(app: App)
}