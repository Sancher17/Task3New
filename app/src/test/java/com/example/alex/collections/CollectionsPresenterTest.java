package com.example.alex.collections;

import com.example.alex.dataCollections.executor.ExecutorCollection;
import com.example.alex.dataCollections.executor.ExecutorCollectionCallback;
import com.example.alex.utils.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest {

    private CollectionsContract.View view;

    @Mock
    private CollectionsAdapter adapter;

    @Mock
    Logger logger;


    @Mock
    private ExecutorCollection executor;

    private CollectionsPresenter presenter;

    ExecutorCollectionCallback callback;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        view = new CollectionsFragment();
        executor = new ExecutorCollection(callback);
        presenter = new CollectionsPresenter();
        adapter = new CollectionsAdapter();
        logger = new Logger(CollectionsPresenterTest.class);

    }

    @Test
    public void responseShowProgress() {
        presenter.responseShowProgress(0);
        verify(view).showProgressBar(0);

    }

}