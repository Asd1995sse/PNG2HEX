package main;


public class Starter extends Thread {
	
	
	
	 private Gui e;

	    public Starter(Gui e) {
	        this.e = e;
	    }

	    @Override
	    public void run() {
	        while (true) {
	            if (e.isVisible()) {
	                // do the validation
	                System.out.println("validation");
	                try {
	                    Thread.sleep(1000);
	                } catch (InterruptedException e1) {
	                    break;
	                }
	            }
	        }
	    }

	

	public static void main(String[] args) {
		CoHexPo.preinitPONY();
		Gui gui = new Gui();
		
	    new Thread(new Starter(gui)).start();
	    }
	 
	
	
		
//	public static void main(String... args)throws IOException {
//		CoHexPo.preinitPONY();		
//		System.out.println("Привед - Медвед");
		 
		
		
//	}
}
		

