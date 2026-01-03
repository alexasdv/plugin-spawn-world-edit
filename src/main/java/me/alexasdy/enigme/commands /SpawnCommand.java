package me.alexasdy.enigme.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class SpawnCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Проверяем, что отправитель - игрок, а не консоль
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cЭту команду может использовать только игрок!");
            return true;
        }
        
        Player player = (Player) sender;
        
        // Получаем локацию спавна мира игрока
        Location spawnLocation = player.getWorld().getSpawnLocation();
        
        // Телепортируем игрока
        player.teleport(spawnLocation);
        
        // Отправляем сообщение об успехе
        player.sendMessage("§aВы были телепортированы на спавн мира!");
        
        return true;
    }
}
