package net.fileInput;

import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;
public class test {

	public static void main(String[] args) throws IOException {
		
		ProductDataProvider pro=new ProductDataProvider();
		pro.LoadProductsFromFolder();
		ProductInfo delet=pro.find("01");
		//pro.deletProduct(delet);
		//System.out.print(pro.find("01").getText("Title"));;
		System.out.print(pro.getFolder());
			 
		}
		
		
		
		
	

}


