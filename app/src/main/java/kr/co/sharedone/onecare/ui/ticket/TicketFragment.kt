package kr.co.sharedone.onecare.ui.ticket/*
package kr.co.sharedone.onecare.ui.ticket

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.sharedone.onecare.R
import kr.co.sharedone.onecare.core.Result
import kr.co.sharedone.onecare.data.model.Ticket
import kr.co.sharedone.onecare.databinding.FragmentMainBinding
import kr.co.sharedone.onecare.presentation.TicketViewModel
import kr.co.sharedone.onecare.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TicketFragment : Fragment(R.layout.fragment_main),
    TicketAdapter.OnMainItemClickListener {
    private val viewModel by activityViewModels<MainViewModel>()
    private lateinit var mainAdapter: TicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainAdapter = TicketAdapter(requireContext(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view)

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = mainAdapter

        binding.searchView.onQueryTextChanged {
            viewModel.setCocktail(it)
        }

        viewModel.fetchCocktailList.observe(viewLifecycleOwner) { result ->
            binding.progressBar.showIf { result is Resource.Loading }

            when (result) {
                is Resource.Loading -> {
                    binding.emptyContainer.root.hide()
                }
                is Resource.Success -> {
                    if (result.data.isEmpty()) {
                        binding.emptyContainer.root.show()
                        return@observe
                    }
                    mainAdapter.setCocktailList(result.data)
                    binding.emptyContainer.root.hide()
                }
                is Resource.Failure -> {
                    showToast("Ocurrió un error al traer los datos ${result.exception}")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favoritos -> {
                findNavController().navigate(R.id.action_mainFragment_to_favoritosFragment)
                false
            }
            else -> false
        }
    }

    override fun onCocktailClick(cocktail: Cocktail, position: Int) {
        findNavController().navigate(
            MainFragmentDirections.actionMainFragmentToTragosDetalleFragment(
                cocktail
            )
        )
    }
}*/
