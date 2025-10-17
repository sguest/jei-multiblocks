package sguest.jeimultiblocks.emi;

import blusunrize.immersiveengineering.api.Lib;
import blusunrize.immersiveengineering.api.ManualHelper;
import blusunrize.immersiveengineering.api.multiblocks.MultiblockHandler;
import blusunrize.lib.manual.ManualInstance;
import blusunrize.lib.manual.SpecialManualElement;
import blusunrize.lib.manual.gui.ManualScreen;
import com.google.gson.JsonObject;
import dev.emi.emi.api.widget.Bounds;
import dev.emi.emi.api.widget.Widget;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;

public class MultiblockWidget extends Widget {

    public final MultiblockHandler.IMultiblock multiblock;
    public final int x;
    public final int y;

    public MultiblockWidget(MultiblockHandler.IMultiblock multiblock, int x, int y) {
        this.multiblock = multiblock;
        this.x = x;
        this.y = y;
    }

    @Override
    public Bounds getBounds() {
        return Bounds.EMPTY;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float delta) {
        ManualInstance manual = ManualHelper.getManual();
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("name", multiblock.getUniqueName().toString());
        SpecialManualElement manualElement = manual.getElementFactory(ResourceLocation.fromNamespaceAndPath(Lib.MODID, "multiblock")).apply(jsonObj);
        ManualScreen screen = ManualHelper.getManual().getGui();
        // Passing 0, 0 for mouse coords because we don't want to render the manual's ingredient list tooltip
        manualElement.render(guiGraphics, screen, x, y, 0, 0);
    }
}
