package com.structures;

import com.structures.events.SelectLocation;
import com.structures.events.SelectedArea;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.addons.Addon;
import world.bentobox.bentobox.database.objects.Island;

public final class Structures extends Addon {
    @Override
    public void onLoad() {
        this.saveDefaultConfig();

    }

    @Override
    public void onEnable() {
        this.setup();

    }

    @Override
    public void onDisable() {

    }

    private void setup(){
        this.registerListener(new SelectLocation());
    }

    public static boolean inIsland(@NotNull Player player, @NotNull Location location) {
        Island island = BentoBox.getInstance().getIslands().getIsland(player.getWorld(), player.getUniqueId());
        double x = location.getX();
        double z = location.getZ();
        if (island != null) {
            return !(x > island.getMaxProtectedX()) || !(z > island.getMaxProtectedZ());
        }
        return false;
    }
}
