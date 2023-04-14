package fr.deuspheara.potterdbapp.data.manager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.deuspheara.potterdbapp.data.manager.network.NetworkManager
import fr.deuspheara.potterdbapp.data.manager.network.NetworkManagerImpl

@InstallIn(SingletonComponent::class)
@Module
object ManagerModule {

    @Provides
    fun provideNetworkManager(@ApplicationContext context: Context): NetworkManager {
        return NetworkManagerImpl(context)
    }

}
