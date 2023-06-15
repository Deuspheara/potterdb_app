package fr.deuspheara.potterdbapp.ui.characters.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.core.model.character.CharacterTraits
import fr.deuspheara.potterdbapp.core.model.character.Characteristics
import fr.deuspheara.potterdbapp.databinding.FragmentCharacterDetailsBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var characterDetailsAdapter: CharacterDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)

        characterDetailsAdapter = CharacterDetailsAdapter()
        binding.rvTraits.apply {
            adapter = characterDetailsAdapter
            layoutManager = LinearLayoutManager(context)
        }

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

                                error(R.drawable.missing_character)
                            }
                            nameDetailCharacter.text = it.name.isNullOrBlank().let { isNameEmpty ->
                                if (isNameEmpty) "Unknown" else it.name
                            }
//
                            characterDetailsAdapter.submitList(
                                listOfNotNull(
                                    it.gender?.let { gender -> CharacterTraits(Characteristics.GENDER, gender) },
                                    it.species?.let { species -> CharacterTraits(Characteristics.SPECIES, species) },
                                    it.height?.let { height -> CharacterTraits(Characteristics.HEIGHT, height) },
                                    it.weight?.let { weight -> CharacterTraits(Characteristics.WEIGHT, weight) },
                                    it.house?.let { house -> CharacterTraits(Characteristics.HOUSE, house) }
                                )
                            )



                            backButtonCharacter.setOnClickListener {
                                findNavController().popBackStack()
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