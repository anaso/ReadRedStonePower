package anaso.ReadRedStonePower;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.EnumSet;
import java.util.HashMap;
import org.lwjgl.input.Keyboard;
import cpw.mods.fml.client.registry.KeyBindingRegistry;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.src.*;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.world.World;

public class ReadRedStonePowerKey extends KeyHandler
{
	//boolean Check = false;

	public static int bindKey;

	HashMap <String, Boolean> Options = new HashMap<String, Boolean>();

	public ReadRedStonePowerKey(KeyBinding[] keyBindings, boolean[] repeatings, HashMap Options)
	{
		//the first value is an array of KeyBindings, the second is whether or not the call
		//keyDown should repeat as long as the key is down
		super(keyBindings, repeatings);

		this.Options = Options;
	}

	@Override
	public String getLabel()
	{
		return "ReadRedStonePower Keybindings";
	}

	@Override
	public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		if(tickEnd)
		{
			Minecraft MC = ModLoader.getMinecraftInstance();
			loadOptions();
			//Check = false;
			//System.out.println("RS in");

			if(kb.keyCode == bindKey)
			{
				if(MC.objectMouseOver != null)
				{
					int BlockX = MC.objectMouseOver.blockX;
					int BlockY = MC.objectMouseOver.blockY;
					int BlockZ = MC.objectMouseOver.blockZ;

					if(Block.redstoneWire.blockID == MC.theWorld.getBlockId(BlockX, BlockY, BlockZ))
					{
						MC.thePlayer.addChatMessage("RedStone Power : " + MC.theWorld.getBlockMetadata(BlockX, BlockY, BlockZ));
					}
				}
			}
		}
	}

	public void loadOptions()
	{
		Minecraft MC = ModLoader.getMinecraftInstance();
		File optionsFile = new File(MC.getMinecraftDir(), "options.txt");

		try
		{
			if (!optionsFile.exists())
			{
				return;
			}

			BufferedReader var1 = new BufferedReader(new FileReader(optionsFile));
			String var2 = "";

			while ((var2 = var1.readLine()) != null)
			{
				try
				{
					String[] var3 = var2.split(":");

					for (int var4 = 0; var4 < this.keyBindings.length; ++var4)
					{
						if (var3[0].equals("key_ReadRedStonePower"))
						{
							bindKey = Integer.parseInt(var3[1]);
						}
					}
				}
				catch (Exception e)
				{
					System.out.println(e);
				}
			}

			KeyBinding.resetKeyBindingArrayAndHash();
			var1.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	@Override
	public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
	{
		//do whatever
	}

	@Override
	public EnumSet<TickType> ticks()
	{
		return EnumSet.of(TickType.CLIENT);
		//I am unsure if any different TickTypes have any different effects.
	}
}