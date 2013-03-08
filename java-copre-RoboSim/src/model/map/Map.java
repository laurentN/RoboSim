package model.map;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Map {
	private boolean[][] grid;
	private final int height=40;
	private final int width=40;
	
	public Map()
	{
		grid=new boolean[width][height];
		for (int i=0;i<grid.length;i++)
		{
			for (int j=0;j<grid[0].length;j++)
			{
				if((i+j)%2==1){grid[i][j]=true;}
				else {grid[i][j]=false;}
			}
		}
	}
	
	public void setGrid(boolean[][] grid){this.grid=grid;}
	public boolean[][] getGrid(){return grid;}
	public void setGridCell(int i, int j, boolean value){grid[i][j]=value;}
	
	/*
	 * Save map to a file
	 */
	public void save(String path)
	{
		try {
			FileWriter file=new FileWriter(new File(path));
			String content="type octile\nheight "+height+"\nwidth "+width+"\nmap\n";
			for (int i=0;i<grid.length;i++)
			{
				for (int j=0;j<grid[0].length;j++)
				{
					if (grid[i][j]) {content+="@";}
					else {content+=".";}
				}
				content+="\n";
			}
			file.write(content);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Map loading from a file
	 */
	public void load(String path) throws MapException
	{
		try {
			String line;
			int i=0;
			InputStream ips=new FileInputStream(path); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			br.readLine();	//first line type octile (not used)
			
			String heightLine=br.readLine();	//second line height		
			String height[] = heightLine.split(" ");
			if (this.height!=Integer.valueOf(height[1]))
			{
				br.close();
				throw new MapException("Wrong height");
			}
			
			String widthLine=br.readLine();	//third line width
			String width[] = widthLine.split(" ");
			if (this.width!=Integer.valueOf(width[1])) 
			{
				br.close();
				throw new MapException("Wrong width");
			}
			
			br.readLine(); //fourth line map (not used)
			while ((line=br.readLine())!=null){
				if (line.length()!=grid[0].length)
				{
					br.close();
					throw new MapException("Malformed Map : wrong line size");
				}
				for (int j=0;j<line.length();j++)
				{
					if (line.charAt(j)=='@') {grid[i][j]=true;}			//obstacle
					else if (line.charAt(j)=='.') {grid[i][j]=false;}	//empty
					else
					{
						br.close();
						throw new MapException("Malformed map : character error");
					}
				}
				i++;
			}
			br.close();
			if (i!=grid.length){throw new MapException("Malformed map : not enough lines"); }
			if (!checkWall()){throw new MapException("Malformed map : border error");}
			System.out.println(showMap());
		} catch (IOException e) {
			e.printStackTrace();
		}
		catch (IndexOutOfBoundsException e){throw new MapException("Malformed map : too many lines");}
	}
	
	private boolean checkWall()
	{
		boolean result=true;
		for (int j=0;j<grid[0].length;j++)		//first and last lines
		{
			if (!grid[0][j]){result=false;}		//if it is not a wall, incorrect map
			if (!grid[grid.length-1][j]){result=false;}
		}
		for (int i=0;i<grid.length;i++)		//left and right lines
		{
			if (!grid[i][0]){result=false;}
			if (!grid[i][grid[0].length-1]){result=false;}
		}
		return result;
	}
	
	public String showMap()
	{
		String s="";
		for (int i=0;i<grid.length;i++)
		{
			for (int j=0;j<grid[0].length;j++)
			{
				if (grid[i][j]) {s+="@";}
				else {s+=".";}
			}
			s+="\n";
		}
		return s;
	}
}
