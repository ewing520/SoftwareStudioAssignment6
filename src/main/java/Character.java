package main.java;

import java.awt.Color;
import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	private String name;
	private int value;
	private String colour;
	private int x=0, y;
	private int x_list,y_list;
	private int x_cir=-1,y_cir;
	private ArrayList<Network> networks = new ArrayList<Network>();
	private boolean isC = false;
	
	public Character(MainApplet parent, String name, int value, String colour, int x, int y){

		this.parent = parent;
		this.name = name;
		this.value = value;
		this.colour = colour;
		this.x_list = x;
		this.y_list = y;
		this.x = x_list; this.y = y_list;
		Ani.init(parent);
	}

	public int display(int size,int n){
		
		parent.fill(PApplet.unhex(colour.substring(1)));
		parent.ellipse(x, y, 30, 30);
		if(parent.mouseX<=x+15 && parent.mouseX>=x-15 && parent.mouseY<=y+15 && parent.mouseY>=y-15){	//mouse click
			parent.fill(0);
			parent.textSize(20);
			parent.text(name,x+15,y-15);
			
			if(parent.mousePressed) {		
				if(x_cir == -1){
					x_cir = (int) (700 + 250*Math.sin(Math.toRadians(360*n/size)));
					y_cir = (int) (350 + 250*Math.cos(Math.toRadians(360*n/size)));
					chargexy();
					return n+1;
				}
				chargexy();
			}
		}
		return n;
	}
	private void chargexy(){
		if(x == x_list)
		{
			Ani.to(this, 1 ,"x" ,x_cir);
		}
		else 
		{
			Ani.to(this, 1 ,"x" ,x_list);
		}
		
		if(y == y_list)
		{
			Ani.to(this, 1 ,"y" ,y_cir);
		}
		else 
		{
			Ani.to(this, 1 ,"y" ,y_list);
		}
		
		isC = !isC;
	}

	public int addAll(int size, int n) {
		// TODO Auto-generated method stub
		if(x_cir == -1){
			x = x_cir = (int) (700 + 250*Math.sin(Math.toRadians(360*n/size)));
			y = y_cir = (int) (350 + 250*Math.cos(Math.toRadians(360*n/size)));
			isC = true;
			return n+1;
		}
		if(x == x_list)
		{
			Ani.to(this, 1 ,"x" ,x_cir);
		}
		
		if(y == y_list)
		{
			Ani.to(this, 1 ,"y" ,y_cir);
		}

		isC = true;
		return n;
	}

	public void clear() {
		// TODO Auto-generated method stub
		if(x == x_cir)
		{
			Ani.to(this, 1 ,"x" ,x_list);
		}
		
		if(y == y_cir)
		{
			Ani.to(this, 1 ,"y" ,y_list);
		}
		isC = false;
	}
	
	public void addNetwork(Network n){
		networks.add(n);
	}
	
	public ArrayList<Network> getNetwork(){
		return this.networks;
	}
	
	
	public boolean getIsInCircle(){
		return this.isC;
	}
	public int getX(){
		return this.x;
	}
	public int getY(){
		return this.y;
	}
	
}
