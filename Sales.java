import java.io.*;
//import hsa.*;
public class Sales
{
    private int hintPosition;
    private String[] day;
    private String[] month;
    private String[] year;
    private String[] [] item;
    private String[] [] type;
    private int[] [] quantity;
    private double[] [] price;
    private double[] [] oPrice;

    public Sales () throws IOException
    {
	hintPosition = 0;
	init ();
    }


    public String[] getType ()
    {
	String[] tps = new String [0];

	for (int i = 0 ; i < type.length ; i++)
	    for (int j = 0 ; j < type [i].length ; j++)
		if (!isExistInArr (tps, type [i] [j]))
		    tps = addArr (tps, type [i] [j]);

	return tps;
    }


    public String[] getItem ()
    {
	String[] itm = new String [0];

	for (int i = 0 ; i < item.length ; i++)
	    for (int j = 0 ; j < item [i].length ; j++)
		if (!isExistInArr (itm, item [i] [j]))
		    itm = addArr (itm, item [i] [j]);

	return itm;
    }


    public boolean isExistInArr (String[] arr, String value)
    {
	boolean rtn = false;
	for (int i = 0 ; i < arr.length ; i++)
	{
	    if (arr [i].equals (value))
	    {
		rtn = true;
		break;
	    }
	}

	return rtn;
    }


    public String[] getMonthTypes ()
    {
	String[] temp = month;
	month = arrFilter (temp);

	return temp;
    }


    public String[] arrFilter (String[] arr)
    {
	String[] temp = new String [0];
	for (int i = 0 ; i < arr.length ; i++)
	    if (isExistArr (temp, arr [i]))
		temp = addArr (temp, arr [i]);
	return temp;
    }


    public boolean isExistArr (String[] arr, String value)
    {
	boolean rtn = false;
	for (int i = 0 ; i < arr.length ; i++)
	    if (value.equals (arr [i]))
		rtn = true;

	return rtn;
    }


    public String[] getYears ()
    {
	return year;
    }


    public String[] getDays ()
    {
	return day;
    }


    public String[] getMonths ()
    {
	return month;
    }


    //
    public int getGrossQuantityOfDay (String y, String m, String d)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    quan += quantity [i] [j];

