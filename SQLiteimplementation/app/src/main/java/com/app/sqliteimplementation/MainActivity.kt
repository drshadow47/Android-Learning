package com.app.sqliteimplementation

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    lateinit var name: EditText
    lateinit var contact : EditText
    lateinit var dob : EditText
    lateinit var insert : Button
    lateinit var update : Button
    lateinit var delete : Button
    lateinit var view : Button
  lateinit var Db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**
         * View Binding
         */
        name = findViewById(R.id.name_edtText)
        contact = findViewById(R.id.contact)
        dob = findViewById(R.id.dob)
        insert = findViewById(R.id.insert)
        update = findViewById(R.id.update)
        delete = findViewById(R.id.delete)
        view = findViewById(R.id.view)
        Db = DBHelper(this)  //DBheleper class object created
        /**
         * Insert Button click listener
         */
        insert.setOnClickListener(View.OnClickListener {
            var nameText: String = name.text.toString()
            var contactText: String = contact.text.toString()
            var dobtext: String = dob.text.toString()

           val checkinsertData : Boolean = Db.insertuser(nameText,contactText,dobtext);
            if(checkinsertData){
                Toast.makeText(this,"New Entry Inserted",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"New Entry Inserted",Toast.LENGTH_SHORT).show()
            }
        })

        /**
         * Update Button click listener
         */

        update.setOnClickListener(View.OnClickListener {
            val nameText: String = name.text.toString()
            val contactText: String = contact.text.toString()
            val dobtext: String = dob.text.toString()

            val checkUpdateData : Boolean = Db.updateUserData(nameText,contactText,dobtext);
            if(checkUpdateData){
                Toast.makeText(this," Entry Updated",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Entry not Updated",Toast.LENGTH_SHORT).show()
            }
        })
        /**
         * delete Button click listener
         */
        delete.setOnClickListener(View.OnClickListener {
            val nameText: String = name.text.toString()

            val deleteData : Boolean = Db.deleteData(nameText);
            if(deleteData){
                Toast.makeText(this," Entry Deleted",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Entry not Deleted",Toast.LENGTH_SHORT).show()
            }
        })

        /**
         * View onClick Listener
         */

        view.setOnClickListener(View.OnClickListener {
            var nameText: String = name.text.toString()

            val viewData : Cursor = Db.data;
            if(viewData.count==0){
                Toast.makeText(this," No Entery Exists ",Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
           val strBuffer = StringBuffer()

            while(viewData.moveToNext()){
                strBuffer.append("Name:"+viewData.getString(0)+"\n")
                strBuffer.append("Contact:"+viewData.getString(1)+"\n")
                strBuffer.append("Dob:"+viewData.getString(2)+"\n \n")
            }
           val builder: AlertDialog.Builder = AlertDialog.Builder(this);
            builder.setCancelable(true)
            builder.setTitle("User Enteries")
            builder.setMessage(strBuffer.toString())
            builder.show()
        })

    }
}