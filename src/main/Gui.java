package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui extends JFrame{
	private static final long serialVersionUID = 1L;
	private static String ls = CoHexPo.ls;
	final JFrame frame;
	static JPanel panel1;
	static JPanel pbyttons;
	static JPanel pou;
	static JLabel ioimage;
	static BufferedImage originalImage;
	static JProgressBar progressBar;
	JFileChooser fileChooser;
	
	//Слайдеры РГБ
	private static JSlider sbR;
	private static JSlider sbG;
	private static JSlider sbB;
	
	public Gui(){		
		//Конструктор? Да так веселее, нефиг писать статичный код, конструктор позволит вам купить асбестовую прокладку на стул!
		super("У меня жопа горит");
		//Хитрожопый режим декорации
		JFrame.setDefaultLookAndFeelDecorated(false);
		//Создаем фрейм
		frame = new JFrame("Поню в ПЗУ!");
		//и основную панель
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());		
		//стандартные операции
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //создаём панель дял кнопок, расположение слева.
        pbyttons = new JPanel();
        pbyttons.setLayout(new FlowLayout());
        //выводы
        pou = new JPanel();
        pou.setLayout(new FlowLayout());
        //добавим её на главную панель
        panel1.add(pbyttons,BorderLayout.WEST );
        panel1.add(pou,BorderLayout.CENTER );
        
        
        pbyttons.setPreferredSize(new Dimension(250,500));
      //  pbyttons.setMinimumSize(new Dimension(50,10));
       // pbyttons.setMaximumSize(new Dimension(200,10));
        
        //помещаем туда кнопки
        final JButton button0 = new JButton("Инстркуция");
        pbyttons.add(button0, FlowLayout.LEFT); 
        final JButton button1 = new JButton("Открыть файл");
        pbyttons.add(button1,  FlowLayout.LEFT);
        final JButton button2 = new JButton("Сохранить в byte");
        pbyttons.add(button2, FlowLayout.LEFT);
        final JButton button3 = new JButton("Сохранить в bin");
        pbyttons.add(button3, FlowLayout.LEFT);        
        final JButton button4 = new JButton("Рекурсивная обработка");
        pbyttons.add(button4, FlowLayout.LEFT);  
        final JButton button5 = new JButton("Преобразовать bin в png");
        pbyttons.add(button5, FlowLayout.LEFT);
        final JButton button6 = new JButton("Преобразовать byte в png");
        pbyttons.add(button6, FlowLayout.TRAILING);
        final JButton button7 = new JButton("Созранить картинку в png");
        pbyttons.add(button7, FlowLayout.TRAILING);
        button0.setPreferredSize(new Dimension(200,25));
        button1.setPreferredSize(new Dimension(200,25));
        button2.setPreferredSize(new Dimension(200,25));
        button3.setPreferredSize(new Dimension(200,25));
        button4.setPreferredSize(new Dimension(200,25));
        button5.setPreferredSize(new Dimension(200,25));
        button6.setPreferredSize(new Dimension(200,25));
        button7.setPreferredSize(new Dimension(200,25));


        sbR = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        sbR.setBorder(new TitledBorder("Чувствительность красного"));
        sbG = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        sbG.setBorder(new TitledBorder("Чувствительность зеленого"));
        sbB = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        sbB.setBorder(new TitledBorder("Чувствительность синего"));
        pbyttons.add(sbR, FlowLayout.LEFT);
        pbyttons.add(sbG, FlowLayout.LEFT);
        pbyttons.add(sbB, FlowLayout.LEFT);
        fileChooser = new JFileChooser();
        progressBar = new JProgressBar();
        panel1.add(progressBar,BorderLayout.SOUTH);
        progressBar.setStringPainted(true);        
        //Слушатели кнопок
        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();  
                info(); 
            }
        }); 
        //открыть файл
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();          
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "Картинки", "png", "jpg", "bmp","jpeg", "gif", "ico" );                
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(CoHexPo.getImputDirectiry());
                JFileChooser.getDefaultLocale();
                int ret = fileChooser.showDialog(null, "Открыть файл");               
                if (ret == JFileChooser.APPROVE_OPTION) {
                   File fileopened = fileChooser.getSelectedFile();
                   try {
                	   CoHexPo.filename = fileopened.getName();
					CoHexPo.fileOpenToBuffer(fileopened);
				} catch (IOException e1) {					
					e1.printStackTrace();
				}
				updateImages();
                }
            }
        }); 
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();  
                try {
					CoHexPo.buffer2byte();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        }); 
        button3.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();
                e.getModifiers();
                try {
					CoHexPo.buffer2bin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}        
              }
        });  
        button4.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();
                e.getModifiers();
                Thread guruthread1 = new Thread();
                guruthread1.start();
                CoHexPo.initPONY();        
              }
        });
        //из бинарника в картинку
        button5.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();
                e.getModifiers();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Бинарные файлы", "bin" );                
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(CoHexPo.getOutputDirectiry());
                JFileChooser.getDefaultLocale();
                int ret = fileChooser.showDialog(null, "Открыть файл");                
                if (ret == JFileChooser.APPROVE_OPTION) {
                   File fileopened = fileChooser.getSelectedFile();
                   try {
					CoHexPo.bin2jpeg(fileopened);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateImages();  
				}       
              }
        }); 
        button6.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
            	e.getActionCommand();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Байты", "byte" );                
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(CoHexPo.getOutputDirectiry());
                JFileChooser.getDefaultLocale();
                int ret = fileChooser.showDialog(null, "Открыть файл");                
                if (ret == JFileChooser.APPROVE_OPTION) {
                   File fileopened = fileChooser.getSelectedFile();
                   CoHexPo.bite2jpeg(fileopened);
				updateImages();
				}    
              }
        });
        
        button7.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();
                e.getModifiers();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("png", "png" );                
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(CoHexPo.getOutputDirectiry());                
                JFileChooser.getDefaultLocale();
                int ret = fileChooser.showDialog(null, "Сохранить картинку");
                if (ret == JFileChooser.APPROVE_OPTION) {                 	
                	File filesaved = fileChooser.getSelectedFile();
                    try {
						CoHexPo.savepng(filesaved);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	
                }
              }
        });  
        originalImage = CoHexPo.getInBuffer();
        
        ioimage = new JLabel("Результат");
        pou.add(ioimage,BorderLayout.SOUTH);
        ioimage.setIcon(new ImageIcon(originalImage));      
        
                   
        frame.getContentPane().add(panel1); 
        frame.setPreferredSize(new Dimension(580, 435));        
        frame.pack();
        frame.setVisible(true);    
        return;
	}
	 
		public void info(){				
			JFrame.setDefaultLookAndFeelDecorated(false);			
			JFrame frame = new JFrame("Информация");
			JTextArea area = new JTextArea(15, 10);
	        area.setText("Инструкция:" +ls
					+ " Кнопка «Открыть файл», открывает файл для одиночной обработки. "+ls
					+ " Кнопка «Рекурсивная обработка», берет файлы из папки imputs и готовые помещает в папку outputs. "+ls
					+ " Кнопка «Сохранить байткод» создает файл с содержимым 0000RGBY. "+ls
					+ " Кнопка «Сохранить в hex» создает бинарный файл с данными. "+ls
					+ " Внимание! Все кнопки, кроме рекурсии, работают исключительно с одним файлом, который надо выбрать с помощью кнопки «Открыть файл»");	        
	        area.setLineWrap(true);
	        area.setWrapStyleWord(true);	     
	        frame.getContentPane().add(area);			
			frame.setPreferredSize(new Dimension(580, 435));        
	        frame.pack();
	        frame.setVisible(true);	        
		}
		public static void updateImages() {	
			
			panel1.revalidate();
			panel1.repaint();	
			originalImage = CoHexPo.getInBuffer();
			if(originalImage!=null) {
			ioimage.setIcon(new ImageIcon(originalImage));
			}
			System.out.println("repaint");
			
		}
	
		public static void updateBar(int max, int value) {
			progressBar.setMinimum(0);
	        progressBar.setMaximum(max);
	        progressBar.setValue(value);
		}
public static int getRs(){
	
	return sbR.getValue();
	
}
public static int getGs(){
	
	return sbG.getValue();
	
}
public static int getBs(){
	
	return sbB.getValue();
	
}
		
}
