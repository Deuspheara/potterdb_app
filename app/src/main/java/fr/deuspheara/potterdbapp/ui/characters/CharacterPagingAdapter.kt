package fr.deuspheara.potterdbapp.ui.characters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import fr.deuspheara.potterdbapp.data.network.model.PotterCharacter
import fr.deuspheara.potterdbapp.data.network.model.CharacterType

class CharacterPagingAdapter : PagingDataAdapter<CharacterType, CharacterViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharacterType>() {
            override fun areItemsTheSame(oldItem: CharacterType, newItem: CharacterType): Boolean {
                return oldItem.id  == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterType, newItem: CharacterType): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder.newInstance(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        //TODO: return PotterType
        holder.bind(getItem(position) ?: return )
    }
}
