package com.example.argos

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.argos.databinding.ActivityMainBinding
import redroid.log.argos.Argos

class MainActivity : AppCompatActivity(), View.OnClickListener {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		val binding = ActivityMainBinding.inflate(layoutInflater)
		enableEdgeToEdge()
		setContentView(binding.root)
		ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}

		binding.hello.setOnClickListener(this)
		binding.hey.setOnClickListener(this)
		binding.hi.setOnClickListener(this)
	}

	override fun onClick(v: View?) {
		val button = v as Button
//		Timber.v("A button with ID %s was clicked to say '%s'.", button.id, button.text)
		Argos.v("A button with ID %s was clicked to say '%s'.", button.id, button.text)
		Argos.e(RuntimeException("a throwable"), "A button with ID %s was clicked to say '%s'.", button.id, button.text)
		Toast.makeText(this, "Check logcat for a greeting!", Toast.LENGTH_SHORT).show()
	}
}