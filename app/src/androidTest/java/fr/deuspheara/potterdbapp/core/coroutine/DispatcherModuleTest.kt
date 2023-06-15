package fr.deuspheara.potterdbapp.core.coroutine

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import fr.deuspheara.potterdbapp.data.network.NetworkModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatcherModule::class]
)
object DispatchersTestModule {

    @Provides
    @Singleton
    fun providesTestScope(): TestScope {
        return TestScope()
    }

    @Provides
    @Singleton
    fun provideTestDispatcher(
        testScope: TestScope
    ): TestDispatcher {
        return StandardTestDispatcher(
            scheduler = testScope.testScheduler,
            name = "testing_dispatcher"
        )
    }

    @Provides
    @DispatcherModule.DispatcherIO
    fun providesDispatcherIO(
        testDispatcher: TestDispatcher
    ): CoroutineDispatcher {
        return testDispatcher
    }

    @Provides
    @DispatcherModule.DispatcherDefault
    fun providesDispatcherDefault(
        testDispatcher: TestDispatcher
    ): CoroutineDispatcher {
        return testDispatcher
    }

    @Provides
    @DispatcherModule.DispatcherMain
    fun providesDispatcherMain(
        testDispatcher: TestDispatcher
    ): CoroutineDispatcher {
        return testDispatcher
    }

}