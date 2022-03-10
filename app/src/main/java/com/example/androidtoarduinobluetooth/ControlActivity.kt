package com.example.androidtoarduinobluetooth

import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidtoarduinobluetooth.databinding.ActivityControlBinding


class ControlActivity : AppCompatActivity() {
    private lateinit var binding: ActivityControlBinding
    private lateinit var actListLauncher: ActivityResultLauncher<Intent>
    lateinit var btConnection: BtConnection
    private var listItem: ListItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityControlBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onBtListResult()
        init()
        binding.apply{
            bA.setOnClickListener{
                btConnection.sendMessage("A")
            }
            bB.setOnClickListener{
                btConnection.sendMessage("B")
            }
        }
    }

    private fun init(){
        val btManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        val btAdapter = btManager.adapter
        btConnection = BtConnection(btAdapter)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.control_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.id_btList){
            actListLauncher.launch(Intent(this, BtListActivity::class.java))
        }
        else if(item.itemId == R.id.id_connect){
            listItem.let{
                btConnection.connect(it?.mac!!)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onBtListResult(){

        actListLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                listItem = it.data?.getSerializableExtra(BtListActivity.DEVICE_KEY) as ListItem
            }
        }

    }
}