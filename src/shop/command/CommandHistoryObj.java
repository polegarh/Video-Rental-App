package shop.command;
import java.util.Stack;

final class CommandHistoryObj implements CommandHistory {
  Stack<UndoableCommand> _undoStack = new Stack<UndoableCommand>();
  Stack<UndoableCommand> _redoStack = new Stack<UndoableCommand>();
  
  RerunnableCommand _undoCmd = new RerunnableCommand () {
	  public boolean run () {
		  boolean result = !_undoStack.empty();
		  if (result) {
//			  UndoableCommand x = _undoStack.pop();
//			  x.undo();
//			  _redoStack.push(x);
			  _redoStack.push(_undoStack.pop()).undo();
		  }
		  return result;
	  }
  };
  
  RerunnableCommand _redoCmd = new RerunnableCommand () {
	  public boolean run () {
		  boolean result = !_redoStack.empty();
		  if (result) {
			  UndoableCommand x = _redoStack.pop();
			  x.redo();
			  _undoStack.push(x);
		  }
		  return result;
      }
  };

  public void add(UndoableCommand cmd) {
	  _undoStack.push(cmd);
	  _redoStack.clear();
  }
  
  public RerunnableCommand getUndo() {
    return _undoCmd;
  }
  
  public RerunnableCommand getRedo() {
    return _redoCmd;
  }
  
  // For testing
  UndoableCommand topUndoCommand() {
    if (_undoStack.empty())
      return null;
    else
      return _undoStack.peek();
  }
  // For testing
  UndoableCommand topRedoCommand() {
    if (_redoStack.empty())
      return null;
    else
      return _redoStack.peek();
  }
}
