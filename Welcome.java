package Cryptology;
import java.awt.*;
import  java.awt.event.*;


public class Welcome extends Frame implements ActionListener
{
	AdditiveCipher ac;
	MultiplicativeCipher mc;
	AffineCipher afc;
	VignereCipher  vc;
	HillDiGraphCipher hc2;
	HillTriGraphCipher hc3;
	Welcome()
	{
		Label l1=new Label();
		l1.setAlignment(Label.CENTER);
		l1.setText("Welcome");
		add(l1);
		l1.setBounds(100,400,400,100);
		
		MenuBar mbar = new MenuBar(); 
		setMenuBar(mbar);
		
		Menu monoalpha,polyalpha;
		monoalpha=new Menu("Mono Alphabetic");
		polyalpha=new Menu("Poly Alphabetic");
		mbar.add(monoalpha);
		mbar.add(polyalpha);
		
		MenuItem item1, item2, item3,item4,item5,item6;
		item1=new  MenuItem("Additive Cipher");
		item2=new  MenuItem("Multiplicative Cipher");
		item3=new  MenuItem("Affine Cipher");
		
		item4=new  MenuItem("Vignere Cipher");
		item5=new  MenuItem("Hill Di Graph Cipher");
		item6=new  MenuItem("Hill Tri Graph Cipher");
		
		monoalpha.add(item1);
		monoalpha.add(item2);
		monoalpha.add(item3);
		
		polyalpha.add(item4);
		polyalpha.add(item5);
		polyalpha.add(item6);
		
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we) 
			{ 
				System.exit(0);	
			} 
		});
		
		item1.addActionListener(this); 
		item2.addActionListener(this); 
		item3.addActionListener(this);
		item4.addActionListener(this); 
		item5.addActionListener(this); 
		item6.addActionListener(this); 
		
		setForeground(Color.black);
		setFont(new Font("Arial", Font.BOLD, 20)); 
		setLayout(null);
		setSize(600, 800); 
		setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		if(s.equals("Additive Cipher"))
			new  AdditiveCipher();
		else  if(s.equals("Multiplicative Cipher"))
			new  MultiplicativeCipher();
		else if(s.equals("Affine Cipher"))
			new  AffineCipher();
		else if(s.equals("Vignere Cipher"))
			new VignereCipher();
		else if(s.equals("Hill Di Graph Cipher"))
			new  HillDiGraphCipher();
		else if(s.equals("Hill Tri Graph Cipher"))
			new  HillTriGraphCipher();
	}
	
	public  static  void main(String[]  args)
	{
		new Welcome();
	}
}
