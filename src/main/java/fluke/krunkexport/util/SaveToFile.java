package fluke.krunkexport.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import net.minecraft.client.Minecraft;

public class SaveToFile 
{
	
	public static final File SAVE_FILE = new File(Minecraft.getMinecraft().mcDataDir, "krunker.txt");
	
	public static void saveFile(String data)
	{
		Writer writer = null;
		PrintWriter pw = null;
		try {
			//writer = new OutputStreamWriter(new FileOutputStream(saveFile), StandardCharsets.UTF_8);
			//writer.write(data);
			
			pw = new PrintWriter(SAVE_FILE);
			pw.write(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
        {
            //IOUtils.closeQuietly(writer);
            pw.flush(); 
            pw.close(); 
        }
	}

}
