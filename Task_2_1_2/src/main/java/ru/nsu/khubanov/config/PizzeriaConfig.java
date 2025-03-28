package ru.nsu.khubanov.config;

import com.google.gson.Gson;


import java.io.FileNotFoundException;
import java.io.FileReader;


public class PizzeriaConfig {

    private int numBakers;
    private int numCouriers;
    private int storageCapacity;
    private int simulationTime;

    public PizzeriaConfig(int numBakers, int numCouriers, int storageCapacity, int simulationTime) {
        this.numBakers = numBakers;
        this.numCouriers = numCouriers;
        this.storageCapacity = storageCapacity;
        this.simulationTime = simulationTime;
    }

    public int getNumBakers() {
        return numBakers;
    }

    public int getNumCouriers() {
        return numCouriers;
    }

    public int getStorageCapacity() {
        return storageCapacity;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public static PizzeriaConfig loadConfig(String filePath) {
        FileReader reader = null;
        try {
            reader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Gson().fromJson(reader, PizzeriaConfig.class);
    }
}
