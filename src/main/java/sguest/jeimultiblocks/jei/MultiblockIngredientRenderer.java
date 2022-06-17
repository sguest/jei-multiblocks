package sguest.jeimultiblocks.jei;

import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import sguest.jeimultiblocks.MultiblockUtil;

public class MultiblockIngredientRenderer implements IIngredientRenderer<IETemplateMultiblock>
{
    @Override
    public List<ITextComponent> getTooltip(IETemplateMultiblock ingredient, ITooltipFlag tooltipFlag)
    {
        net.minecraft.item.ItemStack itemIngredient = MultiblockUtil.getMultiblockItem(ingredient);
        if(itemIngredient.isEmpty()) {
            return Collections.singletonList(new TranslationTextComponent("jeimultiblocks.nameUnavailable"));
        }
        return Collections.singletonList(new TranslationTextComponent(itemIngredient.getDescriptionId()));
    }
    
    @Override
    public void render(MatrixStack matrixStack, int xPosition, int yPosition, IETemplateMultiblock ingredient)
    {
        // lifted from JEI's ItemStackRenderer, since this should just render like normal items
        if (ingredient != null) {
            net.minecraft.item.ItemStack itemIngredient = MultiblockUtil.getMultiblockItem(ingredient);
            if(!itemIngredient.isEmpty()) {
                RenderSystem.pushMatrix();
                RenderSystem.multMatrix(matrixStack.last().pose());
                RenderSystem.enableDepthTest();
                RenderHelper.turnBackOn();
                Minecraft minecraft = Minecraft.getInstance();
                FontRenderer font = getFontRenderer(minecraft, ingredient);
                ItemRenderer itemRenderer = minecraft.getItemRenderer();
                itemRenderer.renderAndDecorateFakeItem(itemIngredient, xPosition, yPosition);
                itemRenderer.renderGuiItemDecorations(font, itemIngredient, xPosition, yPosition);
                RenderSystem.disableBlend();
                RenderHelper.turnOff();
                RenderSystem.popMatrix();
            }
        }
    }
}
