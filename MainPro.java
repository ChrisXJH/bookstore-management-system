// XU JIANHAO 2015081081
// ICS4U Period 1
// Final Project

//import hsa.Stdin;
//import hsa.Stdout;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.*;
import hsa.*;

public class MainPro
{
    private static final int item_Menu = 11;
    private static final String folder_Menu = "data\\menu";
    private static final String folder_Accounts = "data\\accounts";
    private static final String folder_Stock = "data\\stock";
    private static final int index_Align = 26;
    private static int hintPosition; // getStr
    private static int index_Account;
    private static String currentMenu;
    private static String year;
    private static String month;
    private static String day;
    private static String[] menu_Arr;
    private static FMenu[] menu = new FMenu [item_Menu];
    private static Account[] account;
    private static Tax tax;
    private static Stock stock;
    private static Sales sales;

    public static void main (String[] args) throws IOException
    {
	hintPosition = 0;
	accountInit ();
	year = getYear ();
	month = getMonth ();
	day = getDay ();

	/*while (!signIn ())
	{
	    pt ("\nSign in failed!\n");
	}*/

	menuInit ();
	stockInit ();
	salesInit ();
	taxInit ();
	runCmd ("MAIN");
    } // main method


    public static void taxInit () throws IOException
    {
	tax = new Tax ();
    }


    public static void runCmd (String cmd) throws IOException
    {
	if (cmd.equals ("QUIT"))
	{
	    quit ();
	}
	else if (cmd.equals ("CHANGE PASSWORD"))
	{
	    changePsw ();
	}
	else if (cmd.equals ("ADD ACCOUNT"))
	{
	    addAccount ();
	}
	else if (cmd.equals ("SHOW ACCOUNTS"))
	{
	    showAccounts ();
	}
	else if (cmd.equals ("SHOW STOCK"))
	{
	    showStock ();
	}
	else if (cmd.equals ("ADD ITEM"))
	{
	    addItem ();
	}
	else if (cmd.equals ("DELETE ITEM"))
	{
	    delItem ();
	}
	else if (cmd.equals ("EDIT ITEM"))
	{
	    editItem ();
	}
	else if (cmd.equals ("SALES MODE"))
	{
	    searchById ();
	}
	else if (cmd.equals ("SHOW REVENUE BY MONTH"))
	{
	    showRevenueByMonth ();
	}
	else if (cmd.equals ("SHOW REVENUE BY YEAR"))
	{
	    showRevenueByYear ();
	}
	else if (cmd.equals ("SHOW REVENUE BY DAY"))
	{
	    showRevenueByDay ();
	}
	else if (cmd.equals ("BEST SALES OF DAY"))
	{
	    bestSalesOfDay ();
	}
	else if (cmd.equals ("BEST SALES OF MONTH"))
	{
	    bestSalesOfMonth ();
	}
	else if (cmd.equals ("BEST SALES OF YEAR"))
	{
	    bestSalesOfYear ();
	}
	else if (cmd.equals ("BY NAMES (FROM A TO Z)"))
	{
	    sortNamesInRisingAlpOrder ();
	}
	else if (cmd.equals ("BY NAMES (FROM Z TO A)"))
	{
	    sortNamesInDecliningAlpOrder ();
	}
	else if (cmd.equals ("BY PRICES (FROM LOW TO HIGH)"))
	{
	    sortPriceInRisingOrder ();
	}
	else if (cmd.equals ("BY PRICES (FROM HIGH TO LOW)"))
	{
	    sortPriceInDecliningOrder ();
	}
	else if (cmd.equals ("THROUGH NAME"))
	{
	    searchThroughName ();
	}
	else if (cmd.equals ("THROUGH ID"))
	{
	    searchThroughId ();
	}
	else if (cmd.equals ("THROUGH PUBLISHER"))
	{
	    searchThroughPublisher ();
	}
	else if (cmd.equals ("SHOW ECONOMIC BY DAY"))
	{
	    showEconomicByDay ();
	}
	else if (cmd.equals ("SHOW ECONOMIC BY MONTH"))
	{
	    showEconomicByMonth ();
	}
	else if (cmd.equals ("SHOW ECONOMIC BY YEAR"))
	{
	    showEconomicByYear ();
	}
	else if (cmd.equals ("CHANGE GEOGRAPHIC LOCATION"))
	{
	    changeLoc ();
	}
	else
	{
	    for (int i = 0 ; i < menu.length ; i++)
		if (menu [i].getId ().toUpperCase ().equals (cmd))
		    showMenu (cmd);
	}
    }


    public static void changeLoc () throws IOException
    {
	String loc;
	pt ("Enter the new location(e.g BC)--> ");
	loc = Stdin.readLine ();
	tax.changeLoc (loc);
	msg ("Succeeded in changing the location!", "SETTINGS");
    }


    public static void ptArr (String[] arr)
    {
	for (int i = 0 ; i < arr.length ; i++)
	    Stdout.println (arr [i]);
    }


    public static boolean isExistArr (String[] arr, String value)
    {
	boolean rtn = false;
	for (int i = 0 ; i < arr.length ; i++)
	    if (value.equals (arr [i]))
		rtn = true;

	return rtn;
    }


    public static void showEconomicByYear () throws IOException
    {
	int num = stock.getItemNum ();
	int col = 30;
	char select;
	String strTemp;
	int intTemp;
	int indexAlign = index_Align / 3;
	double cost, rev, profit;
	//String temp;
	String[] year = sales.getYears ();
	String[] tempY = new String [0];

	ptLoop ("\n", 12);
	Stdout.println ();
	ptBlank (indexAlign);
	ptGrid ("Date", col / 2);
	ptGrid ("Cost($)", col / 2);
	ptGrid ("Revenue($)", col / 2);
	ptGrid ("Profit($)", col / 2);
	Stdout.println ();
	ptLoop ("-", col * 2, indexAlign);
	Stdout.println ();
	//ptArr (year);
	//ptArr (month);
	for (int i = 0 ; i < year.length ; i++)
	{
	    if (!isExistArr (tempY, year [i]))
	    {
		tempY = addArr (tempY, year [i]);
		cost = sales.getCostByYear (year [i]);
		rev = sales.getRevenueByYear (year [i]);
		profit = rev - cost;
		ptBlank (indexAlign);
		ptGrid (year [i], col / 2);
		ptGrid (twoDecPlaces (cost), col / 2);
		ptGrid (twoDecPlaces (rev), col / 2);
		ptGrid (twoDecPlaces (profit), col / 2);
		Stdout.println ();
	    }
	}
	ptLoop ("\n", 9);
	pause ();
    }



