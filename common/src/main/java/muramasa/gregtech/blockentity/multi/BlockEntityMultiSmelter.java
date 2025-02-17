package muramasa.gregtech.blockentity.multi;

import muramasa.antimatter.machine.types.Machine;
import muramasa.antimatter.blockentity.multi.BlockEntityMultiMachine;
import muramasa.gregtech.block.BlockCoil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityMultiSmelter extends BlockEntityMultiMachine<BlockEntityMultiSmelter> {

    private int level = 1, discount = 1;
    private BlockCoil.CoilData coilData;

    public BlockEntityMultiSmelter(Machine<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

//    @Override
//    public void onRecipeFound() {
////        this.mEfficiency = (10000 - (getIdealStatus() - getRepairStatus()) * 1000);
////        this.mEfficiencyIncrease = 10000;
//
//        int tier = Utils.getVoltageTier(getMaxInputVoltage());
//        EUt = (-4 * (1 << tier - 1) * (1 << tier - 1) * level / discount);
//        maxProgress = Math.max(1, 512 / (1 << tier - 1));
//    }

    /*@Override
    public boolean onStructureFormed() {
        super.onStructureFormed();
        List<BlockState> coils = getStates("coil");
        BlockCoil firstType = ((BlockCoil) coils.get(0).getBlock());
        if (coils.stream().allMatch(s -> s.getBlock() == firstType)) {
            setCoilValues(firstType);
            return true;
        } else {
            this.result.withError("all coils do not match");
            return false;
        }
    }*/

    public void setCoilValues(BlockCoil coil) {
        switch (coil.getId()) {
            case "kanthal":
                level = 2;
                break;
            case "nichrome":
                level = 4;
                break;
            case "tungstensteel":
                level = 8;
                break;
            case "hssg":
                level = 16;
                discount = 2;
                break;
            case "naquadah":
                level = 16;
                discount = 4;
                break;
            case "naquadah_alloy":
                level = 16;
                discount = 8;
                break;
        }
    }

    public void setCoilData(BlockCoil.CoilData coilData) {
        this.coilData = coilData;
    }

    public BlockCoil.CoilData getCoilData() {
        return coilData;
    }
}
