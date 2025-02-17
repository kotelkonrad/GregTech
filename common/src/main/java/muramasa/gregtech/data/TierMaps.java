package muramasa.gregtech.data;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.machine.Tier;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.SubTag;
import muramasa.antimatter.pipe.PipeItemBlock;
import muramasa.antimatter.pipe.PipeSize;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import muramasa.gregtech.items.ItemIntCircuit;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.resources.ResourceLocation;
import org.apache.commons.lang3.function.TriFunction;

import java.util.function.BiFunction;
import java.util.function.Function;

import static muramasa.antimatter.data.AntimatterMaterials.Copper;
import static muramasa.antimatter.data.AntimatterMaterialTypes.GEM;
import static muramasa.antimatter.data.AntimatterMaterialTypes.ROTOR;
import static muramasa.antimatter.machine.Tier.*;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;

public class TierMaps {

    public static final ImmutableMap<Integer, RecipeIngredient> INT_CIRCUITS;
    public static final ImmutableMap<Tier, Material> TIER_MATERIALS;
    public static final ImmutableMap<Tier, Material> TIER_PIPE_MATERIAL;
    public static ImmutableMap<Tier, PipeItemBlock> TIER_WIRES;
    //public static ImmutableMap<Tier, Item> TIER_CABLES;
    public static ImmutableMap<Tier, TagKey<Item>> TIER_CIRCUITS;
    public static ImmutableMap<Tier, ItemBasic<?>> TIER_BOARDS;

    public static ImmutableMap<Tier, Material> EMITTER_RODS;
    public static ImmutableMap<Tier, Object> EMITTER_GEMS;

    public static ImmutableMap<Tier, Item> TIER_ROTORS;
    public static ImmutableMap<Tier, Function<PipeSize, Item>> TIER_PIPES;

    public static final BiFunction<PipeSize, Tier, Object> WIRE_GETTER;

    public static final TriFunction<PipeSize, Tier, Boolean, Object> CABLE_GETTER;

