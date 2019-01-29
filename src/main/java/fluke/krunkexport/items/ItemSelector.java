package fluke.krunkexport.items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.script.ScriptException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fluke.krunkexport.util.MergeBlocks;
import fluke.krunkexport.util.Reference;
import fluke.krunkexport.util.SaveToFile;
import fluke.krunkexport.util.TextureInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World; 

public class ItemSelector extends ItemBasic 
{
	public BlockPos pos1;
	public BlockPos pos2;
	
	public ItemSelector(String name)
	{
        super(name);
        this.setMaxStackSize(1);
        pos1 = null;
        pos2 = null;
    }
	
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
    	if(!world.isRemote)
    	{
	    	if(player.isSneaking())
	    	{
	    		clearPos();
	            player.sendStatusMessage(new TextComponentString("Stored positions cleared"), false);
	            return EnumActionResult.SUCCESS;
	    	}
	    	else
	    	{
		    	if(pos1 == null)
		    	{
		    		pos1 = pos;
		            player.sendStatusMessage(new TextComponentString("First corner selected: " + pos.toString()), false);
		    	}
		    	else if(pos2 == null && pos != pos1)
		    	{
		    		pos2 = pos;
		    		player.sendStatusMessage(new TextComponentString("Second corner selected: " + pos.toString()), false);
		    		convertBlocks(world);
		    		player.sendStatusMessage(new TextComponentString("Saved to file"), false);
		    		clearPos();
		    	}
		    	return EnumActionResult.SUCCESS;
	    	}
    	}
    	
    	return EnumActionResult.SUCCESS;
    }
    
    private void clearPos()
    {
    	pos1 = null;
    	pos2 = null;
    }
    
    private void convertBlocks(World world)
    {
    	int offsetX = (pos1.getX() < pos2.getX()) ? pos1.getX() : pos2.getX();
    	int offsetY = (pos1.getY() < pos2.getY()) ? pos1.getY() : pos2.getY();
    	int offsetZ = (pos1.getZ() < pos2.getZ()) ? pos1.getZ() : pos2.getZ();
    	
    	ArrayList<Integer> voxelSize = new ArrayList<Integer>() {{
	    	add(Reference.VOXEL_SIZE);
	    	add(Reference.VOXEL_SIZE);
	    	add(Reference.VOXEL_SIZE);
    	}};
    	
    	JSONObject mapout = new JSONObject();
    	mapout.put("name", "mcmap");
    	mapout.put("modURL", "");
    	mapout.put("ambient", 9937064);
    	mapout.put("light", 15923452);
    	mapout.put("sky", 14477549);
    	mapout.put("fog", 9280160);
    	mapout.put("fogD", 900);
    	mapout.put("camPos", voxelSize);
    	mapout.put("spawns", new ArrayList<Integer>());
    	
    	
    	JSONArray objects = new JSONArray(); 
    	
    	
    	for(BlockPos pos  : BlockPos.getAllInBoxMutable(pos1, pos2))
    	{
    		IBlockState state = world.getBlockState(pos);
    		if(state != Blocks.AIR.getDefaultState())
    		{
	    		Map newthing = new LinkedHashMap(2);
	    		ArrayList<Integer> krPos = new ArrayList<Integer>() {{
	    			add((pos.getX() - offsetX)*Reference.VOXEL_SIZE);
	    			add((pos.getY() - offsetY)*Reference.VOXEL_SIZE);
	    			add((pos.getZ() - offsetZ)*Reference.VOXEL_SIZE);
	    		}};

	    		newthing.put("p", krPos);
	        	newthing.put("s", voxelSize);
	        	
	        	JSONArray texturedNewthing = TextureInfo.addTextureInfo(newthing, state, pos);
	        	if(texturedNewthing != null)
	        	{
		        	for(int i=0; i<texturedNewthing.size(); i++)
		        	{
		        		objects.add(texturedNewthing.get(i));
		        	}
	        	}
    		}
    	}
    	
    	SaveToFile.saveFile(mapout.toString(), "premerge.txt");
		objects = MergeBlocks.mergeObjs(objects);

    	mapout.put("objects", objects);
    	
    	SaveToFile.saveFile(mapout.toString(), "postmerge.txt");
    }


}
