package com.alvayonara.outsched.ui.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.alvayonara.outsched.R
import com.alvayonara.outsched.utils.ToolbarConfig
import kotlinx.android.synthetic.main.activity_exercise_location.*
import kotlinx.android.synthetic.main.activity_exercise_location.toolbar
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        initToolbar()
        initView()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Settings"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        ToolbarConfig.setSystemBarColor(this, R.color.colorGreen)
    }

    private fun initView() {
        lyt_about_apps_settings.setOnClickListener {
            val intent = Intent(this, SettingsAboutActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}