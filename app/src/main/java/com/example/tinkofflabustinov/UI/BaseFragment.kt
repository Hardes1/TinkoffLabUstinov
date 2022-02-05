package com.example.tinkofflabustinov.UI

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tinkofflabustinov.R
import com.example.tinkofflabustinov.databinding.GifFragmentBinding
import com.example.tinkofflabustinov.models.GifViewModel

open class BaseFragment : Fragment() {
    protected open val type: GifType = GifType.NONE
    protected val viewModel: GifViewModel by activityViewModels()
    private var _binding: GifFragmentBinding? = null

    private val binding: GifFragmentBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GifFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.nextButton.setOnClickListener {
            viewModel.increaseSteps(type)
        }
        binding.prevButton.setOnClickListener {
            viewModel.decreaseSteps(type)
        }
        viewModel.numberOfSteps[type.ordinal].observe(viewLifecycleOwner) {
            binding.prevButton.visibility = if (it == 0) View.GONE else View.VISIBLE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}