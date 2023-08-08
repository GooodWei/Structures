package com.structures.events;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SelectedArea {
    Location start;
    Location end;
    Player player;


    public SelectedArea() {
        this.start = null;
        this.end = null;
    }
    public SelectedArea(Location start, Location end){
        this.start = start;
        this.end = end;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public boolean hasStart(){
        return this.start != null;
    }
    public Location getStart() {
        return start;
    }

    public boolean hasEnd(){
        return this.end != null;
    }
    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }




}
