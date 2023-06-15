package fr.deuspheara.potterdbapp.ui.characters.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.potterdbapp.core.model.character.CharacterTraits

class CharacterDetailsAdapter : RecyclerView.Adapter<CharacterDetailsViewHolder>() {

    private var characterTraitsList: List<CharacterTraits> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterDetailsViewHolder {
        return CharacterDetailsViewHolder.newInstance(parent)
    }

    override fun getItemCount(): Int {
        return characterTraitsList.size
    }

    override fun onBindViewHolder(holder: CharacterDetailsViewHolder, position: Int) {
        holder.bind(characterTraitsList[position])
    }

    fun submitList(list: List<CharacterTraits>) {
        characterTraitsList = list
        notifyDataSetChanged()
    }
}

