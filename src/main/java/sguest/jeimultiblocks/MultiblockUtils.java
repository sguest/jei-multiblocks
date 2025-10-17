package sguest.jeimultiblocks;

import java.util.List;
import java.util.stream.Collectors;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import blusunrize.immersiveengineering.api.multiblocks.TemplateMultiblock;
import net.minecraft.world.item.ItemStack;

public class MultiblockUtils {
    public static List<IMultiblock> getMultiblockRecipes()
    {
        return MultiblockHandler.getMultiblocks().stream()
        .filter(item -> item instanceof TemplateMultiblock)
        .collect(Collectors.toList());
    }

    
    public static List<ItemStack> getMultiblockItems()
    {
        return MultiblockHandler.getMultiblocks().stream()
        .filter(item -> item instanceof TemplateMultiblock)
        .map(item -> new ItemStack(item.getBlock()))
        .collect(Collectors.toList());
    }
}