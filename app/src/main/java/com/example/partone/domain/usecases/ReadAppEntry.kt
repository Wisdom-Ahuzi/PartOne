package com.example.partone.domain.usecases

import com.example.partone.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry (
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke():Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}