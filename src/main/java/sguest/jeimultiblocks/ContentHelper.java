package sguest.jeimultiblocks;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ContentHelper {
    public static final RegistryObject<Item> HAMMER = RegistryObject.create(new ResourceLocation("immersiveengineering:hammer"), ForgeRegistries.ITEMS);
    public static ItemStack getHammer() {
        return new ItemStack(HAMMER.get());
    }
}
