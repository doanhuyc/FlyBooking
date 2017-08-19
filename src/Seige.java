public class Seige{
	int position;
	int range;
	boolean isReserved;
	
	public Seige(int range,int position){
		if(range<1 || range>20 || position <1 || position >5)
	         throw new IllegalArgumentException("badly formed Seige");
		this.range=range;
		this.position=position;
	}
	
	
	public String toString(){
        String a="";
        String positionS="";
		switch(position){
			case 1: positionS="A"; break;
			case 2: positionS="B"; break;
			case 3: positionS="C"; break;
			case 4: positionS="D"; break;
			case 5: positionS="E"; break;
			}
		a= Integer.toString(range)+positionS;
		return a;
    }
	
	public void setReserved(boolean what){
		isReserved=what;
	}
}
	
	