package com.example.smartawning.ui.addawning

import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.viewModels
import com.example.smartawning.R
import com.example.smartawning.databinding.FragmentAddAwningBinding
import com.example.smartawning.domain.entity.DetectAwning
import com.example.vaseisapp.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddAwningFragment : BaseFragment<FragmentAddAwningBinding>() {

    private val viewModel: AddAwningViewModel by viewModels()
    private lateinit var adapter : DetectAwningAdapter

    override fun getViewBinding(): FragmentAddAwningBinding = FragmentAddAwningBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            detected.observe(viewLifecycleOwner, { device ->
                adapter.currentList.add(device)
            })

            stopRotationAnimation.observe(viewLifecycleOwner, {
                binding.refreshButton.clearAnimation()
            })
        }
    }

    private fun setupViews() {
        with(binding) {
            val listener = object : DetectAwningAdapter.DetectAwningListener{
                override fun onDetectAwningClick(detectAwning: DetectAwning) {
                    //TODO navigate
                    Log.v("PING", detectAwning.mac)
                }

            }

            adapter = DetectAwningAdapter(listener)
            detectDevicesRecyclerView.adapter = adapter

            refreshButton.setOnClickListener {
                val animation = AnimationUtils.loadAnimation(context, R.anim.rotation)
                it.startAnimation(animation)
                ping()
            }

        }
    }

    private fun ping()  {
        val wifiMan = context?.getSystemService(WIFI_SERVICE) as WifiManager
        val wifiInf = wifiMan.connectionInfo
        val ipAddress = wifiInf.ipAddress
        val ip = String.format("%d.%d.%d.%d", ipAddress and 0xff, ipAddress shr 8 and 0xff, ipAddress shr 16 and 0xff, ipAddress shr 24 and 0xff)
        viewModel.scanLocalNetwork(ip)
    }
}