    static {
        {
            ImmutableMap.Builder<Integer, RecipeIngredient> builder = ImmutableMap.builder();
            for (int i = 0; i <= 24; i++) {
                builder.put(i, RecipeIngredient.of(new ItemIntCircuit(GTIRef.ID, "int_circuit_"+i,i).tip("ID: " + i),1).setNoConsume());
            }
            INT_CIRCUITS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WroughtIron);
            builder.put(Tier.LV, Steel);
            builder.put(Tier.MV, Aluminium);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            builder.put(LUV, Chromium);
            builder.put(ZPM, Iridium);
            builder.put(UV, Osmium);
            builder.put(UHV, Neutronium);
            TIER_MATERIALS = builder.build();
        }

        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, Copper);
            builder.put(Tier.LV, Bronze);
            builder.put(Tier.MV, Steel);
            builder.put(Tier.HV, StainlessSteel);
            builder.put(Tier.EV, Titanium);
            builder.put(Tier.IV, TungstenSteel);
            TIER_PIPE_MATERIAL = builder.build();
        }

        WIRE_GETTER = (size, tier) -> {
            if (tier == LV) {
                return TagUtils.getItemTag(new ResourceLocation(GTIRef.ANTIMATTER, SubTag.COPPER_WIRE.getId()+"_"+ size.getId()));
            }
            if (tier == MV) {
                return WIRE_CUPRONICKEL.getBlockItem(size);
            }
            if (tier == HV) {
                return WIRE_KANTHAL.getBlockItem(size);
            }
            if (tier == EV) {
                return WIRE_NICHROME.getBlockItem(size);
            }
            if (tier == IV) {
                return WIRE_TUNGSTEN_STEEL.getBlockItem(size);
            }
            throw new IllegalArgumentException("Too high tier in WIRE_GETTER");
        };
        CABLE_GETTER = (size, tier, machine) -> {
            if (tier == ULV) return CABLE_RED_ALLOY.getBlockItem(size);
            if (tier == LV) return CABLE_TIN.getBlockItem(size);
            if (tier == MV){
                return TagUtils.getItemTag(new ResourceLocation(GTIRef.ANTIMATTER, SubTag.COPPER_CABLE.getId()+"_"+ size.getId()));
            }
            if (tier == HV) return CABLE_GOLD.getBlockItem(size);
            if (tier == EV) return CABLE_ALUMINIUM.getBlockItem(size);
            if (tier == IV) return machine ? CABLE_PLATINUM.getBlockItem(size) : CABLE_TUNGSTEN.getBlockItem(size);
            if(tier == LUV) return CABLE_VANADIUM_GALLIUM.getBlockItem(size);
            if(tier == ZPM) return CABLE_NAQUADAH.getBlockItem(size);
            if(tier == UV) return CABLE_NAQUADAH_ALLOY.getBlockItem(size);
            if(tier == UHV) return WIRE_SUPERCONDUCTOR.getBlockItem(size);
            throw new IllegalArgumentException("Invalid tier in CABLE_GETTER");
        };
    }
    //Called to init the INT CIRCUITS and tier materials early on.
    public static void init() {

    }
    //ProviderInit is called by the RecipeProvider during construction.
    private static boolean doneMaps = false;
    public static void providerInit() {
        if (doneMaps) return;
        doneMaps = true;
        {
            ImmutableMap.Builder<Tier, PipeItemBlock> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, WIRE_RED_ALLOY.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, WIRE_TIN.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, WIRE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, WIRE_GOLD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.EV, WIRE_ALUMINIUM.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.IV, WIRE_TUNGSTEN.getBlockItem(PipeSize.VTINY));
            builder.put(LUV, WIRE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY));
            builder.put(ZPM, WIRE_NAQUADAH.getBlockItem(PipeSize.VTINY));
            builder.put(UV, WIRE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL));
            TIER_WIRES = builder.build();
        }
        /*{
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, CABLE_RED_ALLOY.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.LV, CABLE_TIN.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.MV, CABLE_COPPER.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.HV, CABLE_GOLD.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.EV, CABLE_ALUMINIUM.getBlockItem(PipeSize.VTINY));
            builder.put(Tier.IV, CABLE_TUNGSTEN.getBlockItem(PipeSize.VTINY));
            builder.put(LUV, CABLE_VANADIUM_GALLIUM.getBlockItem(PipeSize.VTINY));
            builder.put(ZPM, CABLE_NAQUADAH.getBlockItem(PipeSize.VTINY));
            builder.put(UV, CABLE_NAQUADAH_ALLOY.getBlockItem(PipeSize.SMALL));
            builder.put(MAX, WIRE_SUPERCONDUCTOR.getBlockItem(PipeSize.VTINY));
            TIER_CABLES = builder.build();
        }*/
        {
            ImmutableMap.Builder<Tier, Item> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, ROTOR.get(Bronze));
            builder.put(Tier.LV, ROTOR.get(Tin));
            builder.put(Tier.MV, ROTOR.get(Bronze));
            builder.put(Tier.HV, ROTOR.get(Steel));
            builder.put(Tier.EV, ROTOR.get(StainlessSteel));
            builder.put(Tier.IV, ROTOR.get(TungstenSteel));
            TIER_ROTORS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Material> builder = ImmutableMap.builder();
            builder.put(Tier.LV, Brass);
            builder.put(Tier.MV, Electrum);
            builder.put(Tier.HV, Chromium);
            builder.put(Tier.EV, Platinum);
            builder.put(Tier.IV, Osmium);
            EMITTER_RODS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Object> builder = ImmutableMap.builder();
            builder.put(Tier.LV, GEM.getMaterialTag(MilkyQuartz));
            builder.put(Tier.MV, Items.QUARTZ);
            builder.put(Tier.HV, Items.EMERALD);
            builder.put(Tier.EV, Items.ENDER_PEARL);
            builder.put(Tier.IV, Items.ENDER_EYE);
            EMITTER_GEMS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, Function<PipeSize, Item>> builder = ImmutableMap.builder();
            builder.put(Tier.ULV, FLUID_PIPE_COPPER::getBlockItem);
            builder.put(Tier.LV, FLUID_PIPE_BRONZE::getBlockItem);
            builder.put(Tier.MV, FLUID_PIPE_STEEL::getBlockItem);
            builder.put(Tier.HV, FLUID_PIPE_STAINLESS_STEEL::getBlockItem);
            builder.put(Tier.EV, FLUID_PIPE_TITANIUM::getBlockItem);
            builder.put(Tier.IV, FLUID_PIPE_TUNGSTEN_STEEL::getBlockItem);
            TIER_PIPES = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, TagKey<Item>> builder = ImmutableMap.builder();
            builder.put(Tier.LV, GregTechTags.CIRCUITS_BASIC);
            builder.put(Tier.MV, GregTechTags.CIRCUITS_GOOD);
            builder.put(Tier.HV, GregTechTags.CIRCUITS_ADVANCED);
            builder.put(Tier.EV, GregTechTags.CIRCUITS_ELITE);
            builder.put(Tier.IV, GregTechTags.CIRCUITS_MASTER);
            builder.put(Tier.LUV, GregTechTags.CIRCUITS_DATA_ORB);
            builder.put(Tier.ZPM, GregTechTags.CIRCUITS_DATA_ORB);
            TIER_CIRCUITS = builder.build();
        }
        {
            ImmutableMap.Builder<Tier, ItemBasic<?>> builder = ImmutableMap.builder();
            builder.put(Tier.LV, CircuitBoardCoated);
            builder.put(Tier.MV, CircuitBoardPhenolic);
            builder.put(Tier.HV, CircuitBoardPlastic);
            builder.put(Tier.EV, CircuitBoardEpoxy);
            builder.put(Tier.IV, CircuitBoardFiber);
            builder.put(Tier.LUV, CircuitBoardMultiFiber);
            builder.put(Tier.ZPM, CircuitBoardWetware);
            TIER_BOARDS = builder.build();
        }
    }
}
