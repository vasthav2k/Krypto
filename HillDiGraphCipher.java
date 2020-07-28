package Cryptology;
import java.awt.*;
import java.awt.event.*;
import java.math.*;
public class HillDiGraphCipher extends Frame implements  ActionListener
{
	TextField  plain,cipher,emat;
	Button encrypt,decrypt;
	String a="";
	String b="";
	
	public HillDiGraphCipher() throws HeadlessException
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
		
		String pt=plain.getText().toLowerCase();
		if(pt.length()%2!=0)
			pt+="x";
		
		String  et=emat.getText().toLowerCase();
		
		int kmat[][]=new int[2][2];
		int y=0;
		for(int i=0;i<2;i++)
			for(int j=0;j<2;j++)
				kmat[i][j]=et.charAt(y++)%97;
		
		if(s.equals("Encrypt"))
		{	
			a="";
			
			for(int i=0;i<pt.length();i+=2)
			{
				int pmat[][]=new int[2][1];
				pmat[0][0]=pt.charAt(i)%97;
				pmat[1][0]=pt.charAt(i+1)%97;

				int c[][]=new int[2][1];

				for(int x=0;x<2;x++)
				{
					for(y=0;y<1;y++)
					{
						for(int  z=0;z<2;z++)
							c[x][y]+=kmat[x][z]*pmat[z][y];
						c[x][y]%=26;
						a+=(char)(c[x][y]%26+97);
					}
				}
			}
			
			if(plain.getText().length()%2==0)
				cipher.setText(a);
			else
				cipher.setText(a.substring(0,a.length()-1));
		}
		else if(s.equals("Decrypt"))
		{
			b="";
			
			int det=kmat[0][0]*kmat[1][1]-kmat[0][1]*kmat[1][0];
			
			det=Integer.parseInt((new BigInteger(det+"")).modInverse(new BigInteger(26+""))+"");
			
			int dmat[][]=new int[2][2];	
			dmat[0][0]=(det*((kmat[1][1]+26)%26))%26;
			dmat[1][1]=(det*((kmat[0][0]+26)%26))%26;
			dmat[1][0]=(det*((-kmat[1][0]+26)%26))%26;
			dmat[0][1]=(det*((-kmat[0][1]+26)%26))%26;
			
			String ct=cipher.getText();
			
			if(ct.length()%2!=0)
				ct+="x";
			
			for(int i=0;i<a.length();i+=2)
			{
				int pmat[][]=new int[2][1];
				pmat[0][0]=a.charAt(i)%97;
				pmat[1][0]=a.charAt(i+1)%97;
				
				int c[][]=new int[2][1];
				
				for(int x=0;x<2;x++)
				{
					for(y=0;y<1;y++)
					{
						for(int  z=0;z<2;z++)
							c[x][y]+=dmat[x][z]*pmat[z][y];
						c[x][y]%=26;
						b+=(char)(c[x][y]%26+97);
					}
				}
			}
			
			if(cipher.getText().length()%2==0)
				plain.setText(b);
			else
				plain.setText(b.substring(0,b.length()-1));
			
		}
	}
	
	public static void main(String[] args)
	{
		new  HillDiGraphCipher();
	}
	
}
