import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

class Demo 
{
	
	public static void main(String[] args)
	{
		System.out.println("1. Text Input");
		System.out.println("2. URL Input");
		System.out.println("Enter your choice: ");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		HashMap list = null;
		String text = null;
		switch (choice) {
		case 1:
			TextInput c = new TextInput();
			text= "I am happy to say that i got placed in Wipro, a MNC. It was a tough Journey, but I made it.";
			list = c.testMethod(text);
			break;
		case 2:
			URLInput input = new URLInput();
			text = "https://yourstory.com/2017/07/ace-turtle-startup-omni-channel-specialist-big-brands/";
			list = input.testMethod(text);
			break;
		default:
			break;
		}
		Iterator i = list.entrySet().iterator();
		while (i.hasNext())
		{
			Map.Entry e = (Entry) i.next();
		    System.out.println(e.getKey() + " = " + e.getValue());
		}
	}
}