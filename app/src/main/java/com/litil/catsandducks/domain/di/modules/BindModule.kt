package com.litil.catsandducks.domain.di.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.litil.catsandducks.data.repositories.*
import com.litil.catsandducks.presentation.fragments.AfterShiftFragment
import com.litil.catsandducks.presentation.fragments.BeforeShiftFragment
import com.litil.catsandducks.presentation.viewmodels.MainViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import java.lang.annotation.RetentionPolicy
import javax.inject.Scope
import kotlin.reflect.KClass


@Module
interface BindModule {

    @Binds
    fun bindCatsImagesRepository(repository: CatsImagesRepositoryImpl): CatsImagesRepository

    @Binds
    fun bindDucksImagesRepository(repository: DucksImagesRepositoryImpl): DucksImagesRepository

    @Binds
    fun bindImagesDatabaseRepository(repository: ImagesDatabaseRepositoryImpl): ImagesDatabaseRepository

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    fun bindMainViewModelFactory(factory: MainViewModel.Factory): ViewModelProvider.Factory
}

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope