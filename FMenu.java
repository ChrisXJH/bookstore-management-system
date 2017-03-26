public class FMenu
{
    private String[] menu;
    private String id;

    public FMenu (String[] arr, String str)
    {
	menu = arr;
	id = str;
    }


    public String getMenu (int i)
    {
	return menu [i];
    }


    public String[] getMenu ()
    {
	return menu;
    }


    public String getId ()
    {
	return id;
    }
}