    public static void showEconomicByMonth () throws IOException
    {
	int num = stock.getItemNum ();
	int col = 30;
	char select;
	String strTemp;
	int intTemp;
	int indexAlign = index_Align / 3;
	double cost, rev, profit;
	//String temp;
	String[] month = sales.getMonths ();
	String[] year = sales.getYears ();
	String[] tempY = new String [0];
	String[] tempM = new String [0];

	ptLoop ("\n", 12);
	Stdout.println ();
	ptBlank (indexAlign);
	ptGrid ("Date", col / 2);
	ptGrid ("Cost($)", col / 2);
	ptGrid ("Revenue($)", col / 2);
	ptGrid ("Profit($)", col / 2);
	Stdout.println ();
	ptLoop ("-", col * 2, indexAlign);
	Stdout.println ();
	//ptArr (year);
	//ptArr (month);
	for (int i = 0 ; i < year.length ; i++)
	{
	    if (!(isExistArr (tempY, year [i]) && isExistArr (tempM, month [i])))
	    {
		tempY = addArr (tempY, year [i]);
		tempM = addArr (tempM, month [i]);
		cost = sales.getCostByMonth (year [i], month [i]);
		rev = sales.getRevenueByMonth (year [i], month [i]);
		profit = rev - cost;
		ptBlank (indexAlign);
		ptGrid (year [i] + "/" + month [i], col / 2);
		ptGrid (twoDecPlaces (cost), col / 2);
		ptGrid (twoDecPlaces (rev), col / 2);
		ptGrid (twoDecPlaces (profit), col / 2);
		Stdout.println ();
	    }
	}
	ptLoop ("\n", 9);
	pause ();
    }


    public static void showEconomicByDay () throws IOException
    {
	int num = stock.getItemNum ();
	int col = 30;
	char select;
	String strTemp;
	int intTemp;
	int indexAlign = index_Align / 3;
	double cost, rev, profit;
	String[] day = sales.getDays ();
	String[] month = sales.getMonths ();
	String[] year = sales.getYears ();
	ptLoop ("\n", 12);
	Stdout.println ();
	ptBlank (indexAlign);
	ptGrid ("Date", col / 2);
	ptGrid ("Cost($)", col / 2);
	ptGrid ("Revenue($)", col / 2);
	ptGrid ("Profit($)", col / 2);
	Stdout.println ();
	ptLoop ("-", col * 2, indexAlign);
	Stdout.println ();

	for (int i = 0 ; i < day.length ; i++)
	{
	    cost = sales.getCostByDay (year [i], month [i], day [i]);
	    rev = sales.getRevenueByDay (year [i], month [i], day [i]);
	    profit = rev - cost;
	    ptBlank (indexAlign);
	    ptGrid (year [i] + "/" + month [i] + "/" + day [i], col / 2);
	    ptGrid (twoDecPlaces (cost), col / 2);
	    ptGrid (twoDecPlaces (rev), col / 2);
	    ptGrid (twoDecPlaces (profit), col / 2);
	    Stdout.println ();
	}

	ptLoop ("\n", 5);
	pause ();
    }


    public static String twoDecPlaces (double num)
    {
	num *= 100;
	num = Math.round (num);
	num /= 100;

	return num + "";
    }


    public static void showSearchItem (int[] psts) throws IOException
    {
	int num = stock.getItemNum ();
	int col = 20;
	char select;
	String strTemp;
	int intTemp;
	int indexAlign = index_Align / 3;

	do
	{
	    ptLoop ("\n", 15);
	    Stdout.println ();
	    ptBlank (indexAlign);
	    ptGrid ("ID", col / 2);
	    ptGrid ("Name", col);
	    ptGrid ("Type", col / 2);
	    ptGrid ("Price", col / 2);
	    ptGrid ("Quan", col / 2);
	    ptGrid ("Sold", col / 2);
	    Stdout.println ();
	    ptLoop ("-", col * 3 + col / 2 - 5, indexAlign);
	    Stdout.println ();

	    for (int i = 0 ; i < psts.length ; i++)
	    {
		ptBlank (indexAlign);
		ptGrid (stock.getId (psts [i]), col / 2);
		ptGrid (stock.getName (psts [i]), col);
		ptGrid (stock.getType (psts [i]), col / 2);
		ptGrid ("$" + stock.getPrice (psts [i]), col / 2);
		ptGrid (stock.getQuantity (psts [i]) + "", col / 2);
		ptGrid (stock.getSold (psts [i]) + "", col / 2);
		Stdout.println ();
	    }
	    ptLoop ("\n", 9);
	    pt ("Enter ID for details,");
	    Stdout.println ();
	    pt ("  enter '0' to go back--> ");
	    strTemp = Stdin.readLine ();

	    if (strTemp.equals ("0"))
	    {
		break;
	    }
	    else
	    {
		if (stock.isIdExist (strTemp))
		{
		    intTemp = stock.getPstByID (strTemp);
		    ptLoop ("\n", 9);
		    pt ("DETTAILS", indexAlign);
		    Stdout.println ();
		    ptLoop ("-", col * 3, indexAlign);
		    Stdout.println ();
		    pt ("ID: " + stock.getId (intTemp) + "\n", indexAlign);
		    pt ("Name: " + stock.getName (intTemp) + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Type: " + stock.getType (intTemp) + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Price: " + stock.getPrice (intTemp) + "\n", indexAlign);
		    pt ("Original Price: " + stock.getOriginalPrice (intTemp) + "\n", indexAlign);
		    //Stdout.println ();s
		    pt ("Publisher: " + stock.getPublisher (intTemp) + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Quantity: " + stock.getQuantity (intTemp) + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Sold: " + stock.getSold (intTemp) + "\n", indexAlign);
		    ptLoop ("\n", 9);
		    pause (" ");
		}
		else
		{
		    msg ("The ID entered does not exist!");
		}

	    }
	}
	while (!strTemp.equals ("0"));
	//debug(4.16)
	runCmd (currentMenu);
    }


    public static void searchThroughPublisher () throws IOException
    {

	String name;
	int[] psts;

	pt ("Enter a publisher(no need to be exact. e.g per): ", 8);
	name = Stdin.readLine ();
	psts = stock.getPstsByPublisher (name);
	showSearchItem (psts);
    }


    public static void searchThroughName () throws IOException
    {
	String name;
	int[] psts;

	pt ("Enter a name(no need to be exact. e.g calculus): ", 8);
	name = Stdin.readLine ();
	psts = stock.getPstsByName (name);
	showSearchItem (psts);
    }


    public static void searchThroughId () throws IOException
    {
	String name;
	int[] psts;

	pt ("Enter an id(no need to be exact. e.g 5): ", 15);
	name = Stdin.readLine ();
	psts = stock.getPstsById (name);
	showSearchItem (psts);
    }


