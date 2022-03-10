package com.example.androidtoarduinobluetooth

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
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityControlBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        onBtListResult()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.control_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.id_btList){
            actListLauncher.launch(Intent(this, BtListActivity::class.java))
        }
        else if(item.itemId == R.id.id_connect){}
        return super.onOptionsItemSelected(item)
    }

    private fun onBtListResult(){

        actListLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == RESULT_OK){
                Log.d("MyLog", "Name: ${(it.data?.getSerializableExtra(BtListActivity.DEVICE_KEY) as ListItem).name}")
            }
        }

    }
}