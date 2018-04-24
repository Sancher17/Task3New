package com.example.alex.dagger;

import com.example.alex.collections.dataCollections.CollectionsProcessor;
import com.example.alex.collections.dataCollections.ICollectionsProcessor;

import dagger.Module;
import dagger.Provides;

@Module
public class CollectionExecutorModule {


    @Provides
    ICollectionsProcessor processor() {
        return new CollectionsProcessor();
    }
}
