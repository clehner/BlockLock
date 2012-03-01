package com.celehner.BlockLock;

import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Type;
import org.bukkit.event.Listener;
import org.bukkit.Location;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import de.diddiz.LogBlock.LogBlock;
import de.diddiz.LogBlock.QueryParams;
import de.diddiz.LogBlock.BlockChange;

class BLBlockListener extends BlockListener
{
	private final LogBlock logblock;
	private final BlockLock blocklock;

	BLBlockListener(BlockLock blocklock) {
		this.blocklock = blocklock;
		logblock = blocklock.getLogBlock();
	}

	public void onBlockBreak(BlockBreakEvent event) {
		if (!event.isCancelled()
			&& !canChangeBlock(event.getBlock(), event.getPlayer()))
				event.setCancelled(true);
	}

	public void onBlockPlace(BlockPlaceEvent event) {
		if (!event.isCancelled()
			&& !canChangeBlock(event.getBlock(), event.getPlayer()))
				event.setCancelled(true);
	}

	private boolean canChangeBlock(Block block, Player player) {
		if (player.hasPermission("blocklock.buildanywhere")) return true;

		// lookup most recent player-made edit
		QueryParams params = new QueryParams(logblock);
		params.limit = 1;
		params.radius = 0;
		params.needType = true;
		params.needPlayer = true;
		params.setLocation(block.getLocation());

		//System.out.println(params.getQuery());

		BlockChange bc = null;
		try {
			bc = logblock.getBlockChanges(params).get(0);
		} catch (Exception e) {}
		if (bc == null) return true;
		String name = bc.playerName;

		// allow editing own blocks.
		if (name.equals(player.getName())) return true;

		// allow overriding natural block changes
		if (name.equals("LavaFlow") || name.equals("WaterFlow")) {
			// || name.equals("Creeper")
			// || name.equals("Enderman")
			return true;
		}

		player.sendMessage(ChatColor.RED +
			"You do not have permission to build there.");
		return false;
	}
}


