package com.nandaprasetio.inventintegrasitest.presentation.activity

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.nandaprasetio.inventintegrasitest.databinding.ActivityHomeBinding
import com.nandaprasetio.inventintegrasitest.domain.entity.District
import com.nandaprasetio.inventintegrasitest.domain.entity.GetCityRequestBody
import com.nandaprasetio.inventintegrasitest.domain.entity.Region
import com.nandaprasetio.inventintegrasitest.misc.LoadDataResult
import com.nandaprasetio.inventintegrasitest.viewmodel.HomeViewModel

class HomeActivity: BaseActivity<ActivityHomeBinding>() {
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding?.also {
            homeViewModel.loadProvince()
            homeViewModel.provinceLiveData.observe(this) { result ->
                it.textViewError.isVisible = result is LoadDataResult.Error
                it.spinnerProvince.isEnabled = result !is LoadDataResult.Loading
                if (result is LoadDataResult.Success) {
                    result.output.value?.also { regionList ->
                        initializeProvinceSpinner(it.spinnerProvince, regionList)
                    }
                }
            }
            homeViewModel.cityLiveData.observe(this) { result ->
                it.textViewError.isVisible = result is LoadDataResult.Error
                it.spinnerCity.isEnabled = result !is LoadDataResult.Loading
                if (result is LoadDataResult.Success) {
                    result.output.value?.also { districtList ->
                        initializeCitySpinner(it.spinnerCity, districtList)
                    }
                }
            }
        }
    }

    private fun initializeProvinceSpinner(spinnerProvince: Spinner, region: List<Region>) {
        val stringArray: Array<String> = region.map { it.name }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray)

        spinnerProvince.adapter = adapter
        spinnerProvince.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                homeViewModel.getCity(GetCityRequestBody(adapter.getItem(p2) as String))
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun initializeCitySpinner(spinnerCity: Spinner, district: List<District>) {
        val stringArray: Array<String> = district.map { it.name }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, stringArray)

        spinnerCity.adapter = adapter
    }


    override fun setViewBinding(): ActivityHomeBinding {
        return ActivityHomeBinding.inflate(this.layoutInflater)
    }
}