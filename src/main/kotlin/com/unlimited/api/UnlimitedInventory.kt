package com.unlimited.api

import com.unlimited.api.gui.InventoryListener
import com.unlimited.api.gui.InventoryManager
import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin

class UnlimitedInventory : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: UnlimitedInventory
    }

    override fun onEnable() {
        plugin = this
        Bukkit.getPluginManager().registerEvents(InventoryListener(InventoryManager.getInstance()), this)
    }

}
