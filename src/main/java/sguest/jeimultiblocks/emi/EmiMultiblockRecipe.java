package sguest.jeimultiblocks.emi;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.immersiveengineering.api.crafting.MultiblockRecipe;
import blusunrize.immersiveengineering.api.multiblocks.ClientMultiblocks;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.SpecialManualElement;
import blusunrize.lib.manual.gui.ManualScreen;
import com.google.gson.JsonObject;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.DrawableWidget;
import dev.emi.emi.api.widget.WidgetHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import sguest.jeimultiblocks.ContentHelper;

import java.util.List;
import java.util.stream.Collectors;

import static sguest.jeimultiblocks.JeiMultiblocks.MODID;

public class EmiMultiblockRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final List<EmiIngredient> inputs;
    private final List<EmiStack> output;
    private final MultiblockHandler.IMultiblock multiblock;

    public EmiMultiblockRecipe(MultiblockHandler.IMultiblock multiblock) {
        this.id = new ResourceLocation(MODID,"/"+multiblock.getUniqueName().getPath());
        ClientMultiblocks.MultiblockManualData manualData = ClientMultiblocks.get(multiblock);
        this.inputs = manualData.getTotalMaterials().stream().map(ingredient -> EmiIngredient.of(Ingredient.of(ingredient))).collect(Collectors.toList());;
        this.output = List.of(EmiStack.of(multiblock.getBlock()));
        this.multiblock = multiblock;
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EmiModPlugin.MULTIBLOCK_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return inputs;
    }

    @Override
    public List<EmiIngredient> getCatalysts() {
        return List.of(EmiIngredient.of(Ingredient.of(ContentHelper.getHammer())));
    }

    @Override
    public List<EmiStack> getOutputs() {
        return output;
    }

    @Override
    public int getDisplayWidth() {
        return 176;
    }

    @Override
    public int getDisplayHeight() {
        return 108;
    }

    @Override
    public void addWidgets(WidgetHolder widgetHolder) {
        widgetHolder.addSlot(getOutputs().get(0),2, 2).drawBack(false).recipeContext(this);

        int y = 2;
        int x = getDisplayWidth() - 22;

        widgetHolder.add(new MultiblockWidget(multiblock, 30, 20));

        for(EmiIngredient input : getInputs())
        {
            widgetHolder.addSlot(input, x, y).drawBack(false);
            y += 20;
            if(y > getDisplayHeight() - 8) {
                y = 2;
                x -= 20;
            }
        }
    }

    @Override
    public boolean supportsRecipeTree() {
        return false;
    }
}