package com.example.trenutnovremeusrbijikotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trenutnovremeusrbijikotlin.databinding.FragmentFavoritesBinding
import com.example.trenutnovremeusrbijikotlin.ui.StationAdapter

class FavoritesFragment : Fragment() {
    lateinit var binding: FragmentFavoritesBinding
    private lateinit var adapter: StationAdapter
    private lateinit var viewModel: SharedPlacesViewModel
    private var sharedPreferenceManager = context?.let {
        PreferenceManager.getDefaultSharedPreferences(it)
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)
        activity?.let { viewModel = ViewModelProvider(it).get(SharedPlacesViewModel::class.java) }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpReccy()
        setUpObserver()
    }

    private fun setUpObserver() {
        viewModel.rssFeedData.observe(viewLifecycleOwner) {
            it?.let {
                adapter.setData(it.articleList.filter { station -> station.favorite })
            }
        }
    }


    private fun setUpReccy() {
        binding.favsRecy.setHasFixedSize(true)
        binding.favsRecy.layoutManager = LinearLayoutManager(context)
        adapter = StationAdapter(viewModel.rssFeedData.value?.articleList ?: ArrayList())
        adapter.listener = viewModel
        binding.favsRecy.adapter = adapter
    }

    private fun getFavorites(): HashSet<String>? {
        return sharedPreferenceManager?.getStringSet("stations", HashSet<String>())
            ?.let { HashSet<String>(it) }
    }

}