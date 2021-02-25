package com.example.smartawning.ui.publicadd

import android.os.Bundle
import android.text.InputFilter
import android.view.View
import androidx.fragment.app.viewModels
import com.example.smartawning.R
import com.example.smartawning.databinding.FragmentPublicAddBinding
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PublicAddFragment : BaseFragment<FragmentPublicAddBinding>() {

    private val viewModelPublic: PublicAddViewModel by viewModels()

    override fun getViewBinding(): FragmentPublicAddBinding = FragmentPublicAddBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModelPublic) {
            device.observe(viewLifecycleOwner, {

            })
            showError.observe(viewLifecycleOwner, { message ->
                val snackbar = Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT)
                snackbar.show()
            })
        }
    }

    private fun setupViews() {
        setHasOptionsMenu(true)
        (activity as? AppActivity)?.supportActionBar?.title = resources.getString(R.string.publicTitle)

        with(binding) {
            publicIpInsideEditText.filters = getIpTextFilter()
            publicPortAddressEditText.editText?.filters = getPortFilter()

            saveButton.setOnClickListener {
                var hasError = false

                if (nameEditText.editText?.text.toString().isBlank()) {
                    nameEditText.editText?.error = "Συμπληρώστε ένα όνομα για την συσκευή"
                    hasError = true
                }

                if (publicIpAddressEditText.editText?.text.toString().isBlank()) {
                    publicIpAddressEditText.editText?.error = "Συμπληρώστε την διεύθνση IP"
                    hasError = true
                }

                if (publicPortAddressEditText.editText?.text.toString().isBlank()) {
                    publicPortAddressEditText.editText?.error = "Συμπληρώστε το Port"
                    hasError = true
                }

                if (!hasError)
                    saveDevice()
            }
        }
    }

    private fun saveDevice() {
        viewModelPublic.insertDevice(
            binding.publicIpInsideEditText.text.toString(),
            binding.publicPortInsideEditText.text.toString(),
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

    private fun getPortFilter(): Array<InputFilter?> {
        val filters = arrayOfNulls<InputFilter>(1)
        filters[0] = InputFilter { source, start, end, dest, dstart, dend ->
            if (end > start) {
                val destTxt = dest.toString()
                val resultingTxt = destTxt.substring(0, dstart) +
                        source.subSequence(start, end) +
                        destTxt.substring(dend)
                try {
                    if (resultingTxt.toInt() > 65535 || resultingTxt.toInt() < 0) {
                        return@InputFilter ""
                    }
                } catch (e: NumberFormatException) {
                    return@InputFilter ""
                }

            }
            null
        }
        return filters
    }
}