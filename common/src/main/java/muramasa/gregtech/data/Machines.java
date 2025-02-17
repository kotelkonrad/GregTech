package muramasa.gregtech.data;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtutility.GTUtilityData;
import io.github.gregtechintergalactical.gtutility.machine.DrumMachine;
import muramasa.antimatter.blockentity.single.BlockEntityBatteryBuffer;
import muramasa.antimatter.blockentity.single.BlockEntityDigitalTransformer;
import muramasa.antimatter.blockentity.single.BlockEntityTransformer;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.machine.types.*;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.Utils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.blockentity.multi.*;
import muramasa.gregtech.blockentity.single.*;
import muramasa.gregtech.machine.MultiblockTankMachine;
import muramasa.gregtech.machine.SteamMachine;
import muramasa.gregtech.nuclear.BlockEntityNuclearReactor;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;

import static muramasa.antimatter.Data.*;
import static muramasa.antimatter.data.AntimatterMaterials.Netherite;
import static muramasa.antimatter.data.AntimatterMaterials.Wood;
import static muramasa.antimatter.machine.MachineFlag.*;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechData.COVER_STEAM_VENT;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.*;

public class Machines {
    /**
     ** Steam Singleblock Machines
     **/
    public static SteamMachine COAL_BOILER = new SteamMachine(GTIRef.ID, "coal_boiler").setTiers(BRONZE, STEEL).setMap(COAL_BOILERS).addFlags(GUI, STEAM, ITEM, FLUID, CELL).baseTexture(Textures.BOILER_HANDLER).setTile(BlockEntityCoalBoiler::new).noCovers();
    public static SteamMachine LAVA_BOILER = new SteamMachine(GTIRef.ID, "lava_boiler").setTiers(STEEL).addFlags(GUI, STEAM, ITEM, FLUID).setTile(BlockEntityLavaBoiler::new).noCovers();
    public static SteamMachine SOLAR_BOILER = new SteamMachine(GTIRef.ID, "solar_boiler").setTiers(BRONZE).addFlags(GUI, STEAM, ITEM, FLUID).setTile(BlockEntitySolarBoiler::new).allowFrontIO().noCovers();
    public static SteamMachine STEAM_ALLOY_SMELTER = new SteamMachine(GTIRef.ID, "steam_alloy_smelter").setTiers(BRONZE, STEEL).setMap(STEAM_ALLOY_SMELTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_COMPRESSOR = new SteamMachine(GTIRef.ID, "steam_compressor").setTiers(BRONZE, STEEL).setMap(STEAM_COMPRESSING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_EXTRACTOR = new SteamMachine(GTIRef.ID, "steam_extractor").setTiers(BRONZE, STEEL).setMap(STEAM_EXTRACTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_FORGE_HAMMER = new SteamMachine(GTIRef.ID, "steam_forge_hammer").setTiers(BRONZE, STEEL).setMap(STEAM_HAMMERING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT).setSound(SoundEvents.ANVIL_PLACE, 0.6f);
    public static SteamMachine STEAM_FURNACE = new SteamMachine(GTIRef.ID, "steam_furnace").setTiers(BRONZE, STEEL).setMap(STEAM_SMELTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).covers(COVER_STEAM_VENT);
    public static SteamMachine STEAM_MACERATOR = new SteamMachine(GTIRef.ID, "steam_macerator").setTiers(BRONZE, STEEL).setMap(STEAM_MACERATING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT).setSound(GregTechSounds.MACERATOR,  0.6f);
    public static SteamMachine STEAM_SIFTER = new SteamMachine(GTIRef.ID, "steam_sifter").setTiers(BRONZE, STEEL).setMap(STEAM_SIFTING).addFlags(GUI, ITEM, FLUID).covers(COVER_STEAM_VENT);
    /**
     ** Hatchless Multiblock Machines (Steam Age)
     **/
    public static BasicMachine CHARCOAL_PIT = new BasicMachine(GTIRef.ID, "charcoal_pit").setTiers(NONE).baseTexture(new Texture(GTIRef.ID, "block/machine/base/charcoal_pit")).setTile(BlockEntityCharcoalPit::new).noCovers().setAmbientTicking();
    public static BasicMultiMachine<?> COKE_OVEN = new BasicMultiMachine<>(GTIRef.ID, "coke_oven").setTiers(NONE).setMap(COKING).addFlags(GUI, ITEM, FLUID).setTile(BlockEntityCokeOven::new);
    public static BasicMultiMachine<?> PRIMITIVE_BLAST_FURNACE = new BasicMultiMachine<>(GTIRef.ID, "primitive_blast_furnace").setTiers(NONE).setMap(BASIC_BLASTING).addFlags(GUI, ITEM).setTile(BlockEntityPrimitiveBlastFurnace::new);
    /**
     ** Electric Singleblock Machines
     **/
    /**
     * Processors
     **/
    public static BasicMachine ALLOY_SMELTER = new BasicMachine(GTIRef.ID, "alloy_smelter").setMap(ALLOY_SMELTING).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE,  0.6f);
    public static BasicMachine AMP_FABRICATOR = new BasicMachine(GTIRef.ID, "amp_fabricator").setMap(AMP_FABRICATING).addFlags(GUI, ITEM);
    public static BasicMachine ARC_FURNACE = new BasicMachine(GTIRef.ID, "arc_furnace").setMap(ARC_SMELTING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.FURNACE,  0.6f).amps(3);
    public static BasicMachine ASSEMBLER = new BasicMachine(GTIRef.ID, "assembler").setMap(ASSEMBLING).addFlags(GUI, ITEM, FLUID).setAllowVerticalFacing(true).custom();
    public static BasicMachine AUTOCLAVE = new BasicMachine(GTIRef.ID, "autoclave").setMap(AUTOCLAVING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine BENDER = new BasicMachine(GTIRef.ID, "bender").setMap(BENDING).addFlags(GUI, ITEM);
    public static BasicMachine CANNER = new BasicMachine(GTIRef.ID, "canner").setMap(CANNING).addFlags(GUI, ITEM);
    public static BasicMachine CENTRIFUGE = new BasicMachine(GTIRef.ID, "centrifuge").setMap(CENTRIFUGING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine BATH = new BasicMachine(GTIRef.ID, "bath").setMap(BATHING).addFlags(GUI, ITEM, FLUID).setTiers(HV);
    public static BasicMachine DEHYDRATOR = new BasicMachine(GTIRef.ID, "dehydrator").setMap(DEHYDRATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine CHEMICAL_REACTOR = new BasicMachine(GTIRef.ID, "chemical_reactor").setMap(CHEMICAL_REACTING).addFlags(GUI, ITEM, FLUID).renderContainedLiquids().custom();
    public static BasicMachine CIRCUIT_ASSEMBLER = new BasicMachine(GTIRef.ID, "circuit_assembler").setMap(CIRCUIT_ASSEMBLING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine COMPRESSOR = new BasicMachine(GTIRef.ID, "compressor").setMap(COMPRESSING).addFlags(GUI, ITEM);
    public static BasicMachine CUTTER = new BasicMachine(GTIRef.ID, "cutter").setMap(CUTTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DECAY_CHAMBER = new BasicMachine(GTIRef.ID, "decay_chamber").setMap(DECAYING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine DISASSEMBLER = new BasicMachine(GTIRef.ID, "disassembler").setMap(DISASSEMBLING).addFlags(GUI, ITEM).custom();
    public static BasicMachine DISTILLERY = new BasicMachine(GTIRef.ID, "distillery").setMap(DISTILLING).addFlags(GUI, ITEM, FLUID).custom().renderContainedLiquids().setAllowVerticalFacing(true).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine ELECTROLYZER = new BasicMachine(GTIRef.ID, "electrolyzer").setMap(ELECTROLYZING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.MAGNETIZER, 0.6f);
    public static BasicMachine ELECTROMAGNETIC_SEPARATOR = new BasicMachine(GTIRef.ID, "electromagnetic_separator").setMap(ELECTROMAGNETIC_SEPARATING).addFlags(GUI, ITEM);
    public static BasicMachine EXTRACTOR = new BasicMachine(GTIRef.ID, "extractor").setMap(EXTRACTING).addFlags(GUI, ITEM).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine EXTRUDER = new BasicMachine(GTIRef.ID, "extruder").setMap(EXTRUDING).addFlags(GUI, ITEM).custom();
    public static BasicMachine FERMENTER = new BasicMachine(GTIRef.ID, "fermenter").setMap(FERMENTING).addFlags(GUI, ITEM, FLUID).custom().renderContainedLiquids();
    public static BasicMachine FLUID_CANNER = new BasicMachine(GTIRef.ID, "fluid_canner").setMap(FLUID_CANNING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine FLUID_EXTRACTOR = new BasicMachine(GTIRef.ID, "fluid_extractor").setMap(FLUID_EXTRACTING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_HEATER = new BasicMachine(GTIRef.ID, "fluid_heater").setMap(FLUID_HEATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine FLUID_SOLIDIFIER = new BasicMachine(GTIRef.ID, "fluid_solidifier").setMap(FLUID_SOLIDIFYING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.EXTRACTOR,  0.6f);
    public static BasicMachine FORGE_HAMMER = new BasicMachine(GTIRef.ID, "forge_hammer").setMap(HAMMERING).addFlags(GUI, ITEM).setSound(SoundEvents.ANVIL_PLACE, 0.6f);
    public static BasicMachine FORMING_PRESS = new BasicMachine(GTIRef.ID, "forming_press").setMap(PRESSING).addFlags(GUI, ITEM);
    public static BasicMachine FURNACE = new BasicMachine(GTIRef.ID, "furnace").setMap(SMELTING).addFlags(GUI, ITEM).setSound(GregTechSounds.FURNACE,  0.6f);
    public static BasicMachine LASER_ENGRAVER = new BasicMachine(GTIRef.ID, "laser_engraver").setMap(LASER_ENGRAVING).addFlags(GUI, ITEM).setSound(GregTechSounds.MAGNETIZER,  0.6f);
    public static BasicMachine LATHE = new BasicMachine(GTIRef.ID, "lathe").setMap(LATHING).addFlags(GUI, ITEM);
    public static BasicMachine MACERATOR = new BasicMachine(GTIRef.ID, "macerator").setMap(MACERATING).custom().addFlags(GUI, ITEM).setGuiTiers(ImmutableMap.<Tier, Tier>builder().put(HV, HV).put(EV, EV).put(IV, IV)).setSound(GregTechSounds.MACERATOR,  0.6f);
    public static BasicMachine MASS_FABRICATOR = new BasicMachine(GTIRef.ID, "mass_fabricator").setMap(MASS_FABRICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine MIXER = new BasicMachine(GTIRef.ID, "mixer").setMap(MIXING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine ORE_WASHER = new BasicMachine(GTIRef.ID, "ore_washer").setMap(ORE_WASHING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine PACKAGER = new BasicMachine(GTIRef.ID, "packager").setMap(PACKAGING).addFlags(GUI, ITEM);
    public static BasicMachine POLARIZER = new BasicMachine(GTIRef.ID, "polarizer").setMap(POLARIZING).addFlags(GUI, ITEM);
    public static BasicMachine PLASMA_ARC_FURNACE = new BasicMachine(GTIRef.ID, "plasma_arc_furnace").setMap(PLASMA_ARC_SMELTING).addFlags(GUI, ITEM, FLUID).amps(3);
    public static BasicMachine ROASTER = new BasicMachine(GTIRef.ID, "roaster").setMap(ROASTING).addFlags(GUI, ITEM, FLUID).amps(3);
    public static BasicMachine RECYCLER = new BasicMachine(GTIRef.ID, "recycler").setMap(RECYCLING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine REPLICATOR = new BasicMachine(GTIRef.ID, "replicator").setMap(REPLICATING).addFlags(GUI, ITEM, FLUID);
    public static BasicMachine SCANNER = new BasicMachine(GTIRef.ID, "scanner").setMap(SCANNING).addFlags(GUI, ITEM, FLUID).setSound(GregTechSounds.MAGNETIZER,  0.6f);
    public static BasicMachine SIFTER = new BasicMachine(GTIRef.ID, "sifter").setMap(SIFTING).addFlags(GUI, ITEM);
    public static BasicMachine THERMAL_CENTRIFUGE = new BasicMachine(GTIRef.ID, "thermal_centrifuge").setMap(THERMAL_CENTRIFUGING).addFlags(GUI,ITEM).amps(2);
    public static BasicMachine WIRE_MILL = new BasicMachine(GTIRef.ID, "wire_mill").setMap(WIRE_MILLING).addFlags(GUI, ITEM).custom();
    /**
     * Battery Buffers
     **/
    public static BasicMachine BATTERY_BUFFER_SIXTEEN = new BasicMachine(GTIRef.ID, "16x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_EIGHT = new BasicMachine(GTIRef.ID, "8x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_FOUR = new BasicMachine(GTIRef.ID, "4x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    public static BasicMachine BATTERY_BUFFER_ONE = new BasicMachine(GTIRef.ID, "1x_battery_buffer").addFlags(GUI, ENERGY, ITEM).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).noCovers().setTile(BlockEntityBatteryBuffer::new).setAllowVerticalFacing(true).allowFrontIO();
    /**
     * Filters
     **/
    public static BasicMachine ELECTRIC_ITEM_FILTER = new BasicMachine(GTIRef.ID, "electric_item_filter").addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityItemFilter::new).noCovers().frontCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine ELECTRIC_TYPE_FILTER = new BasicMachine(GTIRef.ID, "electric_type_filter").addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityTypeFilter::new).noCovers().frontCovers().allowFrontIO().setAllowVerticalFacing(true).overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine SUPER_BUFFER =new BasicMachine(GTIRef.ID, "super_buffer").addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityBuffer::new).setAllowVerticalFacing(true).allowFrontIO().noCovers().frontCovers().overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    public static BasicMachine CHEST_BUFFER =new BasicMachine(GTIRef.ID, "chest_buffer").addFlags(GUI, ENERGY, ITEM).setTile(BlockEntityBuffer::new).setAllowVerticalFacing(true).allowFrontIO().noCovers().frontCovers().overlayTexture(Textures.LEFT_RIGHT_HANDLER);
    /**
     * Drums
     */
    public static DrumMachine BRONZE_DRUM = GTUtilityData.createDrum(Materials.Bronze, 16000);
    public static DrumMachine STEEL_DRUM = GTUtilityData.createDrum(Steel, 48000);
    public static DrumMachine INVAR_DRUM = GTUtilityData.createDrum(Materials.Invar, 32000);
    public static DrumMachine STAINLESS_DRUM = GTUtilityData.createDrum(Materials.StainlessSteel, 64000).acidProof();
    public static DrumMachine TITANIUM_DRUM = GTUtilityData.createDrum(Materials.Titanium, 128000);
    public static DrumMachine NETHERRITE_DRUM = GTUtilityData.createDrum(AntimatterMaterials.Netherite, 128000).acidProof();
    public static DrumMachine TUNGSTENSTEEL_DRUM = GTUtilityData.createDrum(Materials.TungstenSteel, 256000);
    public static DrumMachine TUNGSTEN_DRUM = GTUtilityData.createDrum(Materials.Tungsten, 256000);

    public static MultiblockTankMachine WOOD_TANK = new MultiblockTankMachine(GTIRef.ID, Wood, true, 432000);
    public static MultiblockTankMachine[] STEEL_TANKS = createTankMachine(Steel, 3);
    public static MultiblockTankMachine[] INVAR_TANKS = createTankMachine(Invar, 2);
    public static MultiblockTankMachine[] STAINLESS_STEEL_TANKS = createTankMachine(StainlessSteel, 4);
    public static MultiblockTankMachine[] TITANIUM_TANKS = createTankMachine(Titanium, 8);
    public static MultiblockTankMachine[] NETHERITE_TANKS = createTankMachine(Netherite, 8);
    public static MultiblockTankMachine[] TUNGSTENSTEEL_TANKS = createTankMachine(TungstenSteel, 16);
    public static MultiblockTankMachine[] TUNGSTEN_TANKS = createTankMachine(Tungsten, 16);

    /**
     * Transformers
     **/
    public static BasicMachine TRANSFORMER = new BasicMachine(GTIRef.ID, "transformer").addFlags(ENERGY).setTiers(ULV, LV, MV, HV, EV, IV, LUV, ZPM, UV).overlayTexture(Textures.TIER_SPECIFIC_OVERLAY_HANDLER).setTile((v, pos, state) -> new BlockEntityTransformer<>(v, pos, state, 1)).noCovers().allowFrontIO().setAllowVerticalFacing(true).setTooltipInfo((machine, stack, world, tooltip, flag) -> {
        tooltip.remove(tooltip.size() - 1);
        tooltip.remove(tooltip.size() - 1);
        Tier upper = Tier.getTier(machine.getTier().getVoltage() * 4);
        tooltip.add(Utils.translatable("machine.transformer.voltage_info", Utils.literal(upper.getId().toUpperCase()), Utils.literal(machine.getTier().getId().toUpperCase())));
        tooltip.add(Utils.translatable("machine.voltage.in").append(": ").append(Utils.literal(upper.getVoltage() + " (" + upper.getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(Utils.translatable("machine.voltage.out").append(": ").append(Utils.literal(machine.getTier().getVoltage() + " (" + machine.getTier().getId().toUpperCase() + ")")).withStyle(ChatFormatting.GREEN));
        tooltip.add(Utils.translatable("generic.amp").append(": ").append(Utils.literal(String.valueOf(4)).withStyle(ChatFormatting.YELLOW)));
        tooltip.add(Utils.translatable("machine.power.capacity").append(": ").append(Utils.literal(String.valueOf(512L + machine.getTier().getVoltage() * 8L))).withStyle(ChatFormatting.BLUE));
    });
    public static BasicMachine ADJUSTABLE_TRANSFORMER = new BasicMachine(GTIRef.ID, "adjustable_transformer").setTiers(EV, IV).addFlags(GUI, ENERGY).setTile(BlockEntityDigitalTransformer::new).noCovers().allowFrontIO();
    /**
     ** Generators
     **/
    public static GeneratorMachine COMBUSTION_GENERATOR = new GeneratorMachine(GTIRef.ID, "combustion_generator").setTiers(LV, MV, HV).setMap(COMBUSTION_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine SEMIFLUID_GENERATOR = new GeneratorMachine(GTIRef.ID, "semifluid_generator").setTiers(LV, MV, HV).setMap(SEMI_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine GAS_GENERATOR = new GeneratorMachine(GTIRef.ID, "gas_turbine").setTiers(LV, MV, HV).setMap(GAS_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO().custom();
    public static GeneratorMachine NAQUADAH_GENERATOR = new GeneratorMachine(GTIRef.ID, "naquadah_reactor").setTiers(EV, IV, LUV).setMap(NAQUADAH_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine PLASMA_GENERATOR = new GeneratorMachine(GTIRef.ID, "plasma_generator").setTiers(IV, LUV, ZPM).setMap(PLASMA_FUELS).addFlags(GUI, ITEM, FLUID, CELL).allowFrontIO();
    public static GeneratorMachine STEAM_GENERATOR = new GeneratorMachine(GTIRef.ID, "steam_turbine").setTiers(LV, MV, HV).setMap(STEAM_FUELS).addFlags(GUI, ITEM, FLUID, CELL).setTile(BlockEntitySteamTurbine::new).efficiency(t -> {
        return t.getIntegerId() + 6;
    }).allowFrontIO().custom();
    /**
     ** Multiblock Hatch Machines (Electrical Age)
     **/
    public static MultiMachine ADVANCED_MINER = new MultiMachine(GTIRef.ID, "advanced_miner").setTiers(LV).addFlags(GUI, ITEM, ENERGY).setTile(BlockEntityAdvancedMiner::new);
    public static MultiMachine BLAST_FURNACE = new MultiMachine(GTIRef.ID, "electric_blast_furnace").setTiers(LV).setMap(BLASTING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityElectricBlastFurnace::new).custom();
    public static MultiMachine COMBUSTION_ENGINE = new MultiMachine(GTIRef.ID, "combustion_engine").setTiers(EV).setMap(COMBUSTION_FUELS).addFlags(GUI, FLUID, ENERGY).setTile(BlockEntityCombustionEngine::new).custom();
    public static MultiMachine CRACKING_UNIT = new MultiMachine(GTIRef.ID, "cracking_unit").setTiers(HV).setMap(CRACKING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityOilCrackingUnit::new).custom();
    public static MultiMachine DISTLLATION_TOWER = new MultiMachine(GTIRef.ID, "distillation_tower").setTiers(HV).setMap(DISTILLATION).addFlags(GUI, ITEM, FLUID,ENERGY).setTile(BlockEntityDistillationTower::new).custom();
    public static MultiMachine CRYO_DISTLLATION_TOWER = new MultiMachine(GTIRef.ID, "cryo_distillation_tower").setTiers(HV).setMap(CRYO_DISTILLATION).addFlags(GUI, ITEM, FLUID,ENERGY).setTile(BlockEntityDistillationTower::new).custom();
    public static MultiMachine FUSION_REACTOR = new MultiMachine(GTIRef.ID, "fusion_control_computer").setTiers(LUV, ZPM, UV).setMap(FUSION).addFlags(GUI, FLUID,ENERGY).setTile(BlockEntityFusionReactor::new);
    public static MultiMachine HEAT_EXCHANGER = new MultiMachine(GTIRef.ID, "heat_exchanger").setTiers(EV).setMap(HEAT_EXCHANGING).addFlags(GUI, FLUID, ENERGY).setTile(BlockEntityHeatExchanger::new).custom();
    public static MultiMachine IMPLOSION_COMPRESSOR = new MultiMachine(GTIRef.ID, "implosion_compressor").setTiers(HV).setMap(IMPLOSION_COMPRESSING).addFlags(GUI, ITEM, ENERGY).setTile(BlockEntityImplosionCompressor::new);
    public static MultiMachine LARGE_BOILER = new MultiMachine(GTIRef.ID, "large_boiler").setTiers(LV, MV, HV, EV).addFlags(GUI, ITEM, FLUID).setMap(LARGE_BOILERS).setTile(BlockEntityLargeBoiler::new).custom();
    public static MultiMachine LARGE_TURBINE = new MultiMachine(GTIRef.ID, "large_turbine").setTiers(HV, EV, IV).setMap(STEAM_FUELS, HV).setMap(HP_STEAM_FUELS, IV).setMap(GAS_FUELS, EV).addFlags(GUI, ITEM, FLUID, ENERGY, GENERATOR).setTile(BlockEntityLargeTurbine::new).custom(Textures.TURBINE).setTierSpecificLang();
    public static MultiMachine MULTI_SMELTER = new MultiMachine(GTIRef.ID, "multi_smelter").setTiers(HV).setMap(SMELTING).addFlags(GUI, ITEM, ENERGY).setTile(BlockEntityMultiSmelter::new).custom();
    public static MultiMachine NUCLEAR_REACTOR = new MultiMachine(GTIRef.ID, "nuclear_reactor").setTiers(EV).setMap(NUCLEAR).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityNuclearReactor::new).custom();
    public static MultiMachine OIL_DRILLING_RIG = new MultiMachine(GTIRef.ID, "oil_drilling_rig").setTiers(MV).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityOilDrillingRig::new).custom();
    public static MultiMachine PYROLYSIS_OVEN = new MultiMachine(GTIRef.ID, "pyrolysis_oven").setTiers(MV).setMap(PYROLYSISING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityPyrolysisOven::new).custom();
    public static MultiMachine VACUUM_FREEZER = new MultiMachine(GTIRef.ID, "vacuum_freezer").setTiers(HV).setMap(VACUUM_FREEZING).addFlags(GUI, ITEM, FLUID, ENERGY).setTile(BlockEntityVacuumFreezer::new);
    /**
     * Long distance pipelines
     */
    public static BasicMultiMachine<?> LONG_DISTANCE_FLUID_ENDPOINT = new BasicMultiMachine<>(GTIRef.ID,"long_distance_fluid_endpoint").allowFrontIO().setTiers(NONE).addFlags(FLUID).setTile(BlockEntityLongDistancePipeEndpoint::new);
    public static BasicMultiMachine<?> LONG_DISTANCE_ITEM_ENDPOINT = new BasicMultiMachine<>(GTIRef.ID,"long_distance_item_endpoint").allowFrontIO().setTiers(NONE).addFlags(ITEM).setTile(BlockEntityLongDistancePipeEndpoint::new);
    public static BasicMultiMachine<?> LONG_DISTANCE_TRANSFORMER_ENDPOINT = new BasicMultiMachine<>(GTIRef.ID,"long_distance_transformer_endpoint").allowFrontIO().setTiers(EV, IV, LUV, ZPM, UV).addFlags(ENERGY).setTile(BlockEntityLongDistancePipeEndpoint::new).overlayTexture(Textures.STATE_IGNORANT_TIER_SPECIFIC_OVERLAY_HANDLER).baseTexture((m, tier) -> new Texture[]{tier.getBaseTexture(m.getDomain())});

    /**
     ** Hatches
     **/
    public static HatchMachine HATCH_DYNAMO = new HatchMachine(GTIRef.ID, "hatch_dynamo", COVERDYNAMO).addFlags(ENERGY);
    public static HatchMachine HATCH_ENERGY = new HatchMachine(GTIRef.ID, "hatch_energy", COVERENERGY).addFlags(ENERGY);
    public static HatchMachine HATCH_FLUID_I = new HatchMachine(GTIRef.ID, "hatch_fluid_input", COVERINPUT).addFlags(GUI, FLUID, CELL);
    public static HatchMachine HATCH_FLUID_O = new HatchMachine(GTIRef.ID, "hatch_fluid_output", COVEROUTPUT).addFlags(GUI, FLUID, CELL);
    //public static final HeatHatch HATCH_HEAT_COPPER = new HeatHatch(GTIRef.ID, "copper_heat", Copper, 386);
    public static HatchMachine HATCH_ITEM_I = new HatchMachine(GTIRef.ID, "hatch_item_input", COVERINPUT).addFlags(GUI, ITEM);
    public static HatchMachine HATCH_MUFFLER = new HatchMachine(GTIRef.ID, "hatch_muffler", COVERMUFFLER).addFlags(GUI, ITEM).setClientTick();
    public static HatchMachine HATCH_ITEM_O = new HatchMachine(GTIRef.ID, "hatch_item_output", COVEROUTPUT).addFlags(GUI, ITEM);
    /**
     ** Tanks
     **/
    public static TankMachine QUANTUM_TANK = new TankMachine(GTIRef.ID, "quantum_tank", t -> (int) (1602000 * Math.pow(6,  (t.getIntegerId() - 1)))).addFlags(BASIC, GUI, CELL).frontCovers();

    public static BasicMachine PUMP = new BasicMachine(GTIRef.ID, "electric_pump").addFlags(FLUID).setAllowVerticalFacing(true).setTile(BlockEntityPump::new).noCovers();
    public static BasicMachine CROP_HARVESTER = new BasicMachine(GTIRef.ID, "crop_harvester").setTiers(LV).addFlags(GUI, ITEM).setTile(BlockEntityCropHarvester::new);
    //public static BasicMachine MINIATURE_NETHER_PORTAL = new BasicMachine(GTIRef.ID, "miniature_nether_portal").setTiers(NONE).noCovers().allowFrontIO().baseTexture(new Texture("block/obsidian")).overlayTexture(Textures.MINI_NETHER_PORTAL).setBlock((machine, tier) -> new BlockMachine(machine, tier, BlockBehaviour.Properties.of(WRENCH_MATERIAL).strength(1.0f, 10.0f).sound(SoundType.METAL).requiresCorrectToolForDrops().noOcclusion())).custom(Textures.MINI_PORTAL);

    /**
     ** Creative Machines
     **/
    public static TankMachine INFINITE_STEAM = new TankMachine(GTIRef.ID, "infinite_steam").addFlags(FLUID, CELL, GUI).setTile(BlockEntityInfiniteFluid::new).setTiers(LV);

    private static MultiblockTankMachine[] createTankMachine(Material material, int multiplier){
        MultiblockTankMachine[] multiblockTankMachines = {
                new MultiblockTankMachine(GTIRef.ID, material, true, 432 * multiplier * 1000),
                new MultiblockTankMachine(GTIRef.ID, material, false, 2000 * multiplier * 1000)
        };
        if (material == StainlessSteel || material == Netherite){
            multiblockTankMachines[0].acidProof();
            multiblockTankMachines[1].acidProof();
        }
        return multiblockTankMachines;
    }

    public static void init() {
        ENERGY.remove(BATH);
    }
}
