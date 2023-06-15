package fr.deuspheara.potterdbapp.ui.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.databinding.ItemFavoriteBinding

class FavoriteViewHolder(private val binding: ItemFavoriteBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: CharacterLightModel) {
        binding.apply {
            ivCharacter.load(character.image){
                placeholder(R.drawable.missing_character)
                error(R.drawable.missing_character)
            }
            favoriteName.text = character.name
        }
    }
    companion object {
        fun newInstance(parent: ViewGroup): FavoriteViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemFavoriteBinding.inflate(inflater, parent, false)
            return FavoriteViewHolder(binding)
        }
    }
}
