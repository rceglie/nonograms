package com.comp301.a09nonograms.model;
import com.comp301.a09nonograms.view.*;

public class ModelObserverImpl implements ModelObserver {
  public void update(Model model) {
    model.isSolved();
  }
}
