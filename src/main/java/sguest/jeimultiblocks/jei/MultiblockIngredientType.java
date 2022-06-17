package sguest.jeimultiblocks.jei;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import mezz.jei.api.ingredients.IIngredientType;

public class MultiblockIngredientType implements IIngredientType<IETemplateMultiblock>
{
    @Override
    public Class<? extends IETemplateMultiblock> getIngredientClass()
    {
        return IETemplateMultiblock.class;
    }
}