package sguest.jeimultiblocks.jei;

import java.util.Collections;
import java.util.List;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class MultiblockIngredientRenderer implements IIngredientRenderer<IETemplateMultiblock>
{
    @Override
    public List<Component> getTooltip(IETemplateMultiblock ingredient, TooltipFlag tooltipFlag)
    {
        return Collections.singletonList(new TranslatableComponent(ingredient.getBlock().getDescriptionId()));
    }
    
    @Override
    public void render(PoseStack poseStack, IETemplateMultiblock ingredient)
    {
        // lifted from JEI's ItemStackRenderer, since this should just render like normal items
        if (ingredient != null)
        {
            PoseStack modelViewStack = RenderSystem.getModelViewStack();
            modelViewStack.pushPose();
            {
                modelViewStack.mulPoseMatrix(poseStack.last().pose());
                
                RenderSystem.enableDepthTest();
                
                Minecraft minecraft = Minecraft.getInstance();
                Font font = getFontRenderer(minecraft, ingredient);
                ItemRenderer itemRenderer = minecraft.getItemRenderer();
                ItemStack stack = new ItemStack(ingredient.getBlock());
                itemRenderer.renderAndDecorateFakeItem(stack, 0, 0);
                itemRenderer.renderGuiItemDecorations(font, stack, 0, 0);
                RenderSystem.disableBlend();
            }
            modelViewStack.popPose();
            // Restore model-view matrix now that the item has been rendered
            RenderSystem.applyModelViewMatrix();
        }
    }
}
