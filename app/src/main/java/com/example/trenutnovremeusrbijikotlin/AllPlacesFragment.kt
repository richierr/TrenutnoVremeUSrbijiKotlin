package com.example.trenutnovremeusrbijikotlin

import android.content.SharedPreferences
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trenutnovremeusrbijikotlin.databinding.FragmentAllPlacesBinding
import com.example.trenutnovremeusrbijikotlin.network.Station
import com.example.trenutnovremeusrbijikotlin.ui.StationAdapter

class AllPlacesFragment : Fragment() {
    lateinit var binding:FragmentAllPlacesBinding
    private lateinit var adapter: StationAdapter
    private lateinit var viewModel: SharedPlacesViewModel

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
        activity?.let { viewModel = ViewModelProvider(it).get(SharedPlacesViewModel::class.java) }
        viewModel.rssFeedData.value ?: viewModel.refreshData()
        binding.button.setOnClickListener { viewModel.refreshData() }
        setUpObservers()
        setUpReccy()
    }

    private fun setUpReccy() {
        binding.allPlacesRecy.setHasFixedSize(true)
        binding.allPlacesRecy.layoutManager = LinearLayoutManager(context)
        adapter = StationAdapter(viewModel.rssFeedData.value?.articleList ?: ArrayList())
        adapter.listener = viewModel
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
        viewModel.rssFeedData.observe(viewLifecycleOwner) {
            it?.let { adapter.setData(it.articleList) }
        }
    }


}