package anaso.ReadRedStonePower;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

import java.util.HashMap;

@Mod
		(
				modid = "ReadRedStonePower",
				version = "1.0"
		)

public class ReadRedStonePower{

	@SidedProxy(clientSide = "anaso.ReadRedStonePower.ClientProxy", serverSide = "anaso.ReadRedStonePower.CommonProxy")
	public static CommonProxy proxy;

	HashMap<String, Object> Options = new HashMap<String, Object>();

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event){
		proxy.RegisterTicking(Options);
	}
}