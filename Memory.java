import java.applet.Applet;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Scanner;
public class Memory   
{
	static int no_holes,M_size=256,total,low,high,no_proc;
	static int first_flag[]=new int[20];
	static int best_flag[]=new int[20];
	static int worst_flag[]= new int[20];
	static int page_flag[] = new int[20];
	static int Hole[]= new int[20]; 
	
	static int Pframe=M_size/32;                         //Pframe : NO. OF FRAMES----------- Page Size :32
	public static void main(String []args) 
	{
		int k=1;
		System.out.println("ENTER NUMBER OF HOLES:");
		Scanner in = new Scanner(System.in);
		no_holes = in.nextInt();                     //ENTERING NUMBER OF HOLES
	    if(no_holes==0)
	    {
	    	System.out.println("ERROR!!!REENTER NUMBER OF HOLES:");
	    	no_holes = in.nextInt();
	    }
		total=M_size;
	    low=(total/no_holes)/2;
	    high=(total/no_holes)*2;
	    
            System.out.println("low = "+low);
            System.out.println("high = "+high);
            
	    while(k==1)
	    { 
	    	for (int i = 0;i<(no_holes-1);i++) 
	       {
	         double db = Math.random()*(high-low)+low;  //RANDOMLY GENERATING HOLE SIZES-----low is  inclusive---high is exlusive
	         Hole[i]=(int) db;
	         total -= Hole[i];
	       }
	        Hole[no_holes-1]=total;
	        if(total>=low&&total<=high)
	        	k=0;
	        else
	        	total=M_size;                      //
	    }
	    
	    for(int i = 0;i<no_holes;i++)
		{
		   	 System.out.print("SIZE OF HOLE"+(i)+"-"); //DISPLAYING GENERATED HOLE SIZES
			 System.out.println(Hole[i]);			
		}
		  
        int maxsize = maxValue(Hole);
        int minsize = minValue(Hole);
	      
          System.out.println("Hole with Maximum size= "+maxsize);
          
          
	System.out.println("ENTER NUMBER OF PROCESSES");  //ENTER NUMBER OF PROCESSES
    	Scanner inc = new Scanner(System.in);          
	    no_proc = inc.nextInt();
  		Process[] P=new Process[no_proc];

	    for(int i=0;i<no_proc;i++)
	      {
	         System.out.println("PROCESS-"+(i+1));  	      
	         P[i]=new Process(minsize,maxsize);
	      }

	    System.out.println("No. of processes = "+no_proc);
	
		int ch;			//ASKING FOR THE KIND OF ALLOCATION
		do
		{
			System.out.println("*********************SELECT THE MEMORY METHOD*********************");
	    	  System.out.print("\n\t1.SEGMENTATION\n\t2.PAGING\n\t3.EXIT\n");
	    		Scanner chk= new Scanner(System.in);
	    		   ch=chk.nextInt(); 	  
	    	  switch (ch) {
			case 1:
			      int choice;
			      do {
			    	  System.out.println("SELECT THE ALLOCATION METHOD");
			    	  System.out.print("\n1.FIRST FIT\n2.BEST FIT\n3.WORST FIT\n4.EXIT\n");
			    		Scanner cho= new Scanner(System.in);
			    		   choice=cho.nextInt(); 	  
			    	  switch (choice) {
					case 1: //Arrays of Processes,Holes--,FirstFit
						firstfit(P,Hole,no_proc,no_holes,first_flag);
						break;
					case 2:
						bestfit(P, Hole, no_proc, no_holes, best_flag);
						break;
					case 3:
						worstfit(P, Hole, no_proc, no_holes, worst_flag);
						break;
					case 4:
						System.out.println("\nYOU HAVE EXITED THE PROGRAM\n");
						break;
					default:
						System.out.println("INVALID CHOICE\nPLEASE CHOOSE AGAIN\n");
						break;
					}  
				} while (choice!=4);
			
				break;
			case 2:
				paging(P,Pframe,no_proc,page_flag);
			      
				break;
			
			case 3:
				System.out.println("\nYOU HAVE EXITED THE PROGRAM\n");
				break;
			default:
				System.out.println("INVALID CHOICE\nPLEASE CHOOSE AGAIN\n");
				break;
			}  
		}while(ch!=3);  
		}
        
