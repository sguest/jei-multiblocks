 package sguest.jeimultiblocks.jei;

 import javax.annotation.Nullable;

 import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
 import mezz.jei.api.ingredients.IIngredientHelper;
 import mezz.jei.api.ingredients.IIngredientType;
 import mezz.jei.api.ingredients.subtypes.UidContext;
 import net.minecraft.resources.ResourceLocation;
 import net.minecraft.world.level.block.Block;
 import net.minecraftforge.registries.ForgeRegistries;

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
         return ForgeRegistries.BLOCKS.getKey(ingredient.getBlock()).toString();
     }

     @Override
     public ResourceLocation getResourceLocation(IMultiblock ingredient)
     {
         return ingredient.getUniqueName();
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
