package fluke.krunkexport.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleScriptContext;

import org.json.simple.JSONArray;

public class MergeBlocks 
{
	
	public static JSONArray mergeObjs(JSONArray objs) throws ScriptException, IOException
	{   
		Process p = Runtime.getRuntime().exec("python objmerge.py");
		
		/*
	    StringWriter writer = new StringWriter(); //ouput will be stored here

	    ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptContext context = new SimpleScriptContext();

	    context.setWriter(writer); //configures output redirection
	    ScriptEngine engine = manager.getEngineByName("python");
	    engine.eval(new FileReader("objmerge.py"), context); //reads from main MC dir
	    System.out.println(writer.toString()); 
	    
	    */
		
		return objs;
	}

}
