package sguest.jeimultiblocks.jei;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import com.mojang.blaze3d.systems.RenderSystem;

import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler.IMultiblock;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class MultiblockIngredientRenderer implements IIngredientRenderer<IMultiblock>
{
    @Override
    public List<Component> getTooltip(@Nonnull IMultiblock ingredient, @Nonnull TooltipFlag tooltipFlag)
    {
        return Collections.singletonList(ingredient.getDisplayName());
    }

    @Override
    public void render(@Nonnull GuiGraphics guiGraphics, @Nonnull IMultiblock ingredient)
    {
        // lifted from JEI's ItemStackRenderer, since this should just render like normal items
        if (ingredient != null)
        {
            RenderSystem.enableDepthTest();

            Minecraft minecraft = Minecraft.getInstance();
            Font font = getFontRenderer(minecraft, ingredient);
            ItemStack stack = new ItemStack(ingredient.getBlock());
            guiGraphics.renderFakeItem(stack, 0, 0);
            guiGraphics.renderItemDecorations(font, stack, 0, 0);
            RenderSystem.disableBlend();
        }
    }
}
