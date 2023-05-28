package fr.deuspheara.potterdbapp.ui.characters

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import fr.deuspheara.potterdbapp.data.network.model.CharacterLightModel
import fr.deuspheara.potterdbapp.data.network.model.CharacterType

class CharacterPagingAdapter : PagingDataAdapter<CharacterLightModel, CharacterViewHolder>(COMPARATOR) {

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<CharacterLightModel>() {
            override fun areItemsTheSame(oldItem: CharacterLightModel, newItem: CharacterLightModel): Boolean {
                return oldItem.slug  == newItem.slug
            }

            override fun areContentsTheSame(oldItem: CharacterLightModel, newItem: CharacterLightModel): Boolean {
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
