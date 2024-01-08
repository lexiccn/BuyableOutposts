package uk.fyrelight.buyableoutposts;

import com.palmergames.bukkit.towny.TownyCommandAddonAPI;
import com.palmergames.bukkit.towny.object.AddonCommand;
import org.bukkit.plugin.java.JavaPlugin;
import com.palmergames.bukkit.towny.TownyCommandAddonAPI.CommandType;
import uk.fyrelight.buyableoutposts.commands.TownBuyOutpostCommand;

public class BuyableOutposts extends JavaPlugin {
    @Override
    public void onEnable() {
        registerCommands();
    }

    private void registerCommands() {
        AddonCommand townBuyOutpostCommand = new AddonCommand(CommandType.TOWN_BUY, "outpost", new TownBuyOutpostCommand());
        TownyCommandAddonAPI.addSubCommand(townBuyOutpostCommand);
    }
}
