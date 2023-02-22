package com.vicmatskiv.weaponlib.network.packets;

import com.vicmatskiv.weaponlib.UniversalSoundLookup;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static com.vicmatskiv.mw.ModernWarfareMod.mc;
import static com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider.compatibility;

public class HeadshotSFXPacket implements CompatibleMessage {

	

	public HeadshotSFXPacket() {
	
	}
	

	public void fromBytes(ByteBuf buf) {

	}

	public void toBytes(ByteBuf buf) {

	}

	public static class GunFXPacketHandler implements CompatibleMessageHandler<HeadshotSFXPacket, CompatibleMessage> {
		
		
		
		@SideOnly(Side.CLIENT)
		public void act() {
			compatibility.playSound(mc.player, UniversalSoundLookup.lookupSound("headshotsfx"), 10.0f, 1.0f);
        	
		}

		@Override
		public <T extends CompatibleMessage> T onCompatibleMessage(HeadshotSFXPacket m, CompatibleMessageContext ctx) {
			 if(!ctx.isServerSide()) {
		            compatibility.runInMainClientThread(() -> {
					
		            	
		            	//System.out.println("hi");
		            	//PositionedSoundRecord shot = PositionedSoundRecord.getMasterRecord(UniversalSoundLookup.lookupSound("headshotsfx").getSound(), 1.0f);
		            	act();
		            	//mc.getSoundHandler().playSound(shot);
		            	
		            	//mc.world.playSound(mc.player, mc.player.getPosition(), SoundEvents.BLOCK_ANVIL_BREAK, SoundCategory.MASTER, 1.0f, 1.0f);
		            	
		            	//mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_GLASS_BREAK, 1.0f));
		            	
					
				});
			}
			
			return null;
		}

	}

	
}
