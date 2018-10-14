package main;

import java.awt.EventQueue;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Starter
{
// TODO Финальная версия, что бала утеряна после порчи харда	
	public static final Logger log = LogManager.getLogger(Starter.class);
	static Gui gui;
	
	

	public static void main(String[] args)
	{
		log.log(Level.INFO, "Программа запущена");
	    EventQueue.invokeLater(new Runnable()
	    		{
	    	 public void run()
	         {
	           try
	           {
	             CoHexPo.preinitPONY();
	             Starter.gui = new Gui();
	           }
	           catch (Exception e)
	           {
	             Starter.log.log(Level.ERROR, e);
	             e.printStackTrace();
	        //азаз! Вот теперь и проверяйте синтаксис     
	        }}});}}     
	  