    public static void sortPriceInRisingOrder () throws IOException
    {
	String[] id = stock.getId ();
	String[] name = stock.getName ();
	String[] type = stock.getType ();
	String[] publisher = stock.getPublisher ();
	double[] price = stock.getPrice ();
	double[] oPrice = stock.getOriginalPrice ();
	int[] quantity = stock.getQuantity ();
	int[] sold = stock.getSold ();
	String strTemp;
	double dblTemp;
	int intTemp;

	for (int i = 0 ; i < price.length ; i++)
	{
	    for (int j = 0 ; j < price.length - i - 1 ; j++)
	    {
		if (price [j] > price [j + 1])
		{
		    strTemp = name [j];
		    name [j] = name [j + 1];
		    name [j + 1] = strTemp;

		    strTemp = id [j];
		    id [j] = id [j + 1];
		    id [j + 1] = strTemp;

		    strTemp = type [j];
		    type [j] = type [j + 1];
		    type [j + 1] = strTemp;

		    strTemp = publisher [j];
		    publisher [j] = publisher [j + 1];
		    publisher [j + 1] = strTemp;

		    dblTemp = price [j];
		    price [j] = price [j + 1];
		    price [j + 1] = dblTemp;

		    intTemp = quantity [j];
		    quantity [j] = quantity [j + 1];
		    quantity [j + 1] = intTemp;

		    intTemp = sold [j];
		    sold [j] = sold [j + 1];
		    sold [j + 1] = intTemp;

		    dblTemp = oPrice [j];
		    oPrice [j] = oPrice [j + 1];
		    oPrice [j + 1] = dblTemp;

		}
	    }
	}

	stock.setStock (id, name, type, publisher, price, oPrice, quantity, sold);
	showStockItem ();
	pause ();
    }


    public static void sortPriceInDecliningOrder () throws IOException
    {
	String[] id = stock.getId ();
	String[] name = stock.getName ();
	String[] type = stock.getType ();
	String[] publisher = stock.getPublisher ();
	double[] price = stock.getPrice ();
	double[] oPrice = stock.getOriginalPrice ();
	int[] quantity = stock.getQuantity ();
	int[] sold = stock.getSold ();
	String strTemp;
	double dblTemp;
	int intTemp;

	for (int i = 0 ; i < price.length ; i++)
	{
	    for (int j = 0 ; j < price.length - i - 1 ; j++)
	    {
		if (price [j] < price [j + 1])
		{
		    strTemp = name [j];
		    name [j] = name [j + 1];
		    name [j + 1] = strTemp;

		    strTemp = id [j];
		    id [j] = id [j + 1];
		    id [j + 1] = strTemp;

		    strTemp = type [j];
		    type [j] = type [j + 1];
		    type [j + 1] = strTemp;

		    strTemp = publisher [j];
		    publisher [j] = publisher [j + 1];
		    publisher [j + 1] = strTemp;

		    dblTemp = price [j];
		    price [j] = price [j + 1];
		    price [j + 1] = dblTemp;

		    intTemp = quantity [j];
		    quantity [j] = quantity [j + 1];
		    quantity [j + 1] = intTemp;

		    intTemp = sold [j];
		    sold [j] = sold [j + 1];
		    sold [j + 1] = intTemp;

		    dblTemp = oPrice [j];
		    oPrice [j] = oPrice [j + 1];
		    oPrice [j + 1] = dblTemp;
		}
	    }
	}

	stock.setStock (id, name, type, publisher, price, oPrice, quantity, sold);
	showStockItem ();
	pause ();
    }


    public static void sortNamesInRisingAlpOrder () throws IOException
    {
	String[] id = stock.getId ();
	String[] name = stock.getName ();
	String[] type = stock.getType ();
	String[] publisher = stock.getPublisher ();
	double[] price = stock.getPrice ();
	double[] oPrice = stock.getOriginalPrice ();
	int[] quantity = stock.getQuantity ();
	int[] sold = stock.getSold ();
	String strTemp;
	double dblTemp;
	int intTemp;

	for (int i = 0 ; i < name.length ; i++)
	{
	    for (int j = 0 ; j < name.length - i - 1 ; j++)
	    {
		if (name [j].toUpperCase ().compareTo (name [j + 1].toUpperCase ()) > 0)
		{
		    strTemp = name [j];
		    name [j] = name [j + 1];
		    name [j + 1] = strTemp;

		    strTemp = id [j];
		    id [j] = id [j + 1];
		    id [j + 1] = strTemp;

		    strTemp = type [j];
		    type [j] = type [j + 1];
		    type [j + 1] = strTemp;

		    strTemp = publisher [j];
		    publisher [j] = publisher [j + 1];
		    publisher [j + 1] = strTemp;

		    dblTemp = price [j];
		    price [j] = price [j + 1];
		    price [j + 1] = dblTemp;

		    dblTemp = oPrice [j];
		    oPrice [j] = oPrice [j + 1];
		    oPrice [j + 1] = dblTemp;

		    intTemp = quantity [j];
		    quantity [j] = quantity [j + 1];
		    quantity [j + 1] = intTemp;

		    intTemp = sold [j];
		    sold [j] = sold [j + 1];
		    sold [j + 1] = intTemp;
		}
	    }
	}

	stock.setStock (id, name, type, publisher, price, oPrice, quantity, sold);
	showStockItem ();
	pause ();
    }


    public static void sortNamesInDecliningAlpOrder () throws IOException
    {
	String[] id = stock.getId ();
	String[] name = stock.getName ();
	String[] type = stock.getType ();
	String[] publisher = stock.getPublisher ();
	double[] price = stock.getPrice ();
	double[] oPrice = stock.getOriginalPrice ();
	int[] quantity = stock.getQuantity ();
	int[] sold = stock.getSold ();
	String strTemp;
	double dblTemp;
	int intTemp;

	for (int i = 0 ; i < name.length ; i++)
	{
	    for (int j = 0 ; j < name.length - i - 1 ; j++)
	    {
		if (name [j].toUpperCase ().compareTo (name [j + 1].toUpperCase ()) < 0)
		{
		    strTemp = name [j];
		    name [j] = name [j + 1];
		    name [j + 1] = strTemp;

		    strTemp = id [j];
		    id [j] = id [j + 1];
		    id [j + 1] = strTemp;

		    strTemp = type [j];
		    type [j] = type [j + 1];
		    type [j + 1] = strTemp;

		    strTemp = publisher [j];
		    publisher [j] = publisher [j + 1];
		    publisher [j + 1] = strTemp;

		    dblTemp = price [j];
		    price [j] = price [j + 1];
		    price [j + 1] = dblTemp;

		    intTemp = quantity [j];
		    quantity [j] = quantity [j + 1];
		    quantity [j + 1] = intTemp;

		    intTemp = sold [j];
		    sold [j] = sold [j + 1];
		    sold [j + 1] = intTemp;

		    dblTemp = oPrice [j];
		    oPrice [j] = oPrice [j + 1];
		    oPrice [j + 1] = dblTemp;
		}
	    }
	}

	stock.setStock (id, name, type, publisher, price, oPrice, quantity, sold);
	showStockItem ();
	pause ();
    }


    public static void bestSalesOfDay () throws IOException
    {
	String y, m, d, strItm, strTyp;
	int quanItm;
	pt ("Enter year(e.g 2016)--> ");
	y = Stdin.readLine ();
	pt ("Enter month(e.g 01)--> ");
	m = Stdin.readLine ();
	pt ("Enter day(e.g 01)--> ");
	d = Stdin.readLine ();
	strItm = sales.getBestSalesItemOfDay (y, m, d);
	strTyp = sales.getBestSalesTypeOfDay (y, m, d);
	quanItm = sales.getGrossQuantityOfDayByName (strItm.toUpperCase (), y, m, d);
	ptLoop ("\n", 15);
	pt ("Best sales item: " + strItm);
	Stdout.println ();
	pt ("Sold out: " + quanItm);
	Stdout.println ("\n");
	pt ("Best sales type: " + strTyp);
	ptLoop ("\n", 9);
	pause ();
    }


