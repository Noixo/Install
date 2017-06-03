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

class DrawGraph
{
	private List<List<String>> adjList = new ArrayList<List<String>>();
	private LinkedList installedPrograms = new LinkedList();
	public DrawGraph()		//Constructor
	{
		//adjList = new ArrayList<List<String>>();
		AddRow();
	}
	
	public void AddRow()
	{
		adjList.add(new ArrayList<String>());
	}
}

public class DepInstall {
	
	private List<List<String>> adjList = new ArrayList<List<String>>();
	private int n = 0;	// Stores the current filled part of adjList
	public final Integer MAXCOMS = 1000;
	

	public DepInstall() {
		//adjList.add(new ArrayList<String>());
		// TODO
	}
	
//	public String[] split(String command)
//	{
//		return command.split("\\s");
//	}
	
	public void Graph(String command)
	{
		 //adjList = new ArrayList<List<String>>();

		adjList.add(new ArrayList<String>());
		adjList.set(n, new ArrayList<String>());
		
		//if(adjList.get(n) == null)
			//adjList.ensureCapacity();
		//adjList.get(0).add(new String ("A"));	//works
		//adjList.get(0).add("0");//set(0,"a");	//seems to work as well
		
		String[] a = command.split("\\s");
		
//		for(int i = 0; i < a.length; i++)
//			System.out.println("String: "+a[i]);
//		System.out.println("END");
		
//		for (int i = 0; i < adjList.size(); i++)
//		{
//			if (adjList.get(i) == null)
//				
//		}
		
		//if (a.length > 9)
		//adjList.add(new ArrayList<String>());
		
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
	
	public void install(String command)
	{
		String[] a = split(command);
		for(int i = 0; i < adjList.size(); i++)
		{
			//System.out.println("Memes");
			if (adjList.contains(a[1]))
			{
				System.out.println("dependency found");
			}
		}
		
		System.out.println("   installing "+a[1]);
	}
	
	public void runNCommands (Vector<String> commands, Integer N) {
		//System.out.println(commands);//).get(1));
		System.out.println("1");
		
		if (commands == null || commands.isEmpty())
		{
			return;
		}
		
		for (int i = 0; i < N || commands.get(i).contains("END"); i++)
		{
			if (commands.get(i).contains("DEPEND"))
			{
				System.out.println(commands.get(i));
				Graph(commands.get(i));
				//System.out.println(commands.get(i));
			}
			else if (commands.get(i).contains("INSTALL"))
			{
				System.out.println("INSTALL "+ split(commands.get(i))[1]);//commands.get(i));
				install(commands.get(i));
			}
			else if (commands.get(i).contains("REMOVE"))
			{
				
			}
			else
			{
				//List all the currently installed modules
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
		String PATH = "C:\\Users\\Matthew\\workspace\\DepInstallStudent\\src\\data\\";
		// change to your own path
		
		Integer N = d.MAXCOMS;
		//Integer N = 8;
		
		try {
			inCommands = d.readCommandsFromFile(PATH+"sample_D2a.in");
		}
		catch (IOException e) {
			System.out.println("in exception: " + e);
		}
		
		d.runNCommands(inCommands, N);
		System.out.println();

	}
}
