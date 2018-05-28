package com.example.alex.collections;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

/**
 * created on 24.05.2018
 */
public class CollectionsFragmentTest {

    @Mock
    CollectionsContract.Presenter presenter;


    private CollectionsFragment fragment;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        fragment = new CollectionsFragment();
    }

    @Test
    public void clickCalculation() throws Exception {
        fragment.clickCalculation();

    }

    @Test
    public void clickButtonStop() throws Exception {
    }

    @Test
    public void showCalculationStopped() throws Exception {
    }

    @Test
    public void hideAllProgressBars() throws Exception {
    }

    @Test
    public void setAllResultZero() throws Exception {
    }

    @Test
    public void showWait() throws Exception {
    }

}