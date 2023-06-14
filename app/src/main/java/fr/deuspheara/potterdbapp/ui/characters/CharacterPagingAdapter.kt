package fr.deuspheara.potterdbapp.ui.characters

import android.util.Log
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel

class CharacterPagingAdapter(
    private val toggleFavorite: (CharacterLightModel) -> Unit
) : PagingDataAdapter<CharacterLightModel, CharacterViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharacterLightModel>() {
            override fun areItemsTheSame(
                oldItem: CharacterLightModel,
                newItem: CharacterLightModel
            ): Boolean {
                return oldItem.slug == newItem.slug
            }

            override fun areContentsTheSame(
                oldItem: CharacterLightModel,
                newItem: CharacterLightModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position) ?: return
        val isFavorite = character.isFavorite
        holder.bind(
            character,
            isFavorite = isFavorite,
            favoriteButtonTap = {
                Log.d("CharacterPagingAdapter", "Toggle favorite character with slug: ${character.slug}")
                toggleFavorite(character)
            }
        )
    }
}
