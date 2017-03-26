import java.io.*;

public class Stock
{
    private String path;
    private int num_Item;
    private String[] temp;
    private String[] id;
    private String[] name;
    private String[] type;
    private String[] publisher;
    private double[] price;
    private double[] oPrice;
    private int[] quantity;
    private int[] sold;

    private int hintPosition;

    public Stock (String str_Path) throws IOException
    {
	path = str_Path;
	hintPosition = 0;
	//System.out.println ("\naaaaa");
	init ();
    }


    public boolean chkQuantity (int pst, int qua)
    {
	return qua <= quantity [pst];
    }


    public void sellItem (int[] pst, int[] qua)
    {
	for (int i = 0 ; i < pst.length ; i++)
	{
	    sellItem (pst [i], qua [i]);
	}
    }


    public void sellItem (int pst, int qua)
    {
	quantity [pst] -= qua;
	sold [pst] += qua;
    }


    public int getPstByID (String str)
    {
	int p = -1;
	str = str.toUpperCase ();
	for (int i = 0 ; i < num_Item ; i++)
	    if (str.equals (id [i].toUpperCase ()))
		// if (chkSimilarity (str, id [i]))
		p = i;

	return p;
    }


    public int[] getPstsByName (String str)
    {
	int[] p = new int [0];
	str = str.toUpperCase ();
	for (int i = 0 ; i < num_Item ; i++)
	    //if (str.equals (id [i]))
	    if (chkSimilarity (str, name [i].toUpperCase ()))
		p = addArr (p, i);

	return p;
    }


    public int[] getPstsByPublisher (String str)
    {
	int[] p = new int [0];
	for (int i = 0 ; i < num_Item ; i++)
	    //if (str.equals (id [i]))
	    if (chkSimilarity (str, publisher [i]))
		p = addArr (p, i);

	return p;
    }


    public int[] getPstsById (String str)
    {
	int[] p = new int [0];
	for (int i = 0 ; i < num_Item ; i++)
	    //if (str.equals (id [i]))
	    if (chkSimilarity (str, id [i]))
		p = addArr (p, i);

	return p;
    }


    public int[] addArr (int[] arr, int v)
    {
	int[] temp = new int [arr.length + 1];
	for (int i = 0 ; i < arr.length ; i++)
	    temp [i] = arr [i];

	temp [temp.length - 1] = v;

	return temp;
    }


    public boolean chkSimilarity (String keyW, String str)
    {
	boolean rtn = false;
	String temp = "";
	str = str.toUpperCase ();
	keyW = keyW.toUpperCase ();

	for (int i = 0 ; i < str.length () ; i++)
	{
	    if (keyW.charAt (0) == str.charAt (i) && i <= str.length () - keyW.length ())
	    {
		for (int j = 0 ; j < keyW.length () ; j++)
		{
		    temp += str.charAt (i + j);
		}

		if (temp.equals (keyW))
		{
		    rtn = true;
		    break;
		}
		else
		{
		    temp = "";
		}
	    }
	}
	return rtn;
    }


    public String[] getNames (int[] pst)
    {
	String[] arr = new String [pst.length];

	for (int i = 0 ; i < pst.length ; i++)
	{
	    arr [i] = name [pst [i]];
	}

	return arr;
    }


    public boolean setStock (String[] strId, String[] strName, String[] strType, String[] strPub, double[] numP, double[] numOP, int[] numQ, int[] numS)
    {
	name = strName;
	id = strId;
	type = strType;
	publisher = strPub;
	price = numP;
	quantity = numQ;
	sold = numS;
	oPrice = numOP;
	
	temp = new String [name.length];

	for (int i = 0 ; i < name.length ; i++)
	{
	    temp [i] = "id:" + strId + ";" + "name:" + name [i] + ";type:" + type [i] + ";publisher:" + publisher [i] + ";price" + price [i] + ";op:" + oPrice [i] + ";quantity:" + quantity [i] + ";sold:" + sold [i] + ";";
	}

	return true;
    }


