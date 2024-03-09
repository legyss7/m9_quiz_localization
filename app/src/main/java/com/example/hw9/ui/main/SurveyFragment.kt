package com.example.hw9.ui.main

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.hw9.R
import com.example.hw9.databinding.SurveyFragmentBinding


class SurveyFragment : Fragment() {

    private var _binding: SurveyFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SurveyFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.alpha = 0f
        binding.button.animate().apply {
            duration = 3000
            alpha(1f)
        }.start()

        binding.button.setOnClickListener {
            val answers: String = getAnswersByUser()
            val action = SurveyFragmentDirections
                .actionSurveyFragmentToSurveyResultFragment(answers)
            findNavController().navigate(action)
        }

        val textView = binding.title
        val greetingArgsEvaluator = object : TypeEvaluator<IntArray> {
            private val argsEvaluator = ArgbEvaluator()
            override fun evaluate(
                fraction: Float,
                startValue: IntArray,
                endValue: IntArray
            ): IntArray {
                return startValue.mapIndexed { index, item ->
                    argsEvaluator.evaluate(fraction, item, endValue[index]) as Int
                }.toIntArray()
            }
        }
        ValueAnimator.ofObject(
            greetingArgsEvaluator,
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.MAGENTA),
            intArrayOf(Color.MAGENTA, Color.MAGENTA, Color.BLUE),
            intArrayOf(Color.MAGENTA, Color.BLUE, Color.BLACK),
            intArrayOf(Color.BLUE, Color.BLACK, Color.RED),
            intArrayOf(Color.BLACK, Color.RED, Color.GREEN),
            intArrayOf(Color.BLACK, Color.GREEN, Color.BLUE),
            intArrayOf(Color.GREEN, Color.BLUE, Color.CYAN),
            intArrayOf(Color.BLUE, Color.CYAN, Color.YELLOW),
            intArrayOf(Color.CYAN, Color.YELLOW, Color.MAGENTA),
        ).apply {
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            duration = 3000
            addUpdateListener {
                val shader = LinearGradient(
                    0f, 0f,
                    textView.paint.measureText(textView.toString()),
                    textView.textSize,
                    it.animatedValue as IntArray,
                    null,
                    Shader.TileMode.CLAMP
                )
                textView.paint.shader = shader
                textView.invalidate()
            }
            start()
        }

    }

    private fun getAnswersByUser(): String {
        var answer = ""
        if (binding.question1.checkedRadioButtonId == binding.answer11.id) answer +=
            getString(R.string.answer_11_response)
        if (binding.question1.checkedRadioButtonId == binding.answer12.id) answer +=
            getString(R.string.answer_12_response)
        if (binding.question1.checkedRadioButtonId == binding.answer13.id) answer +=
            getString(R.string.answer_13_response)
        if (binding.question1.checkedRadioButtonId == binding.answer14.id) answer +=
            getString(R.string.answer_14_response)
        answer += "\n\n"
        if (binding.question2.checkedRadioButtonId == binding.answer21.id) answer +=
            getString(R.string.answer_21_response)
        if (binding.question2.checkedRadioButtonId == binding.answer22.id) answer +=
            getString(R.string.answer_22_response)
        if (binding.question2.checkedRadioButtonId == binding.answer23.id) answer +=
            getString(R.string.answer_23_response)
        if (binding.question2.checkedRadioButtonId == binding.answer24.id) answer +=
            getString(R.string.answer_24_response)
        answer += "\n\n"
        if (binding.question3.checkedRadioButtonId == binding.answer31.id) answer +=
            getString(R.string.answer_31_response)
        if (binding.question3.checkedRadioButtonId == binding.answer32.id) answer +=
            getString(R.string.answer_32_response)
        if (binding.question3.checkedRadioButtonId == binding.answer33.id) answer +=
            getString(R.string.answer_33_response)
        if (binding.question3.checkedRadioButtonId == binding.answer34.id) answer +=
            getString(R.string.answer_34_response)
        answer += "\n\n"
        return answer
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}