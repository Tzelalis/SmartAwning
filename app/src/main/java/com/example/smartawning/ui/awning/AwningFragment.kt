package com.example.smartawning.ui.awning

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.smartawning.R
import com.example.smartawning.databinding.FragmentAwningBinding
import com.example.smartawning.domain.entity.AwningEntity
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AwningFragment : BaseFragment<FragmentAwningBinding>() {

    private val viewModel : AwningViewModel by viewModels()
    private var adapter : AwningAdapter? = null

    override fun getViewBinding(): FragmentAwningBinding  = FragmentAwningBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupViews()
    }

    private fun setupObservers(){
        with(viewModel) {
            showAwningDetails.observe(viewLifecycleOwner, { awning ->
                val action = AwningFragmentDirections.actionAwningFragmentToMainFragment(awning)
                findNavController().navigate(action)
            })

            awningList.observe(viewLifecycleOwner, { list ->
                adapter?.submitList(list)
            })

            loadLocalAwnings()
        }
    }

    private fun setupViews(){
        setHasOptionsMenu(true)
        (activity as? AppActivity)?.supportActionBar?.title = "Έξυπνη Τέντα"

        with(binding)   {
            val listener = object : AwningAdapter.AwningListener{
                override fun onAwningItemClick(awning: AwningEntity) {
                    viewModel.showDetails(awning)
                }
            }

            adapter = AwningAdapter(listener)
            awningRecyclerView.adapter = adapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addItem -> viewModel.insertLocalAwning(AwningEntity( ipAddress = "ip", name = "name", macAddress = "mac"))     //testing
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }
}