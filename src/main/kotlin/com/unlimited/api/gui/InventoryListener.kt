package com.unlimited.api.gui

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

class InventoryListener(val inventoryManager: InventoryManager) : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        inventoryManager.handleClick(event)
    }

    @EventHandler
    fun onInventoryOpen(event: InventoryOpenEvent) {
        inventoryManager.handleOpen(event)
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        inventoryManager.handleClose(event)
    }

}