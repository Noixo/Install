package mycode;

/*
 * code bundle ver 1.01
 * 
 * 11/5/17: fix to readSoln() to work with Windows files
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//class LinkedList
//{
//	String name;
//	String installType;
//	
//	LinkedList next;
//	
//	public LinkedList()
//	{
//		next = null;
//	}
//}

public class DepInstall {
	//private LinkedList head = null;
	
	private List<List<String>> adjList = new ArrayList<List<String>>();
	private LinkedList installedPrograms = new LinkedList();
	private LinkedList explicitInstall = new LinkedList();
	
	private int n = 0;	// Stores the current filled part of adjList
	public final Integer MAXCOMS = 1000;
	

	public DepInstall() {
		//adjList.add(new ArrayList<String>());
		// TODO
	}
	
	public void Graph(String command)
	{
		 //adjList = new ArrayList<List<String>>();

		adjList.add(new ArrayList<String>());
		adjList.set(n, new ArrayList<String>());
		
		String[] a = command.split("\\s");
		
		for (int i = 1; i < a.length; i++)
		{
			adjList.get(n).add(a[i]);
			//adjList.get(i).set(i, a[i]);
			//System.out.println(adjList.get(n));
		}
		n++;
	}
	
	public String[] split(String line)
	{
		
		return line.split("\\s");
	}
	
	public void install(String target)
	{	
		for(int column = 0; column < adjList.size(); column++)											//Loops the list column till null
		{
			//System.out.println("Current row "+row+" Current size: "+adjList.get(i).size());
			if (adjList.get(column).get(0).equals(target))									//Checks if column i, row 0 is equal to the dependency target
			{
				for (int rows = adjList.get(column).size()-1; rows > 0; rows--)
				{
					//System.out.println("size: "+ adjList.get(column).size());
					install(adjList.get(column).get(rows));								//If target is found, recusrse into the method again but this time finding row+1 as the target
					
				}
			}
		}
		if (!installedPrograms.contains(target))
		{
			System.out.println("   Installing "+target);
			installedPrograms.add(target);
		}
	}
	
	public void remove(String target, int currentColumn)
	{
		for(int column = 0; column < adjList.size(); column++)											//Loops the list column till null
		{
			//System.out.println("Current row "+row+" Current size: "+adjList.get(i).size());
			for (int rows = adjList.get(column).size()-1; rows > 0; rows--)
			{
				if(adjList.get(column).get(rows).contains(target) && currentColumn != column)
				{
					System.out.println("   "+target+" is still needed");
					return;										//Break if dependency is found in a row
				}
				if (adjList.get(column).get(0).contains(target))// && explicitInstall.contains(target))	//check column
				{
					remove(adjList.get(column).get(rows),column);
					
					//System.out.println("Exited at: "+target);
				}
			}						//If target is found, recusrse into the method again but this time finding row+1 as the target
		}
		System.out.println("   Removing "+target);
//		if(installedPrograms.contains(target) )
//		{
//			System.out.println("   Removing "+target);
//			adjList.remove(target);
//			explicitInstall.remove(target);
//		}
		
	}
	
	public void setup()
	{
		//System.out.println(adjList.get(i));
	}
	
	public void runNCommands (Vector<String> commands, Integer N) {
		//System.out.println(commands);//).get(1));
		
//		if (commands == null || commands.isEmpty())
//		{
//			return;
//		}
		
		for (int i = 0; i < N; i++)
		{
			if (commands.get(i).equals("END"))
				break;
	
			if (commands.get(i).contains("DEPEND"))
			{
				System.out.println(commands.get(i));
				Graph(commands.get(i));
				//System.out.println(commands.get(i));
			}
			else if (commands.get(i).contains("INSTALL"))
			{
				if (!explicitInstall.contains(split(commands.get(i))[1]))
					explicitInstall.add(split(commands.get(i))[1]);
				//System.out.println(explicitInstall);
				System.out.println("INSTALL "+ split(commands.get(i))[1]);//commands.get(i));
				
				if(installedPrograms.contains(split(commands.get(i))[1]))
				{
					System.out.println("   "+split(commands.get(i))[1]+ " is already installed");
					//return;
				}
				//explicitInstall.add(split(commands.get(i))[1]);
				install(split(commands.get(i))[1]);
				//System.out.println(split(commands.get(i))[1]);
			}
			else if (commands.get(i).contains("REMOVE"))
			{
				System.out.println(commands.get(i));
				if(!installedPrograms.contains(split(commands.get(i))[1]))
				{
					System.out.println("   "+split(commands.get(i))[1]+" is not installed");
				
				}
				else
				{
					remove(split(commands.get(i))[1],10001);
				}
			}
			else
			{
				System.out.println("LIST");
				for(int j = 0; j < installedPrograms.size(); j++)
					System.out.println("   "+installedPrograms.get(j));//List all the currently installed modules
			}
			//i++;
		}
		//if (commands)
		// PRE: commands contains set of commands read in by readCommandsFromFile()
		// POST: executed min(N, all) commands

		// TODO
	}
	
	public void runNCommandswCheck (Vector<String> commands, Integer N) {
		// PRE: commands contains set of commands read in by readCommandsFromFile()
		// POST: executed min(N, all) commands, checking for cycles

		// TODO
	}
	
	public void runNCommandswCheckRecLarge (Vector<String> commands, Integer N) {
		// PRE: commands contains set of commands read in by readCommandsFromFile()
		// POST: executed min(N, all) commands, checking for cycles and 
		//       recommending fix by removing largest cycle

		// TODO
	}

	public void runNCommandswCheckRecSmall (Vector<String> commands, Integer N) {
		// PRE: commands contains set of commands read in by readCommandsFromFile()
		// POST: executed min(N, all) commands, checking for cycles and 
		//       recommending fix by removing smallest cycle

		// TODO
	}
	

	public Vector<String> readCommandsFromFile(String fInName) throws IOException {
		// PRE: -
		// POST: returns lines from input file as vector of string
		BufferedReader fIn = new BufferedReader(
							 new FileReader(fInName));
		String s;
		Vector<String> comList = new Vector<String>();
		
		while ((s = fIn.readLine()) != null) {
			comList.add(s);
		}
		fIn.close();
		
		return comList;
	}
	
	
	public String readSoln(String fInName, Integer N) throws IOException {
		// PRE: -
		// POST: returns N lines from input file as single string
		BufferedReader fIn = new BufferedReader(
							 new FileReader(fInName));
		String s;
		String out = "";
		Integer i = 0;

		while (((s = fIn.readLine()) != null) && (i <= N)) {
			if ((i != N) || s.startsWith("   ")) // responses to commands start with three spaces
				out += s + System.lineSeparator();
			if (!s.startsWith("   "))  
				i += 1;
		}
		fIn.close();
		
		return out;
	}


	public static void main(String[] args) {
		
//		List<List<String>> adjList = new ArrayList<List<String>>();
//		adjList.add(new ArrayList<String>());
//		adjList.get(0).add(new String ("A"));	//works
//		adjList.get(0).add("0");//set(0,"a");	//seems to work as well
		
		
		DepInstall d = new DepInstall();
		Vector<String> inCommands = null;
		String PATH = "E:\\Workspace\\Install\\src\\data\\";
		// change to your own path
		
		Integer N = d.MAXCOMS;
		//Integer N = 8;
		
		try {
			inCommands = d.readCommandsFromFile(PATH+"sample_P1.in");//sample_D2a.in");
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		d.runNCommands(inCommands, N);
		System.out.println();

	}
}
