package fr.deuspheara.potterdbapp.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import coil.load
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.data.network.model.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.databinding.ItemCharacterBinding

class CharacterViewHolder private constructor(
    private val itemView: View
) : RecyclerView.ViewHolder(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup): CharacterViewHolder {
            return CharacterViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_character,
                    parent,
                    false
                )
            )
        }
    }

    fun bind(character: CharacterLightModel) {
        val binding = ItemCharacterBinding.bind(itemView)

        binding.apply {
            characterImageView.load(character.image){
                placeholder(R.drawable.missing_character)

                error(R.drawable.missing_character)
            }

            nameCharacter.text = character.name

            speciesCharacter.text = character.species

            characterCard.setOnClickListener{
                val bundle : Bundle = bundleOf(
                    "character_slug" to character.slug
                )
                val action = CharactersFragmentDirections.actionCharactersFragmentToCharacterDetailsFragment()
               itemView.findNavController().navigate(action.actionId, bundle)
            }
        }


    }


}