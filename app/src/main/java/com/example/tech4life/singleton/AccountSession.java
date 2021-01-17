package com.example.tech4life.singleton;

import com.example.tech4life.models.Account;

public class AccountSession {
    private static boolean isLogon;

    public static boolean isLogon() {
        return isLogon;
    }
    private static Account account =  new Account();
    public static Account getAccount() {
        return account;
    }

    public static void setAccount(Account account) {
        AccountSession.account = new Account(account.getUsername(), account.getTOKEN(), account.getDisplayName(), account.getEmail(), account.getAvatar(), account.getId());
        isLogon = true;
    }

    public static void clearSesstion() {
        isLogon = false;
        account = new Account();
    }
}
