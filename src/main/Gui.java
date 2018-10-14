package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.logging.log4j.Level;

public class Gui extends JFrame{
	//Вы бы знали, как муторно писать один и тот же код 2 раз, благо декомпилятор помог немного
	private static final long serialVersionUID = 1L;
	private static String ls = CoHexPo.ls;
	private final JFrame frame;
	private static JPanel panel1;
	private static JPanel pbyttons;
	private static JPanel pou;
	private static JLabel ioimage;
	private static JLabel gioimage;
	private static BufferedImage originalImage;
	private static BufferedImage gigasreenImage;
	private static JProgressBar progressBar;
	private JFileChooser fileChooser;
	
	//"Галочки"
	private static JCheckBox bite0;
	private static JCheckBox sysdecor;
	private static JCheckBox swingdecor;
	
	//Слайдеры РГБ
	private static JSlider sbR;
	private static JSlider sbG;
	private static JSlider sbB;
	
	//Обновлние
	 public static boolean bite0v = true;
	 public static boolean sysdecorv = false;
	 public static boolean swingdecorv = false;
	 //Размеры окна надоело мне постоянно менять, так что вынес в переменную
	 private int X = 515;
	 private int Y = 435;
	
	public Gui(){		
		//Конструктор? Да так веселее, нефиг писать статичный код, конструктор позволит вам купить асбестовую прокладку на стул!
		super("У меня жопа горит");
		Starter.log.log(Level.INFO, "Отрисовка gui");
		//Хитрожопый режим декорации
		JFrame.setDefaultLookAndFeelDecorated(swingdecorv);
		if (sysdecorv) {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
					| UnsupportedLookAndFeelException e) {				
				Starter.log.log(Level.ERROR, e);
			}
		}
		//Создаем фрейм
		frame = new JFrame("Поню в ПЗУ!");
		//Так, надо иконку добавить!
		Image image = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/assets/images/ponyI.png"));
		frame.setIconImage(image);
		//и основную панель
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());		
		//стандартные операции
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //создаём панель для кнопок, расположение слева.
        pbyttons = new JPanel();
        pbyttons.setLayout(new FlowLayout());
        //выводы
        pou = new JPanel();
        pou.setLayout(new FlowLayout());
        //добавим её на главную панель
        panel1.add(pbyttons,BorderLayout.WEST);
        panel1.add(pou,BorderLayout.EAST);
        
        //Размеры панелей
        pbyttons.setPreferredSize(new Dimension(250, 500));
        pou.setPreferredSize(new Dimension(250, 500));

        //помещаем туда кнопки
        final JButton button0 = new JButton("Инстркуция");
        pbyttons.add(button0,  FlowLayout.LEFT);
        final JButton button1 = new JButton("Открыть файл");
        pbyttons.add(button1,  FlowLayout.LEFT);
        final JButton button2 = new JButton("Сохранить в byte");
        pbyttons.add(button2,  FlowLayout.LEFT);
        final JButton button3 = new JButton("Сохранить в bin");
        pbyttons.add(button3,  FlowLayout.LEFT);
        final JButton button4 = new JButton("Рекурсивная обработка");
        pbyttons.add(button4,  FlowLayout.LEFT);
        final JButton button5 = new JButton("Преобразовать bin в png");
        pbyttons.add(button5,  FlowLayout.LEFT);
        final JButton button6 = new JButton("Преобразовать byte в png");
        pbyttons.add(button6,  FlowLayout.LEFT);
        final JButton button7 = new JButton("Сохранить картинку в png");
        pbyttons.add(button7,  FlowLayout.LEFT);
        
        final JButton button8 = new JButton("Настройки");
        button0.setPreferredSize(new Dimension(200, 25));
        button1.setPreferredSize(new Dimension(200, 25));
        button2.setPreferredSize(new Dimension(200, 25));
        button3.setPreferredSize(new Dimension(200, 25));
        button4.setPreferredSize(new Dimension(200, 25));
        button5.setPreferredSize(new Dimension(200, 25));
        button6.setPreferredSize(new Dimension(200, 25));
        button7.setPreferredSize(new Dimension(200, 25));
        button8.setPreferredSize(new Dimension(200, 25));


        sbR = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        sbR.setBorder(new TitledBorder("Чувствительность красного"));
        sbG = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        sbG.setBorder(new TitledBorder("Чувствительность зеленого"));
        sbB = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
        sbB.setBorder(new TitledBorder("Чувствительность синего"));
        pbyttons.add(sbR, FlowLayout.LEFT);
        pbyttons.add(sbG, FlowLayout.LEFT);
        pbyttons.add(sbB, FlowLayout.LEFT);
        this.fileChooser = new JFileChooser();
        progressBar = new JProgressBar();
        panel1.add(progressBar,BorderLayout.SOUTH);
        progressBar.setStringPainted(true);        
        //Слушатели кнопок
        button0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
                e.getActionCommand();  
                Starter.log.log(Level.INFO, "Отрисовка gui \"Инстркуция\"");
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
                   final File fileopened = fileChooser.getSelectedFile();                   
                   Thread thr = new Thread()
                   {
                     public void run()
                     {
                       try
                       {
                         CoHexPo.filename = fileopened.getName();
                         CoHexPo.fileOpenToBuffer(fileopened);
                         Starter.log.log(Level.INFO, "Открыт файл - " + fileopened.getName());
                       }
                       catch (IOException e)
                       {
                         Starter.log.log(Level.ERROR, e);
                       }
                       Gui.updateImages();
                     }
                   };
                   thr.start();
                }
            }
        }); 
        //Буфер в байткод(01101001)
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {            	
            	Thread thr = new Thread()
                {
                  public void run()
                  {
                    try
                    {
                      Starter.log.log(Level.INFO, "Запущено преобразование буфера в байткод");
                      CoHexPo.buffer2byte();
                    }
                    catch (IOException e)
                    {
                      Starter.log.log(Level.ERROR, e);
                    }
                  }
                };
                thr.start();
                e.getActionCommand();
            }
        }); 
      //Буфер в бин(готово для прошивки в ПЗУ)
        button3.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
            	Thread thr = new Thread()
                {
                  public void run()
                  {
                    try
                    {
                      Starter.log.log(Level.INFO, "Запущено преобразование буфера в bin");
                      CoHexPo.buffer2bin();
                    }
                    catch (IOException e)
                    {
                      Starter.log.log(Level.ERROR, e);
                    }
                  }
                };
                thr.start();
                e.getActionCommand();
                e.getModifiers();       
              }
        });
        //Рекурсивная обработка - костыль на велосипеде из костылей
        button4.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
            	Thread thr = new Thread()
                {
                  public void run()
                  {
                    Starter.log.log(Level.INFO, "Запущена рекурсивная обработка");
                    CoHexPo.initPONY();
                  }
                };
                thr.start();
                e.getActionCommand();
                e.getModifiers();     
              }
        });
        //TODO Кстати я понял, что потоки то я не завершаю) ОУТ ОФ МЕМОРИ ЭДИТИОН! Ладно, притрется!
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
                   final File fileopened = fileChooser.getSelectedFile();
                   Thread thr = new Thread()
                   {
                     public void run()
                     {
                       Starter.log.log(Level.INFO, "Запущено преобразование bin в картинку");
                       try
                       {
                         CoHexPo.bin2jpeg(fileopened);
                       }
                       catch (IOException e)
                       {
                         Starter.log.log(Level.ERROR, e);
                       }
                     }
                   };
                   thr.start();
				}       
              }
        }); 
        //Выбор файла с байткодом
        button6.addActionListener(new ActionListener() {        	
            public void actionPerformed(ActionEvent e) {            	
            	e.getActionCommand();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("Байты", "byte" );                
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(CoHexPo.getOutputDirectiry());
                JFileChooser.getDefaultLocale();
                int ret = fileChooser.showDialog(null, "Открыть файляя");                
                if (ret == JFileChooser.APPROVE_OPTION) {
                   final File fileopened = fileChooser.getSelectedFile();
                   Thread thr = new Thread()
                   {
                     public void run()
                     {                      
                       Starter.log.log(Level.INFO, "Запущено преобразование байткода в картинку");
                       CoHexPo.bite2jpeg(fileopened);
                     }
                   };
                   thr.start();
				}    
              }
        });
        //Сохранение картинки
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
                	 try
                     {
                       Starter.log.log(Level.INFO, "Сохраняем файл - " + filesaved);
                       CoHexPo.savepng(filesaved);
                     }
                     catch (IOException e1)
                     {
                       Starter.log.log(Level.ERROR, e1);
                     }
                	
                }
              }
        });  
       //Меню настроек
        button8.addActionListener(new ActionListener()
        {
          public void actionPerformed(ActionEvent e)
          {
            e.getActionCommand();
            Starter.log.log(Level.INFO, "Отрисовка gui \"Настройки\"");
            Gui.this.settungs();
          }
        });
        //еще элементы
        
        
        originalImage = CoHexPo.getInBuffer();
        gigasreenImage = CoHexPo.getGinBuffer();
        gioimage = new JLabel();
        ioimage = new JLabel();
        pou.setBorder(new TitledBorder("Результат"));
        pou.add(button8, 0);
        pou.add(ioimage, 1);
        pou.add(gioimage, 1);
        gioimage.setIcon(new ImageIcon(gigasreenImage));
        ioimage.setIcon(new ImageIcon(originalImage));
        frame.getContentPane().add(panel1);
        frame.setPreferredSize(new Dimension(this.X, this.Y));
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
		
		public void settungs()
		  {
		    JFrame.setDefaultLookAndFeelDecorated(false);
		    JFrame frame = new JFrame("Информация");
		    bite0 = new JCheckBox("Заполнение нулями");
		    sysdecor = new JCheckBox("Использование стиля системы");
		    swingdecor = new JCheckBox("Использование стилей Swing");
		    JPanel pp = new JPanel();
		    pp.add(bite0);
		    pp.add(sysdecor);
		    pp.add(swingdecor);
		    
		    frame.getContentPane().add(pp);
		    frame.setPreferredSize(new Dimension(this.X, this.Y));
		    frame.pack();
		    frame.setVisible(true);
		  }
		public static void updateImages() {	
			panel1.revalidate();
			panel1.repaint();
			originalImage = CoHexPo.getInBuffer();
			gigasreenImage = CoHexPo.getGinBuffer();
			if (gigasreenImage != null) {
			   gioimage.setIcon(new ImageIcon(gigasreenImage));
			}
			if (originalImage != null) {
			   ioimage.setIcon(new ImageIcon(originalImage));
			}
			Starter.log.log(Level.INFO, "Обновление GUI");			
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
