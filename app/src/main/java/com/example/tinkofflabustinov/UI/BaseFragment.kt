package com.example.tinkofflabustinov.UI


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.tinkofflabustinov.R
import com.example.tinkofflabustinov.databinding.GifFragmentBinding
import com.example.tinkofflabustinov.models.GifViewModel

open class BaseFragment : Fragment() {
    protected open val type: GifType = GifType.NONE
    private val viewModel: GifViewModel by activityViewModels()
    private var _binding: GifFragmentBinding? = null
    private lateinit var circularProgressDrawable: CircularProgressDrawable
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
        initializeProgressBar()
        viewModel.onStartApp()
        binding.nextButton.setOnClickListener {
            viewModel.increaseSteps(type)
        }
        binding.prevButton.setOnClickListener {
            viewModel.decreaseSteps(type)
        }
        viewModel.imageUrls[type.ordinal].observe(viewLifecycleOwner) {
            setGif(it)
        }
        viewModel.numberOfSteps[type.ordinal].observe(viewLifecycleOwner) {
            binding.prevButton.visibility = if (it == 0) View.GONE else View.VISIBLE
        }
        binding.imgView.setOnClickListener {
            viewModel.onRestoreConnection(type)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeProgressBar() {
        circularProgressDrawable = CircularProgressDrawable(requireContext())
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 150f
        circularProgressDrawable.start()
    }

    private fun setGif(url: String) {
        Glide.with(requireContext()).asGif().load(url)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_baseline_refresh_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imgView)

    }
}