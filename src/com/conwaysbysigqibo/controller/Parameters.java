package com.conwaysbysigqibo.controller;

import com.conwaysbysigqibo.model.Cell;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
public class Parameters {
    public int COLUMNS;
    public int ROWS;
    public int CELLSIZE;
    public ArrayList<Cell> FIRSTGENERATIONCELLS;

    public Parameters() {
        loadParameters();
    }
    private void loadParameters() {
        Properties parameters = new Properties();
        // TODO: implement better way of ingesting resources?
        String propertiesFile = System.getProperty("user.dir") + "\\config.properties";
        try {
            parameters.load(new InputStreamReader(new FileInputStream(propertiesFile), "UTF-8"));
            COLUMNS = Integer.valueOf(parameters.getProperty("Columns", "0"));
            ROWS = Integer.valueOf(parameters.getProperty("Rows", "0"));
            CELLSIZE = Integer.valueOf(parameters.getProperty("CellSize", "0"));
            FIRSTGENERATIONCELLS = loadStartState(parameters.getProperty("StartState", ""));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Cell> loadStartState(String firstGenerationLiveCells) {
        List<String> firstGenCells = Arrays.asList(firstGenerationLiveCells.split(","));
        ArrayList<Cell> cells = new ArrayList<>();
        firstGenCells.stream().forEach(cell -> {
            String[] coordinates = cell.split("_");
            cells.add(new Cell(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
        });
        return cells;
    }
}
