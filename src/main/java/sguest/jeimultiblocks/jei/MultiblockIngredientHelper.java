package sguest.jeimultiblocks.jei;

import javax.annotation.Nullable;

import mezz.jei.api.ingredients.IIngredientHelper;
import mezz.jei.api.ingredients.subtypes.UidContext;
import mezz.jei.api.recipe.IFocus;
import mezz.jei.api.recipe.IFocusFactory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import sguest.jeimultiblocks.MultiblockWrapper;

public class MultiblockIngredientHelper implements IIngredientHelper<MultiblockWrapper>
{
    @Override
    public IFocus<?> translateFocus(IFocus<MultiblockWrapper> focus, IFocusFactory focusFactory) {
        MultiblockWrapper multiblock = focus.getValue();
        ItemStack stack = multiblock.getItemStack();
        if(!stack.isEmpty()) {
            return focusFactory.createFocus(focus.getMode(), stack);
        }
        return focus;
    }

    @Override
    public String getDisplayName(MultiblockWrapper ingredient)
    {
        if(ingredient.getItemStack().isEmpty()) {
            return I18n.get("jeimultiblocks.nameUnavailable");
        }
        return I18n.get(ingredient.getItemStack().getDescriptionId());
    }

    @Override
    public String getUniqueId(MultiblockWrapper ingredient, UidContext context)
    {
        return ingredient.getMultiblock().getUniqueName().toString();
    }

    @Override
    public String getUniqueId(MultiblockWrapper ingredient)
    {
        return ingredient.getMultiblock().getUniqueName().toString();
    }

    @Override
    public String getModId(MultiblockWrapper ingredient)
    {
        return ingredient.getMultiblock().getUniqueName().getNamespace();
    }

    @Override
    public String getResourceId(MultiblockWrapper ingredient)
    {
        return ingredient.getMultiblock().getUniqueName().getPath();
    }

    @Override
    public MultiblockWrapper copyIngredient(MultiblockWrapper ingredient)
    {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable MultiblockWrapper ingredient)
    {
        if(ingredient == null)
        {
            return null;
        }
        return "Multiblock " + ingredient.getMultiblock().getUniqueName();
    }

    @Override
    public MultiblockWrapper getMatch(Iterable<MultiblockWrapper> ingredients, MultiblockWrapper toMatch) {
        for(MultiblockWrapper multiblock : ingredients) {
            if(multiblock.getMultiblock().getUniqueName().equals(toMatch.getMultiblock().getUniqueName())) {
                return multiblock;
            }
        }
        return null;
    }
}
