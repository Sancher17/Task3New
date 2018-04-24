package com.example.alex.dagger;

import com.example.alex.maps.MapsAdapter;
import com.example.alex.maps.MapsContract;
import com.example.alex.maps.MapsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MapsFragmentModule {

    @Singleton
    @Provides
    MapsContract.Presenter presenter(){
        return new MapsPresenter();
    }

    @Singleton
    @Provides
    MapsAdapter mapsAdapter(){
        return new MapsAdapter();
    }
}
