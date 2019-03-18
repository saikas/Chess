package edu.kingsu.SoftwareEngineering.Chess;

abstract interface MoveListener {

  public abstract void getInput(Move paramMove);
  
  public abstract void getUndo();
  
  public abstract void getRedo();
  
  public abstract void getUndoAll();
  
  public abstract void getRedoAll();
  
  public abstract void getHint();
  
  public abstract void save();
  
  public abstract void load();
}
