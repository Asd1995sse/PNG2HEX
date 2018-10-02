package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import javax.imageio.ImageIO;
import net.sf.image4j.util.ConvertUtil;


public class CoHexPo extends Thread {
	static int adress = 0;
	
	public void run() {
		System.out.println("gjnjr");
    }
	//системные переменные	
	public static String fileSeparator = System.getProperty("file.separator");
	public static String ls = System.getProperty("line.separator");
	private static String imputs = "imput";
	private static String outputs = "output";
	private static File imputfolder;
	private static File ouputfolder;
	private static String nuli = "00000000";
	public static String filename = "default";
	//главный буфер изображения
	private static BufferedImage inimage;
	//Вспомогательный буфер
	private static BufferedImage outimage;
	static String[] wii;
	static int Ni =0;
	private static BufferedImage bi;
	//начнем с папок и файлов	
	public static void preinitPONY() {
	//Создадим папки для входа
	imputfolder = new File(System.getProperty("user.dir") + fileSeparator + imputs );
	imputfolder.mkdir();
	//то же самое, но для выхода
	ouputfolder = new File(System.getProperty("user.dir") + fileSeparator + outputs);
	ouputfolder.mkdir();	
	}
	//Преобразование данных из главного буфера в bin
	public static void buffer2bin() throws IOException {
				int n =0;
				int height = 0;
				int width = 0;
				int Rb = 0;
				int Bb = 0;
				int Gb = 0;
				int Yb = 0;
				int gRb = 0;
				int gBb = 0;
				int gGb = 0;
				int gYb = 0;
				if(inimage != null) {
					//Получим параметры изображения
					height = inimage.getHeight();
					width = inimage.getWidth();
					byte[] bytej =  new byte[height*width];
					//пересоздадим файл
				    File outputfile = new File(ouputfolder + fileSeparator + filename + ".bin" );
	         	    System.out.println(outputfile);		            	  
	         	    outputfile.delete();
	         	    outputfile.createNewFile();
					//теперь цикл попиксельного чтения этого говнища по строкам
					for (int hh=0; hh<height; hh++) {
						//то же говно, но по пикселям каждой строки нахуй!
						for (int ww=0; ww<width; ww++) {
							//обработка сраных пикселей
							Color pixels = new Color(inimage.getRGB(ww, hh));
							int R = pixels.getRed();
							int G = pixels.getGreen();
							int B = pixels.getBlue();
							//обновяем переменные
				               Rb = 0;
				               Gb = 0;
				               Bb = 0;
				               Yb = 1;
				               gRb = 0;
							   gBb = 0;
							   gGb = 0;
							   gYb = 1;
							   if(R > Gui.getRs())
				            	   Rb = 1;
				               if(G > Gui.getGs())
				            	   Gb = 1;
				               if(B > Gui.getBs())
				               Bb =1;
				               if(Bb==0 && Rb ==0 && Gb == 0)
				            	   Yb = 0;
				               if(gBb==0 && gRb ==0 && gGb == 0)
				            	   gYb = 0;
				             //формируем байт
				               String bait = String.valueOf(gRb) + String.valueOf(gGb) + String.valueOf(gBb) + String.valueOf(gYb) + String.valueOf(Rb)
				               + String.valueOf(Gb) + String.valueOf(Bb) + String.valueOf(Yb);			             
			                //формирование байтов - хуяйтов
				               System.out.println(bytej.length);       
				               
							bytej [n] = getByteByString(bait)[0];
							n = n+1;
							try (FileOutputStream fos = new FileOutputStream(outputfile)) {
				                fos.write(bytej);
				                fos.flush();
				            }    
					             
					            
						}
					}
					
				}
	}
	
