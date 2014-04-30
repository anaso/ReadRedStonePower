package anaso.ReadRedStonePower;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.MovingObjectPosition;

import java.util.HashMap;

@SideOnly(Side.CLIENT)
public class ReadRedStonePowerTick{
	HashMap<String, int[]> Options = new HashMap<String, int[]>();

	// 画面内の座標
	private int X = 0, Y = 0;

	public ReadRedStonePowerTick(HashMap Options){
		this.Options = Options;
	}

	public void renderReadRedStonePower(Minecraft MC){
		if(MC.theWorld.getBlock(MC.objectMouseOver.blockX, MC.objectMouseOver.blockY, MC.objectMouseOver.blockZ).equals(Block.getBlockFromName("redstone_wire"))){
			ScaledResolution XY = new ScaledResolution(MC.gameSettings, MC.displayWidth, MC.displayHeight);
			X = (XY.getScaledHeight() / 2) - 8 - MC.fontRenderer.FONT_HEIGHT;
			Y = (XY.getScaledWidth() / 2);
			MC.fontRenderer.drawStringWithShadow(String.valueOf(MC.theWorld.getBlockMetadata(MC.objectMouseOver.blockX, MC.objectMouseOver.blockY, MC.objectMouseOver.blockZ)), Y, X, 16777215);
		}
	}

	@SubscribeEvent
	public void RenderTickEvent(TickEvent.RenderTickEvent event){
		Minecraft MC = FMLClientHandler.instance().getClient();

		if(MC.theWorld != null){
			if(MC.currentScreen == null){
				if(MC.objectMouseOver != null){
					if(MC.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK){
						renderReadRedStonePower(MC);
					}
				}
			}
		}
	}
}
