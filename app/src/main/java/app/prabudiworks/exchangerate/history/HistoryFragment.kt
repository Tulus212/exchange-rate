package app.prabudiworks.exchangerate.history

import androidx.fragment.app.viewModels
import app.prabudiworks.common.app.AppFragment
import app.prabudiworks.common.app.HasObservers
import app.prabudiworks.common.app.HasViews
import app.prabudiworks.exchangerate.R
import app.prabudiworks.exchangerate.databinding.FragmentHistoryBinding
import app.prabudiworks.exchangerate.databinding.FragmentProfileBinding
import coil.load
import coil.transform.CircleCropTransformation

class HistoryFragment : AppFragment<FragmentHistoryBinding, HistoryViewModel>(R.layout.fragment_history),
    HasViews, HasObservers {

    override val viewModel: HistoryViewModel by viewModels()

    override fun setupViews() {
        setupLayout()
    }

    private fun setupLayout() {

    }


    override fun setupObservers() {

    }
}