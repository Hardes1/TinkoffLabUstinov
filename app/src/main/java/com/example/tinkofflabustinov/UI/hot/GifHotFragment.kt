package com.example.tinkofflabustinov.UI.hot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.tinkofflabustinov.R
import com.example.tinkofflabustinov.UI.BaseFragment
import com.example.tinkofflabustinov.UI.GifType
import com.example.tinkofflabustinov.models.GifViewModel
import com.example.tinkofflabustinov.databinding.GifFragmentBinding

class GifHotFragment : BaseFragment() {
    override val type: GifType = GifType.HOT

}