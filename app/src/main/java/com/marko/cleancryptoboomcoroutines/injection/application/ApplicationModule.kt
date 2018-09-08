package com.marko.cleancryptoboomcoroutines.injection.application

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.marko.cache.db.CoinsDatabase
import com.marko.cache.repository.CoinsLocalRepositoryImpl
import com.marko.data.datasource.CoinsDataSourceFactory
import com.marko.data.datasource.CoinsLocalDataSource
import com.marko.data.datasource.CoinsRemoteDataSource
import com.marko.data.repository.CoinsLocalRepository
import com.marko.data.repository.CoinsRemoteRepository
import com.marko.data.repository.CoinsRepositoryImpl
import com.marko.domain.repository.CoinsRepository
import com.marko.remote.repository.CoinsRemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule {

	@Provides
	fun provideContext(application: Application): Context {
		return application
	}

	@Singleton
	@Provides
	fun provideCoinsDatabase(context: Context): CoinsDatabase {
		return Room.databaseBuilder(context, CoinsDatabase::class.java, "coinsdb.db").build()
	}

	@Singleton
	@Provides
	fun provideCoinsLocalRepository(coinsDatabase: CoinsDatabase): CoinsLocalRepository {
		return CoinsLocalRepositoryImpl(coinsDatabase)
	}

	@Singleton
	@Provides
	fun provideCachedCoinsDataSource(coinsLocalRepository: CoinsLocalRepository): CoinsLocalDataSource {
		return CoinsLocalDataSource(coinsLocalRepository)
	}

	@Singleton
	@Provides
	fun provideCoinsDataSourceFactory(
			coinsRemoteDataSource: CoinsRemoteDataSource,
			coinsCacheDataSource: CoinsLocalDataSource
	): CoinsDataSourceFactory {
		return CoinsDataSourceFactory(coinsRemoteDataSource, coinsCacheDataSource)
	}

	@Singleton
	@Provides
	fun provideCoinsRemoteRepository(): CoinsRemoteRepository {
		return CoinsRemoteRepositoryImpl()
	}

	@Singleton
	@Provides
	fun provideRemoteDataSource(coinsRemoteRepository: CoinsRemoteRepository): CoinsRemoteDataSource {
		return CoinsRemoteDataSource(coinsRemoteRepository)
	}

	@Singleton
	@Provides
	fun provideCoinsRepository(dataSourceFactory: CoinsDataSourceFactory): CoinsRepository {
		return CoinsRepositoryImpl(dataSourceFactory)
	}
}