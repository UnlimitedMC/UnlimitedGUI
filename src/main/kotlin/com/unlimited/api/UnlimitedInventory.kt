package com.unlimited.api

import com.unlimited.api.gui.InventoryBuilder
import com.unlimited.api.gui.InventoryListener
import com.unlimited.api.gui.InventoryManager
import com.unlimited.api.gui.InventorySize
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

class UnlimitedInventory : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: UnlimitedInventory
    }

    override fun onEnable() {
        plugin = this
        Bukkit.getPluginManager().registerEvents(InventoryListener(InventoryManager.getInstance()), this)
    }

}
