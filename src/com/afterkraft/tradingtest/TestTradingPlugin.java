package com.afterkraft.tradingtest;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.TradeOffer.TradeType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.merchant.MerchantTradeEvent;
import org.bukkit.inventory.TradeOffer;

public class TestTradingPlugin extends JavaPlugin implements Listener {
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    /*
     * Test simply overriding all TradeOffers
     */ 
    @EventHandler
    public void replaceAllTrades(MerchantTradeEvent event) {
        for (TradeOffer offer : event.getOffers()) {
            event.removeOffer(offer);
        }
        ItemStack diamonds = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#replaceAllTrades."));
        result.setItemMeta(meta);
        event.addOffer(new TradeOffer(diamonds, result));
        for (TradeOffer offer: event.getOffers()) {
            getLogger().info("[setUpCustomTrades] - add - Type is: " + offer.getTradeType().toString());
        }
        for (TradeOffer offer: event.getMerchant().getOffers()) {
            getLogger().info("[setUpCustomTrades] - merchant_check - Type is: " + offer.getTradeType().toString());
        }
    }
    
    /*
     * This listener will print out information showing that the event's trades do not
     * reflect the Merchant's offers list.
     */ 
    @EventHandler
    public void setUpCustomTrades(MerchantTradeEvent event) {
        for (TradeOffer offer : event.getOffers()) {
            getLogger().info("[setUpCustomTrades] - remove - Type is: " + offer.getTradeType().toString());
            if (offer.getTradeType() == TradeType.VANILLA) {
                event.removeOffer(offer);
            }
        }
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#setUpCustomTrades."));
        result.setItemMeta(meta);
        event.addOffer(new TradeOffer(emerald, result));
        for (TradeOffer offer: event.getOffers()) {
            getLogger().info("[setUpCustomTrades] - add - Type is: " + offer.getTradeType().toString());
        }
        for (TradeOffer offer: event.getMerchant().getOffers()) {
            getLogger().info("[setUpCustomTrades] - merchant_check - Type is: " + offer.getTradeType().toString());
        }
    }
    
    /*
     * Test whether adding trades to the Merchant alter the event's trades
     */
    @EventHandler
    public void addCustomTradeToMerchant(MerchantTradeEvent event) {
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#addCustomTradeToMerchant."));
        result.setItemMeta(meta);
        event.getMerchant().addOffer(new TradeOffer(emerald, result));
        for (TradeOffer offer: event.getOffers()) {
            getLogger().info("[setUpCustomTrades] - event - Type is: " + offer.getTradeType().toString());
        }
        for (TradeOffer offer: event.getMerchant().getOffers()) {
            getLogger().info("[setUpCustomTrades] - merchant - Type is: " + offer.getTradeType().toString());
        }
    }
    
    /*
     * Add custom trade if doesn't exist
     */
    @EventHandler
    public void addIfNotExists(MerchantTradeEvent event) {
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#addIfNotExists."));
        result.setItemMeta(meta);
        TradeOffer myOffer = new TradeOffer(emerald, result);
        boolean hasMyOffer = false;
        for (TradeOffer offer : event.getOffers()) {
            if (offer.equals(myOffer)) hasMyOffer = true;
        }
        if (!hasMyOffer) {
            event.addOffer(myOffer);
        }
        for (TradeOffer offer: event.getOffers()) {
            getLogger().info("[addIfNotExists] - event - Type is: " + offer.getTradeType().toString());
        }
        for (TradeOffer offer: event.getMerchant().getOffers()) {
            getLogger().info("[addIfNotExists] - merchant - Type is: " + offer.getTradeType().toString());
        }
    }

}
