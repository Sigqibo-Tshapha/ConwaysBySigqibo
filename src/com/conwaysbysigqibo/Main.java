package com.conwaysbysigqibo;

import com.conwaysbysigqibo.controller.Parameters;
import com.conwaysbysigqibo.model.GameState;
import com.conwaysbysigqibo.view.GameUI;

public class Main{

    public static void main(String[] args) {
        Parameters parameters = new Parameters();
        GameState game = new GameState(parameters.ROWS, parameters.COLUMNS, parameters.FIRSTGENERATIONCELLS);
        GameUI gameUI = new GameUI(game, parameters.CELLSIZE);
    }
}