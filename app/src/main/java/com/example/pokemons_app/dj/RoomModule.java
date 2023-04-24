package com.example.pokemons_app.dj;


import android.app.Application;

import androidx.room.Room;

import com.example.pokemons_app.db.Dao;
import com.example.pokemons_app.db.RoomDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class RoomModule {
   @Provides
   @Singleton
    public static RoomDb provideRoomDb (Application application){
        return Room.databaseBuilder(application ,RoomDb.class ,"fav_pokemons")
                .fallbackToDestructiveMigrationOnDowngrade()
                .allowMainThreadQueries()
                .build();
    }
    @Provides
    @Singleton
    public static Dao providDao (RoomDb roomDb){
       return roomDb.pokemonDao();
    }

}
