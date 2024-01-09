package com.dicoding.magame.ui.game.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.core.data.source.remote.network.ApiResponse
import com.dicoding.core.data.source.remote.response.ScreenshotItem
import com.dicoding.core.domain.models.Favorite
import com.dicoding.core.domain.models.Game
import com.dicoding.core.utils.DialogImage
import com.dicoding.magame.R
import com.dicoding.magame.databinding.FragmentDetailGameBinding
import com.dicoding.magame.ui.game.detail.adapter.ScreenshootAdapter
import com.dicoding.magame.ui.game.list.GameFragment.Companion.EXTRA_GAME
import org.koin.androidx.viewmodel.ext.android.viewModel

@Suppress("DEPRECATION")
class DetailGameFragment : Fragment(),View.OnClickListener {

    private lateinit var binding: FragmentDetailGameBinding
    private var favorite: Favorite = Favorite()
    private lateinit var detailGameViewModel: DetailGameViewModel
    private lateinit var game: Game
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var onScrollDown = false
        val cardParam = binding.detailGame.cardDetail.layoutParams as ViewGroup.MarginLayoutParams
        val topCardParam = cardParam.topMargin

        val detailGameViewModel: DetailGameViewModel by viewModel()
        this.detailGameViewModel = detailGameViewModel

        val game: Game? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(EXTRA_GAME, Game::class.java)
        } else {
            arguments?.getParcelable(EXTRA_GAME)
        }
        this.game = game ?: Game()
        binding.detailGame.txtTitle.text = game?.name.toString()
        binding.detailGame.txtPlatform.text = getString(com.dicoding.core.R.string.platform_text,game?.platform)
        binding.detailGame.ratingBar.rating = (game?.rating ?: 0).toFloat()
        binding.detailGame.txtRating.text = getString(com.dicoding.core.R.string.rating_text,game?.rating ?: "" , game?.ratingsCount ?: "")
        Glide.with(requireActivity()).load(game?.image).into(binding.detailGame.imageGame)

        detailGameViewModel.detail(game?.id.toString()).observe(viewLifecycleOwner) { apiResponse ->
                when (apiResponse) {
                    ApiResponse.Loading -> showLoading(true)
                    ApiResponse.Empty -> {
                        showLoading(false)
                        Toast.makeText(requireActivity(),
                            getString(R.string.detail_description_not_show),Toast.LENGTH_LONG).show()
                    }
                    is ApiResponse.Error -> {
                        showLoading(false)
                        Toast.makeText(requireActivity(),
                            getString(R.string.error_message),Toast.LENGTH_LONG).show()
                    }
                    is ApiResponse.Success -> {
                        showLoading(false)
                        binding.detailGame.description.text = apiResponse.data.descriptionRaw

                        Glide.with(requireActivity()).load(apiResponse.data.backgroundImageAdditional).into(binding.imageGameCover)
                    }

                }
        }
        detailGameViewModel.screenshots(game?.id.toString()).observe(viewLifecycleOwner){apiResponse ->
            when (apiResponse) {
                ApiResponse.Loading -> showLoadingScreenshot(true)
                ApiResponse.Empty -> {
                    showLoadingScreenshot(false)
                    Toast.makeText(requireActivity(),
                        getString(R.string.detail_screenshots_not_show),Toast.LENGTH_LONG).show()
                }
                is ApiResponse.Error -> {
                    showLoadingScreenshot(false)
                    Toast.makeText(requireActivity(),
                        getString(R.string.error_message),Toast.LENGTH_LONG).show()
                }
                is ApiResponse.Success -> {
                    showLoadingScreenshot(false)
                    showRvScreenshot(apiResponse.data.results)
                }

            }

        }

        binding.detailGame.rvScreenshot.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)

        binding.btnFavorite.setOnClickListener(this)
        binding.backButton.setOnClickListener(this)
        binding.appBarLayout.addOnOffsetChangedListener{ _, verticalOffset: Int ->
            if(verticalOffset <= -577 && !onScrollDown){
                onScrollDown = true
                cardParam.setMargins(0,0,0,-30)
                binding.detailGame.cardDetail.layoutParams = cardParam
            }else if(verticalOffset >= -50 && onScrollDown){
                onScrollDown = false
                cardParam.setMargins(0,topCardParam,0,-30)
                binding.detailGame.cardDetail.layoutParams = cardParam
            }
        }
        getFavorite()

    }
    private fun showRvScreenshot(list: List<ScreenshotItem>? = null){
        if(list != null){
            val adapter = ScreenshootAdapter(requireContext(),list)
            binding.detailGame.rvScreenshot.adapter = adapter
            adapter.setOnItemClickCallback(object : ScreenshootAdapter.OnItemClickCallback {
                override fun onItemClicked(data: ScreenshotItem) {
                    DialogImage(requireContext(),layoutInflater,data.image.toString()).show()
                }
            })
        }
    }

    private fun showLoading(loading: Boolean) {
        binding.progressBarCover.visibility = if (loading) View.VISIBLE else View.GONE
        binding.detailGame.progressBarDescription.visibility = if (loading) View.VISIBLE else View.GONE
    }
    private fun showLoadingScreenshot(loading: Boolean) {
        binding.detailGame.shimmerSs.progressBarScreenshot.visibility = if (loading) View.VISIBLE else View.GONE
        binding.detailGame.rvScreenshot.visibility = if (!loading) View.VISIBLE else View.GONE
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
        binding.btnFavorite.setSpeed(2f)
        detailGameViewModel.getFavoriteByGame(game).observe(viewLifecycleOwner){
            if(it != Favorite()){
                favorite = it
                binding.btnFavorite.playAnimation()

            }
        }
    }



    override fun onClick(v: View?) {
        if(v?.id == R.id.btn_favorite){
            if(favorite == Favorite()){
                binding.btnFavorite.playAnimation()
                detailGameViewModel.addFavorite(game)
            }else{
                binding.btnFavorite.frame = 0
                detailGameViewModel.deleteFavorite(favorite)
                favorite = Favorite()
            }
        }
        //back button
        if(v?.id == R.id.back_button){
            activity?.onBackPressedDispatcher?.onBackPressed() // with this line
        }
    }

}