    public static void bestSalesOfMonth () throws IOException
    {
	String y, m, strItm, strTyp;
	int quanItm;
	pt ("Enter year(e.g 2016)--> ");
	y = Stdin.readLine ();
	pt ("Enter month(e.g 01)--> ");
	m = Stdin.readLine ();
	strItm = sales.getBestSalesItemOfMonth (y, m);
	strTyp = sales.getBestSalesTypeOfMonth (y, m);
	quanItm = sales.getGrossQuantityOfMonthByName (strItm.toUpperCase (), y, m);
	ptLoop ("\n", 15);
	pt ("Best sales item: " + strItm);
	Stdout.println ();
	pt ("Sold out: " + quanItm);
	Stdout.println ("\n");
	pt ("Best sales type: " + strTyp);
	ptLoop ("\n", 9);
	pause ();
    }


    public static void bestSalesOfYear () throws IOException
    {
	String y, strItm, strTyp;
	int quanItm;
	pt ("Enter year(e.g 2016)--> ");
	y = Stdin.readLine ();
	strItm = sales.getBestSalesItemOfYear (y);
	strTyp = sales.getBestSalesTypeOfYear (y);
	quanItm = sales.getGrossQuantityOfYearByName (strItm.toUpperCase (), y);
	ptLoop ("\n", 15);
	pt ("Best sales item: " + strItm);
	Stdout.println ();
	pt ("Sold out: " + quanItm);
	Stdout.println ("\n");
	pt ("Best sales type: " + strTyp);
	ptLoop ("\n", 9);
	pause ();
    }


    public static void drawChar (String cap, double value, int interval)
    {
	pt (cap + " ");
	ptLoop ("*", (int) Math.round (value / interval), 0);
	Stdout.print (" |");
	Stdout.print (value / interval, 0, 2);
	Stdout.println ("|");
    }


    public static void showRevenueByYear () throws IOException
    {
	//int[] pst = sales.getPstByMonth ("2016", "04");
	double rev;
	String y1, y2, str;
	int startYear, endYear;
	int interval = 1000;

	pt ("Enter the starting year(e.g 2015)--> ");
	y1 = Stdin.readLine ();
	pt ("Enter the ending year(e.g 2016)--> ");
	y2 = Stdin.readLine ();
	startYear = Integer.parseInt (y1);
	endYear = Integer.parseInt (y2);
	ptLoop ("\n", 15);
	pt ("Revenue: " + y1 + "-" + y2 + "\t* $" + interval + "\n");
	ptLoop ("-", 35);
	Stdout.println ();
	for (int i = startYear ; i <= endYear ; i++)
	{
	    if (i < 10)
		str = "0" + i;
	    else
		str = i + "";
	    rev = sales.getRevenueByYear (str);
	    drawChar (str, rev, interval);
	}

	ptLoop ("\n", 12);
	pause ();
    }


    public static void showRevenueByMonth () throws IOException
    {
	//int[] pst = sales.getPstByMonth ("2016", "04");
	double rev;
	String y, month1, month2, str;
	int startMonth, endMonth;
	int interval = 1000;
	pt ("Enter the year(e.g 2015)--> ");
	y = Stdin.readLine ();
	pt ("Enter the starting month(e.g 04)--> ");
	month1 = Stdin.readLine ();
	pt ("Enter the ending month(e.g 04)--> ");
	month2 = Stdin.readLine ();
	startMonth = Integer.parseInt (month1);
	endMonth = Integer.parseInt (month2);
	ptLoop ("\n", 15);
	pt ("Revenue: " + y + " " + month1 + "-" + month2 + "\t* $" + interval + "\n");
	ptLoop ("-", 35);
	Stdout.println ();
	for (int i = startMonth - 1 ; i <= endMonth ; i++)
	{
	    if (i < 10)
		str = "0" + i;
	    else
		str = i + "";
	    rev = sales.getRevenueByMonth (y, str);
	    drawChar (str, rev, interval);
	}

	ptLoop ("\n", 12);
	pause ();
    }


    public static void showRevenueByDay () throws IOException
    {
	//int[] pst = sales.getPstByMonth ("2016", "04");
	double rev;
	String y, m, d1, d2, str;
	int startDay, endDay;
	int interval = 100;

	pt ("Enter the year(e.g 2015)--> ");
	y = Stdin.readLine ();
	pt ("Enter the month(e.g 04)--> ");
	m = Stdin.readLine ();
	pt ("Enter the starting day(e.g 06)--> ");
	d1 = Stdin.readLine ();
	pt ("Enter the ending day(e.g 11)--> ");
	d2 = Stdin.readLine ();
	startDay = Integer.parseInt (d1);
	endDay = Integer.parseInt (d2);
	ptLoop ("\n", 15);
	pt ("Revenue: " + y + "-" + m + " " + d1 + "-" + d2 + "\t* $" + interval + "\n");
	ptLoop ("-", 35);
	Stdout.println ();
	for (int i = startDay ; i <= endDay ; i++)
	{
	    if (i < 10)
		str = "0" + i;
	    else
		str = i + "";
	    rev = sales.getRevenueByDay (y, m, str);
	    drawChar (str, rev, interval);
	}

	ptLoop ("\n", 9);
	pause ();
    }


    public static void salesInit () throws IOException
    {
	sales = new Sales ();
    }


    public static String getYear ()
    {
	DateFormat df = new SimpleDateFormat ("yyyy");
	Date d = new Date ();
	String str = df.format (d);

	return str;
    }


    public static String getMonth ()
    {
	DateFormat df = new SimpleDateFormat ("MM");
	Date d = new Date ();
	String str = df.format (d);

	return str;
    }


    public static String getDay ()
    {
	DateFormat df = new SimpleDateFormat ("dd");
	Date d = new Date ();
	String str = df.format (d);

	return str;
    }


