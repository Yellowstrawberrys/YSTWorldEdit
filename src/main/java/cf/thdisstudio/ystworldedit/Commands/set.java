package cf.thdisstudio.ystworldedit.Commands;

import cf.thdisstudio.ystworldedit.EventType;
import cf.thdisstudio.ystworldedit.YstWorldEdit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class set extends Thread{
    ItemStack m;
    Location l1;
    Location l2;
    Player p;

    public set(ItemStack m, Location l1, Location l2, Player p){
        this.m = m;
        this.l1 = l1;
        this.l2 = l2;
        this.p = p;
    }

    @Override
    public void run() {
        List<Integer> x = Arrays.asList(l1.getBlockX(), l2.getBlockX());
        List<Integer> y = Arrays.asList(l1.getBlockY(), l2.getBlockY());
        List<Integer> z = Arrays.asList(l1.getBlockZ(), l2.getBlockZ());
        Collections.sort(x);
        Collections.sort(y);
        Collections.sort(z);
        HashMap<ItemStack, EventType> l = new HashMap<>();
        l.put(m, EventType.PlaceBlock);
        for(int i1 = y.get(0); i1 < y.get(1)+1; i1++) {
            for (int i = z.get(0); i < z.get(1) + 1; i++) {
                for (int in = x.get(0); in < x.get(1) + 1; in++) {
                    YstWorldEdit.work.put(new Location(l1.getWorld(), in, i1, i), l);
                }
            }
        }
        p.sendMessage(ChatColor.GREEN+"\uC131\uACF5\uC801\uC73C\uB85C \uC2E4\uD589\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
        this.interrupt();
    }
}
