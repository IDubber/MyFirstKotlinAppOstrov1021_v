package com.example.user.myfirstkotlinappostrov1021

import android.app.DownloadManager
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var status = ""
    var blankNumber = ""
    var carrierName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun check(view: View) {
        if (editText.text.isEmpty()) {
            val textString = "Введите что-нибудь"
            val toastMe = Toast.makeText(this, textString, Toast.LENGTH_SHORT)
            toastMe.show()
        }
        else {
            val toastMe = Toast.makeText(this, editText.text, Toast.LENGTH_SHORT)
            toastMe.show()

        }
    }
    fun request() {
        val referance = FirebaseDatabase.getInstance().reference
        val myQuery = referance.orderByChild("VehicleNumber").equalTo("Р642НР197")
        val postListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot?) {
                this@MainActivity.status = p0?.children?.first()?.children?.elementAt(11)?.value as String
                this@MainActivity.status = p0?.children?.first()?.children?.elementAt(0)?.value as String
                this@MainActivity.status = p0?.children?.first()?.children?.elementAt(3)?.value as String

            }
            override fun onCancelled(p0: DatabaseError?) {

            }

        }
        myQuery.addValueEventListener(postListener)
        detail.text = status

    }
    fun tapDetail(view: View) {
        val intentDetailOne = Intent(this, Detail::class.java)
        intentDetailOne.putExtra("status", this@MainActivity.status)
        startActivity(intentDetailOne)

    }
    }
