package com.alvayonara.outsched.core.di

import android.content.Context
import com.alvayonara.outsched.core.domain.repository.IScheduleRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class]
)
interface CoreComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): CoreComponent
    }

    fun provideRepository(): IScheduleRepository
}