package original_ai;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;



class connection 
{
	public Node rootNode;
	public Ucsnode rootNode1;
	//public MyState noderoot;
	public ArrayList<String> nodes=new ArrayList<String>();
	public ArrayList<String> dnodes=new ArrayList<String>();
	public ArrayList<Node> nodes1=new ArrayList<Node>();
	public ArrayList<Node> dnodes1=new ArrayList<Node>();
	public ArrayList<String> ucsnodes=new ArrayList<String>();
	public ArrayList<Ucsnode> ucsnodes1=new ArrayList<Ucsnode>();
	public ArrayList<Ucsnode> ucsnodes2=new ArrayList<Ucsnode>();

	ArrayList<Node> n=new ArrayList<Node>();
	public int[][] adjMatrix;
	public int[][] linkmatrix;
	public int[][] adjMatrix1;
	public int[][] adjMatrix2;
	//Edges will be represented as adjacency Matrix
	public int[][] costMatrix;
	int size;
	int size1;
	int size2;
	public void setRootNode(Node n)
	{
		this.rootNode=n;
	}
	public void setRootNode1(Ucsnode n)
	{
		this.rootNode1=n;
	}
	public Node getRootNode()
	{
		return this.rootNode;
	}
	public Ucsnode getRootNode1()
	{
		return this.rootNode1;
	}
	
	public void addNode(Node n)
	{
		nodes.add(n.nodename);
		nodes1.add(n);
	}
	public void daddNode(Node n)
	{
		//System.out.println("that we are going to add now"+n.nodename);
		dnodes.add(n.nodename);
		//System.out.println(dnodes);
		dnodes1.add(n);
	}
	public void addNode1(Ucsnode n){
		
		ucsnodes.add(n.value);
		ucsnodes1.add(n);
		ucsnodes2.add(n);
	
		
	}
	//This method will be called to make connect two nodes
	public void connectNode(String start,String end)
	{

		if(adjMatrix==null)
		{
			size=nodes.size();
			adjMatrix=new int[size][size];
		}

		int startIndex=nodes.indexOf(start);
		//System.out.println("the index of" + start +" is"+startIndex);
		int endIndex=nodes.indexOf(end);
		//System.out.println("the index of" + end +" is"+endIndex);
		adjMatrix[startIndex][endIndex]=1;
		//adjMatrix[endIndex][startIndex]=1;
	}
	public void connectNode2(String start,String end) throws IndexOutOfBoundsException
	{
		try{

		if(adjMatrix2==null)
		{
			size2=dnodes.size();
			adjMatrix2=new int[size2][size2];
		}

		int startIndex=dnodes.indexOf(start);
		//System.out.println("the index of" + start +" is"+startIndex);
		int endIndex=dnodes.indexOf(end);
		//System.out.println("the index of" + end +" is"+endIndex);
		if(startIndex!=-1 && endIndex!=-1)
		adjMatrix2[startIndex][endIndex]=1;
		else
		{
			//System.out.println("in else loop");
			if(startIndex==-1){
				dnodes.add(start);
				Node k=new Node(start);
				dnodes1.add(k);
				
			}
			if(endIndex==-1){
				dnodes.add(end);
				Node k=new Node(end);
				dnodes1.add(k);
			}
			startIndex=dnodes.indexOf(start);
			endIndex=dnodes.indexOf(end);
			adjMatrix2[startIndex][endIndex]=1;
				
		}
		
		//adjMatrix[endIndex][startIndex]=1;
		}
		catch(IndexOutOfBoundsException ex){
			System.out.println("you are tring to enter a node which you have not mentioned as root or "
					+ "destination or middle node");
		}
	}
	public void connectNode1(String start,String end,int cost,int p)
	{
		//System.out.println("inside connectnode1 of connection");
		
		if(adjMatrix1==null)
		{
			
			size1=ucsnodes.size();
			//System.out.println("size of the adj matrix its going to create is"+size1);
			adjMatrix1=new int[size1][size1];
			
			costMatrix=new int[size1][size1];
			linkmatrix=new int[size1][size1];
		}

		int startIndex=ucsnodes.indexOf(start);
		//System.out.println("start index is"+startIndex);
		
		int endIndex=ucsnodes.indexOf(end);
		//System.out.println("end index is"+endIndex);
		if(startIndex==-1 || endIndex==-1)
System.out.println("You are trying to give an edge to the node which u havent mentioned while giving nodes hence throws index out of bounds exception");		
		adjMatrix1[startIndex][endIndex]=1;
		linkmatrix[startIndex][endIndex]=p;
		
		//adjMatrix1[endIndex][startIndex]=1;
		
		//costMatrix[endIndex][startIndex]=cost;
		costMatrix[startIndex][endIndex]=cost;
		//System.out.println(costMatrix[startIndex][endIndex]);
	
		
	}
	
