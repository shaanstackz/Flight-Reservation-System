
public class Aircraft implements Comparable<Aircraft>
{
  int numEconomySeats;
  int numFirstClassSeats;
  
  String model;
  String[][] seatLayout;
  int seatTotal;
  int row;
  int column;
  
  public Aircraft(int seats, String model)
  {
  	this.numEconomySeats = seats;
  	this.numFirstClassSeats = 0;
  	this.model = model;
	this.seatLayout=new String[row][column];
	this.seatTotal=seats;
	this.column=seatTotal/4;
	this.row=seatTotal/column;
	setSeatLayout(seatTotal);
  }

  public Aircraft(int economy, int firstClass, String model)
  {
  	this.numEconomySeats = economy;
  	this.numFirstClassSeats = firstClass;
  	this.model = model;
	this.seatLayout=new String[row][column];
	this.seatTotal=firstClass+economy;
	this.column=seatTotal/4;
	this.row=seatTotal/column;
	setSeatLayout(seatTotal);
  }
  
	public int getNumSeats()
	{
		return numEconomySeats;
	}
	
	public int getTotalSeats()
	{
		return numEconomySeats + numFirstClassSeats;
	}
	
	public int getNumFirstClassSeats()
	{
		return numFirstClassSeats;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}
	
	public void print()
	{
		System.out.println("Model: " + model + "\t Economy Seats: " + numEconomySeats + "\t First Class Seats: " + numFirstClassSeats);
	}

	public void setSeatLayout(int seatTotal){
		if(numFirstClassSeats==0){
			seatLayout=new String[row][column];
			column=seatTotal/4;
			row=seatTotal/column;
			for(int x=0;x<row;x++){
				for(int i=0;i<column;i++){
					if(x==0){
						seatLayout[x][i]=(i+1) + "A";
					}
					else if(x==1){
						seatLayout[x][i]=(i+1) + "B";
					}
					else if(x==2){
						seatLayout[x][i]=(i+1) + "C";
					}
					else if(x==3){
						seatLayout[x][i]=(i+1) + "D";
					}
				}
			}
		}
		else{
			seatLayout=new String[row][column];
			for(int x=0;x<row;x++){
				for(int i=0;i<column;i++){
					if(x==0){
						if((i==0||i==1||i==2)){
							seatLayout[x][i]=(i+1) + "A+";
						}
						else{
							seatLayout[x][i]=(i+1) + "A";
						}
					}
					else if(x==1){
						if((i==0||i==1||i==2)){
							seatLayout[x][i]=(i+1) + "B+";
						}
						else{
							seatLayout[x][i]=(i+1) + "B";
						}
					}
					else if(x==2){
						if((i==0||i==1||i==2)){
							seatLayout[x][i]=(i+1) + "C+";
						}
						else{
							seatLayout[x][i]=(i+1) + "C";
						}
					}
					else if(x==3){
						if((i==0||i==1||i==2)){
							seatLayout[x][i]=(i+1) + "D+";
						}
						else{
							seatLayout[x][i]=(i+1) + "D";
						}
					}
				}
			}
		}
	}

	public boolean reserveSeatLayout(String seat){
		String sColumn="";
		String sRow="";
		for(int x=0;x<seat.length();x++){
			boolean isdigit=Character.isdigit(seat.charAt(x));
			if(isdigit){
				sColumn+=seat.charAt(x);
			}
			else{
				sRow+=seat.charAt(x);
			}
		}
		if(sRow.equalsIgnoreCase("A")||sRow.equalsIgnoreCase("A+")){
			if(seatLayout[0][Integer.parseInt(sColumn)-1]!="XX"){
				seatLayout[0][Integer.parseInt(sColumn)-1]="XX";
				return true;
			}
			return false;
		}
		else if(sRow.equalsIgnoreCase("B")||sRow.equalsIgnoreCase("B+")){
			if(seatLayout[1][Integer.parseInt(sColumn)-1]!="XX"){
				seatLayout[1][Integer.parseInt(sColumn)-1]="XX";
				return true;
			}
			return false;
		}
		else if(sRow.equalsIgnoreCase("C")||sRow.equalsIgnoreCase("C+")){
			if(seatLayout[2][Integer.parseInt(sColumn)-1]!="XX"){
				seatLayout[2][Integer.parseInt(sColumn)-1]="XX";
				return true;
			}
			return false;
		}
		else if(sRow.equalsIgnoreCase("D")||sRow.equalsIgnoreCase("D+")){
			if(seatLayout[3][Integer.parseInt(sColumn)-1]!="XX"){
				seatLayout[3][Integer.parseInt(sColumn)-1]="XX";
				return true;
			}
			return false;
		}
		return false;
	}

	public void cancelSeatLayout(String seat){
		String sColumn="";
		String sRow="";
		for(int x=0;x<seat.length();x++){
			boolean isdigit=Character.isdigit(seat.charAt(x));
			if(isdigit){
				sColumn+=seat.charAt(x);
			}
			else{
				sRow+=seat.charAt(x);
			}
		}
		if (sRow.equalsIgnoreCase("A")||sRow.equals("A+")){
			if(sRow.equalsIgnoreCase("A+")){
				seatLayout[0][Integer.parseInt(sColumn)-1]=sColumn+"A+";
			}
			else{
				seatLayout[0][Integer.parseInt(sColumn)-1]=sColumn+"A";
			}
		}
		else if (sRow.equalsIgnoreCase("B")||sRow.equals("B+")){
			if(sRow.equalsIgnoreCase("B+")){
				seatLayout[1][Integer.parseInt(sColumn)-1]=sColumn+"B+";
			}
			else{
				seatLayout[1][Integer.parseInt(sColumn)-1]=sColumn+"B";
			}
		}
		else if (sRow.equalsIgnoreCase("C")||sRow.equals("C+")){
			if(sRow.equalsIgnoreCase("C+")){
				seatLayout[2][Integer.parseInt(sColumn)-1]=sColumn+"C+";
			}
			else{
				seatLayout[2][Integer.parseInt(sColumn)-1]=sColumn+"C";
			}
		}
		else if (sRow.equalsIgnoreCase("D")||sRow.equals("D+")){
			if(sRow.equalsIgnoreCase("D+")){
				seatLayout[3][Integer.parseInt(sColumn)-1]=sColumn+"D+";
			}
			else{
				seatLayout[3][Integer.parseInt(sColumn)-1]=sColumn+"D";
			}
		}
	}

  public int compareTo(Aircraft other)
  {
  	if (this.numEconomySeats == other.numEconomySeats)
  		return this.numFirstClassSeats - other.numFirstClassSeats;
  	
  	return this.numEconomySeats - other.numEconomySeats; 
  }
}
