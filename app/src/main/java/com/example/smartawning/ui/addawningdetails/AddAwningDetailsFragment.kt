package com.example.smartawning.ui.addawningdetails

import android.os.Bundle
import android.view.View
import com.example.smartawning.databinding.FragmentAwningDetailsBinding
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment

class AddAwningDetailsFragment : BaseFragment<FragmentAwningDetailsBinding>() {
    override fun getViewBinding(): FragmentAwningDetailsBinding = FragmentAwningDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews()    {
        setHasOptionsMenu(true)
        (activity as? AppActivity)?.supportActionBar?.title = "Προσθήκη συσκευής"
    }
}