	static int maxValue(int[] Hole) {
		int max = Hole[0];
		for (int ktr = 0; ktr < Hole.length; ktr++) {
			if (Hole[ktr] > max) {                         //FINDING MAX VALUE OF HOLE ARRAY
				max = Hole[ktr];
			}
		}
		return max;
	}
	static int minValue(int[] Hole) {
		int min = Hole[0];
		for (int ktr = 0; ktr < Hole.length; ktr++) {
			if (Hole[ktr] < min) {                         //FINDING MIN VALUE OF HOLE ARRAY
				min = Hole[ktr];
			}
		}
		return min;
	}
	
	static void firstfit(Process P[],int H[],int nproc,int nholes,int flag[]){
		int i,j=0,frag=0;
                int count=0;
		for(i=0;i<nproc;i++)
		{   //ALLOCATING TEXT SECTIONS.
				while(j<nholes)
				{   
				       if(P[i].text<=H[j] && flag[j]==0)
					{
						frag+=H[j]-P[i].text;
						flag[j]=1;
						count++;
						System.out.println("FIRST FIT: PROCESS- "+(i+1)+" text ALLOCATED IN HOLE-"+ j);
						break;
					}
					else
					{
						j++;               // going to the next hole
					}
				 }	
		                j=0;
				while(j<nholes)
				{ 		//ALLOCATING DATA SECTION
					if(P[i].data<=H[j] && flag[j]==0)
				 	{
						frag+=H[j]-P[i].data;
						flag[j]=1;
						count++;
						System.out.println("FIRST FIT: PROCESS- "+(i+1)+"data ALLOCATED IN HOLE-"+ j);
						break;
				 	}
				    else
				    {
				     j++;
				    }
				}
				j=0;
				while(j<nholes)
				{	//ALLOCATING HEAP SECTION
					 if(P[i].heap<=H[j] && flag[j]==0)
					{
						frag+=H[j]-P[i].heap;
						flag[j]=1;
						count++;
						System.out.println("FIRST FIT: PROCESS- "+(i+1)+"heap ALLOCATED IN HOLE-"+ j);
						break;
					}
				     else{
				    	 j++; 
				     }					
				}
		     if(j==nholes){
		    	 while(i<nproc)
		    	 {
		    		 System.out.println("Process"+(i+1)+" cannot be allocated");
		    		 i++;
		    	 }
		    	 break;
		     }
		}
		
		System.out.println("The Resulting Fragmentation is : "+frag);		
	}
	
	
	static void bestfit(Process P[],int H[],int nproc,int nholes,int flag[]){
		int min=1000,loc=0,j,i,frag=0,temp, k=1,count=0;
		for(i=0;i<nproc;i++)
		{					//FINDING THE BEST FIT FOR THE TEXT SECTION OF PROCESS
		    for( j=0;j<nholes;j++)
		    {
		    	temp=H[j];
		    	if(flag[j]==0 && P[i].text<=temp)
		    	{
		    		if(min>=temp)
		    		{
		    			min=temp;
		    			loc=j;
		    		}
		    	}
		       // k=1;
		    }
		  	if(k==1 && count<nholes && flag[loc]==0)
		    {
		    
		  		flag[loc]=1;
		  		count++;
		  		frag=frag+(H[loc]-P[i].text);
		         	System.out.println("BEST FIT: PROCESS- "+(i+1)+"text ALLOCATED IN HOLE-"+(loc));
		         	k=0;
		    }   
		  	
		  	min=1000;
		  	loc=0;
		  	k=1;
		  //FINDING THE BEST FIT FOR THE DATA SECTION OF PROCESS
			    for( j=0;j<nholes;j++)
			    {
			    	temp=H[j];
			    	if(flag[j]==0 && P[i].data<=temp)
			    	{
			    		if(min>=temp)
			    		{
			    			min=temp;
			    			loc=j;
			    		}
			    	}
			       // k=1;
			    }
			  	if(k==1&& count<nholes && flag[loc]==0)
			    {
			    		flag[loc]=1;
	         			frag=frag+(H[loc]-P[i].data);
	         			count++;
			         	System.out.println("BEST FIT: PROCESS- "+(i+1)+"data ALLOCATED IN HOLE-"+(loc));
			         	k=0;
			    }   
			  	
				min=1000;
			  	loc=0;
			  	k=1;
			  //FINDING THE BEST FIT FOR THE HEAP SECTION OF PROCESS
				    for( j=0;j<nholes;j++)
				    {
				    	temp=H[j];
				    	if(flag[j]==0 && P[i].heap<=temp)
				    	{
				    		if(min>=temp)
				    		{
				    			min=temp;
				    			loc=j;
				    		}
				    	}
				       // k=1;
				    }
				  	if(k==1&& count<nholes && flag[loc]==0)
				    {
				    		flag[loc]=1;
		         			frag=frag+(H[loc]-P[i].heap);
		         			count++;
				         	System.out.println("BEST FIT: PROCESS- "+(i+1)+"heap ALLOCATED IN HOLE-"+(loc));
				         	k=0;
				    }   
				  	min=1000;
				  	loc=0;
				  	k=1;
				  	if(count==nholes)
				  	{
				  		System.out.println("PROCESS: "+(i+1)+"CANNOT BE ALLOCATED DUE TO INSUFFICCIENT MEMORY");
				  	}
				  	
		}
		System.out.println("The Resulting Fragmentation is : "+frag);
	}
	
