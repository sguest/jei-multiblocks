package sguest.jeimultiblocks.emi;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.api.multiblocks.TemplateMultiblock;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import sguest.jeimultiblocks.ContentHelper;
import sguest.jeimultiblocks.JeiMultiblocks;

import java.util.List;
import java.util.stream.Collectors;

@EmiEntrypoint
public class EmiModPlugin implements EmiPlugin {
    public static final EmiStack HAMMER = EmiStack.of(ContentHelper.getHammer());
    public static final EmiRecipeCategory MULTIBLOCK_CATEGORY
            = new EmiRecipeCategory(ResourceLocation.fromNamespaceAndPath(JeiMultiblocks.MODID, "plugin"), HAMMER);

    @Override
    public void register(EmiRegistry emiRegistry) {
        // Tell EMI to add a tab for your category
        emiRegistry.addCategory(MULTIBLOCK_CATEGORY);

        // Add all the workstations your category uses
        emiRegistry.addWorkstation(MULTIBLOCK_CATEGORY, HAMMER);

        for (MultiblockHandler.IMultiblock multiblock : getMultiblockRecipes()) {
            emiRegistry.addRecipe(new EmiMultiblockRecipe(multiblock));
        }

        for (ItemStack multiblockItem : getMultiblockItems()) {
            emiRegistry.addEmiStack(EmiStack.of(multiblockItem));
        }
    }

    private List<MultiblockHandler.IMultiblock> getMultiblockRecipes()
    {
        return MultiblockHandler.getMultiblocks().stream()
                .filter(item -> item instanceof TemplateMultiblock)
                .collect(Collectors.toList());
    }


    private List<ItemStack> getMultiblockItems()
    {
        return MultiblockHandler.getMultiblocks().stream()
                .filter(item -> item instanceof TemplateMultiblock)
                .map(item -> new ItemStack(item.getBlock()))
                .collect(Collectors.toList());
    }
}
