package com.theworld.androidtemplatewithnavcomponents.ui

import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.theworld.androidtemplatewithnavcomponents.R
import com.theworld.androidtemplatewithnavcomponents.adapters.TrendingAdapter
import com.theworld.androidtemplatewithnavcomponents.databinding.FragmentDashboardBinding
import com.theworld.androidtemplatewithnavcomponents.utils.Resource
import com.theworld.androidtemplatewithnavcomponents.utils.collectLatestFlow
import com.theworld.androidtemplatewithnavcomponents.utils.handleApiError
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashboardFragment : Fragment(R.layout.fragment_dashboard) {

    companion object {
        private const val TAG = "DashboardFragment"
    }

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TrendingViewModel by viewModels()

    private val trendingAdapter by lazy { TrendingAdapter() }

    /*----------------------------------------- On Create View -------------------------------*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDashboardBinding.inflate(inflater)
        return binding.root
    }

    /*----------------------------------------- On ViewCreated -------------------------------*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        init()
        collectData()

    }

    private fun collectData() {

        viewModel.fetchTrendingRepos().observe(viewLifecycleOwner) { data ->
            binding.loadingSpinner.isVisible = false
            trendingAdapter.submitList(data)
            binding.notFound.isVisible = data.isEmpty()
            binding.swipeContainer.isRefreshing = false
        }

        /*collectLatestFlow(viewModel.fetchTrendingRepos()) { resource ->

            binding.loadingSpinner.isVisible = resource is Resource.Loading

            when (resource) {

                is Resource.Success -> {
                    val data = resource.value.items
                    trendingAdapter.submitList(data)
                    binding.notFound.isVisible = data.isEmpty()
                }
                is Resource.Failure -> {
                    handleApiError(resource)
                }

                else -> Unit
            }
        }*/
    }


    /*----------------------------------------- Init -------------------------------*/

    private fun init() {

        binding.apply {
            recyclerView.setHasFixedSize(true)
            recyclerView.adapter = trendingAdapter
        }


        binding.swipeContainer.setOnRefreshListener {
            viewModel.forceUpdateRepo(true)
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.dashboard_menu, menu)

    }


    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}