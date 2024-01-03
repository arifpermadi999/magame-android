package com.dicoding.magame.ui.game.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import com.dicoding.magame.R
import com.dicoding.magame.databinding.FragmentDetailGameBinding
import com.dicoding.magame.ui.game.list.GameFragment.Companion.EXTRA_GAME
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailGameFragment : Fragment(),View.OnClickListener {

    private lateinit var binding: FragmentDetailGameBinding
    private var favorite: Favorite = Favorite()
    private lateinit var detailGameViewModel: DetailGameViewModel
    private lateinit var game: Game
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailGameViewModel: DetailGameViewModel by viewModel()
        this.detailGameViewModel = detailGameViewModel

        val game: Game? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable<Game>(EXTRA_GAME, Game::class.java)
        } else {
            arguments?.getParcelable<Game>(EXTRA_GAME)
        }
        this.game = game!!
        binding.txtTitle.text = game.name.toString()
        binding.txtPlatform.text = "Platform : ${game.platform.toString()}"
        binding.ratingBar.rating = game.rating
        binding.txtRating.text = game.ratingsCount.toString()
        Glide.with(requireActivity()).load(game.image).into(binding.imageGame)

        detailGameViewModel.detail(game.id.toString()).observe(viewLifecycleOwner) { apiResponse ->
                when (apiResponse) {
                    ApiResponse.Loading -> showLoading(true)
                    ApiResponse.Empty -> {
                        showLoading(false)
                        Toast.makeText(requireActivity(),
                            getString(R.string.detail_description_not_show),Toast.LENGTH_LONG)
                    }
                    is ApiResponse.Error -> {
                        showLoading(false)
                        Toast.makeText(requireActivity(),
                            getString(R.string.detail_description_not_show),Toast.LENGTH_LONG)
                    }
                    is ApiResponse.Success -> {
                        showLoading(false)
                        binding.description.text = apiResponse.data.descriptionRaw
                    }

                }
        }

        binding.fabFavorite.setOnClickListener(this)
        getFavorite()
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailGameBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
    private fun getFavorite(){
        detailGameViewModel.getFavoriteByGame(game).observe(viewLifecycleOwner){
            if(it == Favorite()){
                binding.fabFavorite.setImageResource(R.drawable.favorite)
            }else{
                favorite = it
                binding.fabFavorite.setImageResource(R.drawable.favorite_fill)
            }
        }
    }

    override fun onClick(v: View?) {
        if(v?.id == R.id.fab_favorite){
            if(favorite == Favorite()){
                detailGameViewModel.addFavorite(game)
                binding.fabFavorite.setImageResource(R.drawable.favorite_fill)
            }else{
                binding.fabFavorite.setImageResource(R.drawable.favorite)
                detailGameViewModel.deleteFavorite(favorite)
                favorite = Favorite()
            }
        }
    }

}