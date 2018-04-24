package com.example.alex.maps.dataMaps;



import com.example.alex.utils.Logger;

import javax.inject.Inject;

public class MapsData {

    private static Logger LOGGER = new Logger(MapsData.class);

    private String name;
    private String action;
    private int resultOfCalculation = 0;
    private boolean progressBar = false;

    @Inject
    public MapsData() {
    }

    public MapsData(String name, String action, int resultOfCalculation, boolean progressBar) {
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
