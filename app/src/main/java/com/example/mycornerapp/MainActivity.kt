package com.example.mycornerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mycornerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var users:ArrayList<Users>
    lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        users= arrayListOf()
        viewModel.getUsers().observe(this){
            users.clear()
            users.addAll(it)
            Log.d("TAG", "onCreate: $users")
        }

        binding.signUpBtn.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.signInBtn.setOnClickListener {
            binding.apply {
                val username=emailEt.text
                val pass=passEd.text
                if(username.isNotEmpty()&&pass.isNotEmpty()){
                    for(user in users){
                        if (username.toString() == user.username) {
                           if(pass.toString() == user.pass){
                               currentUser=user
                               val intent = Intent(this@MainActivity, HomeActivity::class.java)
                               startActivity(intent)
                               Toast.makeText(this@MainActivity,"Welcome ${currentUser!!.firstname}",Toast.LENGTH_SHORT).show()

                           }
                        }
                    }
                }else{
                    Toast.makeText(this@MainActivity,"Fill the fields",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        var currentUser:Users?=null
    }
}