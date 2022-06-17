package sguest.jeimultiblocks;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class MultiblockWrapper {
    private IMultiblock multiblock;
    private ItemStack itemStack;

    public MultiblockWrapper(IMultiblock multiblock) {
        this.multiblock = multiblock;
        this.itemStack = getItem();
    }

    public IMultiblock getMultiblock() {
        return multiblock;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    private ItemStack getItem() {
        /*
            1.18 has IETemplateMultiblock.getBlock(), but in 1.16 I can't find any way to get the multiblocks
            to tell us what their block item is, so we have to go "by convention" on resource location names
            This matches IE (with some manual fixes) and Petroleum multiblocks. Any addons that don't use the same structure won't
            be found. This will mean the multiblock isn't searchable or associated with the catalyst on those blocks'
            processing recipes. The recipe for the multiblock itself will still be registered and can be found via
            usages on the hammer (the catalyst) or the blocks that make up the multiblock
         */
        ResourceLocation multiblockId = multiblock.getUniqueName();
        String path = multiblockId.getPath().replace("multiblocks/", "");
        String namespace = multiblockId.getNamespace();

        if(namespace.equals("immersiveengineering")) {
            if(path.equals("improved_blast_furnace")) {
                path = "advanced_blast_furnace";
            }
            else if(path.equals("arcfurnace")) {
                path = "arc_furnace";
            }
            else if(path.equals("sheetmetal_tank")) {
                path = "tank";
            }
        }
        ResourceLocation location = new ResourceLocation(namespace, path);
        return new ItemStack(GameRegistry.findRegistry(Item.class).getValue(location));
    }
}
