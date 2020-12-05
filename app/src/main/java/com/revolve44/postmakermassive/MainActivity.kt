package com.revolve44.postmakermassive


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.revolve44.postmakermassive.repository.MassiveRepository
import com.revolve44.postmakermassive.ui.FragmentA
import com.revolve44.postmakermassive.ui.FragmentB
import com.revolve44.postmakermassive.ui.MainViewModel
import com.revolve44.postmakermassive.ui.MassiveViewModelProviderFactory
import com.revolve44.postmakermassive.utils.Constants.Companion.TAGZ
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private var fragmentA: FragmentA? = null
    private var fragmentB: FragmentB? = null

    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.d("Get Started!")
        //Log.d(TAGZ,"Get Started!, not Timber")

        val repository = MassiveRepository() // really? wtf

        val viewModelProviderFactory = MassiveViewModelProviderFactory(application,repository)
        viewModel =ViewModelProvider(this, viewModelProviderFactory).get(MainViewModel::class.java)

        fragmentA = FragmentA()
        fragmentB = FragmentB()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_a, fragmentA!!)
            .replace(R.id.container_b, fragmentB!!)
            .commit()

    }
}