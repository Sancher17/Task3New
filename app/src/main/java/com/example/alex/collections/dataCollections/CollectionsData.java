package com.example.alex.collections.dataCollections;



import com.example.alex.utils.Logger;

import javax.inject.Inject;

public class CollectionsData {

    private static Logger LOGGER = new Logger(CollectionsData.class);

    private String name;
    private String action;
    private int resultOfCalculation = 0;
    private boolean progressBar = false;

    @Inject
    public CollectionsData() {
    }

    public CollectionsData(String name, String action, int resultOfCalculation, boolean progressBar) {
        this.name = name;
        this.action = action;
        this.progressBar = progressBar;
        this.resultOfCalculation = resultOfCalculation;
    }

    //getters - setters
    public String getAction() {
        return action;
    }

    public String getName() {
        return name;
    }

    public boolean getProgressBar() {
        return progressBar;
    }
    public void setProgressBar(boolean progressBar) {
        this.progressBar = progressBar;
    }

    public int getResultOfCalculation() {
        return resultOfCalculation;
    }
    public void setResultOfCalculation(int resultOfCalculation) {
        this.resultOfCalculation = resultOfCalculation;
    }

}
