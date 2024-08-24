package com.unlimited.api.gui

import org.bukkit.Bukkit
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import kotlin.collections.HashMap

class InventoryBuilder : Listener, InventoryHandler {

    private var hasBeenBuilt: Boolean = false

    private var title: String = ""
    private var type: InventoryType? = null
    private var size: Int = InventorySize.GENERIC_9X3
    private var items: HashMap<Int, ItemStack> = hashMapOf()
    private var actions: HashMap<Int, (InventoryClickEvent) -> Unit> = hashMapOf()
    private var onClick: (InventoryClickEvent) -> Unit = {}
    private var onOpen: (InventoryOpenEvent) -> Unit = {}
    private var onClose: (InventoryCloseEvent) -> Unit = {}

    fun title(title: String): InventoryBuilder {
        this.title = title
        return this
    }

    fun type(type: InventoryType): InventoryBuilder {
        this.type = type
        return this
    }

    fun size(size: Int): InventoryBuilder {
        this.size = size
        return this
    }

    fun setItem(slot: Int, item: ItemStack, action: (InventoryClickEvent) -> Unit = {}): InventoryBuilder {
        items[slot] = item
        actions[slot] = action
        return this
    }

    fun addAction(slot: Int, action: (InventoryClickEvent) -> Unit): InventoryBuilder {
        actions[slot] = action
        return this
    }

    fun onClick(action: (InventoryClickEvent) -> Unit): InventoryBuilder {
        onClick = action
        return this
    }

    fun onOpen(action: (InventoryOpenEvent) -> Unit): InventoryBuilder {
        onOpen = action
        return this
    }

    fun onClose(action: (InventoryCloseEvent) -> Unit): InventoryBuilder {
        onClose = action
        return this
    }

    fun build(): Inventory {
        hasBeenBuilt = true
        val inventory = if (type != null) {
            Bukkit.createInventory(null, type!!, title)
        } else {
            Bukkit.createInventory(null, size, title)
        }
        items.forEach { (slot, item) -> inventory.setItem(slot, item) }
        InventoryManager.getInstance().registerHandledInventory(inventory, this)
        return inventory
    }

    override fun onClick(event: InventoryClickEvent) {
        if (!hasBeenBuilt) return
        onClick.invoke(event)
        val actionItemPair = actions[event.slot] ?: return
        actionItemPair.invoke(event)
    }

    override fun onOpen(event: InventoryOpenEvent) {
        if (!hasBeenBuilt) return
        onOpen.invoke(event)
    }

    override fun onClose(event: InventoryCloseEvent) {
        if (!hasBeenBuilt) return
        InventoryManager.getInstance().unregisterInventory(event.inventory)
        onClose.invoke(event)
    }

}