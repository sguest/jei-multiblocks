package sguest.jeimultiblocks;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ContentHelper {
    public static final Item HAMMER = BuiltInRegistries.ITEM.get(ResourceLocation.fromNamespaceAndPath("immersiveengineering", "hammer"));
    public static ItemStack getHammer() {
        return new ItemStack(HAMMER);
    }
}
