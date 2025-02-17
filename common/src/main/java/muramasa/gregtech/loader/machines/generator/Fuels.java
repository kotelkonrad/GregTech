package muramasa.gregtech.loader.machines.generator;

import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.material.MaterialTags;
import muramasa.antimatter.recipe.ingredient.FluidIngredient;
import muramasa.antimatter.recipe.map.RecipeBuilder;
import muramasa.gregtech.data.Materials;

import static muramasa.antimatter.data.AntimatterMaterialTypes.GAS;
import static muramasa.antimatter.data.AntimatterMaterialTypes.LIQUID;
import static muramasa.gregtech.data.GregTechMaterialTags.SEMIFUELS;
import static muramasa.gregtech.data.Materials.Steam;
import static muramasa.gregtech.data.Materials.SuperheatedSteam;
import static muramasa.gregtech.data.RecipeMaps.*;

public class Fuels {
    public static void init() {
        AntimatterAPI.all(Material.class, mat -> {
            if (mat != Materials.Steam && mat != SuperheatedSteam && mat.has(MaterialTags.FUEL_POWER) && MaterialTags.FUEL_POWER.getInt(mat) > 0) {
                if (mat.has(LIQUID)) {
                    RecipeBuilder rb = mat.has(SEMIFUELS) ? SEMI_FUELS.RB() : COMBUSTION_FUELS.RB();
                    rb.fi(mat.getLiquid(1)).add(mat.getId(),1, MaterialTags.FUEL_POWER.getInt(mat));
                } else if (mat.has(GAS)) {
                    GAS_FUELS.RB().fi(mat.getGas(1)).add(mat.getId(),1, MaterialTags.FUEL_POWER.getInt(mat));
                }
            }
        });
        STEAM_FUELS.RB().fi(Steam.getFluidTag(2)).add("steam",1,1);
        HP_STEAM_FUELS.RB().fi(FluidIngredient.ofMB(SuperheatedSteam, 1)).add("superheated_steam", 1, 1);
    }
}
