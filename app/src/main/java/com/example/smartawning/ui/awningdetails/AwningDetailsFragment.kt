package com.example.smartawning.ui.awningdetails

import android.app.AlertDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.smartawning.R
import com.example.smartawning.databinding.FragmentAwningDetailsBinding
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment
import com.google.android.material.slider.Slider
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AwningDetailsFragment : BaseFragment<FragmentAwningDetailsBinding>() {

    private val viewModel: AwningDetailsViewModel by viewModels()
    private val args: AwningDetailsFragmentArgs by navArgs()

    override fun getViewBinding(): FragmentAwningDetailsBinding = FragmentAwningDetailsBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
        setupObservers()
    }

    private fun setupObservers() {
        with(viewModel) {
            temperature.observe(viewLifecycleOwner, { temperature ->
                binding.temperatureTextView.text = String.format(resources.getString(R.string.temperatureText), temperature)

                //todo better if state for first show
                if (!binding.sunSwitcher.isVisible) {
                    setupViewsWithDataFirstTime()
                }
            })

            humidity.observe(viewLifecycleOwner, { humidity ->
                binding.humidityTextView.text = humidity
            })

            rainIndicator.observe(viewLifecycleOwner, { flag ->
                binding.rainIndicator.isVisible = flag
            })

            sunIndicator.observe(viewLifecycleOwner, { flag ->
                binding.sunIndicator.isVisible = flag
            })

            programIndicator.observe(viewLifecycleOwner, { flag ->
                binding.programIndicator.isVisible = flag
            })

            positionIndicator.observe(viewLifecycleOwner, { flag ->
                binding.positionIndicator.isVisible = flag
            })

            isRainEnable.observe(viewLifecycleOwner, { isChecked ->
                binding.rainSwitcher.isChecked = isChecked
            })

            isSunEnable.observe(viewLifecycleOwner, { isChecked ->
                binding.sunSwitcher.isChecked = isChecked
            })

            isProgramEnable.observe(viewLifecycleOwner, { isChecked ->
                binding.timeSwitcher.isChecked = isChecked
            })

            programStartTime.observe(viewLifecycleOwner, { startTime ->
                binding.startTimeTextView.text = startTime
            })

            programEndTime.observe(viewLifecycleOwner, { endTime ->
                binding.stopTimeTextView.text = endTime
            })

            position.observe(viewLifecycleOwner, { position ->
                binding.positionSlider.value = position.toFloat()
                binding.positionPercentTextView.text = position.toInt().toString()
            })

            //loadAwningConfig(args.awning.ip)
            loadAwningConfig("46.176.166.222:300")
        }
    }


    private fun setupViewsWithDataFirstTime() {
        with(binding) {
            controlCardView.animation = null
            rainCardView.animation = null
            sunCardView.animation = null
            timeCardView.animation = null

            temperatureTextView.isVisible = true
            humidityTextView.isVisible = true
            temperatureImageView.isVisible = true
            humidityImageView.isVisible = true
            positionSlider.isVisible = true
            downDescriptionTextView.isVisible = true
            upDescriptionTextView.isVisible = true
            positionDescriptionTextView.isVisible = true
            progressBar.isVisible = true
            positionPercentTextView.isVisible = true
            positionPercentIconTextView.isVisible = true

            sunSwitcher.isVisible = true
            sunTitleTextView.isVisible = true
            sunDescriptionTextView.isVisible = true
            sunImageView.isVisible = true

            rainSwitcher.isVisible = true
            rainTitleTextView.isVisible = true
            rainDescriptionTextView.isVisible = true
            rainImageView.isVisible = true

            timeSwitcher.isVisible = true
            timeTitleTextView.isVisible = true
            timeDescriptionTextView.isVisible = true
            startTimeTextView.isVisible = true
            stopTimeTextView.isVisible = true
            seperatorTimeTextView.isVisible = true
        }
    }

    private fun setupViews() {
        //TODO handle public or local ip
        with(binding) {
            setHasOptionsMenu(true)
            (activity as? AppActivity)?.supportActionBar?.title = args.awning.name

            rainCardView.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)
            sunCardView.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)
            controlCardView.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)
            timeCardView.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)

            rainSwitcher.setOnClickListener {
                viewModel.updateRainSensor("${args.awning.publicIp}:${args.awning.publicPort}", rainSwitcher.isChecked)
            }

            rainSwitcher.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.rainImageView.setImageResource(R.drawable.ic_rainy)
                } else {
                    binding.rainImageView.setImageResource(R.drawable.ic_not_rainy)
                }
            }

            sunSwitcher.setOnClickListener {
                viewModel.updateSunSensor("${args.awning.publicIp}:${args.awning.publicPort}", sunSwitcher.isChecked)
            }

            sunSwitcher.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.sunImageView.setImageResource(R.drawable.ic_sun)
                } else {
                    binding.sunImageView.setImageResource(R.drawable.ic_not_sun)
                }
            }

            timeSwitcher.setOnClickListener {
                viewModel.updateEnableProgram("${args.awning.publicIp}:${args.awning.publicPort}", timeSwitcher.isChecked)
            }

            timeSwitcher.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.startTimeTextView.setTextColor(ResourcesCompat.getColor(resources, R.color.purple_500, null))
                    binding.seperatorTimeTextView.setTextColor(ResourcesCompat.getColor(resources, R.color.purple_500, null))
                    binding.stopTimeTextView.setTextColor(ResourcesCompat.getColor(resources, R.color.purple_500, null))
                } else {
                    binding.startTimeTextView.setTextColor(ResourcesCompat.getColor(resources, android.R.color.tab_indicator_text, null))
                    binding.seperatorTimeTextView.setTextColor(ResourcesCompat.getColor(resources, android.R.color.tab_indicator_text, null))
                    binding.stopTimeTextView.setTextColor(ResourcesCompat.getColor(resources, android.R.color.tab_indicator_text, null))
                }
            }

            startTimeTextView.setOnClickListener {
                val materialTimePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setTitleText(resources.getString(R.string.selectTimeStartTitle))
                    .build()

                materialTimePicker.addOnPositiveButtonClickListener {
                    viewModel.updateStartTimeProgram(
                        "${args.awning.publicIp}:${args.awning.publicPort}",
                        materialTimePicker.hour,
                        materialTimePicker.minute
                    )
                    startTimeTextView.text = String.format(
                        resources.getString(R.string.timeFormat),
                        materialTimePicker.hour,
                        materialTimePicker.minute
                    )
                }

                materialTimePicker.show(requireActivity().supportFragmentManager, "startTime")
            }

            stopTimeTextView.setOnClickListener {
                val materialTimePicker = MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_12H)
                    .setTitleText(resources.getString(R.string.selectTimeEndTitle))
                    .build()

                materialTimePicker.addOnPositiveButtonClickListener {
                    viewModel.updateEndTimeProgram(
                        "${args.awning.publicIp}:${args.awning.publicPort}",
                        materialTimePicker.hour,
                        materialTimePicker.minute
                    )
                    stopTimeTextView.text = String.format(
                        resources.getString(R.string.timeFormat),
                        materialTimePicker.hour,
                        materialTimePicker.minute
                    )
                }

                materialTimePicker.show(requireActivity().supportFragmentManager, "stopTime")
            }

            positionSlider.addOnChangeListener { _, value, _ ->
                progressBar.progress = value.toInt()
                positionPercentTextView.text = value.toInt().toString()
            }

            positionSlider.addOnSliderTouchListener(object : Slider.OnSliderTouchListener {
                override fun onStartTrackingTouch(slider: Slider) {}

                override fun onStopTrackingTouch(slider: Slider) {
                    viewModel.updateAwningPosition("${args.awning.publicIp}:${args.awning.publicPort}", slider.value.toInt())
                }
            })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteItem -> showDeleteAlertDialog()
            R.id.renameItem -> showRenameAlertDialog()
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun showDeleteAlertDialog() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(resources.getString(R.string.deleteDialogTitle))
                setIcon(R.drawable.ic_baseline_delete_forever_24)
                setMessage(String.format(resources.getString(R.string.deleteDescription), args.awning.name))
                setPositiveButton(resources.getString(R.string.deleteDialogPositiveButton)) { dialog, id ->
                    viewModel.deleteLocalAwning(args.awning)
                    findNavController().navigateUp()
                }
                setNegativeButton(resources.getString(R.string.deleteDialogNegativeButton)) { dialog, id ->
                    // User cancelled the dialog
                }

            }.create()
        }

        alertDialog?.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(
                ResourcesCompat.getColor(
                    resources,
                    R.color.design_default_color_error,
                    null
                )
            )
        }

        alertDialog?.show()
    }

    private fun showRenameAlertDialog() {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(resources.getString(R.string.renameDialogTitle))
                setIcon(R.drawable.ic_baseline_drive_file_rename_outline_24)
                setMessage(String.format(resources.getString(R.string.renameDialogDescription), args.awning.name))

                val editText = EditText(activity)
                editText.setText(args.awning.name)
                editText.isSingleLine = true
                val params = FrameLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(48, 0, 48, 36)
                editText.layoutParams = params
                val container = RelativeLayout(context)
                val rlParams = FrameLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
                container.layoutParams = rlParams
                container.addView(editText)
                setView(container)


                setPositiveButton(resources.getString(R.string.renameDialogPositiveButton)) { _, _ ->
                    args.awning.name = editText.text.toString()

                    viewModel.updateLocalAwning(args.awning)
                    (activity as? AppActivity)?.supportActionBar?.title = args.awning.name

                }
                setNeutralButton(resources.getString(R.string.renameDialogNegativeButton)) { dialog, id ->
                    // User cancelled the dialog
                }
            }.create()
        }
        alertDialog?.show()
    }
}