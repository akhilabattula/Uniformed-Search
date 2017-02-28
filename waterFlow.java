package original_ai;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;


public class waterFlow {
	
	

 

	/*public static List<Ucsnode> printPath(Ucsnode target){
	     List<Ucsnode> path = new ArrayList<Ucsnode>();
	     for(Ucsnode node = target; node!=null; node = node.parent){
	         path.add(node);
	     }

	     Collections.reverse(path);

	     return path;

	 }*/
	public static void main(String[] args) throws IOException,NoSuchElementException{
		String fname = "input.txt";
		String line = null;
		int itt=0;
		int it=0;
		int p=0;
		Node[] nD=new Node[1000];
		Node[] nM=new Node[1000];
		Node[] nnD=new Node[1000];
		Node[] nnM=new Node[1000];
		Ucsnode[] nDD=new Ucsnode[1000];
		Ucsnode[] nMM=new Ucsnode[1000];
		//waterFlow f=new waterFlow();
		int npipes=0;
		int loopctl=0;
		ArrayList<String> dest=new ArrayList<String>();
		ArrayList<String> dest1=new ArrayList<String>();
		//ArrayList<String> mid1=new ArrayList<String>();
		//String[] offs=new String[100];
		int[][][] period=new int[100][100][100];
		for(int i=1;i<100;i++)
{
	for(int j=1;j<100;j++)
	{
		for(int k=1;k<100;k++){
			period[i][j][k]=0;
		}
	}
}
		//int[] offperiods=new int[100];
		
		
        try {
        	PrintStream ps=new PrintStream(new File("output.txt"));
        	System.setOut(ps);
            FileReader fr = new FileReader(fname);

            BufferedReader br =  new BufferedReader(fr);
            
            int count=Integer.parseInt(br.readLine());//reads the number of test cases
           
               if(count>0){
                while (loopctl<count) {
   //System.out.println("line entering while lop"+line);
                	//System.out.println("loop controlling varibale "+loopctl);

            	line=br.readLine(); 
            	//System.out.println("1st line in while loop"+line);
            	
            	if(line.isEmpty()==true) { //checking if algo is given or not 
            		System.out.println("algorithm not given");
            	break;	
            	}
            	else{
            		
            if(line.equals("BFS")) {
            	//System.out.println("yes it is in bfs");
            	
            	line=br.readLine();
            	/*write a code here if  root node is not given what happens*/
            	if(line.isEmpty()==true)
            	{
            		System.out.println("source node is not given");
            		break;
            	}
            	else{
            		
            		Node nA=new Node(line);
            		//System.out.println("assigned source node"+nA.nodename);
            	
            	
           
            	line=br.readLine();
            	if(line.isEmpty()==true) //checking if destinations are given or not 
            		{
            		System.out.println("destinations are not given");
            		break;
            		}
            	
            	else{
            		StringTokenizer st = new StringTokenizer(line, " ");
            		it=0;
            	while(st.hasMoreTokens()) { 
            		nD[it]=new Node( st.nextToken());
            		dest.add(nD[it].nodename);
            		//System.out.println("destination nodes are"+nD[it].nodename);
            		it++;
            		
            	   }
            	
            	Collections.sort(dest);
            	//System.out.println("here is it"+dest);
            	
            	
            	} 
            	line=br.readLine();
            	
            	if(line.isEmpty()==true)
            	{
            		//System.out.println("no middle nodes");
            		nM=null;
            		
            	}
            	else{
            		StringTokenizer st1 = new StringTokenizer(line, " ");
            	
            		itt=0;
            	while(st1.hasMoreTokens()) { 
            		nM[itt]=new Node( st1.nextToken());
            		//System.out.println("destination nodes are"+nM[itt].nodename);
            		itt++;
            		
            	}
                		
            	}
            
            	line=br.readLine();//checking if the no. of pipes are given or not
            	if(line.isEmpty()==true)
            	{
            		System.out.println("number of pipes are not given");
            	break;	
            	}
            	else{
            	npipes=Integer.parseInt(line);
            	//System.out.println("no of  pipes"+npipes);
            	}
            	
            	//adding nodes to the connection
            	connection g=new connection();
        		g.addNode(nA);
        		int z=0;
        		for(z=0; z < it;z++)
        		g.addNode(nD[z]);
        		for(z=0;z < itt;z++)
        			if(nM!=null)
            		g.addNode(nM[z]);
        		g.setRootNode(nA);
        		
        		line=br.readLine();
        		//System.out.println("line read"+line);
        		for(p=1;p<=npipes;p++)
        		{
        		//System.out.println("value of p"+p);
        			StringTokenizer st1 = new StringTokenizer(line, " ");
        			//System.out.println("that particular line"+line);
        			 String ab=st1.nextToken();
        			     String cd=st1.nextToken();
        			     g.connectNode(ab,cd);
        			   // System.out.println("got connected");
        			     line=br.readLine();
        			   //  System.out.println("next line"+line);
                }
        		//System.out.println("not coming here");
        		int slen=Integer.parseInt(line);
        		nA.cost=slen;
        	//System.out.println("last line in bfs"+line);
        	//System.out.println("BFS Traversal of a tree is ------------->");
    		g.bfs(dest,slen);
    		//System.out.println("came out froom connection method");
    		line=br.readLine();
    		//System.out.println("line in bfs"+line);
        		
        	}
        	
            }
            
             
            else if(line.equals("UCS"))
            { 
            	//System.out.println("this is uniform cost search");
            	line=br.readLine();
            	/*write a code here if  root node is not given what happens*/
            	if(line.isEmpty()==true)
            	{
            		System.out.println("source node is not given");
            		break;
            	}
            	else{
            		//System.out.println("taking source");
            		
            		Ucsnode nAA=new Ucsnode(line);
            		//System.out.println("assigned source node"+nAA.value);
            	
            	
           
            	line=br.readLine();
            	if(line.isEmpty()==true) //checking if destinations are given or not 
            		{
            		System.out.println("destinations are not given");
            		break;
            		}
            	
            	else{
            		//System.out.println("taking  destinations");
            		StringTokenizer st = new StringTokenizer(line, " ");
            		it=0;
            	while(st.hasMoreTokens()) { 
            		nDD[it]=new Ucsnode( st.nextToken());
            		dest1.add(nDD[it].value);
            		//System.out.println("destination nodes are"+nDD[it].value);
            		//System.out.println("\n");
            		it++;
            		
            	   }
            	
            	//System.out.println("here is it"+dest);
            	
            	
            	} 
            	line=br.readLine();
            	
            	if(line.isEmpty()==true)
            	{
            		System.out.println("no middle nodes");
            		break;
            	}
            	else{
            		//System.out.println("taking middle nodes");
            		StringTokenizer st1 = new StringTokenizer(line, " ");
            	
            		itt=0;
            	while(st1.hasMoreTokens()) { 
            		nMM[itt]=new Ucsnode( st1.nextToken());
            		//mid1.add(nMM[it].value);
            		//System.out.println("middle nodes are"+nMM[itt].value);
            		itt++;
            		
            	}
                		
            	}
            
            	line=br.readLine();//checking if the no. of pipes are given or not
            	if(line.isEmpty()==true)
            	{
            		System.out.println("number of pipes are not given");
            	break;	
            	}
            	else{
            	npipes=Integer.parseInt(line);
           // System.out.println("no of  pipes"+npipes);
            	}
            	
            	//adding nodes to the connection
            	connection g=new connection();
        		g.addNode1(nAA);
        		int z=0;
        		for(z=0; z < it;z++)
        		g.addNode1(nDD[z]);
        		for(z=0;z < itt;z++)
            		g.addNode1(nMM[z]);
        		g.setRootNode1(nAA); 
            	
        		line=br.readLine();
        		//System.out.println("line read"+line);
        		int[] numberofoffperiods=new int[100];
        		for(p=1;p<=npipes;p++)
        		{
        		//System.out.println("value of p"+p);
        			//System.out.println("the line reading is"+line);
        			StringTokenizer st1 = new StringTokenizer(line, " ");
        	
     			 String ab=(st1.nextToken());
     
        			   String cd=st1.nextToken();
        			
        			     int cost=Integer.parseInt(st1.nextToken());
        			    // System.out.println("the cost of this path is"+cost);
        			  numberofoffperiods[p]=Integer.parseInt(st1.nextToken());
        			 //// System.out.println("the number of off periods it has is"+numberofoffperiods[p]);
        			 // int v=1;
        			  if(numberofoffperiods[p]!=0){
        				  //System.out.println("came here becuase no of off periods are non zero");
        			  for(int j=1;j<=numberofoffperiods[p];j++){
        				  String temp=(st1.nextToken());
        				  String[] parts=temp.split("-");
        				  period[p][j][1]=Integer.parseInt(parts[0]);
        				  //System.out.println("the 1st part of 1st pipe"+period[p][j][1]);
        				  period[p][j][2]=Integer.parseInt(parts[1]);
        				  //System.out.println("the 2ndt part of 1st pipe"+period[p][j][2]);
        				  
        			  }
        		}
        			     g.connectNode1(ab,cd, cost,p);
        			     line=br.readLine();
        			  
        			     } 
        		//System.out.println("came here");
        			   //  System.out.println("above line is "+line);
        			     
        		int slen=Integer.parseInt(line);
        	//System.out.println("last line in ucs"+line);
        	//System.out.println("Ucs Traversal of a tree is ------------->");
    		;
    		 g.ucs(nAA,dest1,slen,numberofoffperiods,period);
    		 dest1.clear();

 	        //System.out.println("Path: " + path);
 	        line=br.readLine();
       	}
            
            	}
            else if(line.equals("DFS"))
            {
            	int h=0;
            	int hh=0;
            	//System.out.println("we are in dfs");
            	//System.out.println("first line in dfs"+line);
            	//System.out.println("this is DFS");/*write a code here if  root node is not given what happens*/
            	line=br.readLine();
            	//System.out.println("2nd line in dfs"+line);
            	/*write a code here if  root node is not given what happens*/
            	
            	if(line.isEmpty()==true)
            	{
            		System.out.println("source node is not given");
            		break;
            	}
            	else{
            		Node nA=new Node(line);
            	//	System.out.println("the source we have read is"+nA.nodename);
            		
            	
            	
           
            	line=br.readLine();
            	//System.out.println("3rd line in dfs"+line);
            	if(line.isEmpty()==true) //checking if destinations are given or not 
            		{
            		//System.out.println("destinations are not given");
            		break;
            		}
            	
            	else{
            		StringTokenizer st = new StringTokenizer(line, " ");
            		
            		
            	while(st.hasMoreTokens()) { 
            		//System.out.println("tokens are getting read");
            		nnD[h]=new Node( st.nextToken());
            	//	System.out.println("the destinations are"+nnD[h].nodename);
            		dest1.add(nnD[h].nodename);
            		h++;
            		
            	   }
            	
            	} 
            	line=br.readLine();
            	//System.out.println("4th line in dfs"+line);
            	
            	if(line.isEmpty()==true)
            	{
            		//System.out.println("no middle nodes");
            	
            	}
            	else{
            		StringTokenizer st1 = new StringTokenizer(line, " ");
            	
            		
            	while(st1.hasMoreTokens()) { 
            	//	System.out.println("reading mid nodes in dfs");
            	//	
            		nnM[hh]=new Node( st1.nextToken());
            	//	System.out.println("the middlenodes are"+nnM[hh].nodename);
            		hh++;
            		
            	}
                		
            	}
            
            	line=br.readLine();//checking if the no. of pipes are given or not
            	if(line.isEmpty()==true)
            	{
            		System.out.println("number of pipes are not given");
            	break;	
            	}
            	else{
            		//System.out.println("pipes are given");
            	npipes=Integer.parseInt(line);
            //	System.out.println("no of pipes"+npipes);
            	}
            	
            	//adding nodes to the connection
            	connection g=new connection();
        		g.daddNode(nA);
        		int z=0;
        		for(z=0; z < h;z++)
        		g.daddNode(nnD[z]);
int zz=0;
        		for(zz=0;zz < hh;zz++)
            		g.daddNode(nnM[zz]);
        		g.setRootNode(nA);
        		
        		line=br.readLine();
        		for(p=1;p<=npipes;p++)
        		{
        			//System.out.println("coming here");
        			StringTokenizer st1 = new StringTokenizer(line, " ");
        			 String ab=st1.nextToken();
        			 
        		     String cd=st1.nextToken();
        		//    System.out.println("the line that is getting read is"+line);
        			    
        			    // System.out.println("the 1st part of line"+ab);
        			    // System.out.println("the next part of line"+cd);
        			     g.connectNode2(ab,cd);
        			     line=br.readLine();
                }
        		//System.out.println("came out");
        		int dlen=Integer.parseInt(line);
        		//System.out.println("I am not gng here");
        		//System.out.println("len passing in dfs"+dlen);
        	//System.out.println("last line"+line);
        	//System.out.println("DFS Traversal of a tree is ------------->");
    		g.dfs(dest1, dlen);
    		line=br.readLine();
    		//System.out.println("line in dfs"+line);
        		
        	}
            	
        	}
            
            else{
            	System.out.println("This is not a valid algorithm");
            }
           
                }
            	 loopctl++;
            	 dest.clear();
            }
                    br.close(); 
            }
            
            
            else{
            	System.out.println("invalid test cases ");
            	
            }
            }
        
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fname + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fname + "'");                  
        }
        catch(NumberFormatException ex){
        	System.out.println("Enter a valid number you have entered a character");
        }
        catch(IndexOutOfBoundsException ex)
        {
        	System.out.println("Index out of bounds exception");
        }
        
}
}

