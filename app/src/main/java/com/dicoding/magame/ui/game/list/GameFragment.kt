package com.dicoding.magame.ui.game.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.core.data.Resource
import com.dicoding.core.domain.models.Game
import com.dicoding.core.ui.GameAdapter
import com.dicoding.magame.R
import com.dicoding.magame.databinding.FragmentGameBinding
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : Fragment(),MenuItem.OnMenuItemClickListener {

    private lateinit var binding: FragmentGameBinding
    private lateinit var gameViewModel: GameViewModel
    private var search: String = "";
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameViewModel: GameViewModel by viewModel()
        this.gameViewModel = gameViewModel
        binding.rvGame.layoutManager = LinearLayoutManager(requireContext())
        if(search == ""){
            showUserList()
        }else{
            showUserListSearch(search)
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener{_,_,event ->
                searchBar.setText(searchView.text)
                search = searchView.text.toString()
                searchView.hide()
                binding.rvGame.visibility = View.GONE
                if(searchView.text.toString() == ""){
                    showUserList()
                }else{
                    showUserListSearch(searchView.text.toString())
                }
                false
            }

            searchBar.menu.findItem(R.id.favorite_item).setOnMenuItemClickListener(this@GameFragment)


        }



    }
    private fun showUserListSearch(search:String){
        binding.rvGame.visibility = View.GONE
        gameViewModel.gameSearch(search).observe(viewLifecycleOwner){games ->
            when (games) {
                is Resource.Error -> {
                    showLoading(false)
                    Snackbar.make(binding.root,games.message.toString(),Snackbar.LENGTH_LONG)
                }
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    binding.rvGame.visibility = View.VISIBLE
                    showLoading(false)
                    showRecyclerList(games.data!!)
                }
            }
        }
    }
    private fun showUserList(){
        gameViewModel.games().observe(viewLifecycleOwner) { games ->
            when (games) {
                is Resource.Error -> {
                    showLoading(false)
                    Snackbar.make(binding.root,games.message.toString(),Snackbar.LENGTH_LONG)
                }
                is Resource.Loading -> showLoading(true)
                is Resource.Success -> {
                    binding.rvGame.visibility = View.VISIBLE
                    showLoading(false)
                    showRecyclerList(games.data!!)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGameBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }




    private fun showLoading(loading: Boolean) {
        binding.progressBar.visibility = if (loading) View.VISIBLE else View.GONE
    }

    private fun showRecyclerList(list: List<Game>) {
        binding.rvGame.visibility = View.VISIBLE
        val listUserAdapter = GameAdapter(requireContext(), list)
        binding.rvGame.adapter = listUserAdapter
        listUserAdapter.setOnItemClickCallback(object : GameAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Game) {
                val mBundle = Bundle()
                mBundle.putParcelable(EXTRA_GAME,data)
                binding.root.findNavController().navigate(R.id.action_gameFragment_to_detailGameFragment,mBundle)
            }
        })
    }



    override fun onMenuItemClick(menu: MenuItem): Boolean {
        if(menu.itemId == R.id.favorite_item){
            binding.root.findNavController().navigate(R.id.action_gameFragment_to_favoriteFragment)

        }
        return true
    }
    companion object {
        const val EXTRA_GAME = "extra_game"
    }
}