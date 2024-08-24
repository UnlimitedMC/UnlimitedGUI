package com.unlimited.api.gui

import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.Inventory

class InventoryManager private constructor() {

    companion object {
        private var instance: InventoryManager? = null
        fun getInstance(): InventoryManager {
            if (instance == null) {
                instance = InventoryManager()
            }
            return instance!!
        }
    }

    val activeInventories: HashMap<Inventory, InventoryHandler> = hashMapOf()

    fun registerHandledInventory(inventory: Inventory, handler: InventoryHandler) {
        println("registered")
        activeInventories[inventory] = handler
    }

    fun unregisterInventory(inventory: Inventory) {
        activeInventories.remove(inventory)
    }

    fun handleClick(event: InventoryClickEvent) {
        val handler = activeInventories[event.inventory] ?: return
        handler.onClick(event)
    }

    fun handleOpen(event: InventoryOpenEvent) {
        val handler = activeInventories[event.inventory] ?: return
        handler.onOpen(event)
    }

    fun handleClose(event: InventoryCloseEvent) {
        val handler = activeInventories[event.inventory] ?: return
        handler.onClose(event)
    }
}
