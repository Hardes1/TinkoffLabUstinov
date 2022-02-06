package com.example.tinkofflabustinov.UI.latest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.tinkofflabustinov.R
import com.example.tinkofflabustinov.UI.BaseFragment
import com.example.tinkofflabustinov.UI.GifType
import com.example.tinkofflabustinov.models.GifViewModel
import com.example.tinkofflabustinov.databinding.GifFragmentBinding

class GifLatestFragment : BaseFragment() {

    override val type: GifType = GifType.LATEST
}