package com.example.hw9.ui.main

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AccelerateInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.hw9.R
import com.example.hw9.databinding.SurveyResultFragmentBinding


class SurveyResultFragment : Fragment() {

    private val args: SurveyResultFragmentArgs by navArgs()
    private var _binding: SurveyResultFragmentBinding? = null
    private val binding get() = _binding!!

    private val rotationX = PropertyValuesHolder.ofFloat(View.ROTATION_X, 0f, 3600f)
    private val textColor: PropertyValuesHolder = PropertyValuesHolder.ofInt(
        "backgroundColor",
        Color.parseColor("#FFFFFF"),
        Color.parseColor("#00EE3C")
    ).apply {
        setEvaluator(ArgbEvaluator())
    }

    private val rotation = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 720f)
    private val translateY = PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, -400f, 400f)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SurveyResultFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.result.text = args.answer
        (AnimatorInflater.loadAnimator(
            this.context,
            R.animator.custom_multianimation
        )
                as AnimatorSet).apply {
            setTarget(binding.result)

        }.start()


        ObjectAnimator.ofArgb(
            binding.resultTitle,
            "backgroundColor",
            Color.parseColor("#FFFFFF"),
            Color.parseColor("#00EE3C")
        ).apply {
            duration = 1000
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()

        val firstAnimationButton = ObjectAnimator.ofPropertyValuesHolder(
            binding.button,
            translateY,
            rotation
        ).apply {
            duration = 1000
            interpolator = AccelerateInterpolator()
            repeatCount = 2
            repeatMode = ObjectAnimator.REVERSE
        }


        val secondAnimationButton = ObjectAnimator.ofPropertyValuesHolder(
            binding.button,
            rotationX,
            textColor
        ).apply {
            duration = 1000
            interpolator = AccelerateInterpolator()
            repeatCount = 2
            repeatMode = ObjectAnimator.REVERSE
        }

        AnimatorSet().apply {
            play(secondAnimationButton).before(firstAnimationButton)
            start()
        }

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_surveyResultFragment_to_surveyFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}