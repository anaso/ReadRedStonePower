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
			
			// 押されたボタンの確認
			for(int i = 0; MC.gameSettings.keyBindings.length > i; i++)
			{
				if(MC.gameSettings.keyBindings[i].keyDescription.equals("ReadRedStonePower"))
				{
					this.bindKey = MC.gameSettings.keyBindings[i].keyCode;
				}
			}

			System.out.println(MC.gameSettings.keyBindings);

			if(kb.keyCode == MC.gameSettings.keyBindings[0].keyCode)
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