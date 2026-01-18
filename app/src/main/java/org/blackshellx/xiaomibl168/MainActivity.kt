package org.blackshellx.xiaomibl168

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        //改时间 改这里
        private val year=2026
        private val month=Calendar.JANUARY
        private val day=19
        private val hour=10
        private val minute=33
        // 目标时间
        private val TARGET_TIME = GregorianCalendar(year, month, day, hour, minute).timeInMillis
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 添加按钮来查看剩余时间
        val checkTimeButton = findViewById<Button>(R.id.btnCheckTime)
        checkTimeButton.setOnClickListener {
            val currentTime = System.currentTimeMillis()
            val remainingHours = if (currentTime < TARGET_TIME) {
                (TARGET_TIME - currentTime) / (1000 * 60 * 60)
            } else {
                -(currentTime - TARGET_TIME) / (1000 * 60 * 60) // 负数表示已超过目标时间
            }
            
            val timeMessage = if (currentTime < TARGET_TIME) {
                "还有 ${remainingHours} 小时"
            } else {
                "已超过 ${-remainingHours} 小时"
            }
            
            Toast.makeText(this, timeMessage, Toast.LENGTH_LONG).show()
        }
    }
}