package app.prabudiworks.exchangerate.main

import androidx.activity.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import app.prabudiworks.common.app.AppActivity
import app.prabudiworks.common.app.HasViews
import app.prabudiworks.exchangerate.R
import app.prabudiworks.exchangerate.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main), HasViews {

    override val viewModel: MainViewModel by viewModels()

    private val mNavcontroller by lazy{
        Navigation.findNavController(this, R.id.fragment)
    }

    override fun setupViews() {
        dataBinding.bnv.setupWithNavController(mNavcontroller)
    }

}