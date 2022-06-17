package sguest.jeimultiblocks.jei;

import mezz.jei.api.ingredients.IIngredientType;
import sguest.jeimultiblocks.MultiblockWrapper;

public class MultiblockIngredientType implements IIngredientType<MultiblockWrapper>
{
    @Override
    public Class<? extends MultiblockWrapper> getIngredientClass()
    {
        return MultiblockWrapper.class;
    }
}