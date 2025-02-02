package com.conwaysbysigqibo.model;
import java.awt.*;

public interface ConwaysGameOfLife{
    /*
    For my application I will not be using this interface as it tightly couples the individual points/cells with the
    compute logic and the GUI, which is 3 different layers. While this can be made to work (if you REALLY want it),
    I believe that it would make the application slow, finicky and unnecessarily bloated.
    I am open to suggestions that prove me otherwise.
     */
    boolean liveCellWithFewerThanTwoLiveNeighboursDies(Point point);
    boolean liveCellWithTwoOrThreeLiveNeighboursLives(Point point);
    boolean liveCellWithMoreThanThreeLiveNeighboursDies(Point point);
    boolean deadCellWithExactlyThreeLiveNeighboursBecomesAlive(Point point);
}
