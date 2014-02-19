package net.fileInput;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class ProductInfo {
	

	
    public final String [] fileCont={"Code","Title","Description","Summary","Specifications","AudioVisual","FAQ","TroubleShooting","Learning"};
	public Map<String,String> map =new HashMap <String,String>();
	public String DataPath;
	public String dir;
    //public String combined_path;
    public BufferedImage imagebi;
    public byte[] newImage;
    
	public ProductInfo()
	{
		DataPath="";
		
	}
	
	public void setFolder(String path)
	{
		if (DataPath==null|DataPath.isEmpty()) 
			DataPath=path;

	}
	

	//path is absolute path \product 1
	public void loadFromFolder(String path) throws IOException
	{
		//DataPath=path;
		//dir = System.getProperty("user.dir");
		//if (DataPath==null|DataPath.isEmpty()) {
			DataPath=path;
			
			System.out.println("DataPath is " +DataPath);
		//}
		//combined_path= new File(dir, "\\"+DataPath).getPath();
		reload();
		
	}
	public void reload() throws IOException
	{
		for (String st : fileCont)
		{
			System.out.println(DataPath + "\\"+st+".txt");
	        BufferedReader reader = new BufferedReader(new FileReader(DataPath + "\\"+st+".txt"));
	        
	        String s="";
	        try
	        {  
	        	
	            String line = null;         
	            while ((line = reader.readLine()) != null)
	            {
	            	s+=line;

	            }               
	        }
	        catch (IOException ex)
	        {
	            ex.printStackTrace();
	        }               

	        finally
	        {
	            reader.close();
	        } 
			map.put(st, s);
		}
		reloadImage();
	}
	
	public void reloadImage() throws IOException
	{
		if(new File(DataPath+"\\image.jpg").isFile())
		{
		 File image = new File(DataPath+"\\image.jpg"); 
	    imagebi = ImageIO.read(image);
		}
		else
			imagebi=null;
		newImage=null;
		
	}
	
	public void setNewImage(byte [] imageByte)
	{
		newImage=imageByte;
	}
	
	public void setText(String name,String contant)
	{
		boolean find=false;
		for (String a : fileCont) {
			if(name.equalsIgnoreCase(a))
			{
				find=true;
				name=a;
			}
		}
		if(find)
		{
			map.put(name, contant);
		}
		if(!find)
		{
			System.out.println("Wrong file name!");
		}
		
	}
	
	public String getText(String name)
	{
		boolean find=false;
		for (String a : fileCont) {
			if(name.equalsIgnoreCase(a))
			{
				find=true;
				name=a;
			}
		}
		if(find)
		{
			return  map.get(name);
			
		}
		else
		{
			System.out.println("Wrong file name!");
			return null;
		}
		
	}
	
    public String getPath()
    {
    	return DataPath;
    }
    
    public BufferedImage getImage()
    {
    	return imagebi;
    }
    public void setImage(String path) throws IOException
    {
    	if(new File(path).isFile())
		{
		 File image = new File(path); 
	    imagebi = ImageIO.read(image);
		}
    
    }
    public byte[] getNewImage()
    {
    	return newImage;
    }
    


}


