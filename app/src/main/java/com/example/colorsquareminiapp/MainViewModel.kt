package com.example.colorsquareminiapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.colorsquareminiapp.models.Squares
import kotlin.random.Random

class MainViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _seed = savedStateHandle.getLiveData<Long>(KEY_SEED)
    val squares: LiveData<Squares> = _seed.map { createSquares(it) }

    init {
        if (!savedStateHandle.contains(KEY_SEED))
            savedStateHandle[KEY_SEED] = Random.nextLong()
    }

    fun generateSquares() {
        _seed.value = Random.nextLong()
    }

    private fun createSquares(seed: Long): Squares {
        val random = Random(seed)
        return Squares(
            size = random.nextInt(5, 11),
            colorProducer = { -random.nextInt(0xFFFFFF) }
        )
    }

    companion object {
        const val KEY_SEED = "seed"
    }
}