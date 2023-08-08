package com.structures.events;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.structures.Structures.inIsland;

public class SelectLocation implements Listener {

    Map<Player, SelectedArea> PlayerSelected = new HashMap<>();


    @EventHandler
    public void Select(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!event.getAction().isRightClick() || event.getItem() == null
                || !event.getItem().getType().equals(Material.IRON_NUGGET)
                || !event.hasBlock()) {
            return;
        }
        Block block1 = event.getClickedBlock();
        if (block1 == null) {
            return;
        }
        Location location = block1.getLocation();
        if (!inIsland(player, location)) {
            TextComponent msg = Component.text("Please select the block in your island!");
            player.sendMessage(msg);
            return;
        }
        // 检测玩家是否已经选择第一个坐标点
        //是->设置第二坐标点
        //不是->创建玩家和第一坐标点的map对象
        if (!PlayerSelected.containsKey(player)) {
            TextComponent msg = Component.text("You have selected the first location,select the second in 1 minute!");
            player.sendMessage(msg);
            SelectedArea area = new SelectedArea();
            area.setStart(location);
            PlayerSelected.put(player, area);
        } else if (PlayerSelected.containsKey(player)
                && PlayerSelected.get(player).hasStart()
                && !PlayerSelected.get(player).hasEnd()) {
            PlayerSelected.get(player).setEnd(location);
            String rawMsg = "Type the structures in chat, then the structure will be changed from " +
                    PlayerSelected.get(player).getStart().toString() +
                    " to " +
                    PlayerSelected.get(player).getEnd().toString();
            TextComponent msg = Component.text(rawMsg);
            player.sendMessage(msg);
        }



    }
}
