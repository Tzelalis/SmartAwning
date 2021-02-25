package com.example.smartawning.ui.localorpublicadd

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.smartawning.R
import com.example.smartawning.databinding.FragmentLocalOrPublicBinding
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment

class LocalOrPublicAddFragment : BaseFragment<FragmentLocalOrPublicBinding>(){
    override fun getViewBinding(): FragmentLocalOrPublicBinding = FragmentLocalOrPublicBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        (activity as? AppActivity)?.supportActionBar?.title = resources.getString(R.string.localOrPublicTitle)

        with(binding)   {
            proceedButton.setOnClickListener {
                if(publicRadioButton.isChecked){
                    val action = LocalOrPublicAddFragmentDirections.actionLocalOrPublicAddFragmentToAddAwningDetailsFragment()
                    findNavController().safeNavigate(action, R.id.localOrPublicAddFragment)
                } else{
                    val action = LocalOrPublicAddFragmentDirections.actionLocalOrPublicAddFragmentToLocalAddFragment()
                    findNavController().safeNavigate(action, R.id.localOrPublicAddFragment)
                }

            }
        }
    }
}