    public boolean isIdExist (String str)
    {
	int temp = -1;
	str = str.toUpperCase ();
	//System.out.println (str);
	for (int i = 0 ; i < num_Item ; i++)
	    if (str.equals (id [i].toUpperCase ()))
		return true;

	return false;
    }


    public boolean addItem (String strId, String strName, String strType, String strPub, double numP, double numOp, int numQ)
    {
	int a = 1;
	int intTemp = -1;
	num_Item += a;
	String[] arrTemp = new String [num_Item];
	String[] arrId = new String [num_Item];
	String[] arrName = new String [num_Item];
	String[] arrType = new String [num_Item];
	String[] arrPublisher = new String [num_Item];
	double[] arrPrice = new double [num_Item];
	double[] arrOPrice = new double [num_Item];
	int[] arrQuantity = new int [num_Item];
	int[] arrSold = new int [num_Item];


	for (int i = 0 ; i < num_Item - a ; i++)
	{
	    arrTemp [i] = temp [i];
	    arrId [i] = id [i];
	    arrName [i] = name [i];
	    arrType [i] = type [i];
	    arrPublisher [i] = publisher [i];
	    arrPrice [i] = price [i];
	    arrOPrice [i] = oPrice [i];
	    arrQuantity [i] = quantity [i];
	    arrSold [i] = sold [i];
	}
	arrName [num_Item - 1] = strName;
	arrId [num_Item - 1] = strId;
	arrType [num_Item - 1] = strType;
	arrPublisher [num_Item - 1] = strPub;
	arrPrice [num_Item - 1] = numP;
	arrOPrice [num_Item - 1] = numOp;
	arrQuantity [num_Item - 1] = numQ;
	arrSold [num_Item - 1] = 0;
	arrTemp [num_Item - 1] = "id:" + strId + ";" + "name:" + strName + ";type:" + strType + ";publisher:" + strPub + ";price" + numP + ";op:" + numOp + ";quantity:" + numQ + ";sold:0;";

	temp = arrTemp;
	id = arrId;
	name = arrName;
	type = arrType;
	publisher = arrPublisher;
	price = arrPrice;
	oPrice = arrOPrice;
	quantity = arrQuantity;
	sold = arrSold;

	return true;
    }


    public boolean delItem (String strId)
    {
	String[] arrTemp = new String [num_Item - 1];
	String[] arrId = new String [num_Item - 1];
	String[] arrName = new String [num_Item - 1];
	String[] arrType = new String [num_Item - 1];
	String[] arrPub = new String [num_Item - 1];
	double[] arrPrice = new double [num_Item - 1];
	double[] arrOPrice = new double [num_Item - 1];
	int[] arrQuan = new int [num_Item - 1];
	int[] arrSold = new int [num_Item - 1];

	int pst = -1;
	for (int i = 0 ; i < num_Item ; i++)
	    if (strId.equals (id [i]))
		pst = i;

	if (pst == -1)
	{
	    return false;
	}
	else
	{

	    for (int i = 0 ; i < pst ; i++)
	    {
		arrTemp [i] = temp [i];
		arrId [i] = id [i];
		arrName [i] = name [i];
		arrType [i] = type [i];
		arrPrice [i] = price [i];
		arrOPrice [i] = oPrice [i];
		arrPub [i] = publisher [i];
		arrQuan [i] = quantity [i];
		arrSold [i] = sold [i];
	    }

	    for (int i = pst + 1 ; i < num_Item ; i++)
	    {
		arrTemp [i - 1] = temp [i];
		arrId [i - 1] = id [i];
		arrName [i - 1] = name [i];
		arrType [i - 1] = type [i];
		arrPrice [i - 1] = price [i];
		arrOPrice [i - 1] = oPrice [i];
		arrPub [i - 1] = publisher [i];
		arrQuan [i - 1] = quantity [i];
		arrSold [i - 1] = sold [i];
	    }

	    num_Item--;
	    temp = arrTemp;
	    id = arrId;
	    name = arrName;
	    price = arrPrice;
	    oPrice = arrOPrice;
	    type = arrType;
	    publisher = arrPub;
	    quantity = arrQuan;
	    sold = arrSold;


	    return true;
	}
    }


