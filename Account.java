import java.io.*;
public class Account
{
    private String id;
    private String passwords;

    public Account (String strId, String strPsw)
    {
	id = strId;
	passwords = strPsw;
    }


    public void setPasswords (String psw)
    {
	passwords = psw;
    }


    public void setId (String strId)
    {
	id = strId;
    }


    public String getPasswords ()
    {
	return passwords;
    }


    public String getId ()
    {
	return id;
    }


    public boolean idMatch (String str)
    {
	return id.equals (str);
    }


    public boolean passwordsMatch (String str)
    {
	return passwords.equals (str);
    }
}
