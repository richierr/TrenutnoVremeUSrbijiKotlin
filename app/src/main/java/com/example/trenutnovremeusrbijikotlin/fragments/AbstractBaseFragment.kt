package com.example.trenutnovremeusrbijikotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trenutnovremeusrbijikotlin.SharedPlacesViewModel

abstract class AbstractBaseFragment : Fragment() {
    lateinit var mViewModel: SharedPlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        activity?.let { mViewModel = ViewModelProvider(it).get(SharedPlacesViewModel::class.java) }
        super.onCreate(savedInstanceState)
    }
}