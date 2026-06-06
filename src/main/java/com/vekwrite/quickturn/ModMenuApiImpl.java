package com.vekwrite.quickturn;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

import java.io.IOException;

public class ModMenuApiImpl implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (current_screen) -> {
            ConfigBuilder builder = ConfigBuilder.create()
                    .setParentScreen(current_screen)
                    .setTitle(Text.of("Quick Turn Mod"));
            ConfigCategory general = builder.getOrCreateCategory(Text.of("Quick Turn Mod"));
            ConfigEntryBuilder entry_builder = builder.entryBuilder();
            general.addEntry(entry_builder.startFloatField(Text.of("Turn Amount"), QuickTurn.config.turn_amount)
                    .setDefaultValue(180)
                    .setMax(360)
                    .setMin(0)
                    .setSaveConsumer(new_val -> {
                        try {
                            QuickTurn.config.set_and_save_new_turn_amount(new_val);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .build());
            return builder.build();
        };
    }
}