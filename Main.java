import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import java.util.*;

public class Main extends JavaPlugin {
    
    private Map<String, Location> spawns = new HashMap<>();
    private Map<String, Location> pos1 = new HashMap<>();
    private Map<String, Location> pos2 = new HashMap<>();
    
    public void onEnable() {
        getLogger().info("§aWorldEdit Clone + Spawn загружен!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        String name = p.getName();
        
        if (label.equals("//")) {
            if (args.length == 0) {
                p.sendMessage("§6=== WorldEdit Clone ===");
                p.sendMessage("§e//set <block> - Заполнить");
                p.sendMessage("§e//regen - Регенерировать");
                p.sendMessage("§e//pos1 / //pos2 - Точки");
                p.sendMessage("§e/spawn - Телепорт");
                p.sendMessage("§e/setspawn - Установить спавн");
                return true;
            }
            
            String sub = args[0].toLowerCase();
            if (sub.equals("set")) {
                if (args.length < 2) {
                    p.sendMessage("§c//set <block>");
                    return true;
                }
                if (!pos1.containsKey(name) || !pos2.containsKey(name)) {
                    p.sendMessage("§cСначала //pos1 и //pos2");
                    return true;
                }
                
                // Заполняем область
                fillArea(pos1.get(name), pos2.get(name), args[1]);
                p.sendMessage("§aОбласть заполнена!");
                return true;
            }
            else if (sub.equals("regen")) {
                p.sendMessage("§aРегенерация...");
                return true;
            }
            else if (sub.equals("pos1")) {
                pos1.put(name, p.getLocation());
                p.sendMessage("§aТочка 1 установлена!");
                return true;
            }
            else if (sub.equals("pos2")) {
                pos2.put(name, p.getLocation());
                p.sendMessage("§aТочка 2 установлена!");
                return true;
            }
        }
        
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (spawns.containsKey(name)) {
                p.teleport(spawns.get(name));
                p.sendMessage("§aТелепорт на спавн!");
            } else {
                p.sendMessage("§cСначала /setspawn");
            }
            return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            spawns.put(name, p.getLocation());
            p.sendMessage("§aСпавн установлен!");
            return true;
        }
        
        return false;
    }
    
    private void fillArea(Location loc1, Location loc2, String blockName) {
        World world = loc1.getWorld();
        Material mat = Material.STONE;
        
        if (blockName.equals("2")) mat = Material.GRASS_BLOCK;
        else if (blockName.equals("1")) mat = Material.STONE;
        else if (blockName.equals("3")) mat = Material.DIRT;
        else if (blockName.equals("4")) mat = Material.COBBLESTONE;
        
        int minX = Math.min(loc1.getBlockX(), loc2.getBlockX());
        int maxX = Math.max(loc1.getBlockX(), loc2.getBlockX());
        int minY = Math.min(loc1.getBlockY(), loc2.getBlockY());
        int maxY = Math.max(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Math.min(loc1.getBlockZ(), loc2.getBlockZ());
        int maxZ = Math.max(loc1.getBlockZ(), loc2.getBlockZ());
        
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    world.getBlockAt(x, y, z).setType(mat);
                }
            }
        }
    }
}
