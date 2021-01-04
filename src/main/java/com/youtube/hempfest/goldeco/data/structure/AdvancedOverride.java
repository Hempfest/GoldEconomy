package com.youtube.hempfest.goldeco.data.structure;

import com.youtube.hempfest.economy.construct.AdvancedEconomy;
import com.youtube.hempfest.economy.construct.EconomyAction;
import com.youtube.hempfest.economy.construct.EconomyPriority;
import com.youtube.hempfest.economy.construct.account.Account;
import com.youtube.hempfest.economy.construct.account.permissive.AccountType;
import com.youtube.hempfest.economy.construct.currency.CurrencyLayout;
import com.youtube.hempfest.economy.construct.currency.EconomyCurrency;
import com.youtube.hempfest.goldeco.GoldEconomy;
import com.youtube.hempfest.goldeco.construct.BankListener;
import com.youtube.hempfest.goldeco.construct.PlayerListener;
import com.youtube.hempfest.goldeco.data.PlayerData;
import com.youtube.hempfest.goldeco.data.independant.Config;
import com.youtube.hempfest.goldeco.util.Utility;
import com.youtube.hempfest.hempcore.library.HUID;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class AdvancedOverride implements AdvancedEconomy {
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
		return new CurrencyLayout().setLocale(Locale.US).setPlural(fc.getString("Economy.custom-currency.name") + "'s").setSingular(fc.getString("Economy.custom-currency.name")).toCurrency();
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
	public String format(double v) {
		BigDecimal b1 = new BigDecimal(v);

		MathContext m = new MathContext(3); // 4 precision

		// b1 is rounded using m
		BigDecimal b2 = b1.round(m);
		return String.valueOf(b2.doubleValue());
	}

	@Override
	public String format(double v, Locale locale) {
		return null;
	}

	@Override
	public double getMaxWalletSize() {
		return 500000000;
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
	public boolean isAccountsLimited() {
		return true;
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
	public boolean hasWalletAccount(String s) {
		return false;
	}

	@Override
	public boolean hasWalletAccount(String s, String s1) {
		return false;
	}

	@Override
	public boolean hasWalletAccount(OfflinePlayer offlinePlayer) {
		PlayerListener bl = new PlayerListener(offlinePlayer);
		return bl.has(Utility.BALANCE);
	}

	@Override
	public boolean hasWalletAccount(OfflinePlayer offlinePlayer, String s) {
		PlayerListener bl = new PlayerListener(offlinePlayer);
		return bl.has(Utility.BALANCE, s);
	}

	@Override
	public boolean hasWalletAccount(UUID uuid) {
		return false;
	}

	@Override
	public boolean hasWalletAccount(UUID uuid, String s) {
		return false;
	}

	@Override
	public boolean hasAccount(String s) {
		return false;
	}

	@Override
	public boolean hasAccount(OfflinePlayer offlinePlayer) {
		BankListener bl = new BankListener(offlinePlayer);
		return bl.has(Utility.BANK_ACCOUNT);
	}

	@Override
	public boolean hasAccount(OfflinePlayer offlinePlayer, String s) {
		BankListener bl = new BankListener(offlinePlayer);
		return bl.has(Utility.BANK_ACCOUNT, s);
	}

	@Override
	public boolean hasAccount(String s, String s1) {
		return false;
	}

	@Override
	public boolean hasAccount(UUID uuid) {
		return false;
	}

	@Override
	public boolean hasAccount(UUID uuid, String s) {
		return false;
	}

	@Override
	public boolean walletHasAmount(String s, double v) {
		return false;
	}

	@Override
	public boolean walletHasAmount(String s, String s1, double v) {
		return false;
	}

	@Override
	public boolean walletHasAmount(OfflinePlayer offlinePlayer, double v) {
		final PlayerData data = PlayerData.get(offlinePlayer.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			if (offlinePlayer.isOnline()) {
				if (fc.getDouble("player." + offlinePlayer.getPlayer().getWorld().getName() + ".balance") >= v) {
					return true;
				}
			}
			return fc.getDouble("player." + GoldEconomy.getMainWorld() + ".balance") >= v;
		}
		return false;
	}

	@Override
	public boolean walletHasAmount(OfflinePlayer offlinePlayer, String s, double v) {
		final PlayerData data = PlayerData.get(offlinePlayer.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			return fc.getDouble("player." + s + ".balance") >= v;
		}
		return false;
	}

	@Override
	public boolean walletHasAmount(UUID uuid, double v) {
		return false;
	}

	@Override
	public boolean walletHasAmount(UUID uuid, String s, double v) {
		return false;
	}

	@Override
	public boolean accountHasAmount(String s, double v) {
		return false;
	}

	@Override
	public boolean accountHasAmount(Account account, double v) {
		return false;
	}

	@Override
	public boolean accountHasAmount(Account account, String s, double v) {
		return false;
	}

	@Override
	public boolean accountHasAmount(String s, String s1, double v) {
		return false;
	}

	@Override
	public double getWalletBalance(String s) {
		return 0;
	}

	@Override
	public double getWalletBalance(String s, String s1) {
		return 0;
	}

	@Override
	public double getWalletBalance(OfflinePlayer offlinePlayer) {
		final PlayerData data = PlayerData.get(offlinePlayer.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			if (offlinePlayer.isOnline()) {
				return fc.getDouble("player." + offlinePlayer.getPlayer().getWorld().getName() + ".balance");
			}
			return fc.getDouble("player." + GoldEconomy.getMainWorld() + ".balance");
		}
		return 0;
	}

	@Override
	public double getWalletBalance(OfflinePlayer offlinePlayer, String s) {
		final PlayerData data = PlayerData.get(offlinePlayer.getUniqueId());
		if (data.exists()) {
			FileConfiguration fc = data.getConfig();
			return fc.getDouble("player." + s + ".balance");
		}
		return 0;
	}

	@Override
	public double getWalletBalance(UUID uuid) {
		return 0;
	}

	@Override
	public double getWalletBalance(UUID uuid, String s) {
		return 0;
	}

	@Override
	public double getAccountBalance(String s) {
		return 0;
	}

	@Override
	public double getAccountBalance(String s, String s1) {
		return 0;
	}

	@Override
	public Account getAccount(String s) {
		return null;
	}

	@Override
	public Account getAccount(String s, String s1) {
		return null;
	}

	@Override
	public Account getAccount(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public Account getAccount(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public Account getAccount(String s, AccountType accountType) {
		return null;
	}

	@Override
	public Account getAccount(String s, AccountType accountType, String s1) {
		return null;
	}

	@Override
	public Account getAccount(OfflinePlayer offlinePlayer, AccountType accountType) {
		return null;
	}

	@Override
	public Account getAccount(OfflinePlayer offlinePlayer, AccountType accountType, String s) {
		return null;
	}

	@Override
	public Account getAccount(UUID uuid) {
		return null;
	}

	@Override
	public Account getAccount(UUID uuid, String s) {
		return null;
	}

	@Override
	public Account getAccount(UUID uuid, AccountType accountType) {
		return null;
	}

	@Override
	public Account getAccount(UUID uuid, AccountType accountType, String s) {
		return null;
	}

	@Override
	public EconomyAction walletWithdraw(String s, double v) {
		return null;
	}

	@Override
	public EconomyAction walletWithdraw(String s, String s1, double v) {
		return null;
	}

	@Override
	public EconomyAction walletWithdraw(OfflinePlayer offlinePlayer, double v) {
		PlayerListener el = new PlayerListener(offlinePlayer);
		if (Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")) >= v) {
			el.remove(v);
			return new EconomyAction(v, offlinePlayer.getUniqueId().toString(), true, "Transaction success! New balance: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
		}
		return new EconomyAction(v, offlinePlayer.getUniqueId().toString(), false, "Transaction fail! You dont have enough: " + Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction walletWithdraw(OfflinePlayer offlinePlayer, String s, double v) {
		PlayerListener el = new PlayerListener(offlinePlayer);
		if (Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")) >= v) {
			el.remove(v, s);
			return new EconomyAction(v, offlinePlayer.getUniqueId().toString(), true, "Transaction success! New balance: " + Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")));
		}
		return new EconomyAction(v, offlinePlayer.getUniqueId().toString(), false, "Transaction fail! You dont have enough: " + Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")));
	}

	@Override
	public EconomyAction walletWithdraw(UUID uuid, double v) {
		return null;
	}

	@Override
	public EconomyAction walletWithdraw(UUID uuid, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction accountWithdraw(Account account, double v) {
		return null;
	}

	@Override
	public EconomyAction accountWithdraw(Account account, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction walletDeposit(String s, double v) {
		return null;
	}

	@Override
	public EconomyAction walletDeposit(String s, String s1, double v) {
		return null;
	}

	@Override
	public EconomyAction walletDeposit(OfflinePlayer offlinePlayer, double v) {
		PlayerListener el = new PlayerListener(offlinePlayer);
		el.add(v);
		return new EconomyAction(v, offlinePlayer.getUniqueId().toString(), true, "Transaction success! New balance:" +  Double.parseDouble(el.get(Utility.BALANCE).replaceAll(",", "")));
	}

	@Override
	public EconomyAction walletDeposit(OfflinePlayer offlinePlayer, String s, double v) {
		PlayerListener el = new PlayerListener(offlinePlayer);
		el.add(v, s);
		return new EconomyAction(v, offlinePlayer.getUniqueId().toString(), true, "Transaction success! New balance:" +  Double.parseDouble(el.get(Utility.BALANCE, s).replaceAll(",", "")));
	}

	@Override
	public EconomyAction walletDeposit(UUID uuid, double v) {
		return null;
	}

	@Override
	public EconomyAction walletDeposit(UUID uuid, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction accountDeposit(Account account, double v) {
		return null;
	}

	@Override
	public EconomyAction accountDeposit(Account account, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, String s, String s1, double v) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer) {
		EconomyAction result = new EconomyAction(offlinePlayer.getUniqueId().toString(), true, "Account creation successful under type " + accountType.name());;
		switch (accountType) {
			case BANK_ACCOUNT:
				break;
			case ENTITY_ACCOUNT:
				if (offlinePlayer.isOnline()) {
					Player p = offlinePlayer.getPlayer();
					BankListener bl = new BankListener(p, HUID.randomID().toString(), p.getWorld().getName());
					bl.create();
				}else {
					BankListener bl = new BankListener(offlinePlayer, HUID.randomID().toString(), GoldEconomy.getMainWorld());
					bl.create();
				}
				break;
			case SERVER_ACCOUNT:
				break;
		}

		return result;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, double v) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, String s) {
		EconomyAction result = new EconomyAction(offlinePlayer.getUniqueId().toString(), true, "Account creation successful under type " + accountType.name());;
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
	public EconomyAction createAccount(AccountType accountType, OfflinePlayer offlinePlayer, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, double v) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction createAccount(AccountType accountType, UUID uuid, String s, double v) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(String s) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction deleteWalletAccount(UUID uuid, String s) {
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
	public EconomyAction isAccountOwner(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(String s, Account account) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(String s, String s1, String s2) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(OfflinePlayer offlinePlayer, Account account) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(OfflinePlayer offlinePlayer, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(UUID uuid, Account account) {
		return null;
	}

	@Override
	public EconomyAction isAccountOwner(UUID uuid, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(String s, Account account) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(String s, String s1, String s2) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(OfflinePlayer offlinePlayer, Account account) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(OfflinePlayer offlinePlayer, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(UUID uuid, Account account) {
		return null;
	}

	@Override
	public EconomyAction isAccountMember(UUID uuid, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction addAccountMember(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction addAccountMember(String s, String s1, String s2) {
		return null;
	}

	@Override
	public EconomyAction addAccountMember(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction addAccountMember(OfflinePlayer offlinePlayer, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction addAccountMember(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction addAccountMember(UUID uuid, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction removeAccountMember(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction removeAccountMember(String s, String s1, String s2) {
		return null;
	}

	@Override
	public EconomyAction removeAccountMember(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction removeAccountMember(OfflinePlayer offlinePlayer, String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction removeAccountMember(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction removeAccountMember(UUID uuid, String s, String s1) {
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