package sguest.jeimultiblocks.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.register.IEItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import sguest.jeimultiblocks.JeiMultiblocks;

@JeiPlugin
public class JeiModPlugin implements IModPlugin {
    
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(JeiMultiblocks.MODID, "plugin");
    }
    
    @Override
    public void registerIngredients(IModIngredientRegistration registry)
    {
        registry.register(new MultiblockIngredientType(), getMultiblockRecipes(), new MultiblockIngredientHelper(), new MultiblockIngredientRenderer());
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(new ItemStack(IEItems.Tools.HAMMER), MultiblockRecipeCategory.UID);
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
    
    private Collection<IETemplateMultiblock> getMultiblockRecipes()
    {
        return MultiblockHandler.getMultiblocks().stream()
        .filter(item -> item instanceof IETemplateMultiblock)
        .map(item -> (IETemplateMultiblock)item)
        .collect(Collectors.toList());
    }
}
