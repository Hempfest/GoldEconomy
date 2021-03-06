package com.youtube.hempfest.goldeco.commands;

import com.youtube.hempfest.goldeco.GoldEconomy;
import com.youtube.hempfest.goldeco.data.independant.Config;
import com.youtube.hempfest.goldeco.util.libraries.GoldEconomyCommandBase;
import com.youtube.hempfest.goldeco.util.libraries.StringLibrary;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BuylistCommand extends GoldEconomyCommandBase {
    private static final List<String> ALIASES = new ArrayList<>(Collections.singletonList("buyl"));

    public BuylistCommand() {
        super("buylist", "GoldEconomy item buy list", "/buylist", ALIASES);
    }

    @Override
    protected String permissionNode() {
        return "goldeconomy.use.buylist";
    }

    @Override
    public boolean execute(CommandSender commandSender, String commandLabel, String[] args) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(notPlayer());
            return true;
        }

        /*
        // VARIABLE CREATION
        //  \/ \/ \/ \/ \/ \/
         */
        int length = args.length;
        Player p = (Player) commandSender;
        Config main = Config.get("shop_config");
        FileConfiguration fc = main.getConfig();
        String currency = fc.getString("Economy.custom-currency.name");
        StringLibrary me = new StringLibrary(p);
        /*
        //  /\ /\ /\ /\ /\ /\
        //
         */

        if (length == 0) {
            if (!p.hasPermission(permissionNode())) {
                me.msg(noPermission(this.getPermission()));
                return true;
            }
            if (!GoldEconomy.usingShop()) {
                me.msg("&c&oUse of the &6&oGoldEconomy &c&oitem shop is disabled on this server.");
                return true;
            }
            me.getBuyList(p, 1);
            return true;
        }

        if (length == 1) {
                if (!p.hasPermission(permissionNode())) {
                    me.msg(noPermission(permissionNode()));
                    return true;
                }
            if (!GoldEconomy.usingShop()) {
                me.msg("&c&oUse of the &6&oGoldEconomy &c&oitem shop is disabled on this server.");
                return true;
            }
            me.getBuyList(p, Integer.parseInt(args[0]));
            return true;
        }

        return false;
    }
}
