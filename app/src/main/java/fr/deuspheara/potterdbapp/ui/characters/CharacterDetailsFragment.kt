package fr.deuspheara.potterdbapp.ui.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.paging.map
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.databinding.FragmentCharacterDetailsBinding
import fr.deuspheara.potterdbapp.databinding.FragmentCharactersBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.coroutineScope.launch {
            arguments?.getString("character_slug")?.let {
                viewModel.fetchCharacterBySlug(it)
                Log.d("CharacterDetailsFragments", "fetchCharacterWithId: $it")
            }

            viewModel.state.collectLatest { state ->
                state.currentError?.let {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()

                }

                state.successModel.let {

                    it?.let {
                        binding.apply {
                            imageDetailCharacter.load(it.image){
                                crossfade(true)
                                placeholder(R.drawable.missing_character)
                            }
                            nameDetailCharacter.text = it.name
                            characterGender.text = it.gender
                            characterBirthDate.text = it.born
                            characterSpecies.text = it.species

                            backButtonCharacter.setOnClickListener {
                                val action = CharacterDetailsFragmentDirections.actionCharacterDetailsFragmentToCharactersFragment()
                                findNavController(
                                    this@CharacterDetailsFragment
                                ).navigate(action)
                            }
                        }
                    }

                }
                state.isInProgress.let {


                }

            }
        }
    }
}