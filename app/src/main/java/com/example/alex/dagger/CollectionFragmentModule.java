package com.example.alex.dagger;

import com.example.alex.collections.CollectionsAdapter;
import com.example.alex.collections.CollectionsContract;
import com.example.alex.collections.CollectionsPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CollectionFragmentModule {

    @Provides
    CollectionsContract.Presenter provideCollectionsPresenter(){
        return new CollectionsPresenter();
    }

    @Provides
    CollectionsAdapter provideCollectionsAdapter(){
        return new CollectionsAdapter();
    }
}
