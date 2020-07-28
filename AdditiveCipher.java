package Cryptology;
import java.awt.*;
import java.awt.event.*;
public class AdditiveCipher extends Frame implements ActionListener
{
	TextField ekey,dkey,plain,cipher;
	Button encrypt,decrypt;
	
	AdditiveCipher()
	{
		ekey=new TextField(15);
		dkey=new TextField(15);
		plain=new TextField(15);
		cipher=new TextField(15);
		encrypt=new Button("Encrypt");
		decrypt=new Button("Decrypt");
		
		Label l1=new Label("Encryption Key");
		l1.setBounds(100,100,150,30);
		add(l1);
		
		ekey.setBounds(250,100,150,30);
		add(ekey);
		
		Label l2=new Label("Decryption Key");
		l2.setBounds(100,140,150,30);
		add(l2);
		
		dkey.setBounds(250,140,150,30);
		add(dkey);
		
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
	
	public void actionPerformed(ActionEvent evt)
	{
		try
		{
			
			String s=evt.getActionCommand();
			int ek=Integer.parseInt(ekey.getText());
			int dk=26-ek%26;
			
			dkey.setText((dk)+"");
			
			String pt=plain.getText().toLowerCase();
			String ct=cipher.getText();
			
			if(s.equals("Encrypt"))
			{
				cipher.setText("");
				for(int  i=0;i<pt.length();i++)
				{
					char c=pt.charAt(i);
					char d=(char) ((c-97+ek)%26+97);
					cipher.setText(cipher.getText()+d+"");
				}
			}
			else if(s.equals("Decrypt"))
			{
				plain.setText("");
				dk=Integer.parseInt(dkey.getText());
				for(int i=0;i<ct.length();i++)
				{
					char d=ct.charAt(i);
					char c=(char)((d-97+dk)%26+97);
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
		new AdditiveCipher();
	}
	
}
	
