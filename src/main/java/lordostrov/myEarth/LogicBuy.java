package lordostrov.myEarth;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.Region;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class LogicBuy {

    public void createChunkRegion(Player player, Chunk chunk, String regionName) {
        World world = player.getWorld();
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));
        int minX = chunk.getX() * 16;
        int minZ = chunk.getZ() * 16;
        int maxZ = minZ + 15;
        int maxX = minX + 15;


        BlockVector3 min = BlockVector3.at(minX, -64, minZ);
        BlockVector3 max = BlockVector3.at(maxX, 320, maxZ);

        // Создаем CuboidRegion
        Region region = new CuboidRegion(min,max);

        // Создаем ProtectedRegion из CuboidRegion
        ProtectedRegion protectedRegion = new ProtectedCuboidRegion(regionName,region.getMinimumPoint(),region.getMaximumPoint());
        protectedRegion.getOwners().addPlayer(player.getName());

        regionManager.addRegion(protectedRegion);
        player.sendMessage("Chunk region '" + regionName + "' has been created!");
    }
}
