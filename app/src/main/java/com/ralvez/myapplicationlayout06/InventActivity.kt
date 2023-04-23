package com.ralvez.myapplicationlayout06

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.ralvez.myapplicationlayout06.databinding.ActivityInventBinding
import com.ralvez.myapplicationlayout06.databinding.ActivityMainBinding

class InventActivity : AppCompatActivity() {
    private lateinit var binding : ActivityInventBinding
    val myData = MyDataTest.myDataList
    val itemPosition = MyDataTest.myItemSelected
    val mySelected = myData[itemPosition]

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInventBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycle.addObserver(MyLifecycleObserver())
        Snackbar.make(binding.root, "${MyDataTest.myLife}", 5000).setAction("EVENT"){}.show()
        MyDataTest.myLife.clear()


        binding.textView2.text= "${itemPosition +1}"
        binding.textView4.text= mySelected?.name
        binding.textView5.text= mySelected?.price.toString()
        binding.btnInvent.setOnClickListener {
            showAddItemDialog()
        }


        val actionbar = supportActionBar
        actionbar!!.title = "ITEM INVENTORY"

        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    private fun showAddItemDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Edit Item")

        val view = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, null)
        builder.setView(view)
        val mySelected = myData[MyDataTest.myItemSelected]
        val itemNameEditText = view.findViewById<EditText>(R.id.item_name_edit_text)
        val itemDescriptionEditText = view.findViewById<EditText>(R.id.item_description_edit_text)

        itemNameEditText.hint = mySelected?.name
        itemDescriptionEditText.hint = mySelected?.price.toString()

        builder.setPositiveButton("Update") { _, _ ->
            val itemName = itemNameEditText.text.toString()
            val itemDescription = itemDescriptionEditText.text.toString()

            try {
                price = itemDescription.toInt()
            } catch (e: NumberFormatException) {
                val toast = Toast.makeText(this, "Pls input price number", Toast.LENGTH_SHORT)
                toast.setGravity(0, 0, 0)
                toast.show()
            }

            if (itemName.isNullOrEmpty() || itemDescription.isNullOrEmpty()){
                val toast = Toast.makeText(this, "Pls input item and price", Toast.LENGTH_SHORT)
                toast.setGravity(0, 0, 0)
                toast.show()


            }
            else if (price==0){
                val toast = Toast.makeText(this, "Pls input price number", Toast.LENGTH_SHORT)
                toast.setGravity(0, 0, 0)
                toast.show()
            }
            else {

                MyDataTest.myDataList[MyDataTest.myItemSelected] = Item(itemName, price)
                val mySelected = myData[MyDataTest.myItemSelected]
                binding.textView4.text= mySelected?.name
                binding.textView5.text= mySelected?.price.toString()



            }
        }

        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }





    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true


    }

    override fun onResume() {
        super.onResume()
        Snackbar.make(binding.root, "${MyDataTest.myLife}", 5000).setAction("EVENT"){}.show()
        MyDataTest.myLife.clear()
    }





}