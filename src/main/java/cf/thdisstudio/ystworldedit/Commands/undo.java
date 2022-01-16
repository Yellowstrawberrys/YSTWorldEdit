package cf.thdisstudio.ystworldedit.Commands;

import cf.thdisstudio.ystworldedit.EventType;
import cf.thdisstudio.ystworldedit.YstWorldEdit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class undo extends Thread{
    HashMap<Location, List<ItemStack>> worked;
    Player p;
    public undo(HashMap<Location, List<ItemStack>> worked, Player p){
        this.worked = worked;
        this.p = p;
    }

    @Override
    public void run() {
        String uuid = UUID.randomUUID().toString();
        String playerUUID = p.getUniqueId().toString();
        for(Location loc : worked.keySet()) {
            HashMap<ItemStack, EventType> l = new HashMap<>();
            ItemStack i = worked.get(loc).get(0);
            ItemMeta im = i.getItemMeta();
            im.setLore(Arrays.asList(uuid, playerUUID));
            i.setItemMeta(im);
            l.put(i, EventType.Undo);
            YstWorldEdit.work.put(loc, l);
        }
        p.sendMessage(ChatColor.GREEN+"\uC131\uACF5\uC801\uC73C\uB85C \uC2E4\uD589\uB418\uC5C8\uC2B5\uB2C8\uB2E4");
        this.interrupt();
    }
}
