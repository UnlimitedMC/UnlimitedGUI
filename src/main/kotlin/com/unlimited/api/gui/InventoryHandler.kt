package com.unlimited.api.gui

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent

interface InventoryHandler {
    fun onClick(event: InventoryClickEvent)
    fun onOpen(event: InventoryOpenEvent)
    fun onClose(event: InventoryCloseEvent)
}