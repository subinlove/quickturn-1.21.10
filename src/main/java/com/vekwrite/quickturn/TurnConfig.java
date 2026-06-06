package com.vekwrite.quickturn;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileReader;

public class TurnConfig {
    public static Float turn_amount = 180f;
    public static void reset() throws IOException {
        turn_amount = 180f;
        save();
    }
    public static void load() throws IOException {
        File file = new File(MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/config/quickturn.json");
        if(file.exists()) {
            FileReader reader = new FileReader(file);
            JsonObject config = new Gson().fromJson(reader, JsonObject.class);
            if(config.has("turn_amount")) {
                turn_amount = config.get("turn_amount").getAsFloat();
//                if(turn_amount >)
            }
            reader.close();
        } else {
            if(file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                writer.write("{\"turn_amount\":" + turn_amount + "}");
                writer.close();
            }
        }
    }
    // This code is silly, ik
    public static void set_and_save_new_turn_amount(float new_turn_amount) throws IOException {
        turn_amount = new_turn_amount;
        save();
    }
    public static void save() throws IOException {
        File file = new File(MinecraftClient.getInstance().runDirectory.getAbsolutePath() + "/config/quickturn.json");
        FileWriter writer = new FileWriter(file);
        writer.write("{\"turn_amount\":" + turn_amount + "}");
        writer.close();
    }
}