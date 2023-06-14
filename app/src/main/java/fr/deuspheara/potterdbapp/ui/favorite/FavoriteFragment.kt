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
import androidx.recyclerview.widget.RecyclerView
import fr.deuspheara.potterdbapp.R
import fr.deuspheara.potterdbapp.core.model.character.CharacterLightModel
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

        val listView : ListView = binding.favoriteListView

        viewLifecycleOwner.lifecycle.coroutineScope.launch{
            viewModel.favoriteState.collectLatest { state ->
                state.currentError?.let {
                   Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                }

                state.successModel.let {
                    Log.d("FavoriteFragment", it.toString())
                    it?.let { favoriteList ->
                        val adapter = FavoriteAdapter(requireContext(), favoriteList)
                        listView.adapter = adapter
                    }
                }
            }
        }
    }
}

class FavoriteAdapter(context: Context, favoriteList: List<CharacterLightModel>) : BaseAdapter() {
    private val mContext: Context
    private val favoriteList: List<CharacterLightModel>
    override fun getCount(): Int {
        return favoriteList.size
    }

    override fun getItem(position: Int): Any {
        return favoriteList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val layoutInflater = LayoutInflater.from(mContext)
        val rowMain = layoutInflater.inflate(R.layout.item_favorite, parent, false)
        val nameTextView = rowMain.findViewById<TextView>(R.id.favoriteName)
        nameTextView.text = favoriteList[position].name
        return rowMain
    }

    init {
        mContext = context
        this.favoriteList = favoriteList
    }
}