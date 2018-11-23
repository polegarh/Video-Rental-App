package shop.main;

public enum StateEnum {
	EXITED(0),
	EXIT(1),
	START(2), 
	NOTHING(3),
	NUMSTATES(10);
	
	private int number;
	
	StateEnum(int i){
		this.number = i;
	}
	
	public int getNumber(){
		return number;
	}
}
