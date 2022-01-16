package cf.thdisstudio.ystworldedit.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class wall extends Thread{
    Material m;
    Location l1;
    Location l2;
    Player p;

    public wall(Material m, Location l1, Location l2, Player p){
        this.m = m;
        this.l1 = l1;
        this.l2 = l2;
        this.p = p;
    }

    @Override
    public void run() {
//        List<Integer> x = Arrays.asList(l1.getBlockX(), l2.getBlockX());
//        List<Integer> z = Arrays.asList(l1.getBlockZ(), l2.getBlockZ());
//        Collections.sort(x);
//        Collections.sort(z);
//        System.out.println(x+"/"+z);
//        for(int i = z.get(0); i < z.get(1)+1; i++){
//            for(int in = x.get(0); in < x.get(1)+1; in++){
//                YstWorldEdit.work.put(new Location(l1.getWorld(), in, l1.getBlockY(), i), m);
//            }
//        }
//        p.sendMessage(ChatColor.GREEN+"\uC131\uACF5\uC801\uC73C\uB85C \uC2E4\uD589\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
//        this.interrupt();
    }
}
