/*<applet code= "Paging_gui" height=300 width=300> </applet>*/

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Paging_gui extends Applet implements ActionListener{
  Button allocate,P1,P2,P3;
	int x,y;
	int flag=0;
  TextField tp1,tp2,tp3,tp12,tp22,tp32;
  @Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		Font myFont = new Font("Impact", Font.BOLD, 12);
		P1=new Button("PROCESS1");
		P1.setBackground(Color.ORANGE);
		add(P1);
		tp1=new TextField("SIZE:40",5);
		tp1.setFont(myFont);
		add(tp1);
		tp1.setEnabled(false);
		tp12=new TextField("PAGE:3",5);
		tp12.setFont(myFont);
		add(tp12);
		tp12.setEnabled(false);
		P2=new Button("PROCESS2");
		P2.setBackground(Color.GREEN);
		add(P2);
		tp2=new TextField("SIZE:28",5);
		tp2.setFont(myFont);
		add(tp2);
		tp2.setEnabled(false);
		tp22=new TextField("PAGE:2",5);
		tp22.setFont(myFont);
		add(tp22);
		tp22.setEnabled(false);
		P3=new Button("PROCESS3");
		P3.setBackground(Color.MAGENTA);
		add(P3);
		tp3=new TextField("SIZE:45",5);
		tp3.setFont(myFont);
		add(tp3);
		tp3.setEnabled(false);
		tp32=new TextField("PAGE:3",5);
		tp32.setFont(myFont);
		add(tp32);	
		tp32.setEnabled(false);
		allocate=new Button("ALLOCATE");
		add(allocate);
	     allocate.addActionListener(this);
	     allocate.addMouseListener(new MouseAdapter() {

                        @Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);	
				x=e.getX();
				y=e.getY();
				repaint();
			}			
		});	
	}

	
  @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		for(int i=0;i<10;i++)
		{
		   g.drawRoundRect(575,30+(65*i),150,55,18,18);
		}
		if(flag==1)
		{
			
			for(int i=0;i<10;i++)
			{
				if(i==1||i==3||i==5)
				{
					g.setColor(Color.ORANGE);
				}
				else if(i==0 || i==4)
				{
					g.setColor(Color.GREEN);
				}
				else if(i==2 ||i==7 || i==9){
					g.setColor(Color.MAGENTA);
				}else{
					g.setColor(Color.LIGHT_GRAY);
				}
			   
				g.fillRoundRect(575,30+(65*i),150,55,18,18);
			}
			g.setColor(Color.BLACK);
			g.drawString("PROCESS 1:PAGE 0",600,125);
			g.drawString("PROCESS 1:PAGE 1", 600,256);
			g.drawString("PROCESS 1:PAGE 2", 600, 386);
			g.drawString("PROCESS 2:PAGE 0",600,322);
			g.drawString("PROCESS 2:PAGE 1", 600,62);
			g.drawString("PROCESS 3:PAGE 0",600,646);
			g.drawString("PROCESS 3:PAGE 1", 600,518);
			g.drawString("PROCESS 3:PAGE 2", 600, 192);
                        Font f=new Font ("arial", Font.BOLD, 20);
                        //g.setFont(f);
                        //g.drawString("Paging eliminates External Fragmentation completely", 50, 100);
                        g.setColor(Color.RED);
			g.drawString("FRAGMENTATION: 15", 50, 350);          //assuming page size to be 16
		}
	}
  @Override
	public void actionPerformed(ActionEvent g) {
		// TODO Auto-generated method stub
		if(g.getSource()==allocate)
		{
			flag=1;		
		}	
	}	
}
