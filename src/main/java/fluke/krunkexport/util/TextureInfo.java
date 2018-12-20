package fluke.krunkexport.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class TextureInfo 
{
	/*
	 * class Texture(Enum):
    WALL = 0
    DIRT = 1
    FLOOR = 2
    GRID = 3
    GREY = 4
    DEFAULT = 5
    ROOF = 6
    FLAG = 7
	 */
	
	private static Map<String, int[]> textures; 
	static 
	{
		textures = new HashMap<>();
		
		//("blockname", {texture_id, color, opacity, collidable, hasMoreComplexModel})
		textures.put("minecraft:stone", new int[] {0,0,10,1,0});
		textures.put("minecraft:cobblestone", new int[] {0,0,10,1,0});
		textures.put("minecraft:furnace", new int[] {0,0,10,1,0});
		textures.put("minecraft:dirt", new int[] {1,0,10,1,0});
		textures.put("minecraft:farmland", new int[] {1,0xBEA090,10,1,0});
		textures.put("minecraft:grass", new int[] {1,0x6CD75D,10,1,0});
		textures.put("minecraft:sand", new int[] {5,0x897946,10,1,0});
		textures.put("minecraft:gravel", new int[] {1,0x6899A0,10,1,0});
		textures.put("minecraft:clay", new int[] {5,0x6D7B7B,10,1,0});
		textures.put("minecraft:planks", new int[] {2,0,10,1,0});
		textures.put("minecraft:log", new int[] {2,0xB68E73,10,1,0});
		textures.put("minecraft:log2", new int[] {2,0xB68E73,10,1,0});
		textures.put("minecraft:leaves", new int[] {5,0x173C10,10,1,0});
		textures.put("minecraft:leaves2", new int[] {5,0x173C10,10,1,0});
		textures.put("minecraft:water", new int[] {1,0x0B8DD4,8,1,0});
		textures.put("minecraft:lava", new int[] {1,0x9B350B,10,1,0});
		textures.put("minecraft:glass", new int[] {5,0xADD3ED,4,1,0});
		textures.put("minecraft:glass_pane", new int[] {5,0xADD3ED,4,1,0});
		textures.put("minecraft:double_stone_slab", new int[] {5,0x7F7F7F,10,1,0});
		textures.put("minecraft:snow", new int[] {5,0xFFFAFA,10,1,0});
		textures.put("minecraft:brick_block", new int[] {0,0xC84B4B,10,1,0});
		textures.put("minecraft:nether_brick", new int[] {0,0x910D1D,10,1,0});
		
		//complex models
		textures.put("minecraft:fence", new int[] {2,0,10,1,1});
		textures.put("minecraft:dark_oak_fence", new int[] {2,0xACABAB,10,1,1});
		textures.put("minecraft:cobblestone_wall", new int[] {0,0,10,1,1});
		textures.put("minecraft:stone_slab", new int[] {5,0x7F7F7F,10,1,1});
		textures.put("minecraft:tallgrass", new int[] {5,0x284D22,10,0,1});
		textures.put("minecraft:wheat", new int[] {5,0x9B8625,10,0,1});
		textures.put("minecraft:beetroots", new int[] {5,0x5F1a1a,10,0,1});
		textures.put("minecraft:carrots", new int[] {5,0xA25704,10,0,1});
		textures.put("minecraft:iron_bars", new int[] {5,0x43464B,10,1,1});
		textures.put("minecraft:chest", new int[] {2,0xC3B67B,10,1,1});
		textures.put("minecraft:wooden_pressure_plate", new int[] {2,0,10,1,1});
		textures.put("minecraft:snow_layer", new int[] {5,0xFFFAFA,10,1,1});
		textures.put("minecraft:carpet", new int[] {5,0,10,1,1});
		textures.put("minecraft:grass_path", new int[] {1,0xDEC488,10,1,1});
		textures.put("minecraft:oak_stairs", new int[] {2,0,10,1,1});
		textures.put("minecraft:spruce_stairs", new int[] {2,0,10,1,1});
		textures.put("minecraft:dark_oak_stairs", new int[] {2,0,10,1,1});
		//textures.put("minecraft:spruce_stairs", new int[] {2,0xACABAB,10,1,1});
		//textures.put("minecraft:dark_oak_stairs", new int[] {2,0xACABAB,10,1,1});
		textures.put("minecraft:stone_brick_stairs", new int[] {0,0,10,1,1});
		textures.put("minecraft:brick_stairs", new int[] {0,0xC84B4B,10,1,1});
		textures.put("minecraft:nether_brick_stairs", new int[] {0,0x910D1D,10,1,1});
		textures.put("minecraft:stone_stairs", new int[] {0,0,10,1,1});
		textures.put("minecraft:red_flower", new int[] {5,0xAC1313,10,1,1});
		textures.put("minecraft:wool", new int[] {5,0,10,1,1});
		
		//ignored blocks
		textures.put("minecraft:double_plant", new int[] {-1,0,0,0,0});
		textures.put("minecraft:ladder", new int[] {-1,0,0,0,0});
		textures.put("minecraft:torch", new int[] {-1,0,0,0,0});
		textures.put("minecraft:wooden_door", new int[] {-1,0,0,0,0});
		textures.put("minecraft:iron_door", new int[] {-1,0,0,0,0});
	}
	
	public static JSONArray addTextureInfo(Map objIn, IBlockState state)
	{
		String blockName = state.getBlock().getRegistryName().toString();
		JSONArray objectsOut = new JSONArray();
		
		if(textures.containsKey(blockName))
		{
			int[] extraData = textures.get(blockName);
			
			if(extraData[0] == -1)
			{
				return null;
			}
			
			if(extraData[0] != 0)
			{
				objIn.put("t", extraData[0]);
			}
			
			if(extraData[1] != 0)
			{
				objIn.put("c", extraData[1]);
			}
			
			if(extraData[2] != 10)
			{
				objIn.put("o", extraData[2]/10.0);
			}
			
			if(extraData[3] != 1)
			{
				objIn.put("col", 1);
			}
			
			if(extraData[4] == 1)
			{
				objectsOut = complexModel(objIn, state);
			}
			else
			{
				objectsOut.add(objIn);
			}
		}
		//block isn't in texture map, default to white cube
		else
		{
			objIn.put("t", 5);
			objectsOut.add(objIn);
		}
		
		
		
		return objectsOut;
	}

	private static JSONArray complexModel(Map objIn, IBlockState state) {
		JSONArray fancyObj = new JSONArray();
		Block block = state.getBlock();
		
		//begin if statement from hell
		if(block == Blocks.STONE_SLAB)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add((Reference.VOXEL_SIZE));
		    	add((Reference.VOXEL_SIZE/2));
		    	add((Reference.VOXEL_SIZE));
	    	}};
			objIn.put("s", voxelSize);
			
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			
			//upper slab
			if(block.getMetaFromState(state) == 8)
			{
				ArrayList<Integer> newPos = new ArrayList<Integer>() {{
	    			add(oldPos.get(0));
	    			add(oldPos.get(1)+Reference.VOXEL_SIZE/2);
	    			add(oldPos.get(2));
	    		}};
	    		objIn.put("p", newPos);
			}
			
			fancyObj.add(objIn);
		}
		else if(block == Blocks.OAK_FENCE || block == Blocks.COBBLESTONE_WALL || block == Blocks.DARK_OAK_FENCE)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add((Reference.VOXEL_SIZE/4));
		    	add((Reference.VOXEL_SIZE));
		    	add((Reference.VOXEL_SIZE/4));
	    	}};
			objIn.put("s", voxelSize);
			
			fancyObj.add(objIn);
		}
		else if(block == Blocks.TALLGRASS || block == Blocks.WHEAT || block == Blocks.BEETROOTS || block == Blocks.CARROTS)
		{
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			int centerX = oldPos.get(0);
			int bottomY = oldPos.get(1);
			int centerZ = oldPos.get(2);
			
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(1);
		    	add(3);
		    	add(1);
	    	}};
			objIn.put("s", voxelSize);
			
			ArrayList<Integer> newPos = new ArrayList<Integer>() {{
    			add(centerX - 1);
    			add(bottomY);
    			add(centerZ - 2);
    		}};
    		objIn.put("p", newPos);
    		
    		fancyObj.add(objIn);
    		
    		Map grass2 = (Map) ((LinkedHashMap)objIn).clone();
    		
    		voxelSize = new ArrayList<Integer>() {{
		    	add(1);
		    	add(2);
		    	add(1);
	    	}};
	    	grass2.put("s", voxelSize);
			
			newPos = new ArrayList<Integer>() {{
    			add(centerX);
    			add(bottomY);
    			add(centerZ + 2);
    		}};
    		grass2.put("p", newPos);
    		
    		fancyObj.add(grass2);
    		
    		Map grass3 = (Map) ((LinkedHashMap)objIn).clone();
    		
    		voxelSize = new ArrayList<Integer>() {{
		    	add(1);
		    	add(4);
		    	add(1);
	    	}};
	    	grass3.put("s", voxelSize);
			
			newPos = new ArrayList<Integer>() {{
    			add(centerX + 2);
    			add(bottomY);
    			add(centerZ);
    		}};
    		grass3.put("p", newPos);
    		
    		fancyObj.add(grass3);
		}
		else if(block == Blocks.IRON_BARS)
		{
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			int centerX = oldPos.get(0);
			int bottomY = oldPos.get(1);
			int centerZ = oldPos.get(2);
			
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(1);
		    	add(Reference.VOXEL_SIZE);
		    	add(1);
	    	}};
			objIn.put("s", voxelSize);
			
			ArrayList<Integer> newPos = new ArrayList<Integer>() {{
    			add(centerX-2);
    			add(bottomY);
    			add(centerZ);
    		}};
    		objIn.put("p", newPos);
    		
    		fancyObj.add(objIn);
    		
    		Map bars2 = (Map) ((LinkedHashMap)objIn).clone();
    		
    		newPos = new ArrayList<Integer>() {{
    			add(centerX+2);
    			add(bottomY);
    			add(centerZ);
    		}};
    		bars2.put("p", newPos);
    		
    		fancyObj.add(bars2);
		}
		else if(block == Blocks.CHEST)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE-1);
		    	add(Reference.VOXEL_SIZE-1);
		    	add(Reference.VOXEL_SIZE-1);
	    	}};
			objIn.put("s", voxelSize);
			
			fancyObj.add(objIn);
		}
		else if(block == Blocks.WOODEN_PRESSURE_PLATE || block == Blocks.SNOW_LAYER)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE);
		    	add(1);
		    	add(Reference.VOXEL_SIZE);
	    	}};
			objIn.put("s", voxelSize);
			
			fancyObj.add(objIn);		
		}
		else if(block == Blocks.CARPET)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE);
		    	add(1);
		    	add(Reference.VOXEL_SIZE);
	    	}};
			objIn.put("s", voxelSize);
			
			objIn.put("c", MapColor.BLOCK_COLORS[block.getMetaFromState(state)].colorValue);
			
			fancyObj.add(objIn);		
		}
		else if(block == Blocks.WOOL)
		{
			objIn.put("c", MapColor.BLOCK_COLORS[block.getMetaFromState(state)].colorValue);
			fancyObj.add(objIn);
		}
		else if(block == Blocks.GRASS_PATH)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE);
		    	add(Reference.VOXEL_SIZE-1);
		    	add(Reference.VOXEL_SIZE);
	    	}};
			objIn.put("s", voxelSize);
			
			fancyObj.add(objIn);
		}
		else if(block == Blocks.OAK_STAIRS || block == Blocks.STONE_STAIRS || block == Blocks.SPRUCE_STAIRS || block == Blocks.DARK_OAK_STAIRS || block == Blocks.STONE_BRICK_STAIRS || block == Blocks.BRICK_STAIRS || block == Blocks.NETHER_BRICK_STAIRS)
		{
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add((Reference.VOXEL_SIZE));
		    	add((Reference.VOXEL_SIZE/2));
		    	add((Reference.VOXEL_SIZE));
	    	}};
			objIn.put("s", voxelSize);
			
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			
    		
			int stairMeta = block.getMetaFromState(state);
			int upsideDown = (stairMeta > 3) ? 0 : 1;
			stairMeta = (stairMeta > 3) ? stairMeta - 4 : stairMeta; 
			/*
				0 east
				1 west
				2 south
				3 north
			 */
			
			Map stairs2 = (Map) ((LinkedHashMap)objIn).clone();
			
			if(upsideDown == 0)
			{
				ArrayList<Integer> newPos = new ArrayList<Integer>() {{
	    			add(oldPos.get(0));
	    			add(oldPos.get(1)+Reference.VOXEL_SIZE/2);
	    			add(oldPos.get(2));
	    		}};
	    		objIn.put("p", newPos);
			}
			fancyObj.add(objIn);
			
			if(stairMeta == 0 || stairMeta == 1)
			{
	    		voxelSize = new ArrayList<Integer>() {{
			    	add((Reference.VOXEL_SIZE/2));
			    	add((Reference.VOXEL_SIZE/2));
			    	add((Reference.VOXEL_SIZE));
		    	}};
		    	stairs2.put("s", voxelSize);
		    	
		    	ArrayList<Integer> newPos;
		    	if(stairMeta == 0)
		    	{
			    	newPos = new ArrayList<Integer>() {{
		    			add(oldPos.get(0)+Reference.VOXEL_SIZE/4);
		    			add(oldPos.get(1)+Reference.VOXEL_SIZE/2*upsideDown);
		    			add(oldPos.get(2));
		    		}};
		    	}
		    	else
		    	{
		    		newPos = new ArrayList<Integer>() {{
		    			add(oldPos.get(0)-Reference.VOXEL_SIZE/4);
		    			add(oldPos.get(1)+Reference.VOXEL_SIZE/2*upsideDown);
		    			add(oldPos.get(2));
		    		}};
		    	}
	    		stairs2.put("p", newPos);
			}
			else if(stairMeta == 2 || stairMeta == 3)
			{
				voxelSize = new ArrayList<Integer>() {{
			    	add((Reference.VOXEL_SIZE));
			    	add((Reference.VOXEL_SIZE/2));
			    	add((Reference.VOXEL_SIZE/2));
		    	}};
		    	stairs2.put("s", voxelSize);
		    	
		    	ArrayList<Integer> newPos;
		    	if(stairMeta == 2)
		    	{
			    	newPos = new ArrayList<Integer>() {{
		    			add(oldPos.get(0));
		    			add(oldPos.get(1)+Reference.VOXEL_SIZE/2*upsideDown);
		    			add(oldPos.get(2)+Reference.VOXEL_SIZE/4);
		    		}};
		    	}
		    	else
		    	{
		    		newPos = new ArrayList<Integer>() {{
		    			add(oldPos.get(0));
		    			add(oldPos.get(1)+Reference.VOXEL_SIZE/2*upsideDown);
		    			add(oldPos.get(2)-Reference.VOXEL_SIZE/4);
		    		}};
		    	}
	    		stairs2.put("p", newPos);
			}
    		
    		fancyObj.add(stairs2);
		}
		else if(block == Blocks.RED_FLOWER)
		{
			Map flower2 = (Map) ((LinkedHashMap)objIn).clone();
			
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			int centerX = oldPos.get(0);
			int bottomY = oldPos.get(1);
			int centerZ = oldPos.get(2);
			
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(2);
		    	add(2);
		    	add(2);
	    	}};
			objIn.put("s", voxelSize);
			
			ArrayList<Integer> newPos = new ArrayList<Integer>() {{
    			add(centerX);
    			add(bottomY+3);
    			add(centerZ);
    		}};
    		objIn.put("p", newPos);
			
			fancyObj.add(objIn);

			voxelSize = new ArrayList<Integer>() {{
		    	add(1);
		    	add(3);
		    	add(1);
	    	}};
	    	flower2.put("s", voxelSize);
	    	flower2.put("c", 0x163C10);
	    	
	    	fancyObj.add(flower2);
			
		}
		
		return fancyObj;
	}

}
