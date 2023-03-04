package dev.temez.snippet.layer;

import com.samjakob.spigui.SpiGUI;
import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.item.ItemBuilder;
import dev.temez.snippet.Main;
import dev.temez.snippet.util.ChatUtility;
import dev.temez.snippet.util.configuration.ReflectConfigurationParser;
import dev.temez.snippet.util.configuration.exception.IncomparableFieldType;
import dev.temez.snippet.util.configuration.exception.NoSuchConfigurationSection;
import dev.temez.snippet.util.semantics.Layer;
import dev.temez.snippet.util.settings.Settings;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.bukkit.Material;

/**
 * @author temez
 * @since 0.1dev
 */
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UtilityLayer extends Layer {

    ChatUtility chatUtility;
    SpiGUI spiGUI;
    Settings settings;

    public UtilityLayer(Main plugin) {
        super(plugin);
    }

    @Override
    public void enable() {
        super.enable();
        chatUtility = new ChatUtility("temez huilan");
        parseSettings();
        prepareGUI();
    }

    @SneakyThrows({NoSuchConfigurationSection.class, IncomparableFieldType.class})
    private void parseSettings() {
        settings = new ReflectConfigurationParser<>(plugin.getConfig(), new Settings()).parse().getConfiguration();
    }

    private void prepareGUI() {
        spiGUI = new SpiGUI(plugin);
        spiGUI.setEnableAutomaticPagination(false);
        spiGUI.setDefaultPaginationButtonBuilder((type, inventory) -> {
            switch (type) {
                case PREV_BUTTON -> {
                    if (inventory.getCurrentPage() > 0) return new SGButton(new ItemBuilder(Material.ARROW)
                            .name("&#a7fcabПредыдущая страница")
                            .build()
                    ).withListener(event -> {
                        event.setCancelled(true);
                        inventory.previousPage(event.getWhoClicked());
                    });
                    else return null;
                }
                case CURRENT_BUTTON -> {
                    return new SGButton(new ItemBuilder(Material.NAME_TAG)
                            .name("&7Страница " + (inventory.getCurrentPage() + 1) + " из " + inventory.getMaxPage()).build()
                    ).withListener(event -> event.setCancelled(true));
                }
                case NEXT_BUTTON -> {
                    if (inventory.getCurrentPage() < inventory.getMaxPage() - 1)
                        return new SGButton(new ItemBuilder(Material.ARROW)
                                .name("&#a7fcabСледующая страница").build()
                        ).withListener(event -> {
                            event.setCancelled(true);
                            inventory.nextPage(event.getWhoClicked());
                        });
                    else return null;
                }
                default -> {
                    return null;
                }
            }
        });
    }

}
