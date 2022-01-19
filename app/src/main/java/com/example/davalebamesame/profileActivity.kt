package com.example.davalebamesame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserInfo
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import java.util.jar.Attributes
import com.example.davalebamesame.models.Userinfo as userinfo

class profileActivity : AppCompatActivity() {

    private lateinit var Userimage : ImageView
    private lateinit var UserName : TextView
    private lateinit var addprofilePhoto : EditText
    private lateinit var userNameinput : EditText
    private lateinit var LogOutButton : Button
    private lateinit var Save : Button
    private val db = FirebaseDatabase.getInstance().getReference("UserInfo")
    private val auth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)


        init()

        onClickListeners()

        db.child(auth.currentUser?.uid!!).addChildEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userInfo : UserInfo = snapshot.getValue(UserInfo::class.java) ?: return
                    UserName.text = userinfo.
                    Glide.with(this@profileActivity)
                        .load(UserInfo.imageUrl).placeholder(R.drawable.ic_launcher_foreground)
                        .into(Userimage)
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })


    }

    private fun init (){
        Userimage = findViewById(R.id.Userimage)
        UserName = findViewById(R.id.UserName)
        addprofilePhoto = findViewById(R.id.addprofilePhoto)
        userNameinput = findViewById(R.id.userNameinput)
        LogOutButton = findViewById(R.id.LogOutButton)
        Save = findViewById(R.id.Save)

    }

    private fun onClickListeners() {

        Save.setOnClickListener {
            val name = userNameinput.text.toString()
            val imageUrl = addprofilePhoto.text.toString()

            if (name.isEmpty() || imageUrl.isEmpty()) {
                return@setOnClickListener
            }

            val userinfo = userinfo(name, imageUrl)
            db.child(auth.currentUser?.uid!!)
                .setValue(userinfo)

        }


    }

}