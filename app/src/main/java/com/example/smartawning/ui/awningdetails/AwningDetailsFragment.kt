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
import com.example.smartawning.domain.entity.AwningConfig
import com.example.smartawning.ui.AppActivity
import com.example.vaseisapp.base.BaseFragment
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
            awningConfig.observe(viewLifecycleOwner, { config ->
                setupViewWithConfig(config)
            })

            loadAwningConfig(args.awning.name)
        }
    }

    private fun setupViewWithConfig(config: AwningConfig) {
        with(binding) {
            rainSwitcher.isChecked = config.isRainChecked
            sunSwitcher.isChecked = config.isSunnyChecked
            timeSwitcher.isChecked = config.isTimeChecked

            controlConstraintLayout.animation = null
            rainConstraintLayout.animation = null
            sunConstraintLayout.animation = null
            timeConstraintLayout.animation = null

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
            timeRangeSlider.isVisible = true
        }
    }

    private fun setupViews() {
        with(binding) {
            setHasOptionsMenu(true)
            (activity as? AppActivity)?.supportActionBar?.title = args.awning.name


            rainConstraintLayout.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)
            sunConstraintLayout.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)
            controlConstraintLayout.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)
            timeConstraintLayout.animation = AnimationUtils.loadAnimation(context, R.anim.blinking)

            rainSwitcher.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.rainImageView.setImageResource(R.drawable.ic_rainy)
                } else {
                    binding.rainImageView.setImageResource(R.drawable.ic_not_rainy)
                }
            }

            sunSwitcher.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    binding.sunImageView.setImageResource(R.drawable.ic_sun)
                } else {
                    binding.sunImageView.setImageResource(R.drawable.ic_not_sun)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deleteItem -> showDeleteAlertDialog("Σαλόνι")
            R.id.renameItem -> showRenameAlertDialog("PAOK")
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun showDeleteAlertDialog(name: String) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(resources.getString(R.string.deleteDialogTitle))
                setIcon(R.drawable.ic_baseline_delete_forever_24)
                setMessage(String.format(resources.getString(R.string.deleteDescription), name))
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

    private fun showRenameAlertDialog(name: String) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(resources.getString(R.string.renameDialogTitle))
                setIcon(R.drawable.ic_baseline_drive_file_rename_outline_24)
                setMessage(String.format(resources.getString(R.string.deleteDescription), name))

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


                setPositiveButton(resources.getString(R.string.renameDialogPositiveButton)) { dialog, id ->
                    //todo rename tenta
                }
                setNeutralButton(resources.getString(R.string.renameDialogNegativeButton)) { dialog, id ->
                    // User cancelled the dialog
                }

            }.create()
        }

        alertDialog?.show()
    }
}