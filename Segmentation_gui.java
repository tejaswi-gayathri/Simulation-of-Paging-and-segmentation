/*<applet code= "Segmentation_gui" height=300 width=300> </applet>*/

import java.applet.Applet;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class Segmentation_gui extends Applet implements ActionListener {
	
	Button proc1,proc2,bestfit,worstfit,firstfit;
	int x,y,flag=0;
	TextField proc1_text,proc1_data,proc1_heap;
	TextField proc2_text,proc2_data,proc2_heap;
        Label l1,l2,l3;
        @Override
	public void init() {
		// TODO Auto-generated method stub
		super.init();
		proc1= new Button("PROCESS 1:size 37");
		proc1.setBackground(Color.CYAN);
	
		proc2 = new Button("PROCESS 2:size 14");
		proc2.setBackground(Color.YELLOW);
		
		bestfit = new Button("BEST FIT");
		firstfit = new Button("FIRST FIT");
		worstfit = new Button("WORST FIT");
		
		
		proc1_text= new TextField("TEXT:6",5);
		proc1_data= new TextField("DATA:7",5);
		proc1_heap= new TextField("HEAP:24",5);
		proc2_text= new TextField("TEXT:4",5);
		proc2_data= new TextField("DATA:3",5);
		proc2_heap= new TextField("HEAP:7",5);
		
		
		proc1_text.setEnabled(false);
		proc1_data.setEnabled(false);
		proc1_heap.setEnabled(false);
		
		proc2_text.setEnabled(false);
		proc2_data.setEnabled(false);
		proc2_heap.setEnabled(false);
		bestfit.addActionListener(this);
		bestfit.addMouseListener(new MouseAdapter() {
                        @Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				repaint();	
			}
		});
		firstfit.addActionListener(this);
		firstfit.addMouseListener(new MouseAdapter() {
                @Override
		public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				repaint();	
			}
		});
		worstfit.addActionListener(this);
		worstfit.addMouseListener(new MouseAdapter() {

                        @Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);
				repaint();	
			}
		});
		
		
		add(proc1);                        //all buttons and text fields are being added
		add(proc1_text);
		add(proc1_data);
		add(proc1_heap);
		
		add(proc2);
		add(proc2_text);
		add(proc2_data);
		add(proc2_heap);
		
		add(bestfit);
		add(firstfit);
		add(worstfit);
	}
	
        @Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		g.drawString(x+","+y,x,y);
		for(int i=0;i<8;i++)
		{
		   g.drawRoundRect(575,60+(65*i),150,55,18,18);
		}
		g.drawString("HOLE 0: 19 ", 610, 95);
		g.drawString("HOLE 1: 41 ", 610, 160);
		g.drawString("HOLE 2: 21 ", 610, 223);
		g.drawString("HOLE 3: 45 ", 610, 287);
		g.drawString("HOLE 4: 23 ", 610, 353);
		g.drawString("HOLE 5: 33 ", 610, 417);
		g.drawString("HOLE 6: 26 ", 610, 482);
		g.drawString("HOLE 7: 48 ", 610, 545);
		if(flag==1)
		{
                        Font f=new Font ("arial", Font.BOLD, 30);
                        g.setFont(f);
                        g.drawString("BEST FIT", 240, 70);
                        f=new Font ("arial", Font.PLAIN, 12);
                        g.setFont(f);
			g.drawString("HOLE 0: 19 ", 480, 95);
			g.drawString("HOLE 1: 41 ", 480, 160);
			g.drawString("HOLE 2: 21 ", 480, 223);
			g.drawString("HOLE 3: 45 ", 480, 287);
			g.drawString("HOLE 4: 23 ", 480, 353);
			g.drawString("HOLE 5: 33 ", 480, 417);
			g.drawString("HOLE 6: 26 ", 480, 482);
			g.drawString("HOLE 7: 48 ", 480, 545);
			
			
			for(int i=0;i<8;i++)
			{
				if(i==00||i==2||i==6)
				{
					g.setColor(Color.CYAN);
				}
				else if(i==1||i==4||i==5)
				{
					g.setColor(Color.YELLOW);
				}
				else
				{
					g.setColor(Color.LIGHT_GRAY);
				}
			   g.fillRoundRect(575,60+(65*i),150,55,18,18);
			  
			}
			g.setColor(Color.BLACK);
			g.drawString("PROC 1: TEXT", 610, 95);
			g.drawString("PROC 1: DATA ", 610, 223);
			g.drawString("PROC 1: HEAP", 610, 482);
			g.drawString("PROC 2: TEXT ", 610, 353);
			g.drawString("PROC 2: DATA", 610, 417);
			g.drawString("PROC 2: HEAP", 610, 160);
			
			
			
			g.drawString("REMAINING: 13 ", 760, 95);
			g.drawString("REMAINING: 34 ", 760, 160);
			g.drawString("REMAINING: 14", 760, 223);
			g.drawString("HOLE 3: EMPTY", 760, 287);
			g.drawString("REMAINING: 19", 760, 353);
			g.drawString("REMAINING: 30 ", 760, 417);
			g.drawString("REMAINING: 2 ", 760, 482);
			g.drawString("HOLE 7: EMPTY ", 760, 545);
			g.setColor(Color.RED);
			g.drawString("FRAGMENTATION: 112", 610, 620);
		}
		if(flag==2)
		{
                        Font f=new Font ("arial", Font.BOLD, 30);
                        g.setFont(f);
                        g.drawString("FIRST FIT", 240, 70);
                        f=new Font ("arial", Font.PLAIN, 12);
                        g.setFont(f);
			g.drawString("HOLE 0: 19 ", 480, 95);
			g.drawString("HOLE 1: 41 ", 480, 160);
			g.drawString("HOLE 2: 21 ", 480, 223);
			g.drawString("HOLE 3: 45 ", 480, 287);
			g.drawString("HOLE 4: 23 ", 480, 353);
			g.drawString("HOLE 5: 33 ", 480, 417);
			g.drawString("HOLE 6: 26 ", 480, 482);
			g.drawString("HOLE 7: 48 ", 480, 545);
			
			for(int i=0;i<8;i++)
			{
				if(i==0||i==1||i==3)
				{
					g.setColor(Color.CYAN);
				}
				else if(i==4||i==2||i==5)
				{
					g.setColor(Color.YELLOW);
				}
				else
				{
					g.setColor(Color.LIGHT_GRAY);
				}
			   g.fillRoundRect(575,60+(65*i),150,55,18,18);
			}
				g.setColor(Color.BLACK);
			   g.drawString("PROC 1: TEXT ", 610, 95);
			   g.drawString("PROC 1: DATA", 610, 160);
			   g.drawString("PROC 1: HEAP", 610, 287);
			   
			   g.drawString("PROC 2: DATA", 610, 353);
			   g.drawString("PROC 2: TEXT", 610, 223);
			   g.drawString("PROC 2: HEAP", 610, 417);
			  
			
			   g.drawString("REMAINING: 13 ", 760, 95);
			   g.drawString("REMAINING: 34 ", 760, 160);
			   g.drawString("REMAINING: 17 ", 760, 223);
				g.drawString("REMAINING: 21 ", 760, 287);
				g.drawString("REMAINING: 20 ", 760, 353);
				g.drawString("REMAINING: 26 ", 760, 417);
				g.drawString("HOLE 6: EMPTY ", 760, 482);
				g.drawString("HOLE 7: EMPTY", 760, 545);
				
				g.setColor(Color.RED);
				g.drawString("FRAGMENTATION: 131", 610, 620);
			
		}
		
		if(flag==3)
		{
			Font f=new Font ("arial", Font.BOLD, 30);
                        g.setFont(f);
                        g.drawString("WORST FIT", 240, 70);
                        f=new Font ("arial", Font.PLAIN, 12);
                        g.setFont(f);
			g.drawString("HOLE 0: 19 ", 480, 95);
			g.drawString("HOLE 1: 41 ", 480, 160);
			g.drawString("HOLE 2: 21 ", 480, 223);
			g.drawString("HOLE 3: 45 ", 480, 287);
			g.drawString("HOLE 4: 23 ", 480, 353);
			g.drawString("HOLE 5: 33 ", 480, 417);
			g.drawString("HOLE 6: 26 ", 480, 482);
			g.drawString("HOLE 7: 48 ", 480, 545);
			
			for(int i=0;i<8;i++)
			{
				if(i==1||i==3||i==7)
				{
					g.setColor(Color.CYAN);
				}
				else if(i==2||i==5||i==6)
				{
					g.setColor(Color.YELLOW);
				}
				else
				{
					g.setColor(Color.LIGHT_GRAY);
				}
			   g.fillRoundRect(575,60+(65*i),150,55,18,18);
			}
			g.setColor(Color.BLACK);
			g.drawString("PROC 1: TEXT", 610, 545);
			g.drawString("PROC 1: DATA", 610, 287);
			g.drawString("PROC 1: HEAP ", 610, 160);
			
			g.drawString("PROC 2: TEXT", 610, 417);
			g.drawString("PROC 2: DATA", 610, 482);
			g.drawString("PROC 2: HEAP", 610, 223);
			
			g.drawString("HOLE 0: EMPTY", 760, 95);
			g.drawString("REMAINING: 17", 760, 160);
			g.drawString("REMAINING: 14", 760, 223);
			g.drawString("REMAINING: 38", 760, 287);
			g.drawString("HOLE 4: EMPTY", 760, 353);
			g.drawString("REMAINING: 29", 760, 417);
			g.drawString("REMAINING: 23 ", 760, 482);
			g.drawString("REMAINING: 42", 760, 545);
			g.setColor(Color.RED);
			g.drawString("FRAGMENTATION: 163", 610, 620);
		}	
	}
        @Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bestfit)
		{
			flag=1;
		}
		if(e.getSource()==firstfit)
		{
			flag=2;
		}
		if(e.getSource()==worstfit)
		{
			flag=3;
		}
	}
}
