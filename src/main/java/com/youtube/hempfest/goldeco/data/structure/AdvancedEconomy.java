package com.youtube.hempfest.goldeco.data.structure;

import com.youtube.hempfest.goldeco.GoldEconomy;
import com.youtube.hempfest.goldeco.data.PlayerData;
import com.youtube.hempfest.goldeco.data.independant.Config;
import com.youtube.hempfest.goldeco.construct.BankListener;
import com.youtube.hempfest.goldeco.construct.PlayerListener;
import com.youtube.hempfest.goldeco.util.Utility;
import com.youtube.hempfest.hempcore.library.HUID;
import com.youtube.hempfest.hempcore.library.economy.AccountType;
import com.youtube.hempfest.hempcore.library.economy.BankAccount;
import com.youtube.hempfest.hempcore.library.economy.EconomyAction;
import com.youtube.hempfest.hempcore.library.economy.EconomyExpansion;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AdvancedEconomy implements EconomyExpansion {
	@Override
	public Plugin getPlugin() {
		return GoldEconomy.getInstance();
	}

	@Override
	public String getVersion() {
		return GoldEconomy.getInstance().getDescription().getVersion();
	}

	@Override
	public String format(double amount) {
		BigDecimal b1 = new BigDecimal(amount);

		MathContext m = new MathContext(3); // 4 precision

		// b1 is rounded using m
		BigDecimal b2 = b1.round(m);
		return String.valueOf(b2.doubleValue());
	}

	@Override
	public String currencyPlural() {
		Config main = Config.get("shop_config");
		FileConfiguration fc = main.getConfig();
		return fc.getString("Economy.custom-currency.name") + "'s";
	}

	@Override
	public String currencySingular() {
		Config main = Config.get("shop_config");
		FileConfiguration fc = main.getConfig();
		return fc.getString("Economy.custom-currency.name");
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean isInfiniteWorlds() {
		return true;
	}

	@Override
	public boolean hasBankSupport() {
		return true;
	}

	@Override
	public boolean infiniteBankAccounts() {
		return true;
	}

	@Override
	public boolean hasPlayerAccount(String name) {
		return false;
	}

	@Override
	public boolean hasPlayerAccount(String name, String world) {
		return false;
	}

	@Override
	public boolean hasPlayerAccount(UUID uuid) {
		PlayerListener bl = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		return bl.has(Utility.BALANCE);
	}

	@Override
	public boolean hasPlayerAccount(UUID uuid, String world) {
		PlayerListener bl = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		return bl.has(Utility.BALANCE, world);
	}

	@Override
	public boolean hasBankAccount(String name) {
		return false;
	}

	@Override
	public boolean hasBankAccount(String name, String world) {
		return false;
	}

	@Override
	public boolean hasBankAccount(UUID uuid) {
		BankListener bl = new BankListener(Bukkit.getOfflinePlayer(uuid));
		return bl.has(Utility.BANK_ACCOUNT);
	}

	@Override
	public boolean hasBankAccount(UUID uuid, String world) {
		BankListener bl = new BankListener(Bukkit.getOfflinePlayer(uuid));
		return bl.has(Utility.BANK_ACCOUNT, world);
	}

	@Override
	public boolean entityHasAmount(String name, double amount) {
		return false;
	}

	@Override
	public boolean entityHasAmount(String name, String world, double amount) {
		return false;
	}

	@Override
	public boolean entityHasAmount(UUID uuid, double amount) {
		final PlayerData data = PlayerData.get(uuid);
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
			if (player.isOnline()) {
				if (fc.getDouble(player.getPlayer().getWorld().getName() + ".balance") >= amount) {
					return true;
				}
			}
			if (fc.getDouble(GoldEconomy.getMainWorld() + ".balance") >= amount) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean entityHasAmount(UUID uuid, String world, double amount) {
		final PlayerData data = PlayerData.get(uuid);
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
			if (fc.getDouble(world + ".balance") >= amount) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean bankHasAmount(String accountID, double amount) {
		return false;
	}

	@Override
	public boolean bankHasAmount(String accountID, String world, double amount) {
		return false;
	}

	@Override
	public double getEntityBalance(String name) {
		return 0;
	}

	@Override
	public double getEntityBalance(String name, String world) {
		return 0;
	}

	@Override
	public double getEntityBalance(UUID uuid) {
		final PlayerData data = PlayerData.get(uuid);
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
			if (player.isOnline()) {
				return fc.getDouble("player." + player.getPlayer().getWorld().getName() + ".balance");
			}
			return fc.getDouble("player." + GoldEconomy.getMainWorld() + ".balance");
		}
		return 0;
	}

	@Override
	public double getEntityBalance(UUID uuid, String world) {
		final PlayerData data = PlayerData.get(uuid);
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			return fc.getDouble("player." + world + ".balance");
		}
		return 0;
	}

	@Override
	public double getBankBalance(String accountID) {
		return 0;
	}

	@Override
	public double getBankBalance(String accountID, String world) {
		return 0;
	}

	@Override
	public BankAccount getBankAccount(String name) {
		return null;
	}

	@Override
	public BankAccount getBankAccount(String name, String world) {
		return null;
	}

	@Override
	public BankAccount getBankAccount(String name, String accountType, String world) {
		return null;
	}

	@Override
	public BankAccount getBankAccount(UUID uuid) {
		return null;
	}

	@Override
	public BankAccount getBankAccount(UUID uuid, String world) {
		return null;
	}

	@Override
	public BankAccount getBankAccount(UUID uuid, String accountType, String world) {
		return null;
	}

	@Override
	public EconomyAction entityWithdraw(String name, double amount) {
		return null;
	}

	@Override
	public EconomyAction entityWithdraw(String name, String world, double amount) {
		return null;
	}

	@Override
	public EconomyAction entityWithdraw(UUID uuid, double amount) {
		PlayerListener el = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		if (Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")) >= amount) {
			el.remove(amount);
			return new EconomyAction(amount, uuid.toString(), true, "Transaction success! New balance: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
		}
		return new EconomyAction(amount, uuid.toString(), false, "Transaction fail! You dont have enough: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction entityWithdraw(UUID uuid, String world, double amount) {
		PlayerListener el = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		if (Double.parseDouble(el.get(Utility.BALANCE, world).replaceAll(",", "")) >= amount) {
			el.remove(amount, world);
			return new EconomyAction(amount, uuid.toString(), true, "Transaction success! New balance: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
		}
		return new EconomyAction(amount, uuid.toString(), false, "Transaction fail! You dont have enough: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction bankWithdraw(String accountID, double amount) {
		return null;
	}

	@Override
	public EconomyAction bankWithdraw(String accountID, String world, double amount) {
		return null;
	}

	@Override
	public EconomyAction entityDeposit(String name, double amount) {
		return null;
	}

	@Override
	public EconomyAction entityDeposit(String name, String world, double amount) {
		return null;
	}

	@Override
	public EconomyAction entityDeposit(UUID uuid, double amount) {
		PlayerListener el = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		el.add(amount);
		return new EconomyAction(amount, uuid.toString(), true, "Transaction success! New balance:" +  Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction entityDeposit(UUID uuid, String world, double amount) {
		PlayerListener el = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		el.add(amount, world);
		return new EconomyAction(amount, uuid.toString(), true, "Transaction success! New balance:" +  Double.parseDouble(el.get(Utility.BALANCE, world).replaceAll(",", "")));
	}

	@Override
	public EconomyAction bankDeposit(String accountID, double amount) {
		return null;
	}

	@Override
	public EconomyAction bankDeposit(String accountID, String world, double amount) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name, double amount) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name, String world) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name, String world, double amount) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name, double amount, String accountType) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name, String world, String accountType) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, String name, String world, double amount, String accountType) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid) {
		EconomyAction result = new EconomyAction(uuid.toString(), true, "Account creation successful under type " + type.name());;
		switch (type) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
				if (player.isOnline()) {
					Player p = player.getPlayer();
					BankListener bl = new BankListener(p, HUID.randomID().toString(), p.getWorld().getName());
					bl.create();
				}else {
					BankListener bl = new BankListener(player, HUID.randomID().toString(), GoldEconomy.getMainWorld());
					bl.create();
				}
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid, double amount) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid, String world) {
		EconomyAction result = new EconomyAction(uuid.toString(), true, "Account creation successful under type " + type.name());;
		switch (type) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
				BankListener bl = new BankListener(player, HUID.randomID().toString(), world);
				bl.create();
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid, String world, double amount) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid, double amount, String accountType) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid, String world, String accountType) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType type, UUID uuid, String world, double amount, String accountType) {
		return null;
	}

	@Override
	public EconomyAction deleteEntityAccount(String name) {
		return null;
	}

	@Override
	public EconomyAction deleteEntityAccount(String name, String world) {
		return null;
	}

	@Override
	public EconomyAction deleteEntityAccount(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction deleteEntityAccount(UUID uuid, String world) {
		return null;
	}

	@Override
	public EconomyAction deleteBankAccount(String accountID) {
		return null;
	}

	@Override
	public EconomyAction deleteBankAccount(String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction isBankOwner(String name, String accountID) {
		return null;
	}

	@Override
	public EconomyAction isBankOwner(String name, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction isBankOwner(UUID uuid, String accountID) {
		return null;
	}

	@Override
	public EconomyAction isBankOwner(UUID uuid, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction isBankMember(String name, String accountID) {
		return null;
	}

	@Override
	public EconomyAction isBankMember(String name, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction isBankMember(UUID uuid, String accountID) {
		return null;
	}

	@Override
	public EconomyAction isBankMember(UUID uuid, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction addBankMember(String name, String accountID) {
		return null;
	}

	@Override
	public EconomyAction addBankMember(String name, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction addBankMember(UUID uuid, String accountID) {
		return null;
	}

	@Override
	public EconomyAction addBankMember(UUID uuid, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction removeBankMember(String name, String accountID) {
		return null;
	}

	@Override
	public EconomyAction removeBankMember(String name, String accountID, String world) {
		return null;
	}

	@Override
	public EconomyAction removeBankMember(UUID uuid, String accountID) {
		return null;
	}

	@Override
	public EconomyAction removeBankMember(UUID uuid, String accountID, String world) {
		return null;
	}

	@Override
	public List<BankAccount> getBankAccounts() {
		return null;
	}

	@Override
	public List<String> getBanks() {
		return null;
	}
}