    public static void searchById () throws IOException
    {
	String strId;
	String strTemp;
	int intQua, alignIndex = index_Align - index_Align / 3;
	char select = 'N';
	int intPst, col = 15;
	String[] arrName = new String [0];
	int[] pst = new int [0];
	int[] qua = new int [0];
	String[] tpe = new String [0];
	double[] prc = new double [0];
	double[] oPrc = new double [0];
	double total;

	pt ("Enter the acquired information, enter '0' to go back\n\n");

	do
	{
	    do
	    {
		showStockItem ();
		pt ("Enter item's id--> ");
		strId = Stdin.readLine ();

		if (strId.equals ("0"))
		    break;

		if (!stock.isIdExist (strId))
		    msg ("The id entered does not exist!");
	    }
	    while (select == 0 || !stock.isIdExist (strId));

	    intPst = stock.getPstByID (strId);
	    //
	    //Stdout.println (intPst);
	    if (strId.equals ("0"))
	    {
		break;
	    }
	    else
	    {
		do
		{
		    pt ("Quantity--> ");
		    intQua = Stdin.readInt ();

		    if (intQua == 0)
			break;

		    if (!stock.chkQuantity (intPst, intQua))
			msg ("Only " + stock.getQuantity (intPst) + " in stock!");
		}
		while (!stock.chkQuantity (intPst, intQua) || intQua == 0);

		do
		{
		    Stdout.println ();
		    pt ("Add more item? (Y/N)--> ");
		    select = Stdin.readChar ();

		    if (select != 'y' && select != 'Y' && select != 'n' && select != 'N')
			msg ("Please enter correctly!");
		}
		while (select != 'y' && select != 'Y' && select != 'n' && select != 'N');

	    }

	    pst = salesAddItemPst (pst, intPst);
	    qua = salesAddItemQuan (qua, intQua);
	}


	while ((select == 'Y' || select == 'y') && !strId.equals ("0"));

	total = 0;

	if (!strId.equals ("0"))
	{
	    do
	    {
		ptLoop ("\n", 15);
		for (int i = 0 ; i < qua.length ; i++)
		{
		    total += qua [i] * stock.getPrice (pst [i]);
		    pt ("", alignIndex);
		    Stdout.print (stock.getName (pst [i]) + " x" + qua [i], 35);
		    Stdout.print ("$");
		    Stdout.println (qua [i] * stock.getPrice (pst [i]), 0, 2);
		    arrName = addArr (arrName, stock.getName (pst [i]));
		    prc = addArr (prc, stock.getPrice (pst [i])); //
		    tpe = addArr (tpe, stock.getType (pst [i]));
		    oPrc = addArr (oPrc, stock.getOriginalPrice (pst [i]));
		}

		ptLoop ("-", col * 3, alignIndex);
		Stdout.println ("");
		Stdout.println ();
		strTemp = (Math.round (tax.getTax () * 100)) + "%";

		pt ("Tax(" + strTemp + "): $", alignIndex);
		Stdout.println (total * tax.getTax (), 0, 2);
		total *= (1 + tax.getTax ());
		pt ("Total: $", alignIndex);
		Stdout.println (total, 0, 2);
		ptLoop ("\n", 9);

		pt ("Enter 'Y' to confirm, 'N' to cancel--> ");
		select = Stdin.readChar ();
		if (!(select == 'Y' || select == 'y' || select == 'n' || select == 'N'))
		    msg ("Please enter correctly!");
	    }
	    while (!(select == 'Y' || select == 'y' || select == 'n' || select == 'N'));

	    if ((select == 'Y' || select == 'y'))
	    {
		//arrName = stock.getNames (pst);0
		stock.sellItem (pst, qua);
		sales.addSales (arrName, tpe, qua, prc, oPrc, year, month, day);
		msg ("Operation Succeeded!");
	    }
	    else
	    {
		msg ("Cancelled");
	    }
	}

	showMenu (currentMenu);
    }


    public static double[] addArr (double[] arr, double num)
    {
	double[] a = new double [arr.length + 1];
	for (int i = 0 ; i < arr.length ; i++)
	    a [i] = arr [i];
	a [a.length - 1] = num;

	return a;
    }


    public static String[] addArr (String[] arr, String num)
    {
	String[] a = new String [arr.length + 1];
	for (int i = 0 ; i < arr.length ; i++)
	    a [i] = arr [i];
	a [a.length - 1] = num;

	return a;
    }


    public static int[] salesAddItemPst (int[] pst, int intPst)
    {
	int[] arr = new int [pst.length + 1];

	for (int i = 0 ; i < pst.length ; i++)
	    arr [i] = pst [i];

	arr [arr.length - 1] = intPst;

	return arr;
    }


    public static int[] salesAddItemQuan (int[] qua, int intQua)
    {
	int[] arr = new int [qua.length + 1];

	for (int i = 0 ; i < qua.length ; i++)
	    arr [i] = qua [i];

	arr [arr.length - 1] = intQua;

	return arr;
    }


    public static void showStockItem ()
    {
	int num = stock.getItemNum ();
	int col = 20;
	char select;
	String strTemp;
	int intTemp;
	int indexAlign = index_Align / 3;
	String[] name = stock.getName ();
	String[] id = stock.getId ();
	String[] type = stock.getType ();
	String[] publisher = stock.getPublisher ();
	String[] quantity = stock.getStrQuantity ();
	String[] sold = stock.getStrSold ();
	double[] price = stock.getPrice ();
	double[] oPrice = stock.getOriginalPrice ();
	// num = id.length;

	ptLoop ("\n", 9);
	Stdout.println ();
	ptBlank (indexAlign);
	ptGrid ("ID", col / 2);
	ptGrid ("Name", col);
	ptGrid ("Type", col / 2);
	ptGrid ("Price", col / 2);
	ptGrid ("Quan", col / 2);
	ptGrid ("Sold", col / 2);
	Stdout.println ();
	ptLoop ("-", col * 3 + col / 2 - 5, indexAlign);
	Stdout.println ();

	for (int i = 0 ; i < num ; i++)
	{
	    ptBlank (indexAlign);
	    ptGrid (id [i], col / 2);
	    ptGrid (name [i], col);
	    ptGrid (type [i], col / 2);
	    ptGrid ("$" + price [i], col / 2);
	    ptGrid (quantity [i], col / 2);
	    ptGrid (sold [i], col / 2);
	    Stdout.println ();
	}

	ptLoop ("\n", 9);
    }


