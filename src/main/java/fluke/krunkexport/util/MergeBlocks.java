package fluke.krunkexport.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

public class MergeBlocks 
{
	
	public static JSONArray mergeObjs(JSONArray objs)
	{   
		
		objs = sortJSONArray(objs);
		objs = voxelMerge(objs);

		return objs;
	}
	
	private static JSONArray voxelMerge(JSONArray objs)
	{
		char[] axisList = {'y', 'x', 'z'};
		int[] axisIndexList = {1, 0, 2};
		
		System.out.println("Starting merge of " + objs.size() + " objects");
		
		for(int n=0; n<axisList.length; n++)
		{
			char mergeAxis = axisList[n];
			int axisIndex = axisIndexList[n];
			
			System.out.println("Merging " + mergeAxis + " axis");
			//while(true)
			//{
				int objsMerged = 0;
				
				for(int i=0; i<objs.size(); i++)
				{
					Map obj1 = (LinkedHashMap) objs.get(i);
					Map obj2 = null;
					int obj2Index = -1;
					ArrayList<Integer> obj1pos = (ArrayList) obj1.get("p");
					int searchPos = (int) obj1pos.get(axisIndex) + Reference.VOXEL_SIZE;
					
					while(true)
					{
						boolean mergeFailed = false;
						//obj1 = (LinkedHashMap) objs.get(i);
						
						if(mergeAxis == 'y')
						{
							ArrayList<Integer> search = new ArrayList<Integer>();
							search.add(obj1pos.get(0));
							search.add(searchPos);
							search.add(obj1pos.get(2));
				    		
							obj2Index = bsearchObjIndex(objs, search);
						}
						else if(mergeAxis == 'x')
						{
							ArrayList<Integer> search = new ArrayList<Integer>();
							search.add(searchPos);
							search.add(obj1pos.get(1));
							search.add(obj1pos.get(2));
				    		
							obj2Index = bsearchObjIndex(objs, search);
						}
						else if(mergeAxis == 'z')
						{
							ArrayList<Integer> search = new ArrayList<Integer>();
							search.add(obj1pos.get(0));
							search.add(obj1pos.get(1));
							search.add(searchPos);
				    		
							obj2Index = bsearchObjIndex(objs, search);
						}
						
						if(obj2Index == -1)
							break;
						
						obj2 = (LinkedHashMap) objs.get(obj2Index);
						ArrayList<Integer> obj1size = (ArrayList<Integer>) obj1.get("s");
						ArrayList<Integer> obj2size = (ArrayList<Integer>) obj2.get("s");
						
						if(mergeAxis == 'y')
						{
							if(obj1size.get(1) >= Reference.VOXEL_SIZE 
									&& obj2size.get(1) >= Reference.VOXEL_SIZE 
									&& obj1size.get(0) == obj2size.get(0) 
									&& obj1size.get(2) == obj2size.get(2)) 
							{
								int obj1texture = obj1.containsKey("t") ? (int) obj1.get("t") : -1;
								int obj2texture = obj2.containsKey("t") ? (int) obj2.get("t") : -1;
								int obj1color = obj1.containsKey("c") ? (int) obj1.get("c") : -1;
								int obj2color = obj2.containsKey("c") ? (int) obj2.get("c") : -1;
								double obj1opacity = obj1.containsKey("o") ? (double) obj1.get("o") : -1;
								double obj2opacity = obj2.containsKey("o") ? (double) obj2.get("o") : -1;
								int obj1col = obj1.containsKey("col") ? (int) obj1.get("col") : -1;
								int obj2col = obj2.containsKey("col") ? (int) obj2.get("col") : -1;
								
								if(obj1texture == obj2texture 
										&& obj1color == obj2color
										&& obj1opacity == obj2opacity
										&& obj1col == obj2col)
								{
									ArrayList<Integer> newsize = new ArrayList<Integer>() {{
						    			add(obj1size.get(0));
						    			add(obj1size.get(1) + obj2size.get(1));
						    			add(obj1size.get(2));
						    		}};
						    		obj1.put("s", newsize);
						    		objs.remove(obj2Index);
						    		objsMerged++;
						    		if(objsMerged % 3000 == 0)
						    			System.out.println(objsMerged);
								}
								else
								{
									mergeFailed = true;
								}
							}
							else
							{
								mergeFailed = true;
							}
							
						}
						else if(mergeAxis == 'x')
						{
							if(obj1size.get(0) >= Reference.VOXEL_SIZE 
									&& obj2size.get(0) >= Reference.VOXEL_SIZE
									&& obj1size.get(1) == obj2size.get(1) 
									&& obj1size.get(2) == obj2size.get(2)) 
							{
								int obj1texture = obj1.containsKey("t") ? (int) obj1.get("t") : -1;
								int obj2texture = obj2.containsKey("t") ? (int) obj2.get("t") : -1;
								int obj1color = obj1.containsKey("c") ? (int) obj1.get("c") : -1;
								int obj2color = obj2.containsKey("c") ? (int) obj2.get("c") : -1;
								double obj1opacity = obj1.containsKey("o") ? (double) obj1.get("o") : -1;
								double obj2opacity = obj2.containsKey("o") ? (double) obj2.get("o") : -1;
								int obj1col = obj1.containsKey("col") ? (int) obj1.get("col") : -1;
								int obj2col = obj2.containsKey("col") ? (int) obj2.get("col") : -1;
								
								if(obj1texture == obj2texture 
										&& obj1color == obj2color
										&& obj1opacity == obj2opacity
										&& obj1col == obj2col)
								{
									ArrayList<Integer> newsize = new ArrayList<Integer>() {{
						    			add(obj1size.get(0) + obj2size.get(0));
						    			add(obj1size.get(1));
						    			add(obj1size.get(2));
						    		}};
						    		obj1.put("s", newsize);
						    		
						    		ArrayList<Integer> newpos = new ArrayList<Integer>(); 
				    				newpos.add(obj1pos.get(0) + (obj2size.get(0)/2));
				    				newpos.add(obj1pos.get(1));
				    				newpos.add(obj1pos.get(2));
						    		
						    		obj1pos = newpos;
						    		obj1.put("p", newpos);
						    		objs.remove(obj2Index);
						    		objsMerged++;
								}
								else
								{
									mergeFailed = true;
								}
							}
							else
							{
								mergeFailed = true;
							}
							
						}
						else if(mergeAxis == 'z')
						{
							
							if(obj1size.get(2) >= Reference.VOXEL_SIZE 
									&& obj2size.get(2) >= Reference.VOXEL_SIZE 
									&& obj1size.get(0) == obj2size.get(0) 
									&& obj1size.get(1) == obj2size.get(1)) 
								
							{
								int obj1texture = obj1.containsKey("t") ? (int) obj1.get("t") : -1;
								int obj2texture = obj2.containsKey("t") ? (int) obj2.get("t") : -1;
								int obj1color = obj1.containsKey("c") ? (int) obj1.get("c") : -1;
								int obj2color = obj2.containsKey("c") ? (int) obj2.get("c") : -1;
								double obj1opacity = obj1.containsKey("o") ? (double) obj1.get("o") : -1;
								double obj2opacity = obj2.containsKey("o") ? (double) obj2.get("o") : -1;
								int obj1col = obj1.containsKey("col") ? (int) obj1.get("col") : -1;
								int obj2col = obj2.containsKey("col") ? (int) obj2.get("col") : -1;
								
								if(obj1texture == obj2texture 
										&& obj1color == obj2color
										&& obj1opacity == obj2opacity
										&& obj1col == obj2col)
								{
									ArrayList<Integer> newsize = new ArrayList<Integer>() {{
						    			add(obj1size.get(0));
						    			add(obj1size.get(1));
						    			add(obj1size.get(2) + obj2size.get(2));
						    		}};
						    		obj1.put("s", newsize);
						    		ArrayList<Integer> newpos = new ArrayList<Integer>(); 
						    		newpos.add(obj1pos.get(0));
						    		newpos.add(obj1pos.get(1));
						    		newpos.add(obj1pos.get(2) + (obj2size.get(2)/2));
						    		
						    		obj1pos = newpos;
						    		obj1.put("p", newpos);
						    		objs.remove(obj2Index);
						    		objsMerged++;
								}
								else
								{
									mergeFailed = true;
								}
							}
							else
							{
								mergeFailed = true;
							}
							
						}
						
						if(mergeFailed)
							break;
						
						searchPos += Reference.VOXEL_SIZE;
					}
					
				}
				
				//if(objsMerged == 0)
					//break;
				//else
					System.out.println("    " + objsMerged + " objects merged");
			//}
		}
		
		System.out.println("Finished merge with " + objs.size() + " objects");
		return objs;
	}
	
