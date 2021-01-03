package com.youtube.hempfest.goldeco.data.vault;

import com.youtube.hempfest.goldeco.GoldEconomy;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

import java.util.logging.Logger;

public class VaultListener {

    private final GoldEconomy plugin;
    private Economy provider;
    private final Logger log = Logger.getLogger("Minecraft");

    public VaultListener (GoldEconomy plugin) {
        this.plugin = plugin;
    }

    public void hook() {
    provider = plugin.eco;
        Bukkit.getServicesManager().register(Economy.class, this.provider, this.plugin, ServicePriority.Normal);
        log.info(String.format("[%s] - Vault economy hooked!", plugin.getDescription().getName()));
    }

    public void unhook() {
    Bukkit.getServicesManager().unregister(Economy.class, this.provider);
        log.info(String.format("[%s] - Vault economy un-hooked!", plugin.getDescription().getName()));
    }

}
