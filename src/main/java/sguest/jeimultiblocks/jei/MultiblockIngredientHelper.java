package sguest.jeimultiblocks.jei;

import javax.annotation.Nullable;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import sguest.jeimultiblocks.MultiblockUtil;

public class MultiblockIngredientHelper implements IIngredientHelper<IETemplateMultiblock>
{
    @Override
    public IFocus<?> translateFocus(IFocus<IETemplateMultiblock> focus, IFocusFactory focusFactory) {
        IETemplateMultiblock multiblock = focus.getValue();
        ItemStack stack = MultiblockUtil.getMultiblockItem(multiblock);
        if(!stack.isEmpty()) {
            return focusFactory.createFocus(focus.getMode(), stack);
        }
        return focus;
    }

    @Override
    public String getDisplayName(IETemplateMultiblock ingredient)
    {
        net.minecraft.item.ItemStack itemIngredient = MultiblockUtil.getMultiblockItem(ingredient);
        if(itemIngredient.isEmpty()) {
            return I18n.get("jeimultiblocks.nameUnavailable");
        }
        return I18n.get(itemIngredient.getDescriptionId());
    }

    @Override
    public String getUniqueId(IETemplateMultiblock ingredient, UidContext context)
    {
        return ingredient.getUniqueName().toString();
    }

    @Override
    public String getUniqueId(IETemplateMultiblock ingredient)
    {
        return ingredient.getUniqueName().toString();
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
        return "Multiblock " + ingredient.getUniqueName();
    }

    @Override
    public IETemplateMultiblock getMatch(Iterable<IETemplateMultiblock> ingredients, IETemplateMultiblock toMatch) {
        for(IETemplateMultiblock multiblock : ingredients) {
            if(multiblock.getUniqueName().equals(toMatch.getUniqueName())) {
                return multiblock;
            }
        }
        return null;
    }
}
