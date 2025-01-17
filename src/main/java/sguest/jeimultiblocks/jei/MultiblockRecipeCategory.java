package sguest.jeimultiblocks.jei;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks.MultiblockManualData;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.SpecialManualElement;
import blusunrize.lib.manual.gui.ManualScreen;

import javax.annotation.Nonnull;

import com.google.gson.JsonObject;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import sguest.jeimultiblocks.ContentHelper;
import sguest.jeimultiblocks.JeiMultiblocks;

public class MultiblockRecipeCategory implements IRecipeCategory<IMultiblock>
{
    public static final RecipeType<IMultiblock> recipeType = new RecipeType<IMultiblock>(ResourceLocation.fromNamespaceAndPath(JeiMultiblocks.MODID, "multiblock"), IMultiblock.class);
    private final IDrawable icon;
    private final IDrawable background;

    public MultiblockRecipeCategory(IGuiHelper helper)
    {
        background = helper.createBlankDrawable(176, 108);
        icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, ContentHelper.getHammer());
    }

    @Override
    public void draw(@Nonnull IMultiblock multiblock, @Nonnull IRecipeSlotsView recipeSlotsView, @Nonnull GuiGraphics guiGraphics, double mouseX, double mouseY)
    {
        ManualInstance manual = ManualHelper.getManual();
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("name", multiblock.getUniqueName().toString());
        SpecialManualElement manualElement = manual.getElementFactory(ResourceLocation.fromNamespaceAndPath(Lib.MODID, "multiblock")).apply(jsonObj);
        ManualScreen screen = ManualHelper.getManual().getGui();
        // Passing 0, 0 for mouse coords because we don't want to render the manual's ingredient list tooltip
        manualElement.render(guiGraphics, screen, 30, 20, 0, 0);
    }

    @Override
    public RecipeType<IMultiblock> getRecipeType() {
        return recipeType;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jeimultiblocks.formMultiblock");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull IMultiblock multiblock, @Nonnull IFocusGroup focuses)
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