    public double[] getOriginalPrice ()
    {
	return oPrice;
    }


    public double getOriginalPrice (int i)
    {
	return oPrice [i];
    }


    public String[] getId ()
    {
	return id;
    }


    public String getId (int i)
    {
	return id [i];
    }


    public int getItemNum ()
    {
	return num_Item;
    }


    public String getName (int i)
    {
	return name [i];
    }


    public String[] getName ()
    {
	return name;
    }


    public String getType (int i)
    {
	return type [i];
    }


    public String[] getType ()
    {
	return type;
    }


    public double getPrice (int i)
    {
	return price [i];
    }


    public String[] getPublisher ()
    {
	return publisher;
    }


    public String getPublisher (int i)
    {
	return publisher [i];
    }


    public double[] getPrice ()
    {
	return price;
    }


    public String getPath ()
    {
	return path;
    }


    public int getQuantity (int i)
    {
	return quantity [i];
    }


    public int[] getQuantity ()
    {
	return quantity;
    }


    public String[] getStrQuantity ()
    {
	String[] arr = new String [quantity.length];
	for (int i = 0 ; i < quantity.length ; i++)
	    arr [i] = quantity [i] + "";

	return arr;
    }


    public int getSold (int i)
    {
	return sold [i];
    }


    public String[] getStrSold ()
    {
	String[] arr = new String [sold.length];
	for (int i = 0 ; i < sold.length ; i++)
	    arr [i] = sold [i] + "";

	return arr;
    }


    public int[] getSold ()
    {
	return sold;
    }


    public void init () throws IOException
    {
	String str;
	int i = 0;
	BufferedReader rdr = new BufferedReader (new FileReader (path));

	// get the number of items from the first line of the file
	num_Item = Integer.parseInt (rdr.readLine ());
	temp = new String [num_Item];
	id = new String [num_Item];
	name = new String [num_Item];
	type = new String [num_Item];
	publisher = new String [num_Item];
	price = new double [num_Item];
	oPrice = new double [num_Item];
	quantity = new int [num_Item];
	sold = new int [num_Item];

	// read the file into an array
	while (true)
	{
	    str = rdr.readLine ();
	    if (str != null)
		temp [i++] = str;
	    else
		break;
	}
	rdr.close ();

	// allocate information into each field
	for (int k = 0 ; k < num_Item ; k++)
	{
	    name [k] = getStr (temp [k], "name:", ";", 0);
	    id [k] = getStr (temp [k], "id:", ";", 0);
	    type [k] = getStr (temp [k], "type:", ";", 0);
	    publisher [k] = getStr (temp [k], "publisher:", ";", 0);
	    price [k] = Double.parseDouble (getStr (temp [k], "price:", ";", 0));
	    oPrice [k] = Double.parseDouble (getStr (temp [k], "op:", ";", 0));
	    quantity [k] = Integer.parseInt (getStr (temp [k], "quantity:", ";", 0));
	    sold [k] = Integer.parseInt (getStr (temp [k], "sold:", ";", 0));
	}
    }


    public void saveStock () throws IOException
    {
	String str;
	PrintWriter wtr = new PrintWriter (new FileWriter ("data\\stock\\stock.txt"));
	wtr.println (num_Item);
	//System.out.println (id.length + "\n" + num_Item);
	for (int i = 0 ; i < num_Item ; i++)
	{
	    // System.out.println (oPrice [i]);
	    str = "id:" + id [i] + ";" + "name:" + name [i] + ";type:" + type [i] + ";publisher:" + publisher [i] + ";price:" + price [i] + ";op:" + oPrice [i] + ";quantity:" + quantity [i] + ";sold:" + sold [i] + "; ";
	    wtr.println (str);
	}
	wtr.close ();
    }


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