    public static void editItem () throws IOException
    {
	int len = stock.getItemNum ();
	String[] name;
	String[] id;
	String[] type;
	String[] publisher;
	int[] quantity;
	int[] sold;
	double[] price;
	double[] oPrice;
	int col = 20;
	String strTemp = "";
	int intTemp, intTemp2, intTemp3, pst;
	int indexAlign = index_Align / 3;
	String select;
	double dblTemp;

	do
	{
	    len = stock.getItemNum ();
	    name = stock.getName ();
	    id = stock.getId ();
	    type = stock.getType ();
	    publisher = stock.getPublisher ();
	    quantity = stock.getQuantity ();
	    sold = stock.getSold ();
	    price = stock.getPrice ();
	    oPrice = stock.getOriginalPrice ();

	    showStockItem ();
	    pt ("Enter an id to edit,\n");
	    pt (" enter '0' to go back--> ");
	    select = Stdin.readString ();
	    if (select.equals ("0"))
	    {
		break;
	    }
	    else
	    {
		if (!stock.isIdExist (select))
		{
		    msg ("The ID entered does not exist!");
		}
		else
		{
		    pst = stock.getPstByID (select);
		    do
		    {
			do //
			{
			    ptLoop ("\n", 9);
			    pt ("DETTAILS\n", indexAlign);
			    ptLoop ("-", col * 3, indexAlign);
			    Stdout.println ();
			    pt ("1. ID: " + stock.getId (pst) + "\n", indexAlign);
			    pt ("2. Name: " + name [pst] + "\n", indexAlign);
			    //Stdout.println ();
			    pt ("3. Type: " + type [pst] + "\n", indexAlign);
			    //Stdout.println ();
			    pt ("4. Price: $" + price [pst] + "\n", indexAlign);
			    pt ("5. Original Price: $" + oPrice [pst] + "\n", indexAlign);
			    //Stdout.println ();s
			    pt ("6. Publisher: " + publisher [pst] + "\n", indexAlign);
			    //Stdout.println ();
			    pt ("7. Quantity: " + quantity [pst] + "\n", indexAlign);
			    //Stdout.println ();
			    pt ("8. Sold: " + sold [pst] + "\n", indexAlign);
			    ptLoop ("\n", 10);
			    pt ("Enter a number to edit,\n");
			    pt ("  enter '0' to go back)--> ");
			    intTemp3 = Stdin.readInt ();

			    if (intTemp3 != 0)
			    {
				if (intTemp3 < 0 || intTemp3 > 8)
				{
				    Stdout.println ();
				    msg ("Invalid selection!\n");
				}
			    }
			    else
			    {
				break;
			    }
			}
			while ((intTemp3 < 0 || intTemp3 > 8) && intTemp3 != 0); //

			switch (intTemp3)
			{
			    case 1:
				intTemp2 = -1;
				pt ("Enter the new ID--> ");
				strTemp = Stdin.readLine ();

				if (strTemp.equals ("0"))
				{
				    msg ("ID should not be 0");
				    break;
				}

				if (stock.isIdExist (strTemp))
				{
				    msg ("The ID entered already exist!");
				    break;
				}

				id [pst] = strTemp;

				break;

			    case 2:
				pt ("Enter the new name--> ");
				strTemp = Stdin.readLine ();
				name [pst] = strTemp;

				break;

			    case 3:
				pt ("Enter the new type--> ");
				strTemp = Stdin.readLine ();
				type [pst] = strTemp;

				break;

			    case 4:
				pt ("Enter the new price-->$ ");
				dblTemp = Stdin.readDouble ();
				price [pst] = dblTemp;

				break;
			    case 5:
				pt ("Enter the new original price-->$ ");
				dblTemp = Stdin.readDouble ();
				oPrice [pst] = dblTemp;

				break;
			    case 6:
				pt ("Enter the new publisher--> ");
				strTemp = Stdin.readLine ();
				publisher [pst] = strTemp;

				break;

			    case 7:
				pt ("Enter the new quantity--> ");
				intTemp = Stdin.readInt ();
				quantity [pst] = intTemp;

				break;

			    case 8:
				pt ("Enter the new quantity of sold --> ");
				intTemp = Stdin.readInt ();
				sold [pst] = intTemp;

				break;

			    case 0:

				break;
			}
		    }
		    while (intTemp3 != 0 && !(strTemp.equals ("0") && stock.isIdExist (strTemp)));

		    if (!stock.setStock (id, name, type, publisher, price, oPrice, quantity, sold))
			msg ("Operation failed!");
		}
	    }
	}
	while (!select.equals ("0") || stock.isIdExist (select));

	showMenu (currentMenu);
    }



    public static void delItem () throws IOException
    {
	String select;

	do
	{
	    showStockItem ();
	    pt ("Enter an ID to delete,\n");
	    pt ("or enter '0' to go back--> ");
	    select = Stdin.readLine ();

	    if (select.equals ("0"))
	    {
		break;
	    }
	    else
	    {
		if (!stock.isIdExist (select))
		{
		    msg ("The ID entered does not exist!");
		}
		else
		{
		    if (stock.delItem (select))
			msg ("You've successfully deleted the item!");
		    else
			msg ("Operation failed!");
		}
	    }
	}
	while (!stock.isIdExist (select) || !select.equals ("0"));

	showMenu (currentMenu);
    }


    public static void msg (String str) throws IOException
    {
	String tmp;
	ptLoop ("\n", 15);
	pt (str);
	Stdout.println ();
	ptLoop ("\n", 11);
	pt ("Press <Enter> to continue...");
	tmp = Stdin.readLine ();
    }


    public static void msg (String str, String m) throws IOException
    {
	String tmp;
	ptLoop ("\n", 15);
	pt (str);
	Stdout.println ();
	ptLoop ("\n", 11);
	pt ("Press <Enter to continue...>");
	tmp = Stdin.readLine ();
	showMenu (m);
    }


    public static void addItem () throws IOException
    {
	String id, name, type, publisher;
	double price, op;
	int quantity;
	pt ("Please enter the following information:");
	Stdout.println ("\n");
	pt ("ID--> ");
	id = Stdin.readLine ();

	if (id.equals ("0"))
	{
	    msg ("ID should not be 0", currentMenu);
	}
	else
	{
	    if (stock.isIdExist (id))
	    {
		msg ("The ID entered already exist!", currentMenu);
	    }
	    else
	    {

		pt ("Name--> ");
		name = Stdin.readLine ();
		pt ("Type--> ");
		type = Stdin.readLine ();
		pt ("Publisher--> ");
		publisher = Stdin.readLine ();
		pt ("Price--> $");
		price = Stdin.readDouble ();

		while (price < 0)
		{
		    Stdout.println ();
		    pt ("The price cannot be negative!");
		    Stdout.println ("\n");
		    pt ("Price--> $");
		    price = Stdin.readDouble ();
		}
		pt ("Original Price--> $");
		op = Stdin.readDouble ();
		while (op < 0)
		{
		    Stdout.println ();
		    pt ("The original price cannot be negative!");
		    Stdout.println ("\n");
		    pt ("Original Price--> $");
		    op = Stdin.readDouble ();
		}
		pt ("Quantity--> ");
		quantity = Stdin.readInt ();

		while (quantity < 0)
		{
		    Stdout.println ();
		    pt ("The quantity cannot be negative!");
		    Stdout.println ("\n");
		    pt ("Quantity--> $");
		    price = Stdin.readDouble ();
		}

		if (stock.addItem (id, name, type, publisher, price, op, quantity))
		    msg ("You've successfully added the item!", currentMenu);
		else
		    msg ("Operation failed!", currentMenu);
	    }
	}
    }


    public static void saveStock () throws IOException
    {
	stock.saveStock ();
    }


    public static void ptGrid (String str, int col)
    {
	int intTemp;
	String strTemp = "";

	if (str.length () > col - 4)
	{
	    intTemp = col - 4;
	    for (int i = 0 ; i < intTemp ; i++)
	    {
		strTemp += str.charAt (i) + "";
	    }
	    strTemp += "... ";
	}
	else
	{
	    intTemp = col - str.length ();
	    strTemp = str;

	    for (int j = 0 ; j < intTemp ; j++)
		strTemp += " ";
	}

	System.out.print (strTemp);
    }


