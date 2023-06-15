package fr.deuspheara.potterdbapp.ui.favorite

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.core.model.character.CharacterTraits
import fr.deuspheara.potterdbapp.ui.characters.detail.CharacterDetailsViewHolder


class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var favoriteList: List<CharacterLightModel> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder.newInstance(parent)
    }

    override fun getItemCount(): Int {
        return favoriteList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(favoriteList[position])
    }
    
    fun submitList(list: List<CharacterLightModel>) {
        favoriteList = list
        notifyDataSetChanged()
    }
}

