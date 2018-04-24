package com.example.alex.dagger;

import com.example.alex.collections.dataCollections.CollectionsProcessor;
import com.example.alex.collections.dataCollections.ICollectionsProcessor;
import com.example.alex.maps.dataMaps.IMapsProcessor;
import com.example.alex.maps.dataMaps.MapsProcessor;

import dagger.Module;
import dagger.Provides;

@Module
public class MapsExecutorModule {

    @Provides
    IMapsProcessor processor() {
        return new MapsProcessor();
    }
}
