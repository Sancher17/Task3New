package com.example.alex.dagger;


import com.example.alex.collections.CollectionsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * created on 02.04.2018
 */
@Component(modules = {
        CollectionFragmentModule.class,
})
@Singleton
public interface AppComponent {

    void inject(CollectionsFragment collectionsFragment);
}

