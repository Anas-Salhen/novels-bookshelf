package com.example.novelsbookshelf

import android.app.Application
import com.example.novelsbookshelf.repository.AppContainer
import com.example.novelsbookshelf.repository.AppContainerImpl

class NovelsApplication: Application() {
    lateinit var contaier: AppContainer
    override fun onCreate() {
        super.onCreate()
        contaier = AppContainerImpl
    }
}