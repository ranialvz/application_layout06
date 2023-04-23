package com.ralvez.myapplicationlayout06

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



import com.ralvez.myapplicationlayout06.databinding.ActivityMainBinding
var price = 0

class MainActivity : AppCompatActivity() {   lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MyViewModel
    var myData = MyDataTest.myDataList

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        lifecycle.addObserver(MyObserver())






        viewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        val numberTextView: TextView = binding.textView2
        viewModel.number.observe(this, { MyDataTest.myUpdate=it })
        //numberTextView.text = it.toString()
        viewModel.loadSwitch()
        var test2 = MyDataTest.myUpdate
        Toast.makeText(this, "$test2 ", Toast.LENGTH_SHORT).show()



        val myAdapter=  ItemAdapter(myData) { position ->
            MyDataTest.myItemSelected=position
            val intent = Intent(this, InventActivity::class.java)
            startActivity(intent)
        }

        val recyclerView: RecyclerView = binding.rvBooklist
        recyclerView.adapter = myAdapter
        binding.rvBooklist.layoutManager = LinearLayoutManager(this)


        if(test2==0){
            MyDataTest.test1()

            binding.rvBooklist.layoutManager = LinearLayoutManager(this)
        }

        //myAdapter.notifyDataSetChanged()


        binding.btnAddlist.setOnClickListener {
            showAddItemDialog()
            //binding.textView2.text= mydata.toString()

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

    override fun onResume() {
        super.onResume()
        binding.rvBooklist.layoutManager = LinearLayoutManager(this)
    }
}