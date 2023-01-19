package com.example.mycornerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mycornerapp.MainActivity.Companion.currentUser
import com.example.mycornerapp.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity(),GamesApiAdapter.ClickLisstener {

    lateinit var binding: ActivityProfileBinding
    lateinit var viewModel: ViewModel
    lateinit var adapter: GamesApiAdapter

//    var interestList = listOf<GameItem>()

    var text = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = GamesApiAdapter(this)
        binding.rvInterest.adapter = adapter
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.apply {
            textView6.text=currentUser!!.firstname
            emailEt.text= currentUser!!.username
            passEd.text= currentUser!!.email

        }



        binding.interestBtn.setOnClickListener {
            text = binding.interestEt.text.toString()
            if (text.isNotEmpty()){
                Log.d("TAG", "onCreate: Am here")
            viewModel.getGameAPI(text).observe(this, {
                interestList -> adapter.submitList(interestList.map {it.copy() })
            })
            }
        }

        binding.homeBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
        binding.logoutBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }


    override fun addgameData(gameItem: GameTable) {
      viewModel.addGame(gameItem)
    }

}