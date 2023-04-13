package fr.deuspheara.potterdbapp.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.databinding.FragmentCharacterDetailsBinding
import fr.deuspheara.potterdbapp.databinding.FragmentCharactersBinding


class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


}