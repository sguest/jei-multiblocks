package sguest.jeimultiblocks.jei;

import javax.annotation.Nullable;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.ingredients.subtypes.UidContext;

public class MultiblockIngredientHelper implements IIngredientHelper<IETemplateMultiblock>
{
    @Override
    public IIngredientType<IETemplateMultiblock> getIngredientType()
    {
        return new MultiblockIngredientType();
    }
    
    @Override
    public String getDisplayName(IETemplateMultiblock ingredient)
    {
        return ingredient.getDisplayName().toString();
    }
    
    @Override
    public String getUniqueId(IETemplateMultiblock ingredient, UidContext context)
    {
        return ingredient.getBlock().getRegistryName().toString();
    }
    
    @Override
    public String getModId(IETemplateMultiblock ingredient)
    {
        return ingredient.getUniqueName().getNamespace();
    }
    
    @Override
    public String getResourceId(IETemplateMultiblock ingredient)
    {
        return ingredient.getUniqueName().getPath();
    }
    
    @Override
    public IETemplateMultiblock copyIngredient(IETemplateMultiblock ingredient)
    {
        return ingredient;
    }
    
    @Override
    public String getErrorInfo(@Nullable IETemplateMultiblock ingredient)
    {
        if(ingredient == null)
        {
            return null;
        }
        return "Multiblock " + ingredient.getDisplayName();
    }
}
