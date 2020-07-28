package Cryptology;

import java.awt.*;
import java.awt.event.*;
import java.math.*;

public class HillTriGraphCipher extends Frame implements ActionListener
{
	TextField  plain,cipher,emat;
	Button encrypt,decrypt;
	String a="";
	String b="";
	
	public HillTriGraphCipher() throws HeadlessException
	{
		super();
		emat=new TextField(15);
		plain=new TextField(15);
		cipher=new TextField(15);
		encrypt=new Button("Encrypt");
		decrypt=new Button("Decrypt");
		
		Label l2=new Label("Key Text");
		l2.setBounds(100,140,150,30);
		add(l2);
		
		emat.setBounds(250,140,150,30);
		add(emat);
		
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
	
	public void actionPerformed(ActionEvent ae)
	{
		String s=ae.getActionCommand();
		String et=emat.getText().toLowerCase();
		String ct="";
		int[][] kmat=new int[3][3];
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
				kmat[i][j]=et.charAt(i*3+j)-97;
		}
		
		if(s.equals("Encrypt"))
		{
			a="";
			String pt=plain.getText();
			while(pt.length()%3!=0)
				pt+="x";
			
			for(int x=0;x<pt.length();x+=3)
			{
				int[][] b=new int[3][1];
				for(int i=0;i<3;i++)
					b[i][0]=pt.charAt(x+i)-97;
				
				int c[][]=new int[3][1];
				
				for(int i=0;i<3;i++)
				{
					for(int j=0;j<1;j++)
					{
						c[i][j]=0;
						for(int k=0;k<3;k++)
							c[i][j]+=kmat[i][k]*b[k][j];
						a+=(char)(c[i][j]%26+97);
					}
				}
			}
			cipher.setText(a.substring(0,plain.getText().length()));	
		}
		else if(s.equals("Decrypt"))
		{
			b="";
			int det=0;
			for(int i = 0; i < 3; i++)
				det=det+(kmat[0][i]*(kmat[1][(i+1)%3]*kmat[2][(i+2)%3]-kmat[1][(i+2)%3]*kmat[2][(i+1)%3]));
			det=Integer.parseInt((new BigInteger(det+"").modInverse(new BigInteger(26+""))+""));
			 
			int[][] inv=new int[3][3];
			for(int i = 0; i < 3; ++i)
			{
				for(int j = 0; j < 3; ++j)
					inv[i][j]=(((kmat[(j+1)%3][(i+1)%3] * kmat[(j+2)%3][(i+2)%3]) - (kmat[(j+1)%3][(i+2)%3] * kmat[(j+2)%3][(i+1)%3]))%26+26)%26;
			}
			 
			for(int i = 0; i < 3; ++i)
			{
				for(int j = 0; j < 3; ++j)
					inv[i][j]=(inv[i][j]*det)%26;
			}
			 
			for(int i=0;i<a.length();i+=3)
			{
				int pmat[][]=new int[3][1];
				pmat[0][0]=a.charAt(i)%97;
				pmat[1][0]=a.charAt(i+1)%97;
				pmat[2][0]=a.charAt(i+2)%97;
				int c[][]=new int[3][1];
				
				for(int x=0;x<3;x++)
				{
					for(int y = 0;y<1;y++)
					{
						for(int  z=0;z<3;z++)
							c[x][y]+=inv[x][z]*pmat[z][y];
						c[x][y]%=26;
						b+=(char)(c[x][y]%26+97);
					}
				}
			}
			plain.setText(b.substring(0,cipher.getText().length()));	 
		}
	}

	public static void main(String[] args)
	{
		new  HillTriGraphCipher();
	}
}
