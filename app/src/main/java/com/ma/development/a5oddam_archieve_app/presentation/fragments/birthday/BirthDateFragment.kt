package com.ma.development.a5oddam_archieve_app.presentation.fragments.birthday

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.ma.development.a5oddam_archieve_app.R
import com.ma.development.a5oddam_archieve_app.databinding.FragmentBirthDateBinding
import com.ma.development.a5oddam_archieve_app.presentation.adapters.KhoddamAdapter
import com.ma.development.a5oddam_archieve_app.presentation.fragments.koddamlist.MainViewModel
import com.ma.development.a5oddam_archieve_app.presentation.utils.checkPhoneCallPermission
import com.ma.development.a5oddam_archieve_app.presentation.utils.makePhoneCall
import com.ma.development.a5oddam_archieve_app.presentation.utils.requestPermission
import com.ma.development.a5oddam_archieve_app.presentation.utils.showPhoneCallingDialog
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BirthDateFragment : Fragment(), KhoddamAdapter.OnPhoneClickListener {
    private val binding by lazy { FragmentBirthDateBinding.inflate(layoutInflater) }
    private val adapter = KhoddamAdapter(this)
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.recyclerviewBirthDate.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeOnViewModel()
    }

    private fun observeOnViewModel() {
        viewModel.birthDayKhoddam.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                adapter.submitList(it)
                binding.notExistTv.visibility = View.GONE
                binding.recyclerviewBirthDate.visibility = View.VISIBLE
            } else {
                binding.notExistTv.visibility = View.VISIBLE
                binding.recyclerviewBirthDate.visibility = View.GONE
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
        } else
            showPhoneCallingDialog(requireActivity(), phone, phone2, receiverName = receiverName)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                showPhoneCallingDialog(
                    requireActivity(),
                    phone,
                    phone2,
                    receiverName = receiverName
                )
        }
    }


}