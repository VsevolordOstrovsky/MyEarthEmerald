package lordostrov.myEarth;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    MyEarth plugin;
    LogicBuy logicBuy = new LogicBuy();
    CheckInventory checkInventory = new CheckInventory();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (command.getName().equalsIgnoreCase("buy")) {

            // Проверяем, является ли отправитель команды игроком
            if (!(commandSender instanceof Player)) {
                commandSender.sendMessage(ChatColor.RED + "This command can only be used by a player.");
                return true;
            }

            Player player = (Player) commandSender;
            if(checkInventory.hasEmeraldStack(player)){
                player.sendMessage(ChatColor.GREEN + "You already have an emerald stack.");
                checkInventory.removeEmeraldStack(player);
                player.sendMessage(ChatColor.DARK_PURPLE + "I take your emeralds!!!");
            }else{
                player.sendMessage(ChatColor.RED + "I don't found emeralds in your inventory :(");
                return true;
            }
            // Проверяем, есть ли имя региона
            if(strings.length == 0){
                commandSender.sendMessage(ChatColor.RED + "Buying failed: Region name not provided.");
                return true;
            }
            String name = strings[0];
            Chunk chunk = player.getLocation().getChunk();
            logicBuy.createChunkRegion(player, chunk, name);

            commandSender.sendMessage(ChatColor.GOLD + "You are buying!");
            return true;

        }
        return false;
    }
}
