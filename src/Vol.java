public class Vol implements Comparable<Vol>{



    String Departure, Destination;
    Date date;
    String Key;
    Seige[][] lesSeiges;


    Vol(String Departure, String Destination,Date date){
        if (Departure==null|| Destination==null) throw new NullPointerException();
        this.Departure = Departure;
        this.Destination = Destination;
        this.date=date;
        Key=Departure+Destination+date.toString();
        lesSeiges=new Seige[20][5];
        for(int i=0;i<20;i++){
        	for(int j=0;j<5;j++){
        		lesSeiges[i][j]=new Seige(i+1,j+1);
        	}
        }
    }
    
    public String toString(){
        return Key;
    }
    

    public boolean equals( Vol that ) {
        if(that==this) return true;
        if(!(that instanceof Vol)) return false;
        return Key.equals(that.Key);
    }
    
    public int hashCode(){
        return Key.hashCode();
    }

    public int compareTo(Vol that){
        if(that==this) return 0;
        if(that==null)
            throw new NullPointerException(this+" comparé avec null");
         return Key.compareTo(that.Key);
    }


    public String getDeparture(){
        return Departure;
    }
    
    public String getDestination(){
        return Destination;
    }
    public Date getDate(){
    	return date;
    }

}        