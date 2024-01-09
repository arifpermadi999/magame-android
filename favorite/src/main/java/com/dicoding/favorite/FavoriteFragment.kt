package com.dicoding.favorite

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.data.source.mapper.GameMapper
import com.dicoding.core.domain.models.Favorite
import com.dicoding.favorite.adapter.FavoriteAdapter
import com.dicoding.favorite.databinding.FragmentFavoriteBinding
import com.dicoding.favorite.di.favoriteViewModelModule
import com.dicoding.magame.ui.game.list.GameFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadKoinModules(favoriteViewModelModule)
        val vieModel : FavoriteViewModel by viewModel()
        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        vieModel.favorite().observe(viewLifecycleOwner){list ->
            if(list.size > 0){
                binding.emptyInc.emptyView.visibility = View.GONE
                showRecyclerList(list)
            }
        }
    }
    private fun showRecyclerList(list: List<Favorite>) {
        binding.rvFavorite.visibility = View.VISIBLE
        val listUserAdapter = FavoriteAdapter(requireContext(), list)
        binding.rvFavorite.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : FavoriteAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Favorite) {
                val mBundle = Bundle()
                val game = GameMapper.favDomainToGame(data)
                mBundle.putParcelable(GameFragment.EXTRA_GAME,game)
                binding.root.findNavController().navigate(com.dicoding.magame.R.id.action_favoriteFragment_to_detailGameFragment,mBundle)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}