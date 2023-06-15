package fr.deuspheara.potterdbapp.ui.characters.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.core.model.character.CharacterTraits
import fr.deuspheara.potterdbapp.core.model.character.Characteristics
import fr.deuspheara.potterdbapp.databinding.ItemCharacterTraitBinding
import fr.deuspheara.potterdbapp.databinding.ItemFavoriteBinding
import fr.deuspheara.potterdbapp.ui.characters.CharacterViewHolder

class CharacterDetailsViewHolder(private val binding: ItemCharacterTraitBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterTraits) {
        binding.apply {
            tvNameTraits.text = character.value
            ivTraits.setImageResource(character.characteristics.iconResId)
        }
    }

    companion object {
        fun newInstance(parent: ViewGroup): CharacterDetailsViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemCharacterTraitBinding.inflate(inflater, parent, false)
            return CharacterDetailsViewHolder(binding)
        }
    }
}
