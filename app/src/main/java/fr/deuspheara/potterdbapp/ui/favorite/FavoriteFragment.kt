package fr.deuspheara.potterdbapp.ui.favorite

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.coroutineScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
import fr.deuspheara.potterdbapp.core.model.character.CharacterTraits
import fr.deuspheara.potterdbapp.databinding.FragmentFavoriteBinding
import fr.deuspheara.potterdbapp.ui.characters.CharacterViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = FavoriteAdapter()
        binding.favoriteRecyclerView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycle.coroutineScope.launch{
            viewModel.favoriteState.collectLatest { state ->
                state.currentError?.let {
                   Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }

                state.successModel.let {
                    Log.d("FavoriteFragment", it.toString())
                    val listFavorite = it?.filter { characterLightModel -> characterLightModel.isFavorite }
                    listFavorite?.let { favoriteList ->
                       binding.favoriteRecyclerView.adapter = adapter
                        adapter.submitList(favoriteList.toCollection(ArrayList()))
                    }
                }
            }
        }
    }
}
