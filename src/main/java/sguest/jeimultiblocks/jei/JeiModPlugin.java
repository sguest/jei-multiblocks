package sguest.jeimultiblocks.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.items.IEItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import sguest.jeimultiblocks.JeiMultiblocks;
import sguest.jeimultiblocks.MultiblockWrapper;

@JeiPlugin
public class JeiModPlugin implements IModPlugin {
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(JeiMultiblocks.MODID, "plugin");
    }
    
    @Override
    public void registerIngredients(IModIngredientRegistration registry)
    {
        Collection<MultiblockWrapper> multiblocks = getMultiblockRecipes().stream()
            .filter(m -> !m.getItemStack().isEmpty())
            .collect(Collectors.toList());
        registry.register(new MultiblockIngredientType(), multiblocks, new MultiblockIngredientHelper(), new MultiblockIngredientRenderer());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(IEItems.Tools.hammer), MultiblockRecipeCategory.UID);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry)
    {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new MultiblockRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addRecipes(getMultiblockRecipes(), MultiblockRecipeCategory.UID);
    }
    
    private Collection<MultiblockWrapper> getMultiblockRecipes()
    {
        return MultiblockHandler.getMultiblocks().stream()
        .filter(item -> item instanceof IETemplateMultiblock)
        .map(item -> new MultiblockWrapper(item))
        .collect(Collectors.toList());
    }
}
