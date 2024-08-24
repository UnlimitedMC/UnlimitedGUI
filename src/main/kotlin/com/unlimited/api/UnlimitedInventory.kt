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
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    @EventHandler
    fun onAsyncPlayerChat(event: AsyncPlayerChatEvent) {
        val inventory = InventoryBuilder()
            .title("Test Title")
            .size(InventorySize.GENERIC_9X4)
            .onOpen { a -> a.player.sendMessage("Test!") }
            .setItem(1, ItemStack(Material.DIAMOND_SWORD), { a ->
                println(a.action.name)
                a.isCancelled = false
            })
            .addAction(1, { a ->
                println(a.action.name)
                a.isCancelled = false
            })
            .onClick { event -> event.isCancelled = true }
            .build()
        object : BukkitRunnable() {
            override fun run() {
                event.player.openInventory(inventory)
            }
        }.runTask(this)
    }

}
