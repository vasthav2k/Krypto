package Cryptology;
import java.awt.*;
import java.awt.event.*;

public class VignereCipher extends Frame implements ActionListener
{
	TextField eText,plain,cipher;
	Button encrypt,decrypt;
	
	VignereCipher()
	{
		eText=new TextField(15);
		plain=new TextField(15);
		cipher=new TextField(15);
		encrypt=new Button("Encrypt");
		decrypt=new Button("Decrypt");
		
		Label l2=new Label("Key Text");
		l2.setBounds(100,140,150,30);
		add(l2);
		
		eText.setBounds(250,140,150,30);
		add(eText);
		
		Label l3=new Label("Plain Text");
		l3.setBounds(100,180,150,30);
		add(l3);
		
		plain.setBounds(250,180,150,30);
		add(plain);
		
		encrypt.setBounds(420,180,100,30);
		add(encrypt);
		
		Label l4=new Label("Cipher Text");
		l4.setBounds(100,220,150,30);
		add(l4);
		
		cipher.setBounds(250,220,150,30);
		add(cipher);
		
		decrypt.setBounds(420,220,100,30);
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
	
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//System.out.print(-3%26);
		String s=ae.getActionCommand();
		String pt=plain.getText().toLowerCase();
		String ct=cipher.getText();
		String et=eText.getText().toLowerCase();
		
		if(s.equals("Encrypt"))
		{
			cipher.setText("");
			int i=0,j=0;
			for(i=0;i<pt.length();i++)
			{
				char c=pt.charAt(i);
				char e=et.charAt(j);
				char d=(char)((e+c-97-97)%26+97);
				cipher.setText(cipher.getText()+d+"");
				j++;
				if(j==et.length())
					j=0;
			}
		}
		else if(s.equals("Decrypt"))
		{
			plain.setText("");
			int i=0,j=0;
			for(i=0;i<ct.length();i++)
			{
				char d=ct.charAt(i);
				char e=et.charAt(j);
				char c=(char)((d-e+26)%26+97);
				plain.setText(plain.getText()+c+"");
				j++;
				if(j==et.length())
					j=0;
			}
			
		}
		
		
	}
	
	public static void main(String[] args)
	{
		new VignereCipher();
	}
}
