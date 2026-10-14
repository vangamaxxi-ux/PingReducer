 package com.example.hitbox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class HitboxMod implements ClientModInitializer {
    public static boolean enabled = false;
    public static float margin = 0.3f;

    @Override
    public void onInitializeClient() {
        KeyBinding.Category cat = KeyBinding.Category.create(Identifier.of("hitboxmod", "main"));
        KeyBinding toggle = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.hitboxmod.toggle", GLFW.GLFW_KEY_H, cat));
        KeyBinding up = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.hitboxmod.up", GLFW.GLFW_KEY_UP, cat));
        KeyBinding down = KeyBindingHelper.registerKeyBinding(
            new KeyBinding("key.hitboxmod.down", GLFW.GLFW_KEY_DOWN, cat));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            while (toggle.wasPressed()) {
                enabled = !enabled;
                client.player.sendMessage(Text.literal("Hitbox: " + (enabled ? "ON" : "OFF")), true);
            }
            while (up.wasPressed()) {
                margin = Math.min(2.0f, margin + 0.1f);
                client.player.sendMessage(Text.literal("Hitbox size: " + String.format("%.1f", margin)), true);
            }
            while (down.wasPressed()) {
                margin = Math.max(0.0f, margin - 0.1f);
                client.player.sendMessage(Text.literal("Hitbox size: " + String.format("%.1f", margin)), true);
            }
        });
    }
}
