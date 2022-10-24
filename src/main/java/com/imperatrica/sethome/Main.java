package com.imperatrica.sethome;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.event.Listener;
import cn.nukkit.level.Location;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;
import java.io.File;

public class Main extends PluginBase implements Listener {
    public Main() {
    }

    public void onEnable() {
        this.getLogger().info("\n\n§a sethome by imperatrica\n\n");
        this.getServer().getPluginManager().registerEvents(this, this);
        this.getDataFolder().mkdirs();
        this.saveResource("config.yml");
        this.saveResource("homes.yml");
    }

    public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
        Config var5 = new Config(new File(this.getDataFolder(), "config.yml"), 2);
        Config var6 = new Config(new File(this.getDataFolder(), "homes.yml"), 2);
        Player var7;
        String var8;
        if (var2.getName().toLowerCase().equals("home")) {
            if (var1 instanceof Player) {
                var7 = (Player)var1;
                var8 = var7.getName().toLowerCase();
                if (var6.get(var8) != null) {
                    String[] var9 = var6.get(var8).toString().split(",");
                    var7.teleport(new Location(Double.parseDouble(var9[0]), Double.parseDouble(var9[1]), Double.parseDouble(var9[2]), this.getServer().getLevelByName(var9[3])));
                    var1.sendMessage(TextFormat.colorize(var5.get("home").toString()));
                } else {
                    var1.sendMessage(TextFormat.colorize(var5.get("no-home").toString()));
                }
            } else {
                var1.sendMessage(TextFormat.colorize("§cThis command work in-game."));
            }

            return true;
        } else if (var2.getName().toLowerCase().equals("sethome")) {
            if (var1 instanceof Player) {
                var7 = (Player)var1;
                var8 = var7.getName().toLowerCase();
                var6.set(var8, (new Integer(var7.getFloorX())).toString() + "," + (new Integer(var7.getFloorY())).toString() + "," + (new Integer(var7.getFloorZ())).toString() + "," + var7.getLevel().getName());
                var6.save();
                var1.sendMessage(TextFormat.colorize(var5.get("sethome").toString()));
            } else {
                var1.sendMessage(TextFormat.colorize("§cThis command work in-game."));
            }

            return true;
        } else {
            return true;
        }
    }
}
