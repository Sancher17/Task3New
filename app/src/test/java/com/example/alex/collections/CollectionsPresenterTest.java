package com.example.alex.collections;

import com.example.alex.arch.LifecycleExecutor;
import com.example.alex.collections.dataCollections.executor.ExecutorCollectionCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static com.example.alex.constants.Constants.COUNT_OF_OPERATIONS_COLLECTIONS;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CollectionsPresenterTest {

    @Mock
    CollectionsContract.View view;

    @Mock
    LifecycleExecutor executor;

    CollectionsPresenter presenter;

    @Mock
    ExecutorCollectionCallback callback;

    @Before
    public void setupPresenter() {
        MockitoAnnotations.initMocks(this);
        presenter = new CollectionsPresenter();
        presenter.attachView(view);
//        executor = new ExecutorCollection(callback);
    }

    @Test
    public void attachDetachView() {
        assertNotNull(presenter.getView());
        presenter.detachView();
        assertNull(presenter.getView());
    }


    @Test
    public void responseShowProgress() {
        presenter.responseShowProgress(0);
        verify(view).showProgressBar(0);
    }

    @Test
    public void responseCalculationStopped() {
        presenter.responseCalculationStopped();
        verify(view).hideAllProgressBars();
        verify(view).updateAdapter();
        verify(view).showCalculationStopped();
    }

    @Test
    public void responseHideProgress() {
        presenter.responseHideProgress(0);
        verify(view).hideProgressBar(0);
    }

    @Test
    public void calculateCorrect() {
        presenter.calculate();
        verify(view).setAllResultZero();
        verify(view).updateAdapter();

    }

    @Test
    public void calculateWrong() {
        COUNT_OF_OPERATIONS_COLLECTIONS = 0;
        presenter.calculate();
        assertNotEquals(COUNT_OF_OPERATIONS_COLLECTIONS, 21);
        verify(view).showCalculationIsStillWorking();
    }

    @Test
    public void stopCalculation() {
        presenter.stopСalculation();
        // TODO: 13.05.2018 не сделано
//        verify(executor).stopCalculation();
//        verify(view).updateAdapter();
    }
}