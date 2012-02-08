package com.celehner.BlockLock;

import de.diddiz.LogBlock.LogBlock;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockLock extends JavaPlugin
{
	private LogBlock logblock = null;

	@Override
	public void onEnable() {
		final PluginManager pm = getServer().getPluginManager();
		if (pm.getPlugin("LogBlock") == null) return;
		logblock = (LogBlock)pm.getPlugin("LogBlock");

		final Listener blBlockListener = new BLBlockListener(this);
		pm.registerEvent(Type.BLOCK_PLACE, blBlockListener, Priority.Normal, this);
		pm.registerEvent(Type.BLOCK_BREAK, blBlockListener, Priority.Normal, this);
		
		System.out.println("[BlockLock] enabled");
	}

	@Override
	public void onDisable() {
	}

	public LogBlock getLogBlock() {
		return logblock;
	}
}
