package com.ma.development.a5oddam_archieve_app.presentation.fragments.koddamlist

import android.content.Context.CONNECTIVITY_SERVICE
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.navigation.fragment.findNavController
import com.ma.development.a5oddam_archieve_app.R
import com.ma.development.a5oddam_archieve_app.databinding.FragmentKhoddamListBinding
import com.ma.development.a5oddam_archieve_app.domain.model.Khadem
import com.ma.development.a5oddam_archieve_app.presentation.adapters.KhoddamAdapter
import com.ma.development.a5oddam_archieve_app.presentation.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KhoddamListFragment : Fragment(), KhoddamAdapter.OnPhoneClickListener {

    private val binding by lazy { FragmentKhoddamListBinding.inflate(layoutInflater) }
    private val viewModel: MainViewModel by activityViewModels()
    private val adapter by lazy { KhoddamAdapter(this) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.recyclerView.adapter = adapter

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpSearchView()
        setUpBirthDaysIcon()
        observeOnViewModel()
    }

    private fun setUpBirthDaysIcon() {
        binding.ballonImage.setOnClickListener { findNavController().navigate(R.id.birthDateFragment) }
    }

    private fun setUpSearchView() {
        binding.searchView.onActionViewExpanded()
        binding.searchView.clearFocus()

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.searchView.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (it.isNotEmpty())
                        viewModel.setFilterType(FilterType.SEARCH, newText.toString())
                    else
                        viewModel.setFilterType(FilterType.ALL)
                } ?: run {
                    viewModel.setFilterType(FilterType.ALL)
                }
                return true
            }

        })
    }

    private fun swap(mutableList: MutableList<Khadem>) {
        try {
            val index1 =
                mutableList.indexOf(mutableList.filter { it.name == "ابونا كيرلس روماني" }[0])
            val index2 =
                mutableList.indexOf(mutableList.filter { it.name == "ابونا ارسانيوس عزت" }[0])
            var temp = mutableList[index1]
            mutableList[index1] = mutableList[0]
            mutableList[0] = temp

            temp = mutableList[index2]
            mutableList[index2] = mutableList[1]
            mutableList[1] = temp

        }catch (e:Exception){}

    }

    private fun observeOnViewModel() {
        viewModel.khoddamList.observe(viewLifecycleOwner) {
            it?.let {
                if (it.isEmpty() && viewModel.firstTime) {
                    checkForInternet()
                    viewModel.firstTime = false
                } else {
                    swap(it as MutableList<Khadem>)
                    adapter.submitList(it)
                }
            } ?: run {
                checkForInternet()
                viewModel.firstTime = false
            }
        }
    }

    lateinit var phone: String
    var phone2: Int? = null
    lateinit var receiverName: String

    override fun call(phone: String, phone2: Int?, receiverName: String) {
        this.phone = phone
        this.phone2 = phone2
        this.receiverName = receiverName
        if (checkPhoneCallPermission(requireContext())) {
            requestPermission(this)
        } else {
            showPhoneCallingDialog(
                activity = requireActivity(),
                phone = phone,
                phone2 = phone2,
                receiverName = receiverName
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showPhoneCallingDialog(
                    activity = requireActivity(),
                    phone = phone,
                    phone2 = phone2,
                    receiverName = receiverName
                )
            }
        }
    }

    private fun checkForInternet() {

        val connectivityManager =
            requireActivity().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected = connectivityManager.activeNetwork?.let { true } ?: false
        if (!isConnected)
            showSnackBar(binding.root, getString(R.string.internet_error_message))
    }
}