	private Node getUnvisitChildNode(Node n)
	{
		
		int index=nodes.indexOf(n.nodename);
		int j=0;
		while(j<size)
		{
			if(adjMatrix[index][j]==1 && (nodes1.get(j)).visit.equals("false"))
			{
				return (nodes1.get(j));
			}
			j++;
		}
		return null;
	}
	private ArrayList<Node> getallchildren(Node n)
	{
		ArrayList<Node> a=new ArrayList<Node>();
		int index=dnodes.indexOf(n.nodename);
		int j=0;
		
		while(j<size2)
		{
			if(adjMatrix2[index][j]==1 && j!=index)
			{
				//System.out.println(dnodes1.get(j).nodename);
				a.add(dnodes1.get(j));
			}
			j++;
		}
		return a;
	}
	
	
	
	private ArrayList<Ucsnode> getUnvisitChildNode(Ucsnode n)
	{
		
		int index=ucsnodes.indexOf(n.value);
		ArrayList<Ucsnode> all=new ArrayList<Ucsnode>();
		int j=0;
		while(j<size1)
		{
			if(adjMatrix1[index][j]==1)
			{
				
				//	ucsnodes1.get(j).visit="true";
					//System.out.println("the associated cost of"+ucsnodes1.get(j).value+"is"+ucsnodes1.get(j).pathCost);
				Ucsnode tempnode =new Ucsnode(ucsnodes1.get(j).value);
				tempnode.pathCost=costMatrix[index][j];
				//System.out.println("sfsdfsdfds"+tempnode.pathCost);
				//System.out.println("ucsnodes1"+ucsnodes1.get(j).value+" "+ucsnodes1.get(j).pathCost);
				all.add(tempnode);
			}
			j++;
		}
		return all;
	}
	
	
	//BFS traversal of a tree is performed by the bfs() function
	public void bfs(ArrayList<String> dest,int slen)
	{
		
		//BFS uses Queue data structure
		Queue<Node> q=new LinkedList<Node>();
		int destfound=0;
		q.add(this.rootNode);
		//System.out.println(rootNode.nodename);
		//printNode(this.rootNode);
		//check here if it is a destination node or not 
		rootNode.visit="true";
		while(!q.isEmpty())
		{
			
			Node n=(Node)q.remove();
			//System.out.println("removed node"+n.nodename);
			if(dest.contains(n.nodename))
			{
				destfound++;
				//System.out.println("from connection method"+n.nodename);
				System.out.println(n.nodename+" "+n.cost);
				break;
			}
			Node child=null;
			Node[] child2=new Node[100];
			int n1=0;
			while((child=getUnvisitChildNode(n))!=null)
			{
				//System.out.println("went here");
				child2[n1]=child;
				//System.out.println("assigning here");
				child2[n1].visit="true";
				child2[n1].cost=n.cost+1;
			
				//q.add(child);
				//System.out.println("child is"+child2[n1].nodename);
				n1++;
				
			}
			 for (int i = 0; i < n1; i++) 
		        {
		            for (int j = i + 1; j < n1; j++) 
		            {
		                if (child2[i].nodename.compareTo(child2[j].nodename)>0) 
		                {
		                    String temp = child2[i].nodename ;
		                    child2[i].nodename = child2[j].nodename;
		                    child2[j].nodename = temp;
		                }
		            }
		        }
			 
			for(int i=0;i<n1;i++)
			{
				child=child2[i];
				//System.out.println("before adding to the queue"+child.nodename);
				q.add(child);
			}
			//Collections.sort(c);
			//q.addAll(c);
		
		}
		//Clear visit property of nodes
		if(destfound==0)
			System.out.println("None");
		clearNodes();
		dest.clear();
	}
	/*  public static List<Ucsnode> printPath(Ucsnode target){
	        List<Ucsnode> path = new ArrayList<Ucsnode>();
	        for(Ucsnode node = target; node!=null; node = node.parent){
	            path.add(node);
	        }

	        Collections.reverse(path);

	        return path;

	    }*/
public void ucs(Ucsnode source,ArrayList<String> dest1,int slen,int[] noofoffperiods,int[][][] period){
	//System.out.println("in ucs of connection");
	//System.out.println("the destinatons are"+dest1);
	source.pathCost=slen;
	int p=ucsnodes1.indexOf(source);
	ucsnodes1.get(p).pathCost=slen;
	//System.out.println("name of source is"+ucsnodes1.get(p).value+" and its pathcost is"+ucsnodes1.get(p).pathCost);
Ucsnode child=null;
	 PriorityQueue<Ucsnode> queue = new PriorityQueue<Ucsnode>(40, 
	            new Comparator<Ucsnode>(){
	                //override compare method
	                public int compare(Ucsnode i, Ucsnode j){
	                    if(i.pathCost > j.pathCost){

	                        return 1;
	                    }

	                    else if (i.pathCost < j.pathCost){
	                        return -1;
	                    }

	                    else{
	                        return i.value.compareTo(j.value);
	                    }
	                }
	            });
	 

	 queue.add(source);
	 
    
     boolean found = false;
     do{
    	 
         Ucsnode current = queue.remove();
        // System.out.println("the node that is polled is"+current.value);
         for(int i=0;i<ucsnodes1.size();i++)
         {
        	 if(ucsnodes1.get(i).value.equals(current.value))
        		 ucsnodes1.get(i).visit=true;
         }

         //int pp=ucsnodes1.indexOf(current);
        // ucsnodes1.get(pp).pathCost=current.pathCost;
         

         if(dest1.contains(current.value)){
             found = true;
             
             System.out.println(current.value+" "+current.pathCost%24);
             break;


         }
        // System.out.println(current.value+"not found in destination");
         //System.out.println("here");
         
        // ArrayList<Ucsnode> child1=new ArrayList<Ucsnode>();
         //int n1=0;
        // show s;
        // s=getUnvisitChildNode(current);
        // child=s.A;
         //System.out.println("first child of"+" "+current.value+ " " +"is"+" "+s.A.value);
      /*   while((s=getUnvisitChildNode(current))!=null)
         {
        	 child=s.A;
        	 child1[n1]=child;
        	 System.out.println("the"+n1+"child of "+current.value+" "+"is"+" "+child1[n1].value);
        	 child1[n1].pathCost = current.pathCost + costMatrix[s.i][s.j];
        	 System.out.println("path cost of"+child1[n1].value+" "+"is"+child1[n1].pathCost);
        	 child1[n1].visit="true";
        	 n1++;
        	
        	
         }*/
         int start=0;
         //int start=ucsnodes1.indexOf(current);
  	   for(int i=0;i<ucsnodes1.size();i++)
  	   {
  		   if (current.value.equals(ucsnodes1.get(i).value))
  		   start=ucsnodes1.indexOf((ucsnodes1.get(i)));
  	   }
//System.out.println("then here");
ArrayList<Ucsnode> child1=new ArrayList<Ucsnode>();
                    child1=getUnvisitChildNode(ucsnodes1.get(start));
                    //System.out.println("the unvisited chidren of "+current.value+"is"+child1);
                  //  System.out.println("the pathcost of parent is "+current.pathCost);
                   int n1= child1.size();
                
            	//   System.out.println("start"+start);
                   ArrayList<Ucsnode> childfinal=new ArrayList<Ucsnode>();
                   for(int i=0;i<n1;i++)
                   {
                	   int end=0;
                	   for(int iw=0;iw<ucsnodes1.size();iw++)
                	   {
                		   if (child1.get(i).value.equals(ucsnodes1.get(iw).value))
                		   end=ucsnodes1.indexOf((ucsnodes1.get(iw)));
                	   }
                	   int closed=0;
                	   //int end=ucsnodes1.indexOf((child1.get(i)));
                	child1.get(i).pathCost+=current.pathCost;
                	//child1.get(i).pathCost=child1.get(i).pathCost
                			//;
              //  	System.out.println("the cost of"+child1.get(i).value+"is"+child1.get(i).pathCost);
                	   int pipeno=linkmatrix[start][end];
                	   if(noofoffperiods[pipeno]!=0){
                		   //System.out.println("there is a pipe between "+current.value+ " "+child1.get(i).value
                			//	   +" and it has "+ noofoffperiods[pipeno]+" pipes");
                		  // System.out.println("going into for loop");
                	   for(int kk=1;kk<=noofoffperiods[pipeno];kk++){
                		  // System.out.println("inside for loop");
                		   if(period[pipeno][kk][1]<=(current.pathCost%24) && period[pipeno][kk][2]>=(current.pathCost%24)){
                			   closed=1;
                			 //System.out.println("NOT ADDING"+child1.get(i).value+"which is child of"+current.value);
                			   break;
                		   }
             
                	   }
                   }
                	   
                	   if(closed!=1){
                		   //System.out.println("adding "+child1.get(i).value+" children to final list");
                		   childfinal.add(child1.get(i));
                		   
                	   }
                	   
                	  // System.out.println("hereee"+child1.get(i));
                   }
                  // System.out.println("the final children of "+current.value +" are"+childfinal);
n1=childfinal.size();
         /*for(Edge e: current.adjacencies){
             Node child = e.target;
             double cost = e.cost;
             child.pathCost = current.pathCost + cost;*/
for(int i=0;i<n1;i++){
              child=childfinal.get(i);
              //System.out.println("child considered nexxxxxxxt"+child.value);
              Boolean visited=false;
              for(int q=0;q<ucsnodes1.size();q++)
              {
            	 
                 	 if(ucsnodes1.get(q).value.equals(child.value) && ucsnodes1.get(q).visit==true)
                 		 {visited=true;
                 		 break;}
                 
              }
              if(visited==true)
            	  break;
              Iterator<Ucsnode> itr = queue.iterator();
     Boolean notinqueue=true;
              while(itr.hasNext()){
             	 Ucsnode temp=itr.next();
             	 //System.out.println(temp.value+" teno"+temp.pathCost);
             	 //System.out.println(child.value+" "+child.pathCost);
             	// child.pathCost+=current.pathCost;
              if(temp.value.equals(child.value))
              {
            	  //System.out.println("insssssssssssssssss"+child.value+"sssssssssssssssside"+child.pathCost);
            	      notinqueue=false;
            		  if(temp.pathCost>child.pathCost)  
            		  {
            			 child.parent=current;

                          // the next two calls decrease the key of the node in the queue
                          queue.remove(temp);
                          //System.out.println(temp.value+" -temp value"+temp.pathCost+"-tempcost");
                          
                          //System.out.println("the parent of "+child.value+"is "+child.parent+" cost"+child.pathCost);
                          //System.out.println("hi");
                          queue.add(child);
            		  }
            			  
            	}
              }
              if(notinqueue==true)
              {
            	  child.parent = current;
                  // child.pathCost+=current.pathCost;
                   //int ll=ucsnodes1.indexOf(child);
                   //ucsnodes1.get(ll).pathCost=child.pathCost;
                  // System.out.println("the parent of "+child.value+"is "+child.parent+" cost"+child.pathCost);
                   //queue.add(ucsnodes1.get(ll));
  queue.add(child);
                  // System.out.println(child);
                  
                   //System.out.println();
              }
              
             
            
           
             //else{
            //	 System.out.println("why am I here");
             //}
}


        
     // System.out.println("till now explored are"+explored.toString());
     }while(!queue.isEmpty());
     if(queue.isEmpty() && found==false)System.out.println("None");
     ucsnodes1.clear();
     ucsnodes.clear();
}
	//DFS traversal of a tree is performed by the dfs() function
	public void dfs(ArrayList<String> dest,int slen)
	{
		//System.out.println("in dfs");
		Stack<Node> s=new Stack<Node>();
		rootNode.cost=slen;
		s.push(this.rootNode);
		ArrayList<Node> children=new ArrayList<Node>();
		ArrayList<Node> explored=new ArrayList<Node>();
		Node[] c=new Node[100];
		rootNode.visit="true";
		//System.out.println("root node is"+rootNode.nodename);
		//printNode(rootNode);
		  boolean found = false;
		while(!s.isEmpty())
		{
			Node n=s.pop();
				if(dest.contains(n.nodename))
				{
					found=true;
					System.out.println(n.nodename+" "+n.cost);
					break;
				}
				explored.add(n);
			//System.out.println("pop node"+n.nodename);
			children=getallchildren(n);
			int itssize=children.size();
			for(int me=0;me<itssize;me++){
		//	System.out.println("all children"+children.get(me).nodename);
				
				c[me]=new Node(children.get(me).nodename);
				c[me].parent=n;
				c[me].cost=n.cost+1;			
			}
			//System.out.println("before sorting");
			//for(int me=0;me<itssize;me++){
				//System.out.println("all children"+c[me].nodename);
			//}
			for (int ii= 0; ii < itssize; ii++) 
	        {
	            for (int jj = ii + 1; jj < itssize; jj++) 
	            {
	                if (c[ii].nodename.compareTo(c[jj].nodename)<0) 
	                {
	                    Node temp = c[ii];
	                    c[ii] = c[jj];
	                    c[jj] = temp;
	                }
	            }
	        }
			//System.out.println("after sorting");
		//	for (int ii= 0; ii < itssize; ii++)
			//{
				//System.out.println(c[ii].nodename);
			//}
			//System.out.println("after sorting");
			//for(int me=0;me<itssize;me++){
				//System.out.println("all children"+c[me]);
			//}
			
			for(int me=0;me<itssize;me++){
				//System.out.println("into for");
				if(!s.contains(c[me]) && !explored.contains(c[me]))
				{
					//System.out.println("in if");
				s.push(c[me]);
				//System.out.println(c[me].nodename+"is being pushed with a cost of"+c[me].cost);
				}
				/*else if(s.contains(c[me])&& !explored.add(c[me])){
					//System.out.println("in else if");
					s.push(c[me]);
				}*/
			}
			
			
			
		
		}
		if(s.isEmpty() && found==false)System.out.println("None");
		dnodes.clear();
		dnodes1.clear();
		
	}
	
	


	//Utility methods for clearing visit property of node
	private void clearNodes()
	{
		int i=0;
		while(i<size)
		{
			Node n=(Node)nodes1.get(i);
			n.visit="false";
			i++;
		}
	}
	
	//Utility methods for printing the node's nodename
	/*private void printNode(Node n)
	{
		System.out.print("the child node is"+n.nodename);
	}*/

	
	
	

}



