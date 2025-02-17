package muramasa.gregtech.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;
import muramasa.antimatter.gui.GuiInstance;
import muramasa.antimatter.gui.IGuiElement;
import muramasa.antimatter.gui.Widget;
import muramasa.antimatter.gui.container.ContainerMachine;
import muramasa.antimatter.gui.widget.WidgetSupplier;
import muramasa.antimatter.mixin.client.AbstractContainerScreenAccessor;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.blockentity.single.BlockEntityLavaBoiler;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import tesseract.TesseractGraphWrappers;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static muramasa.antimatter.gui.ICanSyncData.SyncDirection.SERVER_TO_CLIENT;

public class LavaBoilerWidget extends Widget {
    private int heat = 0, maxHeat = 0;
    private long water = 0, steam = 0, lava = 0;

    protected LavaBoilerWidget(@Nonnull GuiInstance gui, @Nullable IGuiElement parent) {
        super(gui, parent);
    }

    public static WidgetSupplier build() {
        return builder(LavaBoilerWidget::new);
    }

    @Override
    public void init() {
        super.init();
        gui.syncInt(() -> ((BlockEntityLavaBoiler)((ContainerMachine<?>)gui.container).getTile()).getHeat(), i -> heat = i, SERVER_TO_CLIENT);
        gui.syncInt(() -> ((BlockEntityLavaBoiler)((ContainerMachine<?>)gui.container).getTile()).getMaxHeat(), i -> maxHeat = i, SERVER_TO_CLIENT);
        gui.syncLong(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getInputs()[0].getFluidAmount()).orElse(0L), i -> water = i, SERVER_TO_CLIENT);
        gui.syncLong(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getInputs()[1].getFluidAmount()).orElse(0L), i -> lava = i, SERVER_TO_CLIENT);
        gui.syncLong(() -> ((ContainerMachine<?>)gui.container).getTile().fluidHandler.map(t -> t.getOutputs()[0].getFluidAmount()).orElse(0L), i -> steam = i, SERVER_TO_CLIENT);
    }

    @Override
    public void render(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= TesseractGraphWrappers.dropletMultiplier) {
            float per = (float) water / (TesseractGraphWrappers.dropletMultiplier * 16000);
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 13, y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 28, 54 - lvl, 10, lvl);

        }
        if (steam >= TesseractGraphWrappers.dropletMultiplier) {
            float per = (float) steam / (TesseractGraphWrappers.dropletMultiplier * 16000);
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX(), y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 18, 54 - lvl, 10, lvl);
        }
        if (lava >= TesseractGraphWrappers.dropletMultiplier) {
            float per = (float) lava / (TesseractGraphWrappers.dropletMultiplier * 16000);
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (realY() + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 52, y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 48, 54 - lvl, 10, lvl);
        }

        if (heat >= 1) {
            float per = (float) heat / maxHeat;
            if (per > 1.0F) {
                per = 1.0F;
            }
            int lvl = (int) (per * (float) 54);
            if (lvl < 0) {
                return;
            }
            int y = (((AbstractContainerScreenAccessor)gui.screen).getTopPos() + 25 + 54) - lvl;
            drawTexture(stack, gui.handler.getGuiTexture(), realX() + 26, y, ((AbstractContainerScreenAccessor)gui.screen).getImageWidth() + 38, 54 - lvl, 10, lvl);
        }
    }

    @Override
    public void mouseOver(PoseStack stack, double mouseX, double mouseY, float partialTicks) {
        if (water >= 1) {
            renderTooltip(stack,"Water: " + water + " MB", mouseX, mouseY, 14, 0, 10, 54);
        }
        if (steam >= 1) {
            renderTooltip(stack,"Steam: " + steam + " MB", mouseX, mouseY, 0, 0, 10, 54);
        }
        if (lava >= 1) {
            renderTooltip(stack,"Lava: " + lava + " MB", mouseX, mouseY, 52, 0, 10, 54);
        }
        renderTooltip(stack,"Heat: " + heat + "C° out of " + maxHeat, mouseX, mouseY, 26, 0, 10, 54);
    }

    @Environment(EnvType.CLIENT)
    protected void renderTooltip(PoseStack matrixStack, String text, double mouseX, double mouseY, int x, int y, int w, int h) {
        if (isInside(x, y, w, h, mouseX, mouseY)){
            renderTooltip(matrixStack, Utils.literal(text), mouseX, mouseY);
        }

    }

    public boolean isInside(int x, int y, int w, int h, double mouseX, double mouseY) {
        int realX = realX() + x;
        int realY = realY() + y;
        return ((mouseX >= realX && mouseX <= realX + w) && (mouseY >= realY && mouseY <= realY + h));
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }
}