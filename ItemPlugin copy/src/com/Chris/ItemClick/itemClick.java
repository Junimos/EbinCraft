package com.Chris.ItemClick;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class itemClick extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);

	}

	@Override
	public void onDisable() {

	}

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (label.equalsIgnoreCase("sticktool")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage(ChatColor.DARK_RED + "For players only!");
				return true;
			}
			Player player = (Player) sender;
			if (player.getInventory().firstEmpty() == -1) {
				Location loc = player.getLocation();
				World world = player.getWorld();

				world.dropItemNaturally(loc, getItem());
				player.sendMessage(ChatColor.GREEN + "RandomMessage");
				return true;
			}
			player.getInventory().addItem(getItem());
			player.sendMessage(ChatColor.GOLD + "You got a stick girly!");
		}
		return false;
	}

	public ItemStack getItem() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName("girl power stick");
		List<String> lore = new ArrayList<String>();
		lore.add("");
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				Player player = (Player) event.getPlayer();

				// LeftClick
				if (event.getAction() == Action.LEFT_CLICK_AIR) {
					player.performCommand("kill");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteractEntityEvent(PlayerInteractEntityEvent event) {
		Entity entity = event.getRightClicked();
		if ((entity instanceof Villager)) {
			Player player = event.getPlayer();
			player.sendMessage(ChatColor.GOLD + "fuck you justin, you suck dirty ass");
			player.chat("/server games");
		}
	}
}
