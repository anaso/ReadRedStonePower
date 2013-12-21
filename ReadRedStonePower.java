package anaso.ReadRedStonePower;

import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.common.*;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.Mod.EventHandler;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import java.util.HashMap;
import java.util.logging.*;
import org.lwjgl.input.Keyboard;

@Mod
(
	modid = "ReadRedStonePower",
	name = "Read RedStonePower",
	version = "1.6"
)
@NetworkMod
(
		clientSideRequired = false,
		serverSideRequired = false
)

public class ReadRedStonePower
{

	public static int KeyRead = Keyboard.KEY_R;

	HashMap <String, Boolean> Options = new HashMap<String, Boolean>();

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		KeyBinding[] myBinding = {new KeyBinding("ReadRedStonePower", KeyRead)};

		boolean[] myBindingRepeat = {false};

		ReadRedStonePowerKey myKeyHandler = new ReadRedStonePowerKey(myBinding, myBindingRepeat, Options);

		KeyBindingRegistry.registerKeyBinding(myKeyHandler);
	}
}