    public static void showStock () throws IOException
    {
	int num = stock.getItemNum ();
	int col = 20;
	char select;
	String strTemp;
	int intTemp;
	int indexAlign = index_Align / 3;
	String[] name = stock.getName ();
	String[] id = stock.getId ();
	String[] type = stock.getType ();
	String[] publisher = stock.getPublisher ();
	String[] price = new String [num];
	String[] oPrice = new String [num];
	String[] quantity = stock.getStrQuantity ();
	String[] sold = stock.getStrSold ();
	double[] dbl_Price = stock.getPrice ();
	double[] dbl_OPrice = stock.getOriginalPrice ();

	for (int i = 0 ; i < num ; i++)
	    price [i] = "$" + dbl_Price [i];

	for (int i = 0 ; i < num ; i++)
	    oPrice [i] = "$" + dbl_OPrice [i];
	do
	{
	    ptLoop ("\n", 9);
	    Stdout.println ();
	    ptBlank (indexAlign);
	    ptGrid ("ID", col / 2);
	    ptGrid ("Name", col);
	    ptGrid ("Type", col / 2);
	    ptGrid ("Price", col / 2);
	    ptGrid ("Quan", col / 2);
	    ptGrid ("Sold", col / 2);
	    Stdout.println ();
	    ptLoop ("-", col * 3 + col / 2 - 5, indexAlign);
	    Stdout.println ();

	    for (int i = 0 ; i < num ; i++)
	    {
		ptBlank (indexAlign);
		ptGrid (id [i], col / 2);
		ptGrid (name [i], col);
		ptGrid (type [i], col / 2);
		ptGrid (price [i], col / 2);
		ptGrid (quantity [i], col / 2);
		ptGrid (sold [i], col / 2);
		Stdout.println ();
	    }

	    ptLoop ("\n", 9);
	    pt ("Enter ID for details,");
	    Stdout.println ();
	    pt ("  enter '0' to go back--> ");
	    strTemp = Stdin.readLine ();

	    if (strTemp.equals ("0"))
	    {
		break;
	    }
	    else
	    {
		if (stock.isIdExist (strTemp))
		{
		    intTemp = stock.getPstByID (strTemp);
		    ptLoop ("\n", 9);
		    pt ("DETTAILS", indexAlign);
		    Stdout.println ();
		    ptLoop ("-", col * 3, indexAlign);
		    Stdout.println ();
		    pt ("ID: " + id [intTemp] + "\n", indexAlign);
		    pt ("Name: " + name [intTemp] + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Type: " + type [intTemp] + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Price: " + price [intTemp] + "\n", indexAlign);
		    pt ("Original Price: " + oPrice [intTemp] + "\n", indexAlign);
		    //Stdout.println ();s
		    pt ("Publisher: " + publisher [intTemp] + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Quantity: " + quantity [intTemp] + "\n", indexAlign);
		    //Stdout.println ();
		    pt ("Sold: " + sold [intTemp] + "\n", indexAlign);
		    ptLoop ("\n", 9);
		    pause (" ");
		}
		else
		{
		    msg ("The ID entered does not exist!");
		}
	    }
	}

	while (!strTemp.equals ("0"));
	//debug(4.16)
	runCmd (currentMenu);
    }


    public static void stockInit () throws IOException
    {
	stock = new Stock (folder_Stock + "\\stock.txt");
	//Stdout.println (stock.getPath ());
    }


    public static void showAccounts () throws IOException
    {
	ptBlank (index_Align);
	Stdout.println ("ID:");

	for (int i = 0 ; i < account.length ; i++)
	{
	    ptBlank (index_Align);
	    Stdout.println (account [i].getId ());
	}

	pause ();
    }


    public static void pause () throws IOException
    {
	String str;
	Stdout.println ();
	pt ("Press <Enter> to continue...");
	str = Stdin.readLine ();
	runCmd (currentMenu);
    }


    public static void pause (String cmd) throws IOException
    {
	String str;
	Stdout.println ();
	pt ("Press <Enter> to continue...");
	str = Stdin.readLine ();
	runCmd (cmd);
    }


    public static void addAccount () throws IOException
    {
	String strId, strPsw, str = "";
	Account[] arr;

	if (passwordsMatch ())
	{
	    Stdout.println ();
	    pt ("Enter the new account's id--> ");
	    strId = Stdin.readLine ();

	    while (strId.equals (""))
	    {
		ptln ("Id cannot be blank!");
		pt ("Enter the new account's id--> ");
		strId = Stdin.readLine ();
	    }

	    for (int i = 0 ; i < account.length ; i++)
		if (account [i].idMatch (strId))
		    str = strId;

	    if (str == strId)
	    {
		//Stdout.println ();
		ptln ("User with id:\"" + strId + "\" already exist!");
		pt ("Press <Enter> to continue...");
		str = Stdin.readLine ();
		runCmd (currentMenu);
	    }
	    else
	    {
		pt ("Enter the new account's password--> ");
		strPsw = Stdin.readLine ();
		arr = new Account [account.length + 1];

		for (int i = 0 ; i < account.length ; i++)
		    arr [i] = new Account (account [i].getId (), account [i].getPasswords ());

		arr [account.length] = new Account (strId, strPsw);
		account = arr;
		Stdout.println ();
		pt ("You have successfully added an account!");
		Stdout.println ();
		/*  for (int i = 0 ; i < account.length ; i++)
		      Stdout.println ("id: " + account [i].getId () + " , psw: " + account [i].getPasswords ());*/
		pause ();
	    }
	}
	else
	{
	    Stdout.println ();
	    ptln ("Wrong password entered!");
	    pt ("Press <Enter> to continue...");
	    str = Stdin.readLine ();
	    runCmd (currentMenu);
	}
    }


    public static void ptln (String str)
    {
	ptBlank (index_Align);
	Stdout.print (str);
	System.out.println ("\n");
    }


    public static void pt (String str)
    {
	ptBlank (index_Align);
	Stdout.print (str);
    }


    public static void pt (String str, int align)
    {
	ptBlank (align);
	Stdout.print (str);
    }


    public static boolean signIn ()
    {
	String id, psw;
	int index = -1;
	boolean success = true;
	Stdout.print ("Enter the user id number (User01 id: \"001\")--> ");
	id = Stdin.readLine ();
	Stdout.print ("Enter the password (User01 password: \"111\")--> ");
	psw = Stdin.readLine ();

	for (int i = 0 ; i < account.length ; i++)
	{
	    //Stdout.println (account [i].getPasswords ());
	    if (id.equals (account [i].getId ()))
		index = i;
	}

	if (index != -1)
	{
	    index_Account = index;
	    success = psw.equals (account [index].getPasswords ());
	}
	else
	{
	    success = false;
	}

	return success;
    }


    public static boolean passwordsMatch ()
    {
	String str;
	pt ("Enter your current password--> ");
	str = Stdin.readLine ();

	return str.equals (account [index_Account].getPasswords ());
    }


    public static void accountInit () throws IOException
    {
	String strTemp, txt = "";
	String id, psw;
	int intTemp;
	BufferedReader rdr = new BufferedReader (new FileReader (folder_Accounts + "\\account.txt"));

	intTemp = Integer.parseInt (rdr.readLine ());
	while (true)
	{
	    strTemp = rdr.readLine ();
	    if (strTemp != null)
		txt += strTemp;
	    else
		break;
	}

	rdr.close ();
	account = new Account [intTemp];

	for (int i = 1 ; i <= intTemp ; i++)
	{
	    id = getStr (txt, "id" + i + ":", ";", hintPosition);
	    psw = getStr (txt, "password" + i + ":", ";", 0);
	    //Stdout.println (psw);
	    account [i - 1] = new Account (id, psw);
	}
    }


    public static void changePsw () throws IOException
    {
	String str;

	if (passwordsMatch ())
	{
	    Stdout.println ();
	    pt ("Enter the new password-->");
	    str = Stdin.readLine ();
	    account [index_Account].setPasswords (str);
	    Stdout.println ();
	    ptln ("You have successfully changed the password!");
	    pt ("Press <Enter> to continue...");
	    str = Stdin.readLine ();
	    runCmd (currentMenu);
	}
	else
	{
	    Stdout.println ();
	    ptln ("Wrong password entered!");
	    pause ();
	}
    }


    public static void quit () throws IOException
    {
	char select;
	ptBlank (index_Align);
	Stdout.print ("Do you want to quit the program?(Y/N)-->");
	select = Stdin.readChar ();

	while (select != 'Y' && select != 'y' && select != 'N' && select != 'n')
	{
	    Stdout.println ();
	    Stdout.print ("Enter 'Y' for yes, or 'N' for no.\n\nDo you want to quit the program?(Y/N)-->");
	    select = Stdin.readChar ();
	}


	if (select == 'Y' || select == 'y')
	{
	    /////////////EXIT
	    saveSales ();
	    saveSettings ();
	    saveStock ();
	    saveTax ();
	    /////////////EXIT
	    ptLoop ("\n", 20);
	    ptBlank (34);
	    Stdout.println ("Good Bye!");
	    ptLoop ("\n", 11);
	}
	else
	{
	    Stdout.println ();
	    runCmd (currentMenu);
	}
    }


    public static void saveTax () throws IOException
    {
	tax.saveToFile ();
    }


    public static void saveSettings () throws IOException
    {
	PrintWriter wtr = new PrintWriter (new FileWriter (folder_Accounts + "\\account.txt"));
	// Save account
	wtr.println (account.length);
	for (int i = 0 ; i < account.length ; i++)
	{
	    wtr.println ("id" + (i + 1) + ":" + account [i].getId () + ";");
	    wtr.println ("password" + (i + 1) + ":" + account [i].getPasswords () + ";");
	}

	wtr.println (" ");
	wtr.close ();
    }


    public static void showMenu (String id) throws IOException
    {
	int temp = 0, select;
	String strTemp = "";

	for (int i = 0 ; i < menu.length ; i++)
	    if (id.equals (menu [i].getId ()))
		temp = i;

	menu_Arr = menu [temp].getMenu ();
	currentMenu = id;
	do
	{
	    ptMenu (menu_Arr);
	    select = Stdin.readInt ();

	    if (select < 1 || select > menu_Arr.length + 1)
		msg ("Please enter CORRECTLY!");
	    /*Stdout.println ();
	    pt ("Enter a number to select--> ");
	    select = Stdin.readInt ();*/
	}

	while (select < 1 || select > menu_Arr.length + 1);

	if (select != menu_Arr.length + 1)
	{
	    strTemp = menu [temp].getMenu (select - 1).toUpperCase ();
	    //currentMenu = strTemp;
	    //Stdout.println (strTemp);
	    select = 0;
	    Stdout.println ();
	    runCmd (strTemp);
	}
	else
	{
	    select = 0;
	    Stdout.println ();
	    runCmd ("QUIT");
	}
    }


    public static void ptLoop (String str, int i, int align)
    {
	for (int j = 0 ; j < align ; j++)
	    System.out.print (" ");
	for (int j = 0 ; j < i ; j++)
	    System.out.print (str);
    }


    public static void ptLoop (String str, int i)
    {
	for (int j = 0 ; j < index_Align ; j++)
	    System.out.print (" ");
	for (int j = 0 ; j < i ; j++)
	    System.out.print (str);
    }


    public static void ptBlank (int i)
    {
	for (int j = 0 ; j < i ; j++)
	    System.out.print (" ");
    }


    public static void ptMenu (String[] arr)
    {
	ptLoop ("\n", 13);
	//ptLoop("_",index_Align);
	Stdout.println ();
	ptBlank (index_Align);
	Stdout.println (currentMenu.toUpperCase ()  /*+ " MENU"*/);
	//
	ptLoop ("-", index_Align);
	Stdout.println ();
	ptLoop ("\\", index_Align);
	Stdout.println ();
	//       Stdout.println (currentMenu);
	for (int i = 0 ; i < arr.length ; i++)
	{
	    ptBlank (index_Align);

	    if (i != arr.length - 1 || currentMenu.equals ("MAIN"))
	    {
		Stdout.print ((i + 1) + ".");
		ptBlank (2);
		Stdout.println ("|" + arr [i] + "|>");
	    }
	    else
	    {
		Stdout.print ((i + 1) + ".");
		ptBlank (1);
		Stdout.println ("<|" + arr [i] + "|");
	    }
	}

	Stdout.println ();
	ptBlank (index_Align);
	Stdout.println ((arr.length + 1) + ".  |QUIT|");
	ptLoop ("\\", index_Align);
	Stdout.println ();
	ptLoop ("-", index_Align);
	ptLoop ("\n", 9);
	pt ("Enter a number to select--> ");
    }


    public static void menuInit () throws IOException
    {
	int intTemp = 0;
	String str, id;
	String path = folder_Menu + "\\menu";
	currentMenu = "MAIN";
	BufferedReader rdr;
	String[] arr = new String [1];

	arr [0] = "";

	for (int i = 1 ; i <= item_Menu ; i++)
	{
	    rdr = new BufferedReader (new FileReader (path + i + ".txt"));
	    id = rdr.readLine ();
	    while (true)
	    {
		str = rdr.readLine ();
		if (str != null)
		    intTemp++;
		else
		    break;
	    }
	    arr = new String [intTemp];
	    rdr.close ();

	    intTemp = 0;
	    rdr = new BufferedReader (new FileReader (path + i + ".txt"));

	    id = rdr.readLine ();
	    while (true)
	    {
		str = rdr.readLine ();
		if (str != null)
		    arr [intTemp++] = str;
		else
		    break;
	    }
	    menu [i - 1] = new FMenu (arr, id);
	    intTemp = 0;
	    rdr.close ();
	}
    }


    public static void saveSales () throws IOException
    {
	sales.saveSales ();
    }


    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////

    // Find specific Strings
    public static String getStr (String str, String start_Tag, String end_Tag, int hint)  // Get string
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


    public static int getPosition (String str, String aimed_Str, int hint)
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
} // MainPro class


