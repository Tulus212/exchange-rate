package app.prabudiworks.exchangerate.home

import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
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

    private var mData = ArrayList<ConverterResponse>()
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

        dataBinding.etInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s!!.isNotEmpty())
                    changedata(s)
                else {
                    mDataAdapter.submitList(mData)
                    mDataAdapter.notifyDataSetChanged()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        viewModel.converterList.observe(this){
            Handler(Looper.getMainLooper())
                .postDelayed({
                    if (it.isEmpty()) {
                        dataBinding.grpConverter.visibility = View.GONE
                        dataBinding.llLoading.visibility = View.VISIBLE
                    }else {
                        dataBinding.grpConverter.visibility = View.VISIBLE
                        dataBinding.llLoading.visibility = View.GONE
                    }
                 }, 3000)

        }

    }

    private fun changedata(s: Editable?) {
        var data = viewModel.converterList.value
        var dataNew = ArrayList<ConverterResponse>()
        var dataOld = ArrayList<ConverterResponse>()
        if (mData.isNullOrEmpty())
            mData.addAll(data!!)
        dataOld.addAll(mData)
        for(i in dataOld!!.indices){
            var singleData = ConverterResponse(dataOld[i].id, dataOld[i].currency, dataOld[i].value?.times(s.toString().toDouble()), dataOld[i].country)
            dataNew.add(singleData)
        }
        mDataAdapter.submitList(dataNew)
        mDataAdapter.notifyDataSetChanged()
    }


    override fun setupObservers() {

    }
}