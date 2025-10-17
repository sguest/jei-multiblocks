package sguest.jeimultiblocks.jei;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import blusunrize.immersiveengineering.api.multiblocks.TemplateMultiblock;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import sguest.jeimultiblocks.ContentHelper;
import sguest.jeimultiblocks.JeiMultiblocks;
import sguest.jeimultiblocks.JeiMultiblocksEventHandler;
import sguest.jeimultiblocks.MultiblockUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

@JeiPlugin
public class JeiModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(JeiMultiblocks.MODID, "plugin");
    }

    @Override
    public void registerRecipeCatalysts(@Nonnull IRecipeCatalystRegistration registration)
    {
        registration.addRecipeCatalyst(ContentHelper.getHammer(), MultiblockRecipeCategory.recipeType);
    }

    @Override
    public void registerCategories(@Nonnull IRecipeCategoryRegistration registry)
    {
        IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addRecipeCategories(new MultiblockRecipeCategory(guiHelper));
    }

    @Override
    public void onRuntimeAvailable(@Nonnull IJeiRuntime jeiRuntime) {

        JeiMultiblocksEventHandler.registerCallback(() -> {
            jeiRuntime.getRecipeManager().addRecipes(MultiblockRecipeCategory.recipeType, MultiblockUtils.getMultiblockRecipes());
            return true;
        });

        jeiRuntime.getIngredientManager().addIngredientsAtRuntime(VanillaTypes.ITEM_STACK, MultiblockUtils.getMultiblockItems());
    }
}