	//binary search for obj index matching pos. Returns -1 if no obj exists at pos
	private static int bsearchObjIndex(JSONArray objs, ArrayList<Integer> pos)
	{
		int min = 0;
		int max = objs.size()-1;
		int guessIndex = (min+max)/2;
		ArrayList<Integer> guessPos = (ArrayList<Integer>) ((LinkedHashMap) objs.get(guessIndex)).get("p");
		
		while (!pos.equals(guessPos))
		{
			if(guessPos.get(1) > pos.get(1))
				max--;
			else if(guessPos.get(1) < pos.get(1))
				min++;
			else if(guessPos.get(2) > pos.get(2))
				max--;
			else if(guessPos.get(2) < pos.get(2))
				min++;
			else if(guessPos.get(0) > pos.get(0))
				max--;
			else if(guessPos.get(0) < pos.get(0))
				min++;
			
			if(max < min)
				return -1;
			
			guessIndex = (min+max)/2;
			guessPos = (ArrayList<Integer>) ((LinkedHashMap) objs.get(guessIndex)).get("p");
		}
		
		return guessIndex;
	}
	
	//sorts voxel objs by y, then z, then x position
	public static JSONArray sortJSONArray(JSONArray objs)
	{
		List mapList = new ArrayList();
		for (int i = 0; i < objs.size(); i++)
			mapList.add(objs.get(i));
		
		Collections.sort(mapList, new Comparator<LinkedHashMap>() 
		{
			public int compare(LinkedHashMap one, LinkedHashMap two)
			{
				int comp = 0;
				ArrayList<Integer> al1 = (ArrayList<Integer>) one.get("p");
				ArrayList<Integer> al2 = (ArrayList<Integer>) two.get("p");
				comp = al1.get(1).compareTo(al2.get(1));
				
				if(comp == 0)
					comp = al1.get(2).compareTo(al2.get(2));
				
				if(comp == 0)
					comp = al1.get(0).compareTo(al2.get(0));
				
				return comp;
			}
		});
		
		objs = new JSONArray();
		for (int i = 0; i < mapList.size(); i++) 
			objs.add(mapList.get(i));

		return objs;
	}

}
