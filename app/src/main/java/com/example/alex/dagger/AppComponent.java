package com.example.alex.dagger;

import com.example.alex.collections.CollectionsFragment;
import com.example.alex.collections.dataCollections.executor.ExecutorCollection;
import com.example.alex.maps.MapsFragment;
import com.example.alex.maps.dataMaps.executor.ExecutorMaps;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        CollectionFragmentModule.class,
        CollectionExecutorModule.class,

        MapsFragmentModule.class,
        MapsExecutorModule.class

})
@Singleton
public interface AppComponent {

    void inject(CollectionsFragment collectionsFragment);
    void inject(MapsFragment mapsFragment);

    void inject(ExecutorCollection executorCollection);
    void inject(ExecutorMaps executorMaps);
}

