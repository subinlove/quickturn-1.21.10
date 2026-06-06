package com.vekwrite.quickturn;

import net.fabricmc.api.ModInitializer;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuickTurn implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    // public static final String MOD_ID = "vekquickturn";
    public static final String MOD_NAME = "VekQuickTurn";

    public static TurnConfig config = new TurnConfig();

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing mod");
    }

    public static void log(Level level, String message){
        LOGGER.log(level, "["+MOD_NAME+"] " + message);
    }

}