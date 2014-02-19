package net.fileInput;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileUtils;


public class ProductDataProvider {
	
	public ArrayList<ProductInfo> products;
	private static String productFolder;
	public static ProductDataProvider global;
	public final String folder="\\Products";
	
	public ProductDataProvider()
	{
		//global=new ProductDataProvider();
		productFolder=System.getProperty("user.dir")+folder;
		System.out.println(productFolder);
		products=new ArrayList<ProductInfo>();
	}
	
	
	public void LoadProductsFromFolder() throws IOException
	{
		products.clear();
		File directory = new File(productFolder);
		String [] names=directory.list();
		 for (String s : names) {
			 System.out.println(s);
			 ProductInfo product=new ProductInfo();
			 product.loadFromFolder(productFolder+"\\"+s);
			 products.add(product);
			 
		}
	}
	
	public ProductInfo find(String searchTerm)
	{
		for (ProductInfo pro : products) {
			if (pro.getText("Code").equalsIgnoreCase(searchTerm.trim())) {
				return pro;
			}
			else if (pro.getText("Title").toLowerCase().contains(searchTerm.trim().toLowerCase())) {
				return pro;
			}
		}
		return null;	
	}
	
	public ProductInfo newProduct()
	{
		ProductInfo info=new ProductInfo();
		String id = new SimpleDateFormat("YYYYMMddHHmmssff").format(Calendar.getInstance().getTime());
		info.setFolder(productFolder+"//"+id);
		return info;
	}
	public void deletProduct(ProductInfo aProduct) throws IOException
	{
		products.remove(aProduct);
		File directory = new File(aProduct.getPath());
		 //make sure directory exists
    	if(!directory.exists()){
           System.out.println("Directory does not exist.");
           System.exit(0);
        }else{
 
           try{
               delete(directory);
 
           }catch(IOException e){
               e.printStackTrace();
               System.exit(0);
           }
        }
 
    	System.out.println("Done");
    }
 
    public static void delete(File file) throws IOException{
 
    	if(file.isDirectory()){
 
    		//directory is empty, then delete it
    		if(file.list().length==0){
 
    		   file.delete();
    		   System.out.println("Directory is deleted : " + file.getAbsolutePath());
 
    		}else{
    		   //list all the directory contents
        	   String files[] = file.list();
        	   for (String temp : files) {
        	      //construct the file structure
        	      File fileDelete = new File(file, temp);
        	      //recursive delete
        	     delete(fileDelete);
        	   }
        	   //check the directory again, if empty then delete it
        	   if(file.list().length==0){
           	     file.delete();
        	     System.out.println("Directory is deleted : " + file.getAbsolutePath());
        	   }
    		}
 
    	}else{
    		//if file, then delete it
    		file.delete();
    		System.out.println("File is deleted : " + file.getAbsolutePath());
    	}
    }
		
	
	public String getFolder()
	{
		return productFolder;
	}

	public void setFolder(String aFolder)
	{
		productFolder=aFolder;
	}
	public ArrayList<ProductInfo> getProducts()
	{
		return products;
	}
	public void setProducts(ArrayList<ProductInfo> inputProducts)
	{
		products=inputProducts;
	}
}
