package fr.deuspheara.potterdbapp.data.manager.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

interface NetworkManager {

    fun isNetworkAvailable(): Boolean

}

class NetworkManagerImpl(
    private val context: Context
) : NetworkManager {

    override fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
        return false
    }


}