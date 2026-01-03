public class Main extends org.bukkit.plugin.java.JavaPlugin {
    public void onEnable() {
        getLogger().info("WorldEdit Clone загружен!");
        getCommand("//").setExecutor((sender, cmd, label, args) -> {
            if (sender instanceof org.bukkit.entity.Player) {
                org.bukkit.entity.Player p = (org.bukkit.entity.Player) sender;
                p.sendMessage("§aWorldEdit команды работают!");
                p.sendMessage("§e//set <block> - заполнить");
                p.sendMessage("§e//regen - регенерировать");
                p.sendMessage("§e/spawn - телепорт");
            }
            return true;
        });
    }
}
