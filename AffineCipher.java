package Cryptology;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

public class AffineCipher extends Frame implements ActionListener
{
	TextField akey,bkey,plain,cipher;
	Button encrypt,decrypt;
	
	AffineCipher()
	{
		akey=new TextField(15);
		bkey=new TextField(15);
		plain=new TextField(15);
		cipher=new TextField(15);
		encrypt=new Button("Encrypt");
		decrypt=new Button("Decrypt");
		
		Label l1=new Label("Encryption Key A");
		l1.setBounds(100,100,170,30);
		add(l1);
		
		akey.setBounds(270,100,150,30);
		add(akey);
		
		Label l2=new Label("Encryption Key B");
		l2.setBounds(100,140,170,30);
		add(l2);
		
		bkey.setBounds(270,140,150,30);
		add(bkey);
		
		Label l3=new Label("Plain Text");
		l3.setBounds(100,180,150,30);
		add(l3);
		
		plain.setBounds(270,180,150,30);
		add(plain);
		
		encrypt.setBounds(440,180,100,30);
		add(encrypt);
		
		Label l4=new Label("Cipher Text");
		l4.setBounds(100,220,150,30);
		add(l4);
		
		cipher.setBounds(270,220,150,30);
		add(cipher);
		
		decrypt.setBounds(440,220,100,30);
		add(decrypt);
		
		encrypt.addActionListener(this);
		decrypt.addActionListener(this);
		
		setLayout(null);
		setSize(600,800);
		setVisible(true);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				dispose();
			}
		});
		
		new Font("Arial",Font.BOLD,20);
		setFont(new Font("Arial",Font.BOLD,20));
		
	}
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			
			String s=evt.getActionCommand();
			int a=Integer.parseInt(akey.getText());
			int b=Integer.parseInt(bkey.getText());
			
			String pt=plain.getText().toLowerCase();
			String ct=cipher.getText();
			
			if(s.equals("Encrypt"))
			{
				cipher.setText("");
				for(int  i=0;i<pt.length();i++)
				{
					char c=pt.charAt(i);
					char d=(char)((a*(c-97)+b)%26+97);
					cipher.setText(cipher.getText()+d+"");
				}
			}
			else if(s.equals("Decrypt"))
			{
				BigInteger ba=new BigInteger(a+"");
				int  ainv=Integer.parseInt(ba.modInverse(new BigInteger(26+""))+"");
				System.out.println(ainv);
				plain.setText("");
				for(int i=0;i<ct.length();i++)
				{
					char d=ct.charAt(i);
					char c=(char)((((ainv*(d-97-b))%26)+26)%26+97);
					System.out.println((((ainv*(d-97-b))%26)+26)%26);
					plain.setText(plain.getText()+c+"");
				}
			}
				
		}
		catch(NumberFormatException e)
		{
			
		}
			
	}
	public static void main(String[] args)
	{
		new AffineCipher();
	}

}