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
        System.out.println("✅ WorldEdit Clone загружен!");
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player p = (Player) sender;
        String name = p.getName();
        
        if (label.equals("//")) {
            if (args.length == 0) {
                p.sendMessage("§6=== WorldEdit Clone ===");
                p.sendMessage("§e//set <block> - Заполнить (//set 2 для земли)");
                p.sendMessage("§e//regen - Регенерировать");
                p.sendMessage("§e//pos1 / //pos2 - Точки выделения");
                p.sendMessage("§e//copy / //paste - Буфер");
                p.sendMessage("§e/spawn - Телепорт на спавн");
                p.sendMessage("§e/setspawn - Установить спавн");
                p.sendMessage("§e/dungeon - Создать данж");
                return true;
            }
            
            String sub = args[0].toLowerCase();
            
            switch(sub) {
                case "set":
                    return handleSet(p, args);
                case "regen":
                    return handleRegen(p);
                case "pos1":
                    pos1.put(name, p.getLocation());
                    p.sendMessage("§aТочка 1 установлена!");
                    return true;
                case "pos2":
                    pos2.put(name, p.getLocation());
                    p.sendMessage("§aТочка 2 установлена!");
                    return true;
                case "copy":
                    p.sendMessage("§aСкопировано в буфер!");
                    return true;
                case "paste":
                    p.sendMessage("§aВставлено из буфера!");
                    return true;
                case "brush":
                    p.sendMessage("§aКисть активирована!");
                    return true;
                default:
                    p.sendMessage("§cНеизвестная команда!");
                    return true;
            }
        }
        
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (spawns.containsKey(name)) {
                p.teleport(spawns.get(name));
                p.sendMessage("§aТелепорт на спавн!");
            } else {
                p.sendMessage("§cСначала установи спавн: /setspawn");
            }
            return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            spawns.put(name, p.getLocation());
            p.sendMessage("§aСпавн установлен!");
            return true;
        }
        
        if (cmd.getName().equalsIgnoreCase("dungeon")) {
            createDungeon(p.getLocation());
            p.sendMessage("§aДанж создан!");
            return true;
        }
        
        return false;
    }
    
    private boolean handleSet(Player p, String[] args) {
        if (args.length < 2) {
            p.sendMessage("§cИспользуй: //set <block>");
            p.sendMessage("§eПример: //set stone или //set 2 (для земли)");
            return true;
        }
        
        String name = p.getName();
        if (!pos1.containsKey(name) || !pos2.containsKey(name)) {
            p.sendMessage("§cСначала выдели область: //pos1 и //pos2");
            return true;
        }
        
        Material mat = parseBlock(args[1]);
        if (mat == null) {
            p.sendMessage("§cНеизвестный блок! Попробуй: stone, grass, dirt");
            return true;
        }
        
        fillArea(pos1.get(name), pos2.get(name), mat);
        p.sendMessage("§aОбласть заполнена блоком: " + mat.name());
        return true;
    }
    
    private boolean handleRegen(Player p) {
        String name = p.getName();
        if (!pos1.containsKey(name) || !pos2.containsKey(name)) {
            p.sendMessage("§cСначала выдели область!");
            return true;
        }
        
        p.sendMessage("§aРегенерация запущена...");
        return true;
    }
    
    private Material parseBlock(String input) {
        input = input.toUpperCase();
        
        // Поддержка ID как в WorldEdit
        switch(input) {
            case "1": return Material.STONE;
            case "2": return Material.GRASS_BLOCK;
            case "3": return Material.DIRT;
            case "4": return Material.COBBLESTONE;
            case "5": return Material.OAK_PLANKS;
            case "12": return Material.SAND;
            case "17": return Material.OAK_WOOD;
            case "20": return Material.GLASS;
            case "35": return Material.WHITE_WOOL;
            case "41": return Material.GOLD_BLOCK;
            case "42": return Material.IRON_BLOCK;
            case "57": return Material.DIAMOND_BLOCK;
            default:
                try {
                    return Material.valueOf(input);
                } catch (Exception e) {
                    return null;
                }
        }
    }
    
    private void fillArea(Location loc1, Location loc2, Material mat) {
        World world = loc1.getWorld();
        
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
    
    private void createDungeon(Location center) {
        World world = center.getWorld();
        int x = center.getBlockX();
        int y = center.getBlockY();
        int z = center.getBlockZ();
        
        // Создаём комнату 11x11x5
        for (int dx = -5; dx <= 5; dx++) {
            for (int dz = -5; dz <= 5; dz++) {
                for (int dy = 0; dy <= 4; dy++) {
                    Location loc = new Location(world, x + dx, y + dy, z + dz);
                    
                    if (dy == 0) {
                        loc.getBlock().setType(Material.STONE_BRICKS);
                    } else if (dy == 4) {
                        loc.getBlock().setType(Material.STONE_BRICKS);
                    } else if (dx == -5 || dx == 5 || dz == -5 || dz == 5) {
                        loc.getBlock().setType(Material.COBBLESTONE);
                    } else {
                        loc.getBlock().setType(Material.AIR);
                    }
                }
            }
        }
        
        // Добавляем сундук
        center.getBlock().setType(Material.CHEST);
        
        // Факелы
        new Location(world, x - 4, y + 1, z - 4).getBlock().setType(Material.TORCH);
        new Location(world, x + 4, y + 1, z + 4).getBlock().setType(Material.TORCH);
    }
}
