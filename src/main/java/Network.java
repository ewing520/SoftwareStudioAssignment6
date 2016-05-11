package main.java;

import processing.core.PApplet;

/**
* This class is used for the visualization of the network.
* Depending on your implementation, you might not need to use this class or create a class on your own.
* I used the class to draw the circle and re-arrange nodes and links.
* You will need to declare other variables.
*/
public class Network {
	
	private PApplet parent;
	private int source;
	private int target;
	private int value;

	public Network(PApplet parent, int source, int target, int value){

		this.parent = parent;
		this.source = source;
		this.target = target;
		this.value = value;
	}

	public void display(){
		//this.parent.
		
	}
	public int getTarget(){
		return this.target;
	}
	public int getValue(){
		return this.value;
	}
	
}
