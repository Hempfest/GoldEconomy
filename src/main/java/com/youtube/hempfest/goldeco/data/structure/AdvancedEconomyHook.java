package com.youtube.hempfest.goldeco.data.structure;

import com.youtube.hempfest.economy.construct.AdvancedEconomy;
import com.youtube.hempfest.goldeco.GoldEconomy;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;

public class AdvancedEconomyHook {

	private final GoldEconomy plugin;
	private AdvancedEconomy provider;
	private final Logger log = GoldEconomy.getInstance().getLogger();

	public AdvancedEconomyHook(GoldEconomy plugin) {
		this.plugin = plugin;
	}

	public void hook() {
		provider = plugin.advancedOverride;
		Bukkit.getServicesManager().register(AdvancedEconomy.class, this.provider, this.plugin, ServicePriority.High);
		log.info("- Advanced economy hooked! Now registered as a provider");
	}

	public void unhook() {
		Bukkit.getServicesManager().unregister(AdvancedEconomy.class, this.provider);
		log.info("- Advanced economy un-hooked! No longer registered as a provider");
	}

}
