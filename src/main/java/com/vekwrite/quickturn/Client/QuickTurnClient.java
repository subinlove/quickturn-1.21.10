package com.vekwrite.quickturn.Client;

import com.vekwrite.quickturn.QuickTurn;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import org.lwjgl.glfw.GLFW;

public class QuickTurnClient implements ClientModInitializer {

```
private static KeyBinding quickTurnKeyBinding;

@Override
public void onInitializeClient() {

    quickTurnKeyBinding =
            KeyBindingHelper.registerKeyBinding(
                    new KeyBinding(
                            "key.vekquickturn.quickturn",
                            InputUtil.Type.KEYSYM,
                            GLFW.GLFW_KEY_R,
                            "category.vekquickturn.keycat"
                    )
            );

    ClientTickEvents.END_CLIENT_TICK.register(client -> {

        while (quickTurnKeyBinding.wasPressed()) {

            MinecraftClient mc = MinecraftClient.getInstance();

            if (mc.player == null) {
                continue;
            }

            mc.player.setYaw(
                    mc.player.getYaw()
                            + QuickTurn.config.turn_amount
            );
        }
    });
}
```

}