	static void worstfit(Process P[],int H[],int nproc,int nholes,int flag[]){
		
		int max=0,loc=0,j,i,frag=0,temp, k=1,count=0;
		for(i=0;i<nproc;i++)
		{			//FINDING THE WORST FIT FOR THE TEXT SECTION OF PROCESS
		    for( j=0;j<nholes;j++)
		    {
		    	temp=H[j];
		    	if(flag[j]==0 && P[i].text<=temp)
		    	{
		    		if(max<=temp)
		    		{
		    			max=temp;
		    			loc=j;
		    		}
		    	}
		       // k=1;
		    }
		  	if(k==1 && count<nholes && flag[loc]==0)
		    {
		    
		  		    flag[loc]=1;
		  			count++;
		  		    frag=frag+(H[loc]-P[i].text);
		         	System.out.println("WORST FIT: PROCESS- "+(i+1)+"text ALLOCATED IN HOLE-"+(loc));
		         	k=0;
		    }   
		  	
		  	max=0;
		  	loc=0;
		  	k=1;
		  //FINDING THE WORST FIT FOR THE DATA SECTION OF PROCESS
			    for( j=0;j<nholes;j++)
			    {		
			    	temp=H[j];
			    	if(flag[j]==0 && P[i].data<=temp)
			    	{
			    		if(max<=temp)
			    		{
			    			max=temp;
			    			loc=j;
			    		}
			    	}
			       // k=1;
			    }
			  	if(k==1&& count<nholes && flag[loc]==0)
			    {
			    		flag[loc]=1;
	         			frag=frag+(H[loc]-P[i].data);
	         			count++;
			         	System.out.println("WORST FIT: PROCESS- "+(i+1)+"data ALLOCATED IN HOLE-"+(loc));
			         	k=0;
			    }   
			  	
				max=0;
			  	loc=0;
			  	k=1;
			  	
			  //FINDING THE WORST FIT FOR THE HEAP SECTION OF PROCESS
				    for( j=0;j<nholes;j++)
				    {
				    	temp=H[j];
				    	if(flag[j]==0 && P[i].heap<=temp)
				    	{
				    		if(max<=temp)
				    		{
				    			max=temp;
				    			loc=j;
				    		}
				    	}
				       // k=1;
				    }
				  	if(k==1&& count<nholes && flag[loc]==0)
				    {
				    		flag[loc]=1;
		         			frag=frag+(H[loc]-P[i].heap);
		         			count++;
				         	System.out.println("WORST FIT: PROCESS- "+(i+1)+"heap ALLOCATED IN HOLE-"+(loc));
				         	k=0;
				    }   
				  	max=0;
				  	loc=0;
				  	k=1;
				  	if(count==nholes)
				  	{
				  		System.out.println("PROCESS: "+(i+1)+"CANNOT BE ALLOCATED DUE TO INSUFFICCIENT MEMORY");
				  	}
		}
		
		System.out.println("The Resulting Fragmentation is : "+frag);
	}

	
	
static void paging(Process P[],int Pframe,int nproc,int page_flag[])
{
	int i,j,count=0,f=0;
	for(i=0;i<nproc;i++)
	{
		for(j=0;j<P[i].pages;j++)
		{   
                        int k=1;
			while(k==1 && count<Pframe)
			{ 
                         int ran=(int)(Math.random()*Pframe);	
			   if(page_flag[ran]==0)
			   {
				 page_flag[ran]=1;
				 k=0;
				 count++;
				 System.out.println("PAGING:PROCESS-"+(i+1)+":PAGE:"+(j+1)+"is ALLOCATED IN FRAME-"+ran);
                                 
			   }
			}
		}
                f+=P[i].Frag;	
	}
        System.out.println("Fragmentation is = "+f);       
}
}