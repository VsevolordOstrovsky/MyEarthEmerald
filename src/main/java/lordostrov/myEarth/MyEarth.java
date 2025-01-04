package lordostrov.myEarth;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.java.JavaPlugin;
public final class MyEarth extends JavaPlugin {
    private WorldGuardPlugin wgp;
    @Override
    public void onEnable() {
        wgp = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
        if(wgp == null) {
            getLogger().severe("No WorldGuard plugin found. Disabling...");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        // Plugin startup logic
        getCommand("buy").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
