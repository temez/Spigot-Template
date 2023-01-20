package dev.temez.snippet.command;

import dev.temez.snippet.Main;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author temez
 * @since 0.0.1dev
 */
@FieldDefaults(level = AccessLevel.PROTECTED, makeFinal = true)
@RequiredArgsConstructor
public abstract class BaseCommand implements CommandExecutor {

    Main plugin;
    public abstract void handle(CommandSender sender, Command command, String label, String[] args);

    protected void register(String command) {
        Objects.requireNonNull(plugin.getCommand(command)).setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        handle(sender, command, label, args);
        return true;
    }
}
