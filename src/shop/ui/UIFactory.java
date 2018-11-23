package shop.ui;

public class UIFactory {
  private UIFactory() {}
  static private UI _UI = new PopupUI();
  static private UI textUI = new TextUI();
 
  static public UI ui () {
    return _UI;
  }
  static public UI textUi () {
	return textUI; 
  }
}
