package com.example.trenutnovremeusrbijikotlin

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trenutnovremeusrbijikotlin.databinding.FragmentAllPlacesBinding
import com.example.trenutnovremeusrbijikotlin.network.RSSFeed
import com.example.trenutnovremeusrbijikotlin.ui.StationAdapter

class AllPlacesFragment : Fragment() {
    lateinit var binding:FragmentAllPlacesBinding
    private lateinit var adapter:StationAdapter


    companion object {
        fun newInstance() = AllPlacesFragment()
    }

    private lateinit var viewModel: AllPlacesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentAllPlacesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AllPlacesViewModel::class.java)
        viewModel.refreshData()
        binding.button.setOnClickListener(View.OnClickListener { view ->
            viewModel.refreshData()
        })
        setUpObservers()
        setUpReccy()
        // TODO: Use the ViewModel
    }

    private fun setUpReccy() {
        binding.allPlacesRecy.setHasFixedSize(true)
        binding.allPlacesRecy.layoutManager= LinearLayoutManager(context)
        adapter=StationAdapter(RSSFeed().articleList)
        binding.allPlacesRecy.adapter=adapter
    }

    private fun setUpObservers() {
        viewModel.rssFeedData.observe(viewLifecycleOwner){
            it?.let {adapter.setData(it.articleList)  }

        }
    }

}