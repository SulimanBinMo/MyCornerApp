package com.example.mycornerapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.example.mycornerapp.MainActivity.Companion.currentUser
import com.example.mycornerapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity(), GamesDbAdapter.ClickListener   {

    lateinit var binding : ActivityHomeBinding
    lateinit var viewModel: ViewModel
    lateinit var adapter: GamesDbAdapter
    var userList = listOf<GameTable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logoutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        binding.profileBtn.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }



        adapter = GamesDbAdapter(this)
        binding.rvMain.adapter = adapter

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        viewModel.getGame().observe(this) {
            userList=it
            getuserList(currentUser!!)
        }
    }

    private fun getuserList(currentUser: Users) {
        var usersGamelist = arrayListOf<GameTable>()
        for(gameList in userList){
            if(gameList.userid==currentUser.id){
               usersGamelist.add(gameList)
            }
        }
        adapter.submitList(usersGamelist)
    }

    override fun delete(gameTable: GameTable) {
        deleteDialog(gameTable)
    }

    //delete dialog
    fun deleteDialog(gameTable: GameTable){
        val dialogBuilder =  AlertDialog.Builder(this)

        dialogBuilder!!.setPositiveButton("YES", DialogInterface.OnClickListener {
                dialog, id ->   viewModel.deleteGame(gameTable)
            dialog.cancel()
        })
            .setNegativeButton("NO", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
        val alert = dialogBuilder.create()
        alert.setTitle("Delete Game ")
        alert.setMessage("Are you sure you want to delete it?")
        alert.show()
    }
}