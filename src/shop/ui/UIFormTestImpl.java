package shop.ui;

import shop.main.MenuEnum;

public class UIFormTestImpl {
	static public boolean run (String input, MenuEnum flag){
		switch (flag) {
		case yearTest: 							return _yearTest  (input);
		case Number_of_copies_to_add_remove:	return _numberTest(input);
		case title: 							return _stringTest(input);
		case year: 								return _yearTest  (input);
		case director: 							return _stringTest(input);
		case numberTest: 						return _numberTest(input);
		case stringTest: 						return _stringTest(input);
		default:
			break;
		}
		return false;
	}
	
	static public boolean _yearTest (String input){
		try {
			int i = Integer.parseInt(input);
			return i > 1800 && i < 5000;
		} catch (NumberFormatException e){
			return false;
		}
	}
	static private boolean _numberTest (String input){
		try {
			Integer.parseInt(input);
			return true;
		} catch (NumberFormatException e){
			return false;
		}
	}
	static private boolean _stringTest (String input){
		return ! "".equals(input.trim());
	}
}
