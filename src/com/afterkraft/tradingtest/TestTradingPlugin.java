package com.afterkraft.tradingtest;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Merchant;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
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
    public void replaceAllTrades(InventoryOpenEvent event) {
        if (!(event.getInventory() instanceof MerchantInventory)) {
            return;
        }
        List<TradeOffer> offers = ((MerchantInventory) event.getInventory()).getOffers();
        Merchant merchant = ((MerchantInventory) event.getInventory()).getMerchant();
        for (TradeOffer offer : offers) {
            merchant.removeOffer(offer);
        }
        ItemStack diamonds = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#replaceAllTrades."));
        result.setItemMeta(meta);
        merchant.addOffer(TradeOffer.builder()
                .withFirstItem(diamonds)
                .withResultingItem(result)
                .build());
        for (TradeOffer offer: ((MerchantInventory) event.getInventory()).getOffers()) {
            getLogger().info("[replaceAllTrades] - inventory - TradeOffer toString is: " + offer.toString());
        }
        for (TradeOffer offer: merchant.getOffers()) {
            getLogger().info("[replaceAllTrades] - merchant_check - TradeOffer toString is: " + offer.toString());
        }
    }
    
    /*
     * Test whether adding trades to the Merchant alter the event's trades
     */
    @EventHandler
    public void addCustomTradeToMerchant(InventoryOpenEvent event) {
        if (!(event.getInventory() instanceof MerchantInventory)) {
            return;
        }
        Merchant merchant = ((MerchantInventory) event.getInventory()).getMerchant();
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#addCustomTradeToMerchant."));
        result.setItemMeta(meta);
        merchant.addOffer(TradeOffer.builder()
                .withFirstItem(emerald)
                .withResultingItem(result)
                .build());
        for (TradeOffer offer: ((MerchantInventory) event.getInventory()).getOffers()) {
            getLogger().info("[addCustomTradeToMerchant] - inventory - TradeOffer toString is: " + offer.toString());
        }
        for (TradeOffer offer: merchant.getOffers()) {
            getLogger().info("[addCustomTradeToMerchant] - merchant - TradeOffer toString is: " + offer.toString());
        }
    }
    
    /*
     * Add custom trade if doesn't exist
     */
    @EventHandler
    public void addIfNotExists(InventoryOpenEvent event) {
        if (!(event.getInventory() instanceof MerchantInventory)) {
            return;
        }
        List<TradeOffer> offers = ((MerchantInventory) event.getInventory()).getOffers();
        Merchant merchant = ((MerchantInventory) event.getInventory()).getMerchant();
        ItemStack emerald = new ItemStack(Material.EMERALD, 1);
        ItemStack result = new ItemStack(Material.GOLD_INGOT, 1);
        ItemMeta meta = result.getItemMeta();
        meta.setDisplayName(ChatColor.AQUA + "Customized TradeOffer Result");
        meta.setLore(Arrays.asList("This is a customized offer generated from", "TestTradingPlugin#addIfNotExists."));
        result.setItemMeta(meta);
        TradeOffer myOffer = TradeOffer.builder()
                .withFirstItem(emerald)
                .withResultingItem(result)
                .build();
        boolean hasMyOffer = false;
        for (TradeOffer offer : offers) {
            if (offer.equals(myOffer)) hasMyOffer = true;
        }
        if (!hasMyOffer) {
            getLogger().info("[addIfNotExists] - the trade list does not have my offer!");
            merchant.addOffer(myOffer);
        }
        for (TradeOffer offer: ((MerchantInventory) event.getInventory()).getOffers()) {
            getLogger().info("[addIfNotExists] - event - TradeOffer toString is: " + offer.toString());
        }
        for (TradeOffer offer: merchant.getOffers()) {
            getLogger().info("[addIfNotExists] - merchant - TradeOffer toString is: " + offer.toString());
        }
    }
    /*
     * Easy comment line to allow simple removal of one '/' to isolate single listeners.
     */

}
