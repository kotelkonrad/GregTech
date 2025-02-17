package muramasa.gregtech.cover;

import muramasa.antimatter.blockentity.BlockEntityMachine;
import muramasa.antimatter.blockentity.pipe.BlockEntityPipe;
import muramasa.antimatter.capability.ICoverHandler;
import muramasa.antimatter.cover.BaseCover;
import muramasa.antimatter.cover.CoverFactory;
import muramasa.antimatter.gui.ButtonOverlay;
import muramasa.antimatter.gui.event.GuiEvents;
import muramasa.antimatter.gui.event.IGuiEvent;
import muramasa.antimatter.machine.Tier;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import tesseract.api.gt.IEnergyHandler;

public class CoverEnergyDetector extends BaseCover {
    boolean inverted = false;
    int outputRedstone = 0;
    public CoverEnergyDetector(@NotNull ICoverHandler<?> source, @Nullable Tier tier, Direction side, CoverFactory factory) {
        super(source, tier, side, factory);
        addGuiCallback(t -> {
            t.addSwitchButton(79, 34, 16, 16, ButtonOverlay.TORCH_OFF, ButtonOverlay.TORCH_ON, h -> inverted, true, b -> "tooltip.gti.redstone_mode." + (b ? "inverted" : "normal"));
        });
    }

    @Override
    public boolean canPlace() {
        return handler.getTile() instanceof BlockEntityMachine<?> machine && machine.energyHandler.isPresent();
    }

    @Override
    public String getId() {
        return "energy_detector";
    }

    @Override
    public ResourceLocation getModel(String type, Direction dir) {
        if (type.equals("pipe")) return PIPE_COVER_MODEL;
        return getBasicModel();
    }

    @Override
    public boolean hasGui() {
        return true;
    }

    @Override
    public void onUpdate() {
        if (handler.getTile().getLevel() == null || handler.getTile().getLevel().isClientSide) return;
        if (handler.getTile() instanceof BlockEntityMachine<?> machine && machine.energyHandler.isPresent()){
            IEnergyHandler energyHandler = machine.energyHandler.get();
            int oldRedstone = outputRedstone;
            long scale = energyHandler.getCapacity() / 15L;
            if (scale > 0){
                outputRedstone = inverted ? (int) (15L - energyHandler.getEnergy() / scale) : (int) (energyHandler.getEnergy() / scale);
            } else {
                outputRedstone = inverted ? 15 : 0;
            }
            if (outputRedstone != oldRedstone){
                markAndNotifySource();
            }
        }
    }

    @Override
    public int getWeakPower() {
        return outputRedstone;
    }

    @Override
    public CompoundTag serialize() {
        CompoundTag nbt =  super.serialize();
        nbt.putBoolean("inverted", inverted);
        return nbt;
    }

    @Override
    public void onGuiEvent(IGuiEvent event, Player playerEntity) {
        if (event.getFactory() == GuiEvents.EXTRA_BUTTON){
            GuiEvents.GuiEvent ev = (GuiEvents.GuiEvent) event;
            if (ev.data[1] == 0){
                inverted = !inverted;
                if (handler.getTile() instanceof BlockEntityPipe<?> pipe) pipe.onBlockUpdate(pipe.getBlockPos());
                if (handler.getTile() instanceof BlockEntityMachine<?> machine) machine.onBlockUpdate(machine.getBlockPos());
            }
        }
    }

    @Override
    public void deserialize(CompoundTag nbt) {
        super.deserialize(nbt);
        inverted = nbt.getBoolean("inverted");
    }
}
