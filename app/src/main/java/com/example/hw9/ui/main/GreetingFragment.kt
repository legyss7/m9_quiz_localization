package com.example.hw9.ui.main

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.fragment.findNavController
import com.example.hw9.R
import com.example.hw9.databinding.GreetingFragmentBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class GreetingFragment : Fragment() {

    private lateinit var binding: GreetingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GreetingFragmentBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataFormat = SimpleDateFormat("dd.MM.yy",  Locale.US)
        val calendar = Calendar.getInstance()
        binding.buttonDate.setOnClickListener {
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .setTitleText(resources.getString(R.string.date_dialog_text))
                .build()
            dateDialog.addOnPositiveButtonClickListener { time ->
                calendar.timeInMillis = time
                val data = calendar.time
                val formatter = dataFormat.format(data)
                Snackbar.make(binding.buttonDate, formatter, Snackbar.LENGTH_SHORT).show()
            }
            dateDialog.show(parentFragmentManager, "Date")
        }

        binding.button.animate().apply {
            duration = 1000
            rotation(360f)
        }
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_greetingFragment_to_surveyFragment)
        }
    }

}