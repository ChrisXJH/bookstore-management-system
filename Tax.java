import java.io.*;
public class Tax
{
    private String loc;
    private double taxRate;
    private String[] temp;
    private String[] province;
    private double[] tax;
    private int hintPosition;
    final private String path1 = "data\\tax\\taxdatabase.txt";

    public Tax () throws IOException
    {
	readFile ();
	init ();
    }


    public void readFile () throws IOException
    {
	int num = 0;
	String str;
	BufferedReader rdr = new BufferedReader (new FileReader (path1));

	while (true)
	{
	    str = rdr.readLine ();
	    if (str != null)
		num++;
	    else
		break;
	}

	rdr.close ();
	temp = new String [num];
	province = new String [num - 1];
	tax = new double [num - 1];

	rdr = new BufferedReader (new FileReader (path1));
	for (int i = 0 ; i < num ; i++)
	    temp [i] = rdr.readLine ();

	rdr.close ();

	loc = temp [0];
	for (int i = 1 ; i < temp.length ; i++)
	{
	    province [i - 1] = getStr (temp [i], "p:", ";", 0).toUpperCase ();
	    tax [i - 1] = Double.parseDouble (getStr (temp [i], "t:", ";", 0));
	}
    }


    public void init ()
    {
	if (isArrExist (province, loc))
	    taxRate = getTaxThroughLoc (loc);
	else
	    taxRate = 0.13;
    }


    public boolean isArrExist (String[] arr, String str)
    {
	boolean rtn = false;
	for (int i = 0 ; i < arr.length ; i++)
	    if (arr [i].equals (str))
		rtn = true;

	return rtn;
    }


    public double getTaxThroughLoc (String p)
    {
	int pst = 0;
	for (int i = 0 ; i < province.length ; i++)
	    if (p.equals (province [i]))
		pst = i;

	return tax [pst];

    }


    public double getTax ()
    {
	return taxRate;
    }


    public String getLocation ()
    {
	return loc;
    }


    public void changeLoc (String str)
    {
	loc = str.toUpperCase ();
	temp [0] = str.toUpperCase ();
	init ();
    }


    public void saveToFile () throws IOException
    {
	PrintWriter wtr = new PrintWriter (new FileWriter (path1));

	for (int i = 0 ; i < temp.length ; i++)
	    wtr.println (temp [i]);

	wtr.close ();
    }


    ///////////////////////////////////////////////////////////
    // Find specific Strings
    public String getStr (String str, String start_Tag, String end_Tag, int hint)   // Get string
    {
	/*char first_Char= start_Tag.charAt(0);*/
	String str_Temp = "";
	int start_Pst, end_Pst;

	start_Pst = getPosition (str, start_Tag, hint) + start_Tag.length ();

	if (start_Pst == -1)
	{
	    return "null";
	}
	else
	{
	    end_Pst = getPosition (str, end_Tag, start_Pst);
	    if (end_Pst == -1)
	    {
		return "null";
	    }
	    else
	    {
		str_Temp = "";
		for (int i = start_Pst ; i < end_Pst ; i++)
		    str_Temp += str.charAt (i);

		return str_Temp;
	    }
	}
    }


    public int getPosition (String str, String aimed_Str, int hint)
    {
	int pst = -1;
	char first_Char = aimed_Str.charAt (0);
	String str_Temp = "";

	for (int i = hint ; i < str.length () - aimed_Str.length () ; i++)
	{
	    if (str.charAt (i) == first_Char)
	    {
		for (int j = i ; j < i + aimed_Str.length () ; j++)
		{
		    str_Temp += str.charAt (j) + "";
		}

		if (str_Temp.equals (aimed_Str))
		{
		    pst = i;
		    break;
		}
		else
		{
		    str_Temp = "";
		}
	    }
	} // Finish getting the aimed_Str

	hintPosition = pst;
	// Stdout.println (hintPosition);
	return pst;
    }
}


