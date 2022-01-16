package cf.thdisstudio.ystworldedit;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class wand implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onClick(PlayerInteractEvent event){
        if(event.getMaterial().equals(Material.WOOD_AXE)){
            if(event.getAction().equals(Action.LEFT_CLICK_BLOCK)){
                event.setCancelled(true);
                if(YstWorldEdit.xyz.containsKey(event.getPlayer().getUniqueId())){
                    List<Location> loc = YstWorldEdit.xyz.get(event.getPlayer().getUniqueId());
                    try {
                        loc.set(0, event.getClickedBlock().getLocation());
                    }catch (IndexOutOfBoundsException e){
                        loc.add(0, event.getClickedBlock().getLocation());
                    }
                    YstWorldEdit.xyz.replace(event.getPlayer().getUniqueId(), loc);
                }else {
                    List<Location> loc = new ArrayList<>();
                    loc.add(0, event.getClickedBlock().getLocation());
                    YstWorldEdit.xyz.put(event.getPlayer().getUniqueId(), loc);
                }
                event.getPlayer().sendMessage(ChatColor.DARK_PURPLE + String.format("\uCCAB\uBC88\uC9F8 \uC9C0\uC810 \uC124\uC815\uC774 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4(%s, %s, %s)", event.getClickedBlock().getLocation().getBlockX(), event.getClickedBlock().getLocation().getBlockY(), event.getClickedBlock().getLocation().getBlockZ()));
            }else if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
                event.setCancelled(true);
                if(YstWorldEdit.xyz.containsKey(event.getPlayer().getUniqueId())){
                    List<Location> loc = YstWorldEdit.xyz.get(event.getPlayer().getUniqueId());
                    try {
                        loc.set(1, event.getClickedBlock().getLocation());
                    }catch (IndexOutOfBoundsException e){
                        loc.add(1, event.getClickedBlock().getLocation());
                    }
                    YstWorldEdit.xyz.replace(event.getPlayer().getUniqueId(), loc);
                }else {
                    List<Location> loc = new ArrayList<>();
                    loc.add(0, null);
                    loc.add(1, event.getClickedBlock().getLocation());
                    YstWorldEdit.xyz.put(event.getPlayer().getUniqueId(), loc);
                }
                event.getPlayer().sendMessage(ChatColor.DARK_PURPLE + String.format("\uB450\uBC88\uC9F8 \uC9C0\uC810 \uC124\uC815\uC774 \uC644\uB8CC\uB418\uC5C8\uC2B5\uB2C8\uB2E4(%s, %s, %s)", event.getClickedBlock().getLocation().getBlockX(), event.getClickedBlock().getLocation().getBlockY(), event.getClickedBlock().getLocation().getBlockZ()));
            }
        }
    }
}
