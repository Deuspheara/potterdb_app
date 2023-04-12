package fr.deuspheara.potterdbapp.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.network.model.CharacterType
import fr.deuspheara.potterdbapp.databinding.ItemCharacterBinding

class CharacterViewHolder private constructor(
    private val binding: ItemCharacterBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun newInstance(parent: ViewGroup): CharacterViewHolder {
            return CharacterViewHolder(
                ItemCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    fun bind(character: CharacterType) {
        binding.characterImageView.load(character.attributes.image){
            placeholder(R.drawable.missing_character)

            error(R.drawable.missing_character)
        }
        binding.nameCharacter.text = character.attributes.name
        binding.speciesCharacter.text = character.attributes.species
    }


}