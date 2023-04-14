package fr.deuspheara.potterdbapp.core.coroutine

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fr.deuspheara.potterdbapp.data.network.NetworkModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import javax.inject.Qualifier

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatcherModule::class]
)
object TestDispatcherModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TestDispatcherIO

    @TestDispatcherIO
    @Provides
    fun provideTestDispatcherIO(): CoroutineDispatcher = TestCoroutineDispatcher()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TestDispatcherMain

    @TestDispatcherMain
    @Provides
    fun provideTestDispatcherMain(): CoroutineDispatcher = TestCoroutineDispatcher()

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TestDispatcherDefault

    @TestDispatcherDefault
    @Provides
    fun provideTestDispatcherDefault(): CoroutineDispatcher = TestCoroutineDispatcher()

}
