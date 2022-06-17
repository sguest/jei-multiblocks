package sguest.jeimultiblocks.jei;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.vertex.PoseStack;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks.MultiblockManualData;
import blusunrize.immersiveengineering.client.manual.ManualElementMultiblock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.register.IEItems;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.SpecialManualElement;
import blusunrize.lib.manual.gui.ManualScreen;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class MultiblockRecipeCategory implements IRecipeCategory<IETemplateMultiblock>
{
    public static final ResourceLocation UID = new ResourceLocation(Lib.MODID, "multiblock");
    private final IDrawable icon;
    private final IDrawable background;
    
    public MultiblockRecipeCategory(IGuiHelper helper)
    {
        background = helper.createBlankDrawable(176, 108);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(IEItems.Tools.HAMMER));
    }
    
    @Override
    public void draw(IETemplateMultiblock multiblock, IRecipeSlotsView recipeSlotsView, PoseStack transform, double mouseX, double mouseY)
    {
        ManualInstance manual = ManualHelper.getManual();
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("name", multiblock.getUniqueName().toString());
        SpecialManualElement manualElement = manual.getElementFactory(new ResourceLocation(Lib.MODID, "multiblock")).apply(jsonObj);
        if(manualElement instanceof ManualElementMultiblock)
        {
            ManualScreen screen = ManualHelper.getManual().getGui();
            // Passing 0, 0 for mouse coords because we don't want to render the manual's ingredient list tooltip
            ((ManualElementMultiblock)manualElement).render(transform, screen, 30, 20, 0, 0);
        }
    }
    
    @Override
    public ResourceLocation getUid() {
        return UID;
    }
    
    @Override
    public Class<? extends IETemplateMultiblock> getRecipeClass() {
        return IETemplateMultiblock.class;
    }
    
    @Override
    public Component getTitle() {
        return new TranslatableComponent("jeimultiblocks.formMultiblock");
    }
    
    @Override
    public IDrawable getBackground() {
        return background;
    }
    
    @Override
    public IDrawable getIcon() {
        return icon;
    }
    
    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, IETemplateMultiblock multiblock, IFocusGroup focuses)
    {
        builder.addSlot(RecipeIngredientRole.OUTPUT, 2, 2)
        .addItemStack(new ItemStack(multiblock.getBlock()));
        
        MultiblockManualData manualData = ClientMultiblocks.get(multiblock);
        int y = 2;
        int x = 154;
        for(ItemStack input : manualData.getTotalMaterials())
        {
            builder.addSlot(RecipeIngredientRole.INPUT, x, y)
            .addItemStack(input);
            y += 20;
            if(y > 100) {
                y = 2;
                x -= 20;
            }
        }
    }
}
