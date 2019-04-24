package com.dicoding.picodiploma.aplikasisederhana.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.annotation.NonNull
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.dicoding.picodiploma.aplikasisederhana.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var wordAdapter: WordAdapter

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)


        mainViewModel = ViewModelProviders.of(this).get<MainViewModel>(MainViewModel::class.java!!)
        mainViewModel.word.observe(this, Observer { list ->
            wordAdapter = WordAdapter()

            recyclerView.adapter = wordAdapter

            wordAdapter.submitList(list)
        })

        //Memberikan aksi untuk swipe
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    // Digunakan untuk melakukan swipe kepada RecyclerView
    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder): Int {
            // Aksi di bawah digunakan untuk melakukan swap ke kenan dan ke kiri
            return makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(@NonNull recyclerView: RecyclerView, @NonNull viewHolder: RecyclerView.ViewHolder, @NonNull target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(@NonNull viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // Sebelum melakukan penghapusan, course harus mendapatkan posisi dari item yang di swipe
            val swipedPosition = viewHolder.getAdapterPosition()

            // Kemudian memanggil CourseEntity sesuai posisi ketika diswipe
            val word = wordAdapter.getItemById(swipedPosition)

            // Melakukan setBookmark untuk menghapus bookmark dari list course
            mainViewModel.delete(word)

            // Memanggil Snackbar untuk melakukan pengecekan, apakah benar melakukan penghapusan bookmark
            val snackbar = Snackbar.make(recyclerView, "Undo?", Snackbar.LENGTH_LONG)

            // Mengembalikan item yang terhapus
            snackbar.setAction("OK") { v -> mainViewModel.insert(word) }

            // Menampilkan snackbar
            snackbar.show()
        }
    })
}
