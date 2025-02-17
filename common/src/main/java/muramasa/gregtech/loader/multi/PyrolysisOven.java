package muramasa.gregtech.loader.multi;

import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.AntimatterMaterials;
import muramasa.antimatter.recipe.ingredient.RecipeIngredient;
import muramasa.gregtech.data.GregTechData;
import muramasa.gregtech.data.Materials;
import muramasa.gregtech.data.RecipeMaps;
import muramasa.gregtech.data.TierMaps;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterials.*;
import static muramasa.gregtech.data.Materials.*;
import static muramasa.gregtech.data.RecipeMaps.PYROLYSISING;

public class PyrolysisOven {
    public static void init(){
        PYROLYSISING.RB().ii(DUST.getMaterialIngredient(Materials.OilShale, 1)).fo(Materials.Oil.getLiquid(25)).add("oil", 200, 120);
        PYROLYSISING.RB().ii(RecipeIngredient.of(GregTechData.Biochaff, 1), TierMaps.INT_CIRCUITS.get(1)).fi(AntimatterMaterials.Water.getLiquid(1000)).fo(Materials.Biomass.getLiquid(1500)).add("biomass", 100, 10);
        PYROLYSISING.RB().ii(RecipeIngredient.of(GregTechData.Biochaff, 1), TierMaps.INT_CIRCUITS.get(2)).fi(AntimatterMaterials.Water.getLiquid(1500)).fo(Materials.Biomass.getLiquid(1500)).add("fermented_biomass", 200, 10);
        PYROLYSISING.RB().ii(DUST.getMaterialIngredient(Sugar, 23), TierMaps.INT_CIRCUITS.get(1)).io(DUST.get(Charcoal, 12)).fo(Water.getLiquid(11000)).add("sugar_to_charcoal", 320, 64);
        PYROLYSISING.RB().ii(DUST.getMaterialIngredient(Sugar, 23), TierMaps.INT_CIRCUITS.get(2)).fi(Nitrogen.getGas(500)).io(DUST.get(Charcoal, 12)).fo(Water.getLiquid(11000)).add("sugar_to_charcoal_2", 160, 96);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(5)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodGas.getGas(1500)).add("wood_gas", 640, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(7)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodVinegar.getLiquid(3000)).add("wood_vinegar", 640, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(9)).io(new ItemStack(Items.CHARCOAL, 20)).fo(WoodTar.getLiquid(1500)).add("wood_tar", 640, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(6)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodGas.getGas(1500)).add("wood_gas_2", 320, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(8)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodVinegar.getLiquid(3000)).add("wood_vinegar_2", 320, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(10)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(WoodTar.getLiquid(1500)).add("wood_tar_2", 320, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(2)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(Creosote.getLiquid(4000)).add("creosote_oil", 320, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(4)).io(new ItemStack(Items.CHARCOAL, 20)).fi(Nitrogen.getGas(1000)).fo(CharcoalByproducts.getGas(4000)).add("charcoal_byproducts", 320, 64);
        PYROLYSISING.RB().ii(RecipeIngredient.of(ItemTags.LOGS, 16), TierMaps.INT_CIRCUITS.get(3)).io(DUST.get(Ash, 4)).fo(OilHeavy.getLiquid(4000)).add("oil_heavy", 320, 64);
    }
}
