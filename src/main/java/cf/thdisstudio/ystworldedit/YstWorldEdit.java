package cf.thdisstudio.ystworldedit;

import cf.thdisstudio.ystworldedit.Commands.set;
import cf.thdisstudio.ystworldedit.Commands.undo;
import cf.thdisstudio.ystworldedit.Commands.wall;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class YstWorldEdit extends JavaPlugin {

    public static HashMap<UUID, List<Location>> xyz = new HashMap<>();
    public static HashMap<Location, HashMap<ItemStack, EventType>> work = new HashMap<>();
    //HashMap<PlayerUUID, HashMap<JobUUID, HasMap<Location, List<Before Block / After Block>>>>
    public static HashMap<String, HashMap<String, HashMap<Location, List<ItemStack>>>> worked = new HashMap<>();
    public static HashMap<String, HashMap<String, HashMap<Location, List<ItemStack>>>> undid = new HashMap<>();


    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Starting to load YST WorldEdit 1.1v");
        Bukkit.getPluginManager().registerEvents(new wand(), this);
        for(int i=0; 800 > i; i++){
            Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
                if(!work.isEmpty()) {
                    Location l = new ArrayList<>(work.keySet()).get(0);
                    ItemStack block = new ArrayList<>(work.get(l).keySet()).get(0);
                    EventType eventType = work.get(l).get(block);
                    ItemStack pastBlock = new ItemStack(l.getBlock().getTypeId(), 1, (short) 0, l.getBlock().getData());
                    if(!l.getBlock().getType().equals(block.getType())
                            || !(l.getBlock().getState().getData().getData() == block.getData().getData())) {
                        l.getBlock().setTypeIdAndData(block.getTypeId(), block.getData().getData(), true);
                    }
                    work.remove(l);
                    if(eventType.equals(EventType.PlaceBlock)) {
                        putAction(eventType, l, block, pastBlock);
                    }else{
                        putAction(eventType, l, block, pastBlock);
                    }
                }
            }, 1L, 0L);
        }
        System.out.println("Finished to load YST WorldEdit 1.1v");
    }

    private void putAction(EventType eventType, Location l, ItemStack block, ItemStack pastBlock) {
        if ((eventType.equals(EventType.PlaceBlock) ? worked : undid).containsKey(block.getItemMeta().getLore().get(1))) {
            HashMap<String, HashMap<Location, List<ItemStack>>> ll = (eventType.equals(EventType.PlaceBlock) ? worked : undid).get(block.getItemMeta().getLore().get(1));
            HashMap<Location, List<ItemStack>> lll = ll.get(block.getItemMeta().getLore().get(0));
            lll.put(l, Arrays.asList(pastBlock, block));
            ll.put(block.getItemMeta().getLore().get(0), lll);
            (eventType.equals(EventType.PlaceBlock) ? worked : undid).replace(block.getItemMeta().getLore().get(1), ll);
        } else {
            HashMap<String, HashMap<Location, List<ItemStack>>> ll = new HashMap<>();
            HashMap<Location, List<ItemStack>> lll = new HashMap<>();
            lll.put(l, Arrays.asList(pastBlock, block));
            ll.put(block.getItemMeta().getLore().get(0), lll);
            (eventType.equals(EventType.PlaceBlock) ? worked : undid).put(block.getItemMeta().getLore().get(1), ll);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Disabling YST WorldEdit 1.1v");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(command.getName().equalsIgnoreCase("/set")){
            if(xyz.containsKey(p.getUniqueId()) && xyz.get(p.getUniqueId()).get(0) != null && xyz.get(p.getUniqueId()).get(1) != null){
                List<Location> list = xyz.get(p.getUniqueId());
                try {
                    if (Material.matchMaterial(args[0].split(":")[0]) != null) {
                        ItemStack working = new ItemStack(Material.matchMaterial(args[0].split(":")[0]), 1, (short) 1, ((args[0].split(":").length < 2) ? 0 : Byte.parseByte(args[0].split(":")[1])));
                        ItemMeta im = working.getItemMeta();
                        im.setLore(Arrays.asList(UUID.randomUUID().toString(), ((Player) sender).getUniqueId().toString()));
                        working.setItemMeta(im);
                        new set(working, list.get(0), list.get(1), p).start();
                    }else
                        p.sendMessage(ChatColor.RED + "\uD574\uB2F9 \uBE14\uB85D\uC744 \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4");
                }catch (NullPointerException e){
                    p.sendMessage(ChatColor.RED + "\uD574\uB2F9 \uBE14\uB85D\uC744 \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4");
                }
            }
        }else if(command.getName().equalsIgnoreCase("/wall")){
            if(xyz.containsKey(p.getUniqueId()) && xyz.get(p.getUniqueId()).get(0) != null && xyz.get(p.getUniqueId()).get(1) != null){
                List<Location> list = xyz.get(p.getUniqueId());
                try {
                    if (Material.matchMaterial(args[0]) != null) {
                        new wall(Material.matchMaterial(args[0]), list.get(0), list.get(1), p).start();
                    }else
                        p.sendMessage(ChatColor.RED + "\uD574\uB2F9 \uBE14\uB85D\uC744 \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4");
                }catch (NullPointerException e){
                    p.sendMessage(ChatColor.RED + "\uD574\uB2F9 \uBE14\uB85D\uC744 \uCC3E\uC744 \uC218 \uC5C6\uC2B5\uB2C8\uB2E4");
                }
            }
        }else if(command.getName().equals("/undo")){
            if(worked.containsKey(((Player) sender).getUniqueId().toString()) && !worked.get(((Player) sender).getUniqueId().toString()).isEmpty()){
                new undo(worked.get(((Player) sender).getUniqueId().toString()).get(
                        new ArrayList<>(worked.get(((Player) sender).getUniqueId().toString()).keySet()).get(0)
                ), (Player) sender).start();
                worked.get(((Player) sender).getUniqueId().toString()).remove(
                        new ArrayList<>(worked.get(((Player) sender).getUniqueId().toString()).keySet()).get(0)
                );
            }else
                sender.sendMessage("\uB418\uB3CC\uB9B4 \uC791\uC5C5\uC774 \uC5C6\uC2B5\uB2C8\uB2E4.");
        }

        return false;
    }
}
