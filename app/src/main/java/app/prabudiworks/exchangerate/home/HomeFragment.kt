package app.prabudiworks.exchangerate.home

import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import app.prabudiworks.common.app.AppFragment
import app.prabudiworks.common.app.HasObservers
import app.prabudiworks.common.app.HasViews
import app.prabudiworks.common.app.adapter.AppRecyclerViewImpl
import app.prabudiworks.exchangerate.R
import app.prabudiworks.exchangerate.databinding.FragmentHomeBinding
import app.prabudiworks.exchangerate.databinding.ItemListConverterBinding
import app.prabudiworks.exchangerate.home.model.ConverterResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : AppFragment<FragmentHomeBinding, HomeViewModel>(R.layout.fragment_home),
    HasViews, HasObservers {

    override val viewModel: HomeViewModel by viewModels()

    private val mDataAdapter by lazy {
        AppRecyclerViewImpl(
            ConverterResponse.DIFF_UTIL,
            onCreateViewBinding = { inflater, parent, _ ->
                ItemListConverterBinding
                    .inflate(inflater, parent, false)
            },
            onBinding = { binding, model ->
                (binding as ItemListConverterBinding).apply {
                    data = model
                    when(model.country){
                        "BRAZIL" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_brazil)
                        }
                        "CHINA" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_china)
                        }
                        "FRANCE" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_france)
                        }
                        "GERMANY" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_germany)
                        }
                        "INDONESIA" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_indonesia)
                        }
                        "ITALY" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_italy)
                        }
                        "JAPAN" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_japan)
                        }
                        "SPAIN" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_spain)
                        }
                        "UNITED KINGDOM" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_united_kingdom)
                        }
                        "UNITED STATES" -> {
                            binding.ivBaseCountry.setImageResource(R.drawable.ic_united_states)
                        }
                    }
                }
            }
        )
    }

    override fun setupViews() {
        setupLayout()
    }

    private fun setupLayout() {
        dataBinding.rvConverter.let {
            it.setHasFixedSize(true)
            it.adapter = mDataAdapter
        }
    }


    override fun setupObservers() {

    }
}