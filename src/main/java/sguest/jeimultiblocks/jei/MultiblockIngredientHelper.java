package sguest.jeimultiblocks.jei;

import javax.annotation.Nullable;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;

public class MultiblockIngredientHelper implements IIngredientHelper<IMultiblock>
{
    @Override
    public IIngredientType<IMultiblock> getIngredientType()
    {
        return new MultiblockIngredientType();
    }
    
    @Override
    public String getDisplayName(IMultiblock ingredient)
    {
        return ingredient.getDisplayName().toString();
    }
    
    @Override
    public String getUniqueId(IMultiblock ingredient, UidContext context)
    {
        return ingredient.getBlock().getRegistryName().toString();
    }
    
    @Override
    public String getModId(IMultiblock ingredient)
    {
        return ingredient.getUniqueName().getNamespace();
    }
    
    @Override
    public String getResourceId(IMultiblock ingredient)
    {
        return ingredient.getUniqueName().getPath();
    }
    
    @Override
    public IMultiblock copyIngredient(IMultiblock ingredient)
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
