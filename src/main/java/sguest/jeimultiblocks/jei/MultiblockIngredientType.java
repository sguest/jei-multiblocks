 package sguest.jeimultiblocks.jei;

 import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
 import mezz.jei.api.ingredients.IIngredientType;

 public class MultiblockIngredientType implements IIngredientType<IMultiblock>
 {
     @Override
     public Class<? extends IMultiblock> getIngredientClass()
     {
         return IMultiblock.class;
     }
 }