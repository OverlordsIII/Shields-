package io.github.overlordsiii;


import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import io.github.overlordsiii.item.FireShield;

import net.minecraft.item.Item;
import net.minecraft.item.Items;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ShieldsPlus implements ModInitializer {

	public static final Item FIRE_SHIELD = new FireShield();
	/**
	 * Runs the mod initializer.
	 */
	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new Identifier("shieldsplus", "fire_shield"), FIRE_SHIELD);
	}
}
