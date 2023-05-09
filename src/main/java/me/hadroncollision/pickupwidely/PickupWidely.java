package me.hadroncollision.pickupwidely;

import me.hadroncollision.pickupwidely.config.ModConfigs;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PickupWidely implements ModInitializer
{
	public static final String MOD_ID = "pickupwidely";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize()
	{
		ModConfigs.registerConfigs();
		PickupWidely.LOGGER.info("Starting PickupWidely mod...");
	}
}
