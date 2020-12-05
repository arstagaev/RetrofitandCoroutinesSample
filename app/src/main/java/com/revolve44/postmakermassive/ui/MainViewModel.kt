package com.revolve44.postmakermassive.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.TYPE_WIFI
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.wifi.WifiInfo
import android.os.Build
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.revolve44.postmakermassive.MassiveApp
import com.revolve44.postmakermassive.models.alfa.CurrentForecast
import com.revolve44.postmakermassive.repository.MassiveRepository
import com.revolve44.postmakermassive.utils.Constants.Companion.TAGZ
import com.revolve44.postmakermassive.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

class MainViewModel(app : Application, val  mrepository: MassiveRepository) :AndroidViewModel(app) {

    private val alphaRequest : MutableLiveData<Resource<CurrentForecast>> = MutableLiveData()

    init {
        startAlphaRequest()
        startBetaRequest()
        Timber.i("init viewModel")


    }

    private fun startAlphaRequest() = viewModelScope.launch {
         safeAplhaRequest()
    }

    private fun startBetaRequest() = viewModelScope.launch {
        safeBetaRequest()

    }

    private suspend fun safeBetaRequest() {

    }


    private suspend fun safeAplhaRequest() {
        alphaRequest.postValue(Resource.Loading())

        try {
            if (hasInternetConnection()){
                Log.d(TAGZ,"asd start request")
               val response = mrepository.getAlpha()
                alphaRequest.postValue(handleAlphaResponse(response))
            }
        }catch (e : Exception){
            Timber.e("asd pizdec"+ e)
        }

    }

    private fun handleAlphaResponse(response: Response<CurrentForecast>): Resource<CurrentForecast>? {
        //catchresponse
        if (response.isSuccessful){
            response.body()?.let {resultResponse ->
                try {
                    Timber.i("asd answer: "+resultResponse.clouds)
                    //Log.d("pizdec ",resultResponse.name+" //")
                }catch (e : Exception){
                    Timber.e("asd Error in ViewModel: "+e)
                }
            }
        }else{
            Timber.e("asd Response is NOT successful"+ response.message())
        }
        return Resource.Error(response.message())
    }

    // check internet connection
    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<MassiveApp>().getSystemService(
                Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }




}