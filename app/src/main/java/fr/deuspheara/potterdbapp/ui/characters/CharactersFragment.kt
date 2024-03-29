package fr.deuspheara.potterdbapp.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.databinding.FragmentCharactersBinding
import fr.deuspheara.potterdbapp.utils.NetworkHelper
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharacterViewModel by activityViewModels()
    private lateinit var pagingAdapter: CharacterPagingAdapter

    var listFavorite : List<CharacterLightModel>? = listOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagingAdapter = CharacterPagingAdapter { character ->
            lifecycleScope.launch {
                character.slug?.let {
                    viewModel.toggleFavorite(it, character)
                }
            }
            Log.d("CharacterFragment", character.name.toString())
        }


        binding.charactersRecyclerView.adapter = pagingAdapter
        binding.charactersRecyclerView.layoutManager =  LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycle.coroutineScope.launch{
            viewModel.characterState.collectLatest { state ->
                state.currentError?.let {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    binding.characterProgressBar.isVisible = true
                }

                state.successModel.let { pagingDataFlow ->
                    binding.characterProgressBar.visibility = View.GONE
                    pagingDataFlow?.collectLatest { pagingCharacterData ->
                        pagingCharacterData.map {
                            Log.d("CharacterFragment", it.name.toString())
                            it.isFavorite = listFavorite?.any { characterLightModel -> characterLightModel.slug == it.slug } ?: false
                        }
                        pagingAdapter.submitData(pagingCharacterData)
                    }

                }
                state.isInProgress.let {
                    binding.characterProgressBar.visibility = View.VISIBLE
                }

            }

            viewModel.fetchFilteredCharacterPaginated(null, "name")
        }

        viewLifecycleOwner.lifecycle.coroutineScope.launch{
            viewModel.favoriteState.collectLatest { state ->
                state.currentError?.let {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }

                state.successModel.let {
                    Log.d("FavoriteFragment", it.toString())
                    listFavorite = it?.filter { characterLightModel -> characterLightModel.isFavorite }
                }
            }
        }


        binding.searchViewCharacter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewLifecycleOwner.lifecycle.coroutineScope.launch{
                    viewModel.fetchFilteredCharacterPaginated("name", query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.searchViewCharacter.setOnClickListener(View.OnClickListener {
            binding.searchViewCharacter.isIconified = false
        })
    }



}