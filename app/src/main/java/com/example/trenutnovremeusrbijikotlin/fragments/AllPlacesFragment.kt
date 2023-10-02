package com.example.trenutnovremeusrbijikotlin.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trenutnovremeusrbijikotlin.SharedPlacesViewModel
import com.example.trenutnovremeusrbijikotlin.databinding.FragmentAllPlacesBinding
import com.example.trenutnovremeusrbijikotlin.ui.StationAdapter

class AllPlacesFragment : AbstractBaseFragment() {
    lateinit var binding: FragmentAllPlacesBinding
    private lateinit var adapter: StationAdapter

    companion object {
        fun newInstance() = AllPlacesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllPlacesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let { mViewModel = ViewModelProvider(it).get(SharedPlacesViewModel::class.java) }
        binding.button.setOnClickListener { mViewModel.refreshDataSync() }
        setUpObservers()
        setUpReccy()
    }

    private fun setUpReccy() {
        binding.allPlacesRecy.setHasFixedSize(true)
        binding.allPlacesRecy.layoutManager = LinearLayoutManager(context)
        adapter = StationAdapter(mViewModel._rssFeedData.value?.articleList ?: ArrayList())
        adapter.listener = mViewModel
        binding.allPlacesRecy.adapter = adapter
    }

    private fun getFavs() {
        val sharedPreferences = context?.let {
            PreferenceManager
                .getDefaultSharedPreferences(it)
        }
        var prefSet: HashSet<String>? =
            sharedPreferences?.getStringSet("stations", HashSet<String>())
                ?.let { HashSet<String>(it) }
    }

    private fun setUpObservers() {
        mViewModel.latestData.observe(viewLifecycleOwner) {
            it?.let { adapter.setData(it.articleList) }
        }
    }


}