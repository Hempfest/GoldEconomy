package com.youtube.hempfest.goldeco.data.structure;

import com.youtube.hempfest.economy.construct.EconomyAction;
import com.youtube.hempfest.economy.construct.account.Account;
import com.youtube.hempfest.economy.construct.account.permissive.AccountType;
import com.youtube.hempfest.economy.construct.entity.EconomyEntity;
import java.math.BigDecimal;
import java.util.UUID;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.jetbrains.annotations.Nullable;

public class GoldAccount extends Account {



	public GoldAccount(AccountType accountType, EconomyEntity holder, EconomyEntity... members) {
		super(accountType, holder, members);
	}

	@Override
	public EconomyAction isOwner(String s) {
		return null;
	}

	@Override
	public EconomyAction isOwner(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isOwner(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public EconomyAction isOwner(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction isOwner(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction isOwner(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction isJointOwner(String s) {
		return null;
	}

	@Override
	public EconomyAction isJointOwner(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isJointOwner(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public EconomyAction isJointOwner(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction isJointOwner(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction isJointOwner(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction isMember(String s) {
		return null;
	}

	@Override
	public EconomyAction isMember(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction isMember(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public EconomyAction isMember(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction isMember(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction isMember(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction addMember(String s) {
		return null;
	}

	@Override
	public EconomyAction addMember(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction addMember(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public EconomyAction addMember(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction addMember(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction addMember(UUID uuid, String s) {
		return null;
	}

	@Override
	public EconomyAction removeMember(String s) {
		return null;
	}

	@Override
	public EconomyAction removeMember(String s, String s1) {
		return null;
	}

	@Override
	public EconomyAction removeMember(OfflinePlayer offlinePlayer) {
		return null;
	}

	@Override
	public EconomyAction removeMember(OfflinePlayer offlinePlayer, String s) {
		return null;
	}

	@Override
	public EconomyAction removeMember(UUID uuid) {
		return null;
	}

	@Override
	public EconomyAction removeMember(UUID uuid, String s) {
		return null;
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
		return null;
	}

	@Override
	public @Nullable BigDecimal getBalance(String s) {
		return null;
	}

	@Override
	public boolean has(BigDecimal bigDecimal) {
		return false;
	}

	@Override
	public boolean has(BigDecimal bigDecimal, String s) {
		return false;
	}

	@Override
	public EconomyAction deposit(BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction deposit(BigDecimal bigDecimal, String s) {
		return null;
	}

	@Override
	public EconomyAction withdraw(BigDecimal bigDecimal) {
		return null;
	}

	@Override
	public EconomyAction withdraw(BigDecimal bigDecimal, String s) {
		return null;
	}
}
