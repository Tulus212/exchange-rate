package app.prabudiworks.exchangerate.profile

import androidx.fragment.app.viewModels
import app.prabudiworks.common.app.AppFragment
import app.prabudiworks.common.app.HasObservers
import app.prabudiworks.common.app.HasViews
import app.prabudiworks.exchangerate.R
import app.prabudiworks.exchangerate.databinding.FragmentProfileBinding
import coil.load
import coil.transform.CircleCropTransformation

class ProfileFragment : AppFragment<FragmentProfileBinding, ProfileViewModel>(R.layout.fragment_profile),
    HasViews, HasObservers {

    override val viewModel: ProfileViewModel by viewModels()

    override fun setupViews() {
        setupLayout()
    }

    private fun setupLayout() {
        dataBinding.ivProfile.load(R.mipmap.me) {
            crossfade(true)
            transformations(CircleCropTransformation())
        }
    }


    override fun setupObservers() {

    }
}