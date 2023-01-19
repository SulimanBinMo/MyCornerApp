package com.example.mycornerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mycornerapp.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(ViewModel::class.java)

        binding.signInBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.signUpBtn.setOnClickListener {

            val firstn=binding.firstNameEt.text
            val lastn=binding.lastNameEt.text
            val username=binding.usernameEt.text
            val email=binding.emailEt.text
            val pass=binding.passEt.text
            val confirmPass=binding.confPassEt.text
            val userintreset=binding.interestEt.text



            if(firstn.isNotEmpty()&&lastn.isNotEmpty()&&username.isNotEmpty()&&
                    email.isNotEmpty()&&pass.isNotEmpty()&&confirmPass.isNotEmpty()&&userintreset.isNotEmpty()){
                if(pass.toString()==confirmPass.toString()){
                    viewModel.adduser(
                        Users(0,firstn.toString(),lastn.toString(),username.toString(),
                    email.toString(),userintreset.toString(),pass.toString()))
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this@SignUpActivity,"The passwords must be match", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}