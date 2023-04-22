package com.ralvez.myapplicationlayout06

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



import com.ralvez.myapplicationlayout06.databinding.ActivityMainBinding
var price = 0

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var myData = MyDataTest.myDataList
    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        MyDataTest.test1()
        val myAdapter = MyAdapter(myData)

        val recyclerView: RecyclerView = binding.rvBooklist
        recyclerView.adapter = myAdapter
        binding.rvBooklist.layoutManager = LinearLayoutManager(this)




        //myAdapter.notifyDataSetChanged()


        binding.btnAddlist.setOnClickListener {
            showAddItemDialog()
            // binding.textView2.text= mydata.toString()

        }



        val swipeToDelete = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                myData.removeAt(position)
                recyclerView.adapter?.notifyItemRemoved(position)
            }

        }
        val itemTouchHelper = ItemTouchHelper(swipeToDelete)
        itemTouchHelper.attachToRecyclerView(recyclerView)

    }

    private fun showAddItemDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add Item")

        val view = LayoutInflater.from(this).inflate(R.layout.add_item_dialog, null)
        builder.setView(view)

        val itemNameEditText = view.findViewById<EditText>(R.id.item_name_edit_text)
        val itemDescriptionEditText = view.findViewById<EditText>(R.id.item_description_edit_text)

        builder.setPositiveButton("Add") { _, _ ->
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

                // Add the new item to the list
                MyDataTest.myDataList.add(Item(itemName, price))

                // Notify the adapter that the data set has changed
                binding.rvBooklist.layoutManager = LinearLayoutManager(this)
            }
        }

        builder.setNegativeButton("Cancel", null)

        val dialog = builder.create()
        dialog.show()
    }




}