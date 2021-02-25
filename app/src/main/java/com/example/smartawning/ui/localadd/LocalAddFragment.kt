package com.example.smartawning.ui.localadd

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.fragment.app.viewModels
import com.example.smartawning.databinding.FragmentLocalAddBinding
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel

@AndroidEntryPoint
class LocalAddFragment : BaseFragment<FragmentLocalAddBinding>() {

    private val viewModel : LocalAddViewModel by viewModels()

    override fun getViewBinding(): FragmentLocalAddBinding = FragmentLocalAddBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            device.observe(viewLifecycleOwner, {

            })
            showError.observe(viewLifecycleOwner, { message ->
                val snackbar = com.google.android.material.snackbar.Snackbar.make(binding.root, message, com.google.android.material.snackbar.Snackbar.LENGTH_SHORT)
                snackbar.show()
            })
        }
    }

    private fun setupViews() {
        setHasOptionsMenu(true)
        (activity as? AppActivity)?.supportActionBar?.title = "Προσθήκη συσκευής"

        with(binding) {
            localIpAddressEditText.editText?.filters = getIpTextFilter()

            saveButton.setOnClickListener {
                var hasError = false

                if (nameEditText.editText?.text.toString().isBlank()) {
                    nameEditText.editText?.error = "Συμπληρώστε ένα όνομα για την συσκευή"
                    hasError = true
                }

                if(localIpAddressEditText.editText?.text.toString().isBlank()){
                    localIpAddressEditText.editText?.error = "Συμπληρώστε την διεύθνση IP"
                    hasError = true
                }

                if(!hasError)
                    saveDevice()
            }
        }
    }

    private fun saveDevice() {
        viewModel.insertDevice(
            binding.localIpInsideEditText.text.toString(),
            binding.nameEditText.editText?.text.toString()
        )
    }


    private fun getIpTextFilter(): Array<InputFilter?> {
        val filters = arrayOfNulls<InputFilter>(1)
        filters[0] = InputFilter { source, start, end, dest, dstart, dend ->
            if (end > start) {
                val destTxt = dest.toString()
                val resultingTxt = destTxt.substring(0, dstart) +
                        source.subSequence(start, end) +
                        destTxt.substring(dend)
                if (!resultingTxt.matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?".toRegex())) {
                    return@InputFilter ""
                } else {
                    val splits = resultingTxt.split("\\.".toRegex()).toTypedArray()
                    for (i in splits) {
                        try {
                            if (i.toInt() > 255) {
                                return@InputFilter ""
                            }
                        } catch (e: NumberFormatException) {
                            //todo better handle of . (dot)
                        }

                    }
                }
            }
            null
        }
        return filters
    }
}