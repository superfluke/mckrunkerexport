package fluke.krunkexport.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.DimensionManager;

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
		textures.put("minecraft:stonebrick", new int[] {0,0,10,1,0});
		textures.put("minecraft:furnace", new int[] {0,0,10,1,0});
		textures.put("minecraft:dirt", new int[] {1,0,10,1,0});
		textures.put("minecraft:farmland", new int[] {1,0xBEA090,10,1,0});
		textures.put("minecraft:grass", new int[] {1,0x6CD75D,10,1,0});
		textures.put("minecraft:sand", new int[] {5,0x897946,10,1,0});
		textures.put("minecraft:sandstone", new int[] {5,0x897946,10,1,0});
		textures.put("minecraft:red_sandstone", new int[] {1,0xFF8700,10,1,0});
		textures.put("minecraft:gravel", new int[] {1,0x6899A0,10,1,0});
		textures.put("minecraft:clay", new int[] {5,0x6D7B7B,10,1,0});
		textures.put("minecraft:noteblock", new int[] {2,0,10,1,0});
		textures.put("minecraft:planks", new int[] {2,0,10,1,0});
		textures.put("minecraft:log", new int[] {2,0xB68E73,10,1,0});
		textures.put("minecraft:log2", new int[] {2,0xB68E73,10,1,0});
		textures.put("minecraft:jukebox", new int[] {2,0,10,1,0});
		textures.put("minecraft:leaves", new int[] {5,0x173C10,10,1,0});
		textures.put("minecraft:leaves2", new int[] {5,0x173C10,10,1,0});
		textures.put("minecraft:water", new int[] {1,0x0B8DD4,8,0,0});
		textures.put("minecraft:lava", new int[] {1,0x9B350B,10,1,0});
		textures.put("minecraft:glass", new int[] {5,0xADD3ED,4,1,0});
		textures.put("minecraft:stained_glass", new int[] {5,0xADD3ED,4,1,0});
		textures.put("minecraft:glass_pane", new int[] {5,0xADD3ED,4,1,0});
		textures.put("minecraft:stained_glass_pane", new int[] {5,0xADD3ED,4,1,0});
		textures.put("minecraft:double_stone_slab", new int[] {5,0x7F7F7F,10,1,0});
		textures.put("minecraft:double_wooden_slab", new int[] {2,0,10,1,0});
		textures.put("minecraft:snow", new int[] {5,0xFFFAFA,10,1,0});
		textures.put("minecraft:quartz_block", new int[] {5,0xFFFAFA,10,1,0});
		textures.put("minecraft:brick_block", new int[] {0,0xC84B4B,10,1,0});
		textures.put("minecraft:nether_brick", new int[] {0,0x910D1D,10,1,0});
		textures.put("minecraft:iron_block", new int[] {4,0xA0A0A0,10,1,0});
		textures.put("minecraft:obsidian", new int[] {0,0x6F0F75,10,1,0});
		textures.put("minecraft:bedrock", new int[] {0,0x505050,10,1,0});
		textures.put("minecraft:netherrack", new int[] {0,0xD23838,10,1,0});
		textures.put("minecraft:coal_block", new int[] {1,0x1E1E22,10,1,0});
		textures.put("minecraft:quartz_block", new int[] {5,0xBECAD4,10,1,0});
		
		//complex models
		textures.put("minecraft:fence", new int[] {2,0,10,1,1});
		textures.put("minecraft:spruce_fence", new int[] {2,0,10,1,1});
		textures.put("minecraft:birch_fence", new int[] {2,0,10,1,1});
		textures.put("minecraft:dark_oak_fence", new int[] {2,0xACABAB,10,1,1});
		textures.put("minecraft:nether_brick_fence", new int[] {0,0x910D1D,10,1,1});
		textures.put("minecraft:cobblestone_wall", new int[] {0,0,10,1,1});
		textures.put("minecraft:stone_slab", new int[] {5,0x7F7F7F,10,1,1});
		textures.put("minecraft:wooden_slab", new int[] {2,0,10,1,1});
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
		textures.put("minecraft:sandstone_stairs", new int[] {5,0x897946,10,1,1});
		textures.put("minecraft:jungle_stairs", new int[] {2,0,10,1,1});
		textures.put("minecraft:birch_stairs", new int[] {2,0,10,1,1});
		textures.put("minecraft:stone_brick_stairs", new int[] {0,0,10,1,1});
		textures.put("minecraft:brick_stairs", new int[] {0,0xC84B4B,10,1,1});
		textures.put("minecraft:nether_brick_stairs", new int[] {0,0x910D1D,10,1,1});
		textures.put("minecraft:stone_stairs", new int[] {0,0,10,1,1});
		textures.put("minecraft:quartz_stairs", new int[] {5,0xFFFAFA,10,1,1});
		textures.put("minecraft:red_flower", new int[] {5,0xAC1313,10,1,1});
		textures.put("minecraft:wool", new int[] {5,0,10,1,1});
		textures.put("minecraft:hardened_clay", new int[] {5,0,10,1,1});
		textures.put("minecraft:stained_hardened_clay", new int[] {1,0,10,1,1});
		textures.put("minecraft:anvil", new int[] {5,0x2A2A2A,10,1,1});
		textures.put("minecraft:redstone_lamp", new int[] {5,0x939366,10,1,1});
		textures.put("minecraft:lit_redstone_lamp", new int[] {5,0x939366,10,1,1});
		textures.put("minecraft:glowstone", new int[] {5,0x939366,10,1,1});
		
		//ignored blocks
		textures.put("minecraft:double_plant", new int[] {-1,0,0,0,0});
		textures.put("minecraft:ladder", new int[] {-1,0,0,0,0});
		textures.put("minecraft:torch", new int[] {-1,0,0,0,0});
		textures.put("minecraft:wooden_door", new int[] {-1,0,0,0,0});
		textures.put("minecraft:birch_door", new int[] {-1,0,0,0,0});
		textures.put("minecraft:dark_oak_door", new int[] {-1,0,0,0,0});
		textures.put("minecraft:iron_door", new int[] {-1,0,0,0,0});
		textures.put("minecraft:lever", new int[] {-1,0,0,0,0});
		textures.put("minecraft:rail", new int[] {-1,0,0,0,0});
		textures.put("minecraft:golden_rail", new int[] {-1,0,0,0,0});
		textures.put("minecraft:web", new int[] {-1,0,0,0,0});
		textures.put("minecraft:redstone_lamp", new int[] {-1,0,0,0,0});
		textures.put("minecraft:waterlily", new int[] {-1,0,0,0,0});
		textures.put("minecraft:sign", new int[] {-1,0,0,0,0});
		textures.put("minecraft:wall_sign", new int[] {-1,0,0,0,0});
		textures.put("minecraft:stone_button", new int[] {-1,0,0,0,0});
		textures.put("minecraft:wooden_button", new int[] {-1,0,0,0,0});
		textures.put("minecraft:barrier", new int[] {-1,0,0,0,0});
		textures.put("minecraft:flower_pot", new int[] {-1,0,0,0,0});
		textures.put("minecraft:trapdoor", new int[] {-1,0,0,0,0});
		textures.put("minecraft:dark_oak_fence_gate", new int[] {-1,0,0,0,0});
		textures.put("minecraft:tripwire_hook", new int[] {-1,0,0,0,0});
		textures.put("minecraft:hopper", new int[] {-1,0,0,0,0});
		textures.put("minecraft:bookshelf", new int[] {-1,0,0,0,0});
		textures.put("minecraft:hay_block", new int[] {-1,0,0,0,0});
		textures.put("minecraft:wall_banner", new int[] {-1,0,0,0,0});
	}
	
	public static JSONArray addTextureInfo(Map objIn, IBlockState state, BlockPos pos)
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
				objectsOut = complexModel(objIn, state, pos);
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
			objIn.put("c", 0x840000);
			objectsOut.add(objIn);
		}
		
		
		
		return objectsOut;
	}

	private static JSONArray complexModel(Map objIn, IBlockState state, BlockPos pos) {
		JSONArray fancyObj = new JSONArray();
		Block block = state.getBlock();
		
		//begin if statement from hell
		if(block == Blocks.STONE_SLAB || block == Blocks.WOODEN_SLAB)
		{
			int meta = block.getMetaFromState(state);
			
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add((Reference.VOXEL_SIZE));
		    	add((Reference.VOXEL_SIZE/2));
		    	add((Reference.VOXEL_SIZE));
	    	}};
			objIn.put("s", voxelSize);
			
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			
			//brick slabs
			if(meta == 4 || (meta - 8) == 4)
			{
				objIn.put("c", 0xC84B4B);
				objIn.remove("t");
			}
			
			//upper slab
			if(meta >= 8)
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
		else if(block == Blocks.OAK_FENCE || block == Blocks.COBBLESTONE_WALL || block == Blocks.DARK_OAK_FENCE || block == Blocks.NETHER_BRICK_FENCE || block == Blocks.SPRUCE_FENCE || block == Blocks.BIRCH_FENCE)
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
			state = state.getActualState(DimensionManager.getWorld(0), pos);
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
			
			if(state.getValue(BlockFence.EAST) || state.getValue(BlockFence.WEST))
			{
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
			else if((state.getValue(BlockFence.NORTH) || state.getValue(BlockFence.SOUTH)))
			{
				ArrayList<Integer> newPos = new ArrayList<Integer>() {{
	    			add(centerX);
	    			add(bottomY);
	    			add(centerZ-2);
	    		}};
	    		objIn.put("p", newPos);
	    		
	    		fancyObj.add(objIn);
	    		
	    		Map bars2 = (Map) ((LinkedHashMap)objIn).clone();
	    		
	    		newPos = new ArrayList<Integer>() {{
	    			add(centerX);
	    			add(bottomY);
	    			add(centerZ+2);
	    		}};
	    		bars2.put("p", newPos);
	    		
	    		fancyObj.add(bars2);
				
			}
			else
			{
				fancyObj.add(objIn);
			}
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
		else if(block == Blocks.WOOL || block == Blocks.HARDENED_CLAY || block == Blocks.STAINED_HARDENED_CLAY)
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
		else if(block == Blocks.OAK_STAIRS || block == Blocks.STONE_STAIRS || block == Blocks.SPRUCE_STAIRS || block == Blocks.DARK_OAK_STAIRS || block == Blocks.STONE_BRICK_STAIRS || block == Blocks.BRICK_STAIRS || block == Blocks.NETHER_BRICK_STAIRS || block == Blocks.SANDSTONE_STAIRS || block == Blocks.JUNGLE_STAIRS || block == Blocks.QUARTZ_STAIRS)
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
		else if(block == Blocks.ANVIL)
		{
			ArrayList<Integer> oldPos = (ArrayList<Integer>) objIn.get("p");
			int centerX = oldPos.get(0);
			int bottomY = oldPos.get(1);
			int centerZ = oldPos.get(2);
			
			ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE-1);
		    	add(2);
		    	add(Reference.VOXEL_SIZE-1);
	    	}};
			objIn.put("s", voxelSize);
    		
    		fancyObj.add(objIn);
    		
    		Map anvilMid = (Map) ((LinkedHashMap)objIn).clone();
    		
    		voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE/2);
		    	add(3);
		    	add(Reference.VOXEL_SIZE/2);
	    	}};
	    	anvilMid.put("s", voxelSize);
    		
    		ArrayList<Integer> newPos = new ArrayList<Integer>() {{
    			add(centerX);
    			add(bottomY+2);
    			add(centerZ);
    		}};
    		anvilMid.put("p", newPos);
    		
    		fancyObj.add(anvilMid);
    		
    		Map anvilTop = (Map) ((LinkedHashMap)objIn).clone();
    		
    		voxelSize = new ArrayList<Integer>() {{
		    	add(Reference.VOXEL_SIZE);
		    	add(3);
		    	add(Reference.VOXEL_SIZE);
	    	}};
	    	anvilTop.put("s", voxelSize);
    		
    		newPos = new ArrayList<Integer>() {{
    			add(centerX);
    			add(bottomY+5);
    			add(centerZ);
    		}};
    		anvilTop.put("p", newPos);
    		
    		fancyObj.add(anvilTop);
		}
		else if(block == Blocks.REDSTONE_LAMP || block == Blocks.GLOWSTONE || block == Blocks.LIT_REDSTONE_LAMP)
		{
			objIn.put("e", 0xE1FF00);
			fancyObj.add(objIn);
		}
		
		return fancyObj;
	}
	

}