	return quan;
    }


    public int getGrossQuantityOfDayByType (String tp, String y, String m, String d)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    if (type [i] [j].toUpperCase ().equals (tp))
			quan += quantity [i] [j];

	return quan;
    }


    public int getGrossQuantityOfDayByName (String n, String y, String m, String d)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    if (item [i] [j].toUpperCase ().equals (n))
			quan += quantity [i] [j];

	return quan;
    }



    //

    public int getGrossQuantityOfMonth (String y, String m)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y) && month [i].equals (m))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    quan += quantity [i] [j];

	return quan;
    }


    public int getGrossQuantityOfMonthByType (String tp, String y, String m)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y) && month [i].equals (m))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    if (type [i] [j].toUpperCase ().equals (tp))
			quan += quantity [i] [j];

	return quan;
    }


    public int getGrossQuantityOfMonthByName (String n, String y, String m)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y) && month [i].equals (m))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    if (item [i] [j].toUpperCase ().equals (n))
			quan += quantity [i] [j];

	return quan;
    }


    //
    public int getGrossQuantityOfYear (String y)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    quan += quantity [i] [j];

	return quan;
    }


    public int getGrossQuantityOfYearByName (String n, String y)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    if (item [i] [j].toUpperCase ().equals (n))
			quan += quantity [i] [j];

	return quan;
    }


    public int getGrossQuantityOfYearByType (String t, String y)
    {
	int quan = 0;
	for (int i = 0 ; i < quantity.length ; i++)
	    if (year [i].equals (y))
		for (int j = 0 ; j < quantity [i].length ; j++)
		    if (type [i] [j].toUpperCase ().equals (t))
			quan += quantity [i] [j];

	return quan;
    }


    //

    // best sales
    public String getBestSalesTypeOfYear (String y)
    {
	String[] tps = getType ();
	int pst = -1; ////////rev
	int quan = 0;
	/*for (int i = 0 ; i < tps.length ; i++)
	    System.out.println (tps [i]);*/
	for (int i = 0 ; i < tps.length ; i++)
	{
	    if (getGrossQuantityOfYearByType (tps [i].toUpperCase (), y) > quan)
	    {
		quan = getGrossQuantityOfYearByType (tps [i].toUpperCase (), y);
		pst = i;
	    }
	}
	if (pst != -1)
	    return tps [pst];
	else
	    return "N\\A";
    }


    public String getBestSalesTypeOfMonth (String y, String m)
    {
	String[] tps = getType ();
	int pst = -1; ////////rev
	int quan = 0;
	/*for (int i = 0 ; i < tps.length ; i++)
	    System.out.println (tps [i]);*/
	for (int i = 0 ; i < tps.length ; i++)
	{
	    if (getGrossQuantityOfMonthByType (tps [i].toUpperCase (), y, m) > quan)
	    {
		quan = getGrossQuantityOfMonthByType (tps [i].toUpperCase (), y, m);
		pst = i;
	    }
	}
	if (pst != -1)
	    return tps [pst];
	else
	    return "N\\A";
    }


    public String getBestSalesTypeOfDay (String y, String m, String d)
    {
	String[] tps = getType ();
	int pst = -1; ////////rev
	int quan = 0;
	/*for (int i = 0 ; i < tps.length ; i++)
	    System.out.println (tps [i]);*/
	for (int i = 0 ; i < tps.length ; i++)
	{
	    if (getGrossQuantityOfDayByType (tps [i].toUpperCase (), y, m, d) > quan)
	    {
		quan = getGrossQuantityOfDayByType (tps [i].toUpperCase (), y, m, d);
		pst = i;
	    }
	}
	if (pst != -1)
	    return tps [pst];
	else
	    return "N\\A";
    }


    //Item
    public String getBestSalesItemOfYear (String y)
    {
	String[] tps = getItem ();
	int pst = -1; ////////rev
	int quan = 0;
	/*for (int i = 0 ; i < tps.length ; i++)
	    System.out.println (tps [i]);*/
	for (int i = 0 ; i < tps.length ; i++)
	{
	    if (getGrossQuantityOfYearByName (tps [i].toUpperCase (), y) > quan)
	    {
		quan = getGrossQuantityOfYearByName (tps [i].toUpperCase (), y);
		pst = i;
	    }
	}
	if (pst != -1)
	    return tps [pst];
	else
	    return "N\\A";
    }


    public String getBestSalesItemOfMonth (String y, String m)
    {
	String[] tps = getItem ();
	int pst = -1; ////////rev
	int quan = 0;
	/*for (int i = 0 ; i < tps.length ; i++)
	    System.out.println (tps [i]);*/
	for (int i = 0 ; i < tps.length ; i++)
	{
	    if (getGrossQuantityOfMonthByName (tps [i].toUpperCase (), y, m) > quan)
	    {
		quan = getGrossQuantityOfMonthByName (tps [i].toUpperCase (), y, m);
		pst = i;
	    }
	}
	if (pst != -1)
	    return tps [pst];
	else
	    return "N\\A";
    }


    public String getBestSalesItemOfDay (String y, String m, String d)
    {
	String[] tps = getItem ();
	int pst = -1; ////////rev
	int quan = 0;
	/*for (int i = 0 ; i < tps.length ; i++)
	    System.out.println (tps [i]);*/
	for (int i = 0 ; i < tps.length ; i++)
	{
	    if (getGrossQuantityOfDayByName (tps [i].toUpperCase (), y, m, d) > quan)
	    {
		quan = getGrossQuantityOfDayByName (tps [i].toUpperCase (), y, m, d);
		pst = i;
	    }
	}
	if (pst != -1)
	    return tps [pst];
	else
	    return "N\\A";
    }


    //get revenue

    public double getRevenueByYear (String y1, String y2)
    {
	double rev = 0;
	int startY = Integer.parseInt (y1);
	int endY = Integer.parseInt (y2);
	int cY;
	for (int i = 0 ; i < year.length ; i++)
	{
	    cY = Integer.parseInt (year [i]);
	    if (cY >= startY && cY <= endY)
		for (int j = 0 ; j < item [i].length ; j++)
		    rev += quantity [i] [j] * price [i] [j];
	}

	return rev;
    }


    public double getRevenueByYear (String y)
    {
	double rev = 0;

	for (int i = 0 ; i < year.length ; i++)
	{
	    if (year [i].equals (y))
		for (int j = 0 ; j < item [i].length ; j++)
		    rev += quantity [i] [j] * price [i] [j];
	}

	return rev;

    }


    // MONTH
    public double getRevenueByMonth (String y, String m1, String m2)
    {
	double rev = 0;
	int startM = Integer.parseInt (m1);
	int endM = Integer.parseInt (m2);
	int cM;

	for (int i = 0 ; i < month.length ; i++)
	{
	    cM = Integer.parseInt (month [i]);
	    if (year [i].equals (y) && cM >= startM && cM <= endM)
		for (int j = 0 ; j < item [i].length ; j++)
		    rev += quantity [i] [j] * price [i] [j];
	}

	return rev;

    }


    public double getRevenueByMonth (String y, String m)
    {
	double rev = 0;

	for (int i = 0 ; i < month.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m))
		for (int j = 0 ; j < item [i].length ; j++)
		    rev += quantity [i] [j] * price [i] [j];
	}

	return rev;

    }


    //day

    public double getRevenueByDay (String y, String m, String d1, String d2)
    {
	double rev = 0;
	int startD = Integer.parseInt (d1);
	int endD = Integer.parseInt (d2);
	int cD;

	for (int i = 0 ; i < day.length ; i++)
	{
	    cD = Integer.parseInt (day [i]);
	    if (year [i].equals (y) && month [i].equals (m) && cD >= startD && cD <= endD)
		for (int j = 0 ; j < item [i].length ; j++)
		    rev += quantity [i] [j] * price [i] [j];
	}

	return rev;

    }


    public double getRevenueByDay (String y, String m, String d)
    {
	double rev = 0;

	for (int i = 0 ; i < day.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
		for (int j = 0 ; j < item [i].length ; j++)
		    rev += quantity [i] [j] * price [i] [j];
	}

	return rev;
    }



    public double getCostByDay (String y, String m, String d)
    {
	double cost = 0;

	for (int i = 0 ; i < day.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
		for (int j = 0 ; j < item [i].length ; j++)
		    cost += quantity [i] [j] * oPrice [i] [j];
	}

	return cost;

    }


    public double getProfitByDay (String y, String m, String d)
    {
	double prof = 0;

	for (int i = 0 ; i < day.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
		for (int j = 0 ; j < item [i].length ; j++)
		    prof += quantity [i] [j] * price [i] [j] - quantity [i] [j] * oPrice [i] [j];
	}

	return prof;

    }


    public double getProfitByMonth (String y, String m)
    {
	double prof = 0;

	for (int i = 0 ; i < month.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m))
		for (int j = 0 ; j < item [i].length ; j++)
		    prof += quantity [i] [j] * price [i] [j] - quantity [i] [j] * oPrice [i] [j];
	}

	return prof;

    }


    public double getProfitByYear (String y)
    {
	double prof = 0;

	for (int i = 0 ; i < month.length ; i++)
	{
	    if (year [i].equals (y))
		for (int j = 0 ; j < item [i].length ; j++)
		    prof += quantity [i] [j] * price [i] [j] - quantity [i] [j] * oPrice [i] [j];
	}

	return prof;

    }


    public double getCostByMonth (String y, String m)
    {
	double cost = 0;

	for (int i = 0 ; i < month.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m))
		for (int j = 0 ; j < item [i].length ; j++)
		    cost += quantity [i] [j] * oPrice [i] [j];
	}

	return cost;

    }


    public double getCostByYear (String y)
    {
	double cost = 0;

	for (int i = 0 ; i < year.length ; i++)
	{
	    if (year [i].equals (y))
		for (int j = 0 ; j < item [i].length ; j++)
		    cost += quantity [i] [j] * oPrice [i] [j];
	}

	return cost;

    }


    //
    public int getPstThroughDate (String y, String m, String d)
    {
	int rtn = -1;
	for (int i = 0 ; i < year.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
	    {
		rtn = i;
		break;
	    }
	}

	return rtn;
    }


    public String[] getItemsByDate (String d)
    {
	int pst = -1;
	for (int i = 0 ; i < year.length ; i++)
	    if (d.equals (year [i] + month [i] + day [i]))
		pst = i;

	return item [pst];
    }


    public int[] getQuanByDate (String d)
    {
	int pst = -1;
	for (int i = 0 ; i < year.length ; i++)
	    if (d.equals (year [i] + month [i] + day [i]))
		pst = i;

	return quantity [pst];
    }


    public void init () throws IOException
    {
	String path = "data\\sales\\dates.txt";
	String str;
	int intTemp = 0, num = 0;
	BufferedReader rdr = new BufferedReader (new FileReader (path));
	while (true)
	{
	    str = rdr.readLine ();
	    if (str == null)
		break;
	    else
		intTemp++;
	}
	rdr.close ();

	day = new String [intTemp];
	month = new String [intTemp];
	year = new String [intTemp];
	quantity = new int [intTemp] [];
	price = new double [intTemp] [];
	oPrice = new double [intTemp] [];
	item = new String [intTemp] [];
	type = new String [intTemp] [];
	rdr = new BufferedReader (new FileReader (path));

	for (int i = 0 ; i < intTemp ; i++)
	{
	    str = rdr.readLine ();
	    year [i] = getStr (str, "y:", ";", 0);
	    month [i] = getStr (str, "m:", ";", 0);
	    day [i] = getStr (str, "d:", ";", 0);
	}
	rdr.close ();

	for (int i = 0 ; i < intTemp ; i++)
	{
	    num = 0;
	    path = "data\\sales\\" + year [i] + month [i] + day [i] + ".txt";
	    //Stdout.println (path);
	    rdr = new BufferedReader (new FileReader (path));
	    while (true)
	    {
		str = rdr.readLine ();
		if (str == null)
		    break;
		else
		    num++;
	    }
	    rdr.close ();

	    rdr = new BufferedReader (new FileReader (path));
	    item [i] = new String [num];
	    type [i] = new String [num];
	    quantity [i] = new int [num];
	    price [i] = new double [num];
	    oPrice [i] = new double [num];

	    for (int j = 0 ; j < num ; j++)
	    {
		str = rdr.readLine ();
		item [i] [j] = getStr (str, "n:", ";", 0);
		type [i] [j] = getStr (str, "t:", ";", 0);
		quantity [i] [j] = Integer.parseInt (getStr (str, "q:", ";", 0));
		price [i] [j] = Double.parseDouble (getStr (str, "p:", ";", 0));
		oPrice [i] [j] = Double.parseDouble (getStr (str, "op:", ";", 0));
	    }
	    rdr.close ();
	} //
    }


    public void addSales (String[] arrName, String arrType[], int[] quan, double[] prc, double[] oPrc, String y, String m, String d)
    {
	for (int i = 0 ; i < arrName.length ; i++)
	    add (arrName [i], arrType [i], quan [i], prc [i], oPrc [i], y, m, d);
    }


    public void add (String itm, String typ, int q, double p, double op, String y, String m, String d)
    {
	int[] intArr;
	String[] strArr, arrType;
	double[] dblArr, dblArr4;
	int[] [] intArr2;
	String[] [] strArr2, arrType2;
	double[] [] dblArr2, dblArr3;

	int pst;
	//year
	if (!isDateExist (y, m, d))
	{
	    strArr = new String [year.length + 1];
	    for (int i = 0 ; i < year.length ; i++)
		strArr [i] = year [i];
	    strArr [strArr.length - 1] = y;
	    year = strArr;

	    strArr = new String [month.length + 1];
	    for (int i = 0 ; i < month.length ; i++)
		strArr [i] = month [i];
	    strArr [strArr.length - 1] = m;
	    month = strArr;

	    strArr = new String [day.length + 1];
	    for (int i = 0 ; i < day.length ; i++)
		strArr [i] = day [i];
	    strArr [strArr.length - 1] = d;
	    day = strArr;

	    /* for (int i = 0 ; i < month.length ; i++)
		 Stdout.println (year [i] + month [i] + day [i]);*/

	    pst = day.length;
	    strArr2 = item;
	    arrType2 = type;
	    intArr2 = quantity;
	    dblArr2 = price;
	    dblArr3 = oPrice;

	    type = new String [arrType2.length + 1] [];
	    item = new String [strArr2.length + 1] [];
	    quantity = new int [strArr2.length + 1] [];
	    price = new double [strArr2.length + 1] [];
	    oPrice = new double [strArr2.length + 1] [];

	    for (int i = 0 ; i < item.length - 1 ; i++)
	    {
		item [i] = strArr2 [i];
		type [i] = arrType2 [i];
		quantity [i] = intArr2 [i];
		price [i] = dblArr2 [i];
		oPrice [i] = dblArr3 [i];
	    }

	    item [item.length - 1] = new String [1];
	    type [item.length - 1] = new String [1];
	    quantity [item.length - 1] = new int [1];
	    price [item.length - 1] = new double [1];
	    oPrice [item.length - 1] = new double [1];

	    item [item.length - 1] [0] = itm;
	    type [item.length - 1] [0] = typ;
	    quantity [item.length - 1] [0] = q;
	    price [item.length - 1] [0] = p;
	    oPrice [item.length - 1] [0] = op;
	}
	else
	{
	    pst = getPstThroughDate (y, m, d);

	    arrType = new String [item [pst].length + 1];
	    strArr = new String [item [pst].length + 1];
	    dblArr = new double [item [pst].length + 1];
	    dblArr4 = new double [item [pst].length + 1];
	    intArr = new int [item [pst].length + 1];

	    for (int i = 0 ; i < item [pst].length ; i++)
		strArr [i] = item [pst] [i];

	    for (int i = 0 ; i < item [pst].length ; i++)
		arrType [i] = type [pst] [i];

	    for (int i = 0 ; i < quantity [pst].length ; i++)
		intArr [i] = quantity [pst] [i];

	    for (int i = 0 ; i < price [pst].length ; i++)
		dblArr [i] = price [pst] [i];

	    for (int i = 0 ; i < oPrice [pst].length ; i++)
		dblArr4 [i] = oPrice [pst] [i];

	    arrType [arrType.length - 1] = typ;
	    strArr [strArr.length - 1] = itm;
	    intArr [intArr.length - 1] = q;
	    dblArr [dblArr.length - 1] = p;
	    dblArr4 [dblArr.length - 1] = op;

	    type [pst] = arrType;
	    item [pst] = strArr;
	    quantity [pst] = intArr;
	    price [pst] = dblArr;
	    oPrice [pst] = dblArr4;
	}
    }


    public boolean isDateExist (String y, String m, String d)
    {
	boolean rtn = false;
	for (int i = 0 ; i < year.length ; i++)
	{
	    if (year [i].equals (y) && month [i].equals (m) && day [i].equals (d))
	    {
		rtn = true;
		break;
	    }
	}

	return rtn;
    }


    public void saveSales () throws IOException
    {
	String path = "data\\sales\\dates.txt";
	PrintWriter wtr = new PrintWriter (new FileWriter (path));

	/* for (int i = 0 ; i < month.length ; i++)
	     Stdout.println (year [i] + month [i] + day [i]);*/

	for (int i = 0 ; i < day.length ; i++)
	    wtr.println ("y:" + year [i] + ";m:" + month [i] + ";d:" + day [i] + "; ");
	wtr.close ();

	for (int i = 0 ; i < day.length ; i++)
	{
	    path = "data\\sales\\" + year [i] + month [i] + day [i] + ".txt";
	    //Stdout.println (year [i] + month [i] + day [i] + ".txt");
	    wtr = new PrintWriter (new FileWriter (path));
	    for (int j = 0 ; j < item [i].length ; j++)
	    {
		wtr.println ("n:" + item [i] [j] + ";t:" + type [i] [j] + ";q:" + quantity [i] [j] + ";p:" + price [i] [j] + ";op:" + oPrice [i] [j] + "; ");
	    }
	    wtr.close ();
	}
    }


    public double[] addArr (double[] arr, double value)
    {
	double[] temp = new double [arr.length + 1];
	for (int i = 0 ; i < arr.length ; i++)
	    temp [i] = arr [i];
	temp [temp.length - 1] = value;

	return temp;
    }


    public int[] addArr (int[] arr, int value)
    {
	int[] temp = new int [arr.length + 1];
	for (int i = 0 ; i < arr.length ; i++)
	    temp [i] = arr [i];
	temp [temp.length - 1] = value;

	return temp;
    }


    public String[] addArr (String[] arr, String value)
    {
	String[] temp = new String [arr.length + 1];
	for (int i = 0 ; i < arr.length ; i++)
	    temp [i] = arr [i];
	temp [temp.length - 1] = value;

	return temp;
    }


    /* public int[] getPstByMonth (String y, String m)
     {
	 int[] pst = new int [0];
	 String str1, str2;
	 for (int i = 0 ; i < item.length ; i++)
	     if ((year [i] + month [i]).equals (y + m))
		 pst = addArr (pst, i);

	 return pst;
     }


     public double getRevenue (int pst)
     {
	 double rev = 0;

     }*/


    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    // Find specific Strings
    public String getStr (String str, String start_Tag, String end_Tag, int hint)   // Get string
    {
	//char first_Char= start_Tag.charAt(0);
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
