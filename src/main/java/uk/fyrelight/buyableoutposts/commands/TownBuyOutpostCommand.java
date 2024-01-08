package uk.fyrelight.buyableoutposts.commands;

import com.palmergames.bukkit.towny.TownyMessaging;
import com.palmergames.bukkit.towny.TownySettings;
import com.palmergames.bukkit.towny.command.BaseCommand;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.util.ChatTools;
import com.palmergames.bukkit.util.Colors;
import com.palmergames.util.MathUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;

public class TownBuyOutpostCommand extends BaseCommand implements TabExecutor {
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return Collections.emptyList();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player player) {
            try {
                parseBuyOutpostCommand(player, strings);
            } catch (TownyException e) {
                TownyMessaging.sendErrorMsg(player, e.getMessage(player));
            }
        } else
            sender.sendMessage(ChatTools.formatCommand("Eg", "/t buy outpost", "[n]", "Buy outposts for your town."));

        return true;
    }

    private void parseBuyOutpostCommand(Player player, String[] args) throws TownyException {
        Town town = getTownFromPlayerOrThrow(player);

        if (args.length == 0) {
            String line = Colors.Yellow + "[Purchased Outposts] " + Colors.Green + "Cost: " + Colors.LightGreen + "%s" + Colors.Gray + " | " + Colors.Green + "Max: " + Colors.LightGreen + "%d";
            TownyMessaging.sendMessage(player, String.format(line, prettyMoney(town.getBonusBlockCost()), TownySettings.getMaxPurchasedBlocks(town)));
            if (TownySettings.getPurchasedBonusBlocksIncreaseValue() != 1.0)
                TownyMessaging.sendMessage(player, Colors.Green + "Cost Increase per TownBlock: " + Colors.LightGreen + "+" +  new DecimalFormat("##.##%").format(TownySettings.getPurchasedBonusBlocksIncreaseValue()-1));
            return;
        }

        int amount = MathUtil.getIntOrThrow(args[0].trim());
    }
}
