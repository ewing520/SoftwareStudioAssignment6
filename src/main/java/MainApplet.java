package main.java;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.data.JSONArray;
import processing.data.JSONObject;

/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	private final static int width = 1200, height = 650;
	
	
	private ArrayList<Character> characters ;
	private ArrayList<Network> networks; 
	
	JSONArray nodes;
	int vision = 1;		//計算第幾代
	
	public void setup() {

		size(width, height);
		smooth();
		loadData();
	}
	
	int nodeInCircle=1;
	public void draw() {
		
		background(255); 
		for(Character character: characters){		//印出每個node
			stroke(255);
			nodeInCircle = character.display(nodes.size(),nodeInCircle);
			
			for(Network n: character.getNetwork()){			//印network ，用curve 連結
				if(character.getIsInCircle() && characters.get(n.getTarget()).getIsInCircle()){
					noFill(); stroke(0, 0, 255); strokeWeight(n.getValue()/2);
					curve(character.getX()+((character.getX()>=700)?500:-500), character.getY()+((character.getY()>=350)?500:-500),
							character.getX(),character.getY(),
							characters.get(n.getTarget()).getX(), characters.get(n.getTarget()).getY(),
							characters.get(n.getTarget()).getX()+((characters.get(n.getTarget()).getX()>=700)?500:-500), characters.get(n.getTarget()).getY()+((characters.get(n.getTarget()).getY()>=350)?500:-500));
					strokeWeight(1);
					//arc((character.getX()+characters.get(n.getTarget()).getX())/2, (character.getY()+characters.get(n.getTarget()).getY())/2,50,100,0 , PI);
				}
			}
		}
			
		
		
		noFill(); stroke(0, 255, 0); 
		ellipse(700,350,500,500); /*圓圈圈*/
		
		stroke(255, 0, 0); 		//button
		rect(1000,50,150,50);
		rect(1000,150,150,50);
		
		textSize(35); fill(0);
		text("ADD ALL",1000,85);	
		text("CLEAR",1020,185);
		
		if(mouseX<=1150 && mouseX>=1000 && mouseY<=100 && mouseY>=50 && mousePressed) { 	
			for(Character character: characters)
				nodeInCircle = character.addAll(nodes.size(),nodeInCircle);
		}
		if(mouseX<=1150 && mouseX>=1000 && mouseY<=200 && mouseY>=150 && mousePressed) {
			for(Character character: characters)
				character.clear();
		}
		
		
		text("Star Wars "+vision,500,50 );
		
	}

	private void loadData(){		//讀檔
		characters = new ArrayList<Character>();
		networks = new ArrayList<Network>();
		
		JSONObject json;
		
		JSONArray links;
		json = loadJSONObject(path+file);
		nodes = json.getJSONArray("nodes");
		links = json.getJSONArray("links");
		
		for(int i=0 ; i<nodes.size(); i++){
			JSONObject node_obj = nodes.getJSONObject(i);
			
			String name = node_obj.getString("name");
			int value = node_obj.getInt("value");
			String colour = node_obj.getString("colour");
			
			Character c = new Character(this,name,value,colour,(i%4)*40+20,(i/4)*40+40);
			characters.add(c);
			
		}
		
		for(int i=0 ; i<links.size(); i++){
			JSONObject link_obj = links.getJSONObject(i);
			
			int source = link_obj.getInt("source");
			int target = link_obj.getInt("target");
			int value = link_obj.getInt("value");
			
			Network n = new Network(this,source,target,value);
			characters.get(source).addNetwork(n);
			//networks.add(n);
		}
		
	}
	
	public void keyPressed(){		//鍵盤
		//int keyIndex = -1;
		switch(key){
		case '1': 
			file = "starwars-episode-1-interactions.json";	vision = 1;
			break;
		case '2': 
			file = "starwars-episode-2-interactions.json";	vision = 2;
			break;
		case '3': 
			file = "starwars-episode-3-interactions.json";	vision = 3;
			break;
		case '4': 
			file = "starwars-episode-4-interactions.json";	vision = 4;
			break;
		case '5': 
			file = "starwars-episode-5-interactions.json";	vision = 5;
			break;
		case '6': 
			file = "starwars-episode-6-interactions.json";	vision = 6;
			break;
		case '7': 
			file = "starwars-episode-7-interactions.json";	vision = 7;
			break;
		default: return ;
		}
		characters.clear(); networks.clear();
		loadData();
		draw();
	}

}
