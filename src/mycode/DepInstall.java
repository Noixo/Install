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

class node
{
	
}

public class DepInstall {
	public ArrayList<ArrayList<String>> adjList = new ArrayList<ArrayList<String>>();
	
	public final Integer MAXCOMS = 1000;
	

	public DepInstall() {
		// TODO
	}
	
//	public String[] split(String command)
//	{
//		return command.split("\\s");
//	}
	
	public void Graph(String command)
	{
		//adjList.add("");
		String[] a = command.split("\\s");
		for (int i = 1; i < a.length; i++)
		{
			adjList.get(adjList.size()).set(i, a[i]);
			System.out.println(adjList.get(i));
		}
	}
	
	public void runNCommands (Vector<String> commands, Integer N) {
		//System.out.println(commands);//).get(1));
		System.out.println("1");
		
		if (commands == null || commands.isEmpty())
		{
			return;
		}
		
		for (int i = 0; i < N; i++)
		{
			if (commands.get(i).contains("DEPEND"))
			{
				Graph(commands.get(i));
			}
			else if (commands.get(i).contains("INSTALL"))
			{
				
			}
			else if (commands.get(i).contains("REMOVE"))
			{
				
			}
			else
			{
				//List all the currently installed modules
			}
			i++;
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
