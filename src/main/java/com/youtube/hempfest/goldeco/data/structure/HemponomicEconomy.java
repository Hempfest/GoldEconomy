package com.youtube.hempfest.goldeco.data.structure;

import com.youtube.hempfest.economy.construct.EconomyAction;
import com.youtube.hempfest.economy.construct.EconomyPriority;
import com.youtube.hempfest.economy.construct.account.Account;
import com.youtube.hempfest.economy.construct.account.Wallet;
import com.youtube.hempfest.economy.construct.account.permissive.AccountType;
import com.youtube.hempfest.economy.construct.currency.normal.EconomyCurrency;
import com.youtube.hempfest.economy.construct.entity.types.PlayerEntity;
import com.youtube.hempfest.economy.construct.implement.AdvancedEconomy;
import com.youtube.hempfest.goldeco.GoldEconomy;
import com.youtube.hempfest.goldeco.construct.BankListener;
import com.youtube.hempfest.goldeco.construct.PlayerListener;
import com.youtube.hempfest.goldeco.data.independant.Config;
import com.youtube.hempfest.goldeco.util.Utility;
import com.youtube.hempfest.hempcore.library.HUID;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.Nullable;

public class HemponomicEconomy implements AdvancedEconomy {
	@Override
	public Plugin getPlugin() {
		return GoldEconomy.getInstance();
	}

	@Override
	public String getVersion() {
		return GoldEconomy.getInstance().getDescription().getVersion();
	}

	@Override
	public EconomyCurrency getCurrency() {
		Config main = Config.get("shop_config");
		FileConfiguration fc = main.getConfig();
		return EconomyCurrency.getCurrencyLayoutBuilder().setLocale(Locale.US).setMajorPlural(fc.getString("Economy.custom-currency.name") + "'s").setMajorSingular(fc.getString("Economy.custom-currency.name")).setMinorPlural(fc.getString("Economy.custom-currency.change") + "'s").setMinorSingular(fc.getString("Economy.custom-currency.change")).toCurrency();
	}

	@Override
	public EconomyCurrency getCurrency(String s) {
		return null;
	}

	@Override
	public EconomyPriority getPriority() {
		return EconomyPriority.HIGH;
	}

	@Override
	public String format(BigDecimal bigDecimal) {
		MathContext m = new MathContext(3); // 4 precision

		// b1 is rounded using m
		BigDecimal b2 = bigDecimal.round(m);
		return String.valueOf(b2.doubleValue());
	}

	@Override
	public String format(BigDecimal bigDecimal, Locale locale) {
		return null;
	}

	@Override
	public BigDecimal getMaxWalletSize() {
		return new BigDecimal(500000000);
	}

	@Override
	public boolean isMultiWorld() {
		return true;
	}

	@Override
	public boolean isMultiCurrency() {
		return false;
	}

	@Override
	public boolean hasMultiAccountSupport() {
		return false;
	}

	@Override
	public boolean hasWalletSizeLimit() {
		return true;
	}

	@Override
	public boolean hasWalletAccount(OfflinePlayer player) {
		PlayerListener bl = new PlayerListener(player);
		if (player.isOnline()) {
			return bl.has(Utility.BALANCE, player.getPlayer().getWorld().getName());
		}
		return bl.has(Utility.BALANCE, GoldEconomy.getMainWorld());
	}

	@Override
	public boolean hasWalletAccount(UUID uuid) {
		PlayerListener bl = new PlayerListener(Bukkit.getOfflinePlayer(uuid));
		if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
			return bl.has(Utility.BALANCE, Bukkit.getOfflinePlayer(uuid).getPlayer().getWorld().getName());
		}
		return bl.has(Utility.BALANCE, GoldEconomy.getMainWorld());
	}

	@Override
	public boolean hasAccount(OfflinePlayer player) {
		return false;
	}

	@Override
	public boolean hasAccount(UUID uuid) {
		return false;
	}

	@Override
	public @Nullable Account getAccount(String s) {
		return null;
	}

	@Override
	public @Nullable Account getAccount(String s, String s1) {
		return null;
	}

	@Override
	public @Nullable Account getAccount(String s, AccountType accountType) {
		return null;
	}

	@Override
	public @Nullable Account getAccount(OfflinePlayer offlinePlayer, AccountType accountType) {
		return null;
	}

	@Override
	public @Nullable Account getAccount(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public Account getAccount(String s, OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public @Nullable Account getAccount(UUID uuid) {
		return null;
	}

	@Override
	public @Nullable Account getAccount(UUID uuid, AccountType accountType) {
		return null;
	}

	@Override
	public Account getAccount(String s, UUID uuid) {
		return null;
	}

	@Override
	public @Nullable Wallet getWallet(String s) {
		return null;
	}

	@Override
	public @Nullable Wallet getWallet(OfflinePlayer offlinePlayer) {
		return new GoldWallet(offlinePlayer);
	}

	@Override
	public @Nullable Wallet getWallet(UUID uuid) {
		return new GoldWallet(Bukkit.getOfflinePlayer(uuid));
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, String s1, String s2) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, String s1, String s2, BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer) {
		EconomyAction result = new EconomyAction(new PlayerEntity(offlinePlayer), true, "Account creation successful under type " + accountType.name());;
		switch (accountType) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				BankListener bl;
				if (offlinePlayer.isOnline()) {
					bl = new BankListener(offlinePlayer, HUID.randomID().toString(), offlinePlayer.getPlayer().getWorld().getName());
				} else {
					bl = new BankListener(offlinePlayer, HUID.randomID().toString(), GoldEconomy.getMainWorld());
				}
				bl.create();
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, String s) {
		EconomyAction result = new EconomyAction(new PlayerEntity(offlinePlayer), true, "Account creation successful under type " + accountType.name());;
		switch (accountType) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				BankListener bl = new BankListener(offlinePlayer, HUID.randomID().toString(), s);
				bl.create();
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, String s, String s1, BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid) {
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
		EconomyAction result = new EconomyAction(new PlayerEntity(offlinePlayer), true, "Account creation successful under type " + accountType.name());;
		switch (accountType) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				BankListener bl;
				if (offlinePlayer.isOnline()) {
					bl = new BankListener(offlinePlayer, HUID.randomID().toString(), offlinePlayer.getPlayer().getWorld().getName());
				} else {
					bl = new BankListener(offlinePlayer, HUID.randomID().toString(), GoldEconomy.getMainWorld());
				}
				bl.create();
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, String s) {
		OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);
		EconomyAction result = new EconomyAction(new PlayerEntity(offlinePlayer), true, "Account creation successful under type " + accountType.name());;
		switch (accountType) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				BankListener bl = new BankListener(offlinePlayer, HUID.randomID().toString(), s);
				bl.create();
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, String s, String s1, BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(Wallet wallet) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(Wallet wallet, String s) {
		return null;
	}

	@Override
	public EconomyAction deleteAccount(String s) {
		return null;
	}

	@Override
	public EconomyAction deleteAccount(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction deleteAccount(Account account) {
		return null;
	}

	@Override
	public EconomyAction deleteAccount(Account account, String s) {
		return null;
	}

	@Override
	public List<Account> getAccounts() {
		return null;
	}

	@Override
	public List<String> getAccountList() {
		return null;
	}
}
