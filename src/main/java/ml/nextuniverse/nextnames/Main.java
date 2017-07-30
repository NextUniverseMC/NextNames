package ml.nextuniverse.nextnames;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

/**
 * Created by TheDiamondPicks on 28/07/2017.
 */
public class Main extends JavaPlugin implements Listener {
    Team owner;
    Team admin;
    Team mod;
    Team helper;
    Team supporter;
    Team member;

    Scoreboard board;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);

        board = Bukkit.getScoreboardManager().getMainScoreboard();

        owner = board.registerNewTeam("owner");
        admin = board.registerNewTeam("admin");
        mod = board.registerNewTeam("mod");
        helper = board.registerNewTeam("helper");
        supporter = board.registerNewTeam("supporter");
        member = board.registerNewTeam("member");

        owner.setPrefix(ChatColor.RED + "[Owner] ");
        admin.setPrefix(ChatColor.YELLOW + "[Admin] ");
        mod.setPrefix(ChatColor.DARK_GREEN + "[Mod] ");
        helper.setPrefix(ChatColor.AQUA + "[Helper] ");
        supporter.setPrefix(ChatColor.LIGHT_PURPLE + "");
        member.setPrefix(ChatColor.GRAY + "");
    }

    @Override
    public void onDisable() {
        board.getTeam("owner").unregister();
        board.getTeam("admin").unregister();
        board.getTeam("mod").unregister();
        board.getTeam("helper").unregister();
        board.getTeam("supporter").unregister();
        board.getTeam("member").unregister();
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("nametag.owner"))
            owner.addPlayer(p);
        else if (p.hasPermission("nametag.admin"))
            admin.addPlayer(p);
        else if (p.hasPermission("nametag.mod"))
            mod.addPlayer(p);
        else if (p.hasPermission("nametag.helper"))
            helper.addPlayer(p);
        else if (p.hasPermission("nametag.supporter"))
            supporter.addPlayer(p);
        else
            member.addPlayer(p);
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerQuitEvent e) {
        for (Player o : Bukkit.getServer().getOnlinePlayers()) {
            o.setScoreboard(board);
        }
        Player p = e.getPlayer();
        if (p.hasPermission("nametag.owner"))
            owner.removePlayer(p);
        else if (p.hasPermission("nametag.admin"))
            admin.removePlayer(p);
        else if (p.hasPermission("nametag.mod"))
            mod.removePlayer(p);
        else if (p.hasPermission("nametag.helper"))
            helper.removePlayer(p);
        else if (p.hasPermission("nametag.supporter"))
            supporter.removePlayer(p);
        else
            member.removePlayer(p);
    }
}
