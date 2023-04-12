package fr.deuspheara.potterdbapp.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import fr.deuspheara.potterdbapp.databinding.FragmentCharactersBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding
    private val viewModel: CharacterViewModel by viewModels()
    private lateinit var pagingAdapter: CharacterPagingAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pagingAdapter = CharacterPagingAdapter()


        binding.charactersRecyclerView.adapter = pagingAdapter
        binding.charactersRecyclerView.layoutManager =  LinearLayoutManager(requireContext())

        viewLifecycleOwner.lifecycle.coroutineScope.launch{
            viewModel.state.collectLatest { state ->
                state.currentError?.let {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }

                state.successModel.let {

                    it?.collectLatest { pagingCharacterData ->
                        pagingCharacterData.map {
                            Log.d("CharacterFragment", it.attributes.name.toString())
                        }
                        pagingAdapter.submitData(pagingCharacterData)
                    }
                }

            }

            viewModel.fetchFilteredCharacterPaginated(null, "name")
        }


        binding.tempButton.setOnClickListener{
            viewLifecycleOwner.lifecycle.coroutineScope.launch{
                viewModel.fetchFilteredCharacterPaginated("name", null)
            }
        }
    }



}