package me.Corbis.Execution.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TeamUtils {

    public static boolean isTeam2(final EntityPlayer e, final EntityLivingBase e2) {
		final String localPlayerTeamCode = String.valueOf(e.getDisplayName().getFormattedText().charAt(1)).toLowerCase();
		final String otherPlayerTeamCode = String.valueOf(e2.getDisplayName().getFormattedText().charAt(1)).toUpperCase();
		if(localPlayerTeamCode == otherPlayerTeamCode){
			return true;
		}else
			return false;

    }

    private static String isTeam(EntityPlayer player) {
        final Matcher m = Pattern.compile("Â§(.).*Â§r").matcher(player.getDisplayName().getFormattedText());
        if (m.find()) {
            return m.group(1);
        }
        return "f";
    }

	public static boolean isTeam(final EntityPlayer e, final EntityPlayer e2) {
		if(e2.getTeam() != null && e.getTeam() != null){
			Character target = e2.getDisplayName().getFormattedText().charAt(1);
			Character player = e.getDisplayName().getFormattedText().charAt(1);
			if(target.equals(player)){
				return true;
			}
		}else{
			return true;
		}
		return false;
    }
	
}
