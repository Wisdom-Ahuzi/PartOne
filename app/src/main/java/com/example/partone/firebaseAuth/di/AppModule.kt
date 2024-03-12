//package com.example.parttwo.FirebaseAuth.di
//
//import com.example.parttwo.FirebaseAuth.data.AuthRepository
//import com.example.parttwo.FirebaseAuth.data.AuthRepositoryImpl
//import com.google.firebase.auth.FirebaseAuth
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Inject
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule  {
//
//    @Provides
//    @Singleton
//    fun providesFirebaseAuth( ) = FirebaseAuth.getInstance()
//
//    @Provides
//    @Singleton
//    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth) : AuthRepository{
//        return AuthRepositoryImpl(firebaseAuth)
//    }
//}