	//Преобразование данных из главного буффера в байт(00001111)
		public static void buffer2byte() throws IOException {			
			int height = 0;
			int width = 0;
			int Rb = 0;
			int Bb = 0;
			int Gb = 0;
			int Yb = 0;
			int gRb = 0;
			int gBb = 0;
			int gGb = 0;
			int gYb = 0;
			if(inimage != null) {
				//Получим параметры изображения
				height = inimage.getHeight();
				width = inimage.getWidth();				
				//пересоздадим файл
			    File outputfile = new File(ouputfolder + fileSeparator + filename + ".byte" );
         	    System.out.println(outputfile);		            	  
         	    outputfile.delete();
         	    outputfile.createNewFile();
         	   System.out.println("height= " +height);
      		   System.out.println("width= " +width);
				//теперь цикл попиксельного чтения этого говнища по строкам
				for (int hh=0; hh<=height-1;hh++) {
					//то же говно, но по пикселям каждой строки нахуй!
					for (int ww=0; ww<=width-1; ww++) {
						//обработка сраных пикселей
						Color pixels = new Color(inimage.getRGB(ww, hh));
						
						int R = pixels.getRed();
						int G = pixels.getGreen();
						int B = pixels.getBlue();
						//обновяем переменные
			               Rb =0;
			               Gb = 0;
			               Bb = 0;
			               Yb = 1;
			               gRb = 0;
						   gBb = 0;
						   gGb = 0;
						   gYb = 1;
						   if(R > Gui.getRs())
			            	   Rb = 1;
			               if(G > Gui.getGs())
			            	   Gb = 1;
			               if(B > Gui.getBs())
			               Bb =1;
			               if(Bb==0 && Rb ==0 && Gb == 0)
			            	   Yb = 0;
			               if(gBb==0 && gRb ==0 && gGb == 0)
			            	   gYb = 0;
			               String bait = String.valueOf(gRb) + String.valueOf(gGb) + String.valueOf(gBb) + String.valueOf(gYb) + String.valueOf(Rb)
			               + String.valueOf(Gb) + String.valueOf(Bb) + String.valueOf(Yb); 
		                   BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile, true));
		                 
		                   	if(Ni<width) {		                   		
		                   		writer.write(bait+"|");
		                   		System.out.println("Ni= " +Ni);
		                   		System.out.println("height= " +hh);
					      		System.out.println("width= " +ww);
					      		Ni++;
		                   	}else {
		                   		Ni = 0;
		                   	 writer.write(ls);
		                   	//последний элемент строки + 24 бита хуйни	
		                   //		writer.write(bait+"|");
		                   	for (int zz=0; zz<23; zz++) {
		                  // 		writer.write(nuli+"|");
		                   	}		                   		
		                  //    writer.write(ls); 
		                    
		                      
		                     }  
		                   	writer.flush();
		                    writer.close(); 
			                 
		                           
					}
					
				}
				
			}
		}
		
		//конвертер из String
	public static byte[] getByteByString(String byteString){
		    return new BigInteger(byteString, 2).toByteArray();
		}
		
	//рекурсивное преобразование. нам не нужно пока
	public static void initPONY() {
		int height = 0;
		int width = 0;
		int Rb = 0;
		int Bb = 0;
		int Gb = 0;
		int Yb = 0;
		
		//Проверяем наличие файлов(генеальный код)		
		try { 
	         // Массив файлов и папок
			String[] filepatch = imputfolder.list();

	         for(String path:filepatch) {
	        	File images = new File(imputfolder + fileSeparator + path); 
	     		BufferedImage bi = ImageIO.read( images );
	     		BufferedImage bi8 = ConvertUtil.convert8(bi);
	     		height = bi8.getHeight();
	     		width = bi8.getWidth();	 
	     		//вы думали, что самое веселое кончилось? Так нет! Вот корень зла!
	     		for (int xx=0; xx<height; xx++) {
		               for (int yy=0; yy<width; yy++) {
		            	   Color cp = new Color(bi8.getRGB(yy, xx));		            	  
			               int R = cp.getRed();
			               int B = cp.getBlue();
			               int G = cp.getGreen();
			               //обнуляем переменные
			               Rb =0;
			               Gb = 0;
			               Bb = 0;
			               Yb = 1;
			               inimage = bi;	              
			               Gui.updateBar(height + width, xx + yy);
			               if(R > 128)
			            	   Rb = 1;
			               if(G > 128)
			            	   Gb = 1;
			               if(B > 128)
			               Bb =1;
			               if(Bb==0 && Rb ==0 && Gb == 0)
			            	   Yb = 0;
		            	  
			              
		            	   
		            	   String bait = "0000" + Rb + Gb + Bb + Yb;
		            	   //запись в файл
		            	   File outputfile = new File(ouputfolder + fileSeparator + path );
		                   BufferedWriter writer = new BufferedWriter(new FileWriter(outputfile + ".txt" , true));
		                   
		                 //  System.out.println(bait);
		                   		                   	//строка полезной инфы = 40
		                   	if(Ni<39) {
		                   		Ni++;
		                   		writer.write(bait);
		                   		
		                   	}else {
		                   	//последний элемент строки + 24 бита хуйни	
		                   		writer.write(bait);
		                   	for (int zz=0; zz<23; zz++) {
		                   		writer.write("00000000");
		                   	}		                   		
		                    //  writer.write(ls); 
		                    
		                      Ni = 0;
		                       }
		                   	writer.flush();
		                    writer.close();                           	   
		            	  
		               }
	     		}     		
	     		
	     		
	     		      
	                          
	       }
	      }catch(Exception e) {	         
	         e.printStackTrace();
		}		
	   }


	public static String ToHex(String s){
		System.out.println("байткод" + s);
		
		BigInteger b= new BigInteger(s,2);
		String	a = String.format("%2s", b.toString(16)).replace(' ', '0');
		 System.out.println("hex= " +a);
		 System.out.println("hex= " +s);
		 System.out.println("hex= " +b.toString(16));
		
		return a;
	}

	

	public static void bin2jpeg(File fileopened) throws FileNotFoundException, IOException {
		bi = new BufferedImage(66,225,BufferedImage.TYPE_INT_RGB);
		
		 byte[] bytes = new byte[(int) fileopened.length()];
	             // Получаем массив байт
		 int hight = 1;
    	 int widht = 1;
	            try (FileInputStream fis  = new FileInputStream(fileopened)) {
	                fis.read(bytes);
	             for(int z=0; z<fileopened.length();z++) {
	            	// System.out.println("байты" + bytes[z]);
	            	
	            	 StringBuilder ret  = new StringBuilder();
	            	 byte b = bytes[z]; 
	            	 
	            	 ret.append(Integer.toBinaryString(b & 255 | 256).substring(1));
	            	 widht++;
	            	 System.out.println("hight" + hight + "widht" + widht);
	            		  if (widht==64) {
	            			  hight++;
	            			  widht=0;
	            			  System.out.println("hight" + hight + "widht" + widht);
	            		  }
	            		int R = 0;
	  	            	int G = 0;
	  	            	int B = 0;
	            		  switch (ret.toString()) {            		  
	            		  
	  	            	case "00001111":
	  	            		R = 255;
	  	            		B = 255;
	  	            		G = 255;
	  	            		break;
	  	            	case "00000000":
	  	            		R = 1;
	  	            		G = 1;
	  	            		B = 1;
	  	            		break;
	  	            	case "00001101":
	  	            		R = 255;
	  	            		G = 255;
	  	            		B = 0;
	  	            		break;
	  	            	case "00001011":
	  	            		R = 255;
	  	            		G = 0;
	  	            		B = 255;
	  	            		break;
	  	            	
	  	            	case "00000111":
	  	            		R = 0;
	  	            		G = 255;
	  	            		B = 255;
	  	            		break;
	  	            	case "00000011":
	  	            		R = 0;
	  	            		G = 0;
	  	            		B = 255;
	  	            		break;
	  	            	case "00001001":
	  	            		R = 255;
	  	            		G = 0;
	  	            		B = 0;
	  	            		break;
	  	            	case "00000101":
	  	            		R = 0;
	  	            		G = 255;
	  	            		B = 0;
	  	            		break;
	  	            		
	              	}
	            		 Color rgb = new Color(B, G, R);
	  	            	System.out.println("[eqyz"+widht + ";jgf" + hight);
	  	            	System.out.println(R +"|"+G+"|"+ B);   	
	  	                
	  	                 
	  	            	
	  	            	bi.setRGB(widht, hight, rgb.getRGB());
	  	            	inimage = bi;
	  	            	Gui.updateImages();
			
	             }
	            }
		
	        
	}
	

	public static void bite2jpeg(File fileopened) {
		bi = new BufferedImage(66,225,BufferedImage.TYPE_INT_RGB);
		int hight = 1;
		 try (BufferedReader reader = new BufferedReader( new InputStreamReader( new FileInputStream(fileopened.getAbsolutePath()), StandardCharsets.UTF_8))){
	            String line;          
	            while ((line = reader.readLine()) != null) {
	            wii = line.split("\\|",-1);
	            
	            for(int widht = 1; widht<wii.length+1; widht++) {	 
	            	int R = 0;
	            	int G = 0;
	            	int B = 0;	            
	            	String ss = wii[widht-1];
	         
	            	switch (ss) {
	            	case "00001111":
	            		R = 255;
	            		B = 255;
	            		G = 255;
	            		break;
	            	case "00000000":
	            		R = 1;
	            		G = 1;
	            		B = 1;
	            		break;
	            	case "00001101":
	            		R = 255;
	            		G = 255;
	            		B = 0;
	            		break;
	            	case "00001011":
	            		R = 255;
	            		G = 0;
	            		B = 255;
	            		break;
	            	
	            	case "00000111":
	            		R = 0;
	            		G = 255;
	            		B = 255;
	            		break;
	            	case "00000011":
	            		R = 0;
	            		G = 0;
	            		B = 255;
	            		break;
	            	case "00001001":
	            		R = 255;
	            		G = 0;
	            		B = 0;
	            		break;
	            	case "00000101":
	            		R = 0;
	            		G = 255;
	            		B = 0;
	            		break;
	            		
            	}
	            	
	            	Color rgb = new Color(R, G, B);
	            	System.out.println("[eqyz"+widht + ";jgf" + hight);
	            	System.out.println(R +"|"+G+"|"+ B);   	
	                
	                 
	            	
	            	bi.setRGB(widht, hight, rgb.getRGB());
	            	inimage = bi;
	            	Gui.updateImages();
	            }
	            hight++;
	            System.out.println(wii.length);			
	          
	            }
	        } catch (IOException e) {
	            
	        }
	}

//получение директории сохранения
	public static File getOutputDirectiry() {		
		return ouputfolder;
	}
//получение директории входных файлов
	public static File getImputDirectiry() {
		return imputfolder;
	}

//загрузка картинки в главный буфер.
	public static void fileOpenToBuffer(File fileopened) throws IOException {
		BufferedImage buf = ImageIO.read( fileopened );
		inimage = buf;
		System.out.println("размеры файла" + fileopened);
		
	}
//загрузка картинки в буффер, при старте программы
	public static BufferedImage getInBuffer() {
		if (inimage==null) {
			BufferedImage bi;
			try {
				bi = ImageIO.read( new File( imputfolder+fileSeparator + "pony3.bmp"));
				inimage = bi;
			} catch (IOException e) {				
				e.printStackTrace();
			}
			
		}else {
			return inimage;	
	}
	return inimage;		
	
}
//Получение дополнительного буфера изображений
	public static BufferedImage getOutBuffer() {
		return outimage;
}

	public static void savepng(File filesaved) throws IOException {		
		ImageIO.write(inimage, "png", filesaved);
		
	}

}