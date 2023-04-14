package fr.deuspheara.potterdbapp.data.repository.network

import fr.deuspheara.potterdbapp.data.manager.network.NetworkManager
import javax.inject.Inject

interface NetworkRepository {

    /**
     * Check availability of network
     * @return a boolean of network state
     */
    fun isNetworkAvailable(): Boolean

}

class NetworkRepositoryImpl @Inject constructor(
    private val networkManager : NetworkManager
) : NetworkRepository {

    /**
     * heck availability of network
     * @return a boolean of network state
     */
    override fun isNetworkAvailable(): Boolean {
        return networkManager.isNetworkAvailable()
    }
}