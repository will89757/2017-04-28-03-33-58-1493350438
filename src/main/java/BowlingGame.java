import java.util.Scanner;

class lattice {
	public int kind,second,score;
	public int first;
	
	public lattice(){
		kind=0;
		second=0;
		score=0;
		first=0;
	}
}
public class BowlingGame {
	private final static  int strike=1;
	private final  static int spare=2;
	private final  static int other=3;
	private final  static int extra=4;
    public int getBowlingScore(String bowlingCode) {
	lattice[] a=new lattice[21];
	for(int i=0;i<21;i++)
		a[i]= new lattice();
	String c=bowlingCode;
	int i=0,j=0;
	int l=c.length();
	while(i < l)
	{
		if(c.charAt(i)=='X')
		{ 	
			a[j].kind=strike;
			a[j].first=10;
			a[j].second=0;
			a[j].score=10;
			i=i+2;
		}
		else 
		{
			if(c.charAt(i)=='-')
				a[j].first=0;
			else
			{
				a[j].first=c.charAt(i)-48;
			}
			if(c.charAt(i+1)=='/')
		    {
    	 	    a[j].kind=spare;
				a[j].score=10;
				a[j].second=a[j].score-a[j].first;	
			}
			else {
					 a[j].kind=other;
					 if(c.charAt(i+1)=='-')
				    	a[j].second=0;
				  	 else
					    a[j].second=c.charAt(i+1)-48;
					 a[j].score=a[j].first+a[j].second; 
			     }
			i=i+3;
		}	
		
		j++;
		
		if(c.charAt(i)=='|')
		{
			i++;
			while(i<l)
			{
				a[j].kind=extra;
				if(c.charAt(i)=='X')	
					a[j].score=10;
				else if(c.charAt(i)=='-')
						a[j].score=0;
					 else if(c.charAt(i)=='/')
					 		 a[j].score=10-a[j-1].score;
					 	  else
						     a[j].score=c.charAt(i)-48;	
				i++;
				j++;
			}		
		} 
	}
		
	int total=0;
	
	for(i=0;i<10;i++)
	{
		switch(a[i].kind)
		{
			case strike:
				total=total+10;
				switch(a[i+1].kind)
				{
					case strike:
						total=total+10;
						switch(a[i+2].kind)
						{
							case strike:
								total=total+10;	
								break;
							case extra:
								total=total+a[i+2].score;
								break;
							default:
								total=total+a[i+2].first;
						}
						break;
					case extra:
						total=total+a[i+1].score+a[i+2].score;
						break;
					default:
						total=total+a[i+1].score;
				}
				break;
			case spare:
				total=total+10;
				switch(a[i+1].kind)
				{
					case strike:
						total=total+10;	
						break;
					case extra:
						total=total+a[i+1].score;
						break;
					default:
						total=total+a[i+1].first;
				}
				break;
			default:
				total=total+a[i].score;
		}
	}
        return total;
    }
}
