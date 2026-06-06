package com.vekwrite.quickturn.Client;

import com.vekwrite.quickturn.QuickTurn;
import com.vekwrite.quickturn.TurnConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class QuickTurnClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        try {
            QuickTurn.config.load();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        KeyBinding quickTurnKeyBinding;
        quickTurnKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
           "key.vekquickturn.quickturn",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.vekquickturn.keycat"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (quickTurnKeyBinding.wasPressed()) {
                MinecraftClient.getInstance().player.setYaw(MinecraftClient.getInstance().player.getYaw() + QuickTurn.config.turn_amount);
            }
        });

    }

}
