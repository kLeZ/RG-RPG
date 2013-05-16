package it.d4nguard.rgrpg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Welcome
{
    public static void print()
    {
	System.out.println("Welcome to RG-RPG!");
	try
	{
	    Scanner scn = new Scanner(new File("README.md"));
	    while(scn.hasNext())
	    {
		System.out.println(scn.nextLine());
	    }
	}
	catch(FileNotFoundException e)
	{
	    e.printStackTrace();
	}
    }
}
