package sguest.jeimultiblocks.jei;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.JsonObject;
import com.mojang.blaze3d.matrix.MatrixStack;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.immersiveengineering.client.manual.ManualElementMultiblock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.items.IEItems;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.SpecialManualElement;
import blusunrize.lib.manual.gui.ManualScreen;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import sguest.jeimultiblocks.MultiblockUtil;

public class MultiblockRecipeCategory implements IRecipeCategory<IETemplateMultiblock>
{
    public static final ResourceLocation UID = new ResourceLocation(Lib.MODID, "multiblock");
    private final IDrawable icon;
    private final IDrawable background;
    
    public MultiblockRecipeCategory(IGuiHelper helper)
    {
        background = helper.createBlankDrawable(176, 108);
        icon = helper.createDrawableIngredient(new ItemStack(IEItems.Tools.hammer));
    }

    @Override
    public void draw(IETemplateMultiblock multiblock, MatrixStack transform, double mouseX, double mouseY)
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
    public String getTitle() {
        return I18n.get("jeimultiblocks.formMultiblock");
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
    public void setIngredients(IETemplateMultiblock recipe, IIngredients ingredients) {
        ItemStack output = MultiblockUtil.getMultiblockItem(recipe);
        if(!output.isEmpty()) {
            ingredients.setOutput(VanillaTypes.ITEM, output);
        }
        ingredients.setInputIngredients(getBlocks(recipe));
    }

    @Override
    public void setRecipe(IRecipeLayout recipeLayout, IETemplateMultiblock recipe, IIngredients ingredients) {
        IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

        ItemStack output = MultiblockUtil.getMultiblockItem(recipe);
        int baseIndex = 0;
        if(!output.isEmpty()) {
            guiItemStacks.init(0, false, 2, 2);
            guiItemStacks.set(0, output);
            baseIndex = 1;
        }

        int y = 2;
        int x = 154;
        List<Ingredient> outputs = getBlocks(recipe);
        for(int i = 0; i < outputs.size(); i++) {
            guiItemStacks.init(i + baseIndex, true, x, y);
            guiItemStacks.set(i + baseIndex, outputs.get(i).getItems()[0]);
            y += 20;
            if(y > 100) {
                y = 2;
                x -= 20;
            }
        }
    }

    private List<Ingredient> getBlocks(IETemplateMultiblock recipe) {
        return Arrays.asList(recipe.getTotalMaterials()).stream()
        .map(i -> Ingredient.of(i))
        .collect(Collectors.toList());
    }
}
