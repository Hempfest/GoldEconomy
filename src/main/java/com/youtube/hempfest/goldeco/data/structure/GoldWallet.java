package com.youtube.hempfest.goldeco.data.structure;

import com.youtube.hempfest.economy.construct.EconomyAction;
import com.youtube.hempfest.economy.construct.account.Wallet;
import com.youtube.hempfest.economy.construct.entity.EconomyEntity;
import com.youtube.hempfest.economy.construct.entity.types.PlayerEntity;
import com.youtube.hempfest.goldeco.GoldEconomy;
import com.youtube.hempfest.goldeco.construct.PlayerListener;
import com.youtube.hempfest.goldeco.data.PlayerData;
import com.youtube.hempfest.goldeco.util.Utility;
import java.math.BigDecimal;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.Nullable;

public class GoldWallet extends Wallet {

	protected final OfflinePlayer player;

	protected GoldWallet(OfflinePlayer holder) {
		super(new PlayerEntity(holder));
		this.player = holder;
	}


	@Override
	public void setBalance(BigDecimal bigDecimal) {

	}

	@Override
	public void setBalance(BigDecimal bigDecimal, String s) {

	}

	@Override
	public boolean exists() {
		return false;
	}

	@Override
	public boolean exists(String s) {
		return false;
	}

	@Override
	public @Nullable BigDecimal getBalance() {
		final PlayerData data = PlayerData.get(player.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			if (player.isOnline()) {
				return BigDecimal.valueOf(fc.getDouble("player." + player.getPlayer().getWorld().getName() + ".balance"));
			}
			return BigDecimal.valueOf(fc.getDouble("player." + GoldEconomy.getMainWorld() + ".balance"));
		}
		return new BigDecimal(0);
	}

	@Override
	public @Nullable BigDecimal getBalance(String s) {
		final PlayerData data = PlayerData.get(player.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			return BigDecimal.valueOf(fc.getDouble("player." + s + ".balance"));
		}
		return new BigDecimal(0);
	}

	@Override
	public boolean has(BigDecimal bigDecimal) {
		final PlayerData data = PlayerData.get(player.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			if (player.isOnline()) {
				if (fc.getDouble("player." + player.getPlayer().getWorld().getName() + ".balance") >= bigDecimal.doubleValue()) {
					return true;
				}
			}
			return fc.getDouble("player." + GoldEconomy.getMainWorld() + ".balance") >= bigDecimal.doubleValue();
		}
		return false;
	}

	@Override
	public boolean has(BigDecimal bigDecimal, String s) {
		final PlayerData data = PlayerData.get(player.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			return fc.getDouble("player." + s + ".balance") >= bigDecimal.doubleValue();
		}
		return false;
	}

	@Override
	public EconomyAction deposit(BigDecimal bigDecimal) {
		PlayerListener el = new PlayerListener(player);
		el.add(bigDecimal.doubleValue());
		return new EconomyAction(bigDecimal, new PlayerEntity(player), true, "Transaction success! New balance:" +  Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction deposit(BigDecimal bigDecimal, String s) {
		PlayerListener el = new PlayerListener(player);
		el.add(bigDecimal.doubleValue(), s);
		return new EconomyAction(bigDecimal, new PlayerEntity(player), true, "Transaction success! New balance:" +  Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")));
	}

	@Override
	public EconomyAction withdraw(BigDecimal bigDecimal) {
		PlayerListener el = new PlayerListener(player);
		if (Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")) >= bigDecimal.doubleValue()) {
			el.remove(bigDecimal.doubleValue());
			return new EconomyAction(bigDecimal, new PlayerEntity(player), true, "Transaction success! New balance: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
		}
		return new EconomyAction(bigDecimal, new PlayerEntity(player), false, "Transaction fail! You dont have enough: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction withdraw(BigDecimal bigDecimal, String s) {
		PlayerListener el = new PlayerListener(player);
		if (Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")) >= bigDecimal.doubleValue()) {
			el.remove(bigDecimal.doubleValue(), s);
			return new EconomyAction(bigDecimal, new PlayerEntity(player), true, "Transaction success! New balance: " + Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")));
		}
		return new EconomyAction(bigDecimal, new PlayerEntity(player), false, "Transaction fail! You dont have enough: " + Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")));
	}
}
