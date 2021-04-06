package app.prabudiworks.exchangerate.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import app.prabudiworks.common.app.AppActivity
import app.prabudiworks.common.app.HasObservers
import app.prabudiworks.common.app.HasViews
import app.prabudiworks.common.extension.startActivityAsNewTask
import app.prabudiworks.exchangerate.BuildConfig
import app.prabudiworks.exchangerate.R
import app.prabudiworks.exchangerate.databinding.ActivitySplashBinding
import app.prabudiworks.exchangerate.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppActivity<ActivitySplashBinding, SplashViewModel>(R.layout.activity_splash),
    HasViews, HasObservers {

    override val viewModel: SplashViewModel by viewModels()

    @SuppressLint("StringFormatInvalid")
    override fun setupViews() {
        dataBinding.tvVersion.text = getString(
            R.string.app_version,
            BuildConfig.VERSION_NAME,
            BuildConfig.VERSION_CODE
        )
    }

    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper())
            .postDelayed({ viewModel.decidesNext() }, 1000)
    }

    override fun setupObservers() {
        viewModel.openMainPageEvent.observe(this, {
            startActivityAsNewTask<MainActivity>()
        })
    }
}