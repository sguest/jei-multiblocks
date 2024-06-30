package sguest.jeimultiblocks.jei;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MultiblockIngredientHelper implements IIngredientHelper<IMultiblock>
{
    @Override
    public IIngredientType<IMultiblock> getIngredientType()
    {
        return new MultiblockIngredientType();
    }

    @Override
    public String getDisplayName(@Nonnull IMultiblock ingredient)
    {
        return ingredient.getDisplayName().toString();
    }

    @Override
    public String getUniqueId(@Nonnull IMultiblock ingredient, @Nonnull UidContext context)
    {
        return BuiltInRegistries.BLOCK.getKey(ingredient.getBlock()).toString();
    }

    @Override
    public ResourceLocation getResourceLocation(@Nonnull IMultiblock ingredient)
    {
        return ingredient.getUniqueName();
    }

    @Override
    public IMultiblock copyIngredient(@Nonnull IMultiblock ingredient)
    {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable IMultiblock ingredient)
    {
        if(ingredient == null)
        {
            return null;
        }
        return "Multiblock " + ingredient.getDisplayName();
    }
}
