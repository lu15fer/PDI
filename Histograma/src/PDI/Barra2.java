package PDI;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*; 

public class Barra2 extends JFrame {
	JProgressBar current;
	int num = 0;    
        
	public Barra2(int max) {
	  super("Progress");
	  
	  JPanel pane = new JPanel();
	  pane.setLayout(new FlowLayout());
	  current = new JProgressBar(0, max);
	  current.setValue(0);
          current.setSize(700, 25);
	  current.setStringPainted(true);
	  pane.add(current);
	  setContentPane(pane);
	  }

	public void iterate(int max) {
	  while (num <= max) {
	  current.setValue(num);
	  try {
	  Thread.sleep(1);
	  } catch (InterruptedException e) { 
	    }
	  num += 1;
	  }
          current.setValue(0);
	  }
        
        public void oneMore(){
            int numero= current.getValue()+1;
            if(numero <= current.getMaximum()){
                System.out.println(numero);
                current.setValue(numero);
            }else{
                dispose();
            }
        }
        
        public void eliminar(){
            this.dispose();
        }
        
        public void addOne(int max){
            for (int i = 0; i < max; i++) {
                current.setValue(current.getValue()+1);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Barra2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
	public static void main(String[] arguments) {
	  Barra2 frame = new Barra2(2000);
	  frame.pack();
	  frame.setVisible(true);
            for (int i = 0; i < 2001; i++) {
                frame.oneMore();
              try {
                  Thread.sleep(1);
              } catch (InterruptedException ex) {
                  Logger.getLogger(Barra2.class.getName()).log(Level.SEVERE, null, ex);
              }
            }
	  }
	  }


