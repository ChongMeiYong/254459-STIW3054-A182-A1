import java.util.Scanner;
import java.util.InputMismatchException;
import java.lang.Exception;

class SwEstimation{
	
	Scanner scan = new Scanner(System.in);
	int simple,average,complex;
	int ttlAP=0,ttlUC=0,ER=0,ttlRMHours=0;
	double ttlTF=0,TCF=0,SzUC=0,ttlEF=0,EF=0,UCP=0,MHours=0,ttlMHours=0,adjustMHours=0;
	static int y=0;
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		SwEstimation t1 = new SwEstimation();
		
		t1.actor();
		t1.usecase();
		t1.tfactor();
		t1.ef();
		t1.manHours();
		t1.adjust();
		t1.report();
	}
	
	//ACTOR POINT
	public void actor() throws Exception{	
		int actor[] =new int[3];
		String type [] = {"Simple","Average","Complex"};
		String desc [] = {"API","IP/TCP","GUI"};
		int factor [] = {1,2,3};
		
		System.out.println("**Weighting Actor**");
		System.out.println("----------------------------------------------");
		System.out.println("Actor Type \tDescription \tFactor  Qty");
		System.out.println("----------------------------------------------");
		
		for(int i=0;i<actor.length;i++) {
			do {
				try {
					System.out.print(type[i] +"\t\t" +desc[i] +"\t\t" +factor[i] +"\t" );
					int qty = scan.nextInt();
					ttlAP = ttlAP +(qty*factor[i]);
					y=1;
				}catch(InputMismatchException e) {
					System.out.println("Wrong Input Please Enter Again!");
					scan.next();
					y=2;
				}
			}while(y==2);
		}	
		System.out.println("\nTotal Actor Point = "+ttlAP);	
	}
	
	//USE CASE 
	public void usecase() throws Exception {
		int usecase[] =new int[3];
		String type [] = {"Simple","Average","Complex"};
		String desc [] = {"<3","4-7",">7"};
		int factor [] = {5,10,15};
		
		System.out.println("\n");
		System.out.println("**Weighting Use Case**");
		System.out.println("------------------------------------------------");
		System.out.println("Use Case Type \tDescription \tFactor \tQty");
		System.out.println("------------------------------------------------");
		
		for(int i=0;i<usecase.length;i++) {
			do {
				try {
					System.out.print(type[i] +"\t\t" +desc[i] +"\t\t" +factor[i] +"\t" );
					int qty = scan.nextInt();
					ttlUC = ttlUC +(qty*factor[i]);
					y=1;
				}catch(InputMismatchException e) {
					System.out.println("Wrong Input Please Enter Again!");
					scan.next();
					y=2;
				}
			}while(y==2);
		}	
		System.out.println("\nUse Case Point = "+(ttlUC+ttlAP));
	}
	
	//T Factor, Technical Complexity Factor, Size of Use Case
	public void tfactor() throws Exception {
		int x=0;
		int TFactor[]=new int[13];
		double rating[] = new double[13];
		String desc[]= {"\t\tHave distributed solution \t\t\t","\t\tRespond specific performance\t\t\t",
						"\t\tMeet end-user efficiency\t\t\t","\t\tComplex internal processing\t\t\t",
						"\t\tCode must be reusable\t\t\t\t","\t\tMust be easy to install\t\t\t\t",
						"\t\tMust be easy to use\t\t\t\t","\t\tMust be portable\t\t\t\t",
						"\t\tMust be easy to change\t\t\t\t","\t\tMust allow concurrent users\t\t\t",
						"\t\tIncludes special security features\t\t","\t\tProvide direct access for 3rd parties\t\t"
						,"\t\tRequires special user training facilities\t"};
		double factor[]= {2,1,1,1,1,0.5,0.5,2,1,1,1,1,1};
		
		System.out.println("\n");
		System.out.println("**Weighting Technical Factor**");
		System.out.println("-------------------------------------------------------------------------------");
		System.out.println("Technical Factor  Description \t\t\t\t\tFactor  Rating");
		System.out.println("-------------------------------------------------------------------------------");
		
		for(int i=0;i<=TFactor.length;i++) {
			do {
				try {
					System.out.print("T"+(i+1) +desc[i] +factor[i]  +"\t ");
					rating[i] = scan.nextDouble();
					if((rating[i]<0) || (rating[i]>5)) {
						x=1;
						throw new Exception("Wrong Input");
					}
					ttlTF = ttlTF+(rating[i]*factor[i]);
					x=0;
					y=0;
				}catch(InputMismatchException e) {
					System.out.println("Wrong Input Please Enter Again!");
					scan.next();
					y=2;
				}catch(Exception e) {
					System.out.println("...");
				}	
			}while(x==1||y==2);
		}	
		System.out.printf("\nTotal T Factor = %.0f\n",ttlTF);
		TCF = (0.01*ttlTF)+0.6;
		System.out.printf("Technical Complexity Factor = %.2f\n",TCF);
		SzUC = (ttlAP+ttlUC)*TCF;
		System.out.printf("Size of Use Case = %.2f\n",SzUC);
	}
	
	//E Factor, Experience Factor, Use Case Point
	public void ef() throws Exception {
		int x = 0;
		int EFactor[]=new int[8];
		double rating[] = new double[8];
		String des[]= {"\t\tFamiliar with FPT software process","\t\tApplication Experience\t\t",
					   "\t\tParadigm Experience\t\t","\t\tLead analyst capability\t\t",
					   "\t\tMotivation\t\t\t","\t\tStable Requirement\t\t","\t\tPart-time worker\t\t",
					   "\t\tDifficult of programming language"};
		double factor[]= {1,0.5,1,0.5,0,2,-1,-1};
		
		System.out.println("\n");
		System.out.println("**Weighting Experience Factors**");
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Experience Factor  Description \t\t\t\tFactor  Rating");
		System.out.println("-----------------------------------------------------------------------");
		
		for(int i=0;i<=EFactor.length;i++) {
			do {
				try {
					System.out.print("E"+(i+1) +des[i] +"\t"+factor[i] +"\t");
					rating[i] = scan.nextDouble();
					if((rating[i]<0) || (rating[i]>5)) {
						x=1;
						throw new Exception("Wrong Input");
					}
					ttlEF = ttlEF + (rating[i]*factor[i]);
					x=0;
					y=0;
				}catch(InputMismatchException e) {
					System.out.println("Wrong Input Please Enter Again!");
					scan.next();
					y=2;
				}catch(Exception e) {
					System.out.println("...");
				}
			}while(x==1||y==2);
		}
		System.out.printf("\nTotal E Factor = %.0f\n",ttlEF);
		EF = (-0.03*ttlEF)+1.4;
		System.out.printf("Experience Factor = %.2f\n",EF);
		UCP= SzUC*EF;
		System.out.printf("Use Case Point = %.3f\n\n",UCP);
	}
	
	//Man Hours FROM UCP
	public void manHours() {
		System.out.print("Number of Factor Rating of E1-E6 below 3 : ");
		int num = scan.nextInt();
		System.out.print("Number of Factor Rating of E7-E8 above 3 : ");
		int num1 = scan.nextInt();
		int sum = num + num1;
		
		if(sum>0 && sum<=2) { 
			ER = 20;
		}
		else if(sum>2 && sum<=4) {
			ER = 28;
		}
		else if(sum>=5) {
			System.out.println("Please restructing the project team to fall the number at least 5.");
		}
		else
			ER=0;
		
		MHours = ER * UCP;
		System.out.printf("Total Man Hours ： %.3f\n\n",MHours);
	}
	
	//Adjusted Man-Hours
	public void adjust() {
		System.out.print("Risk Coefficients(%) ： ");
		double percent = scan.nextDouble();
		adjustMHours = (1+(percent/100))*MHours;
		System.out.printf("Adjusted Man-Hours ： %.2f\n\n",adjustMHours);
	}
	
	//Man-Hours
	public void report() {	
		int report[] =new int[3];
		String type [] = {"Simple","Average","Complex"};
		int average [] = {12,20,40};
		System.out.println("\n**Weighting Report**");
		System.out.println("------------------------------------------------");
		System.out.println("Report Type \t Average Man-Hours \t Qty");
		System.out.println("------------------------------------------------");
		do {
			try {
				for(int i=0;i<report.length;i++) {
					System.out.print(type[i] +"\t\t" +average[i] +"\t\t\t" );
					int qty = scan.nextInt();
					ttlRMHours = ttlRMHours +(qty*average[i]);
				}
			}catch(InputMismatchException e) {
				System.out.println("Wrong Input Please Enter Again!");
				scan.next();
				y=2;
			}
		}while(y==2);
		System.out.println("\nTotal Report Man-Hours = "+ttlRMHours);
		ttlMHours = ttlRMHours + adjustMHours;
		System.out.printf("Total Man Hours ： %.2f\n",ttlMHours);
	}
}
