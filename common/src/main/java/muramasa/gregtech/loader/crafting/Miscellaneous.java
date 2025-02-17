package muramasa.gregtech.loader.crafting;

import muramasa.antimatter.data.AntimatterDefaultTools;
import muramasa.antimatter.data.AntimatterMaterialTypes;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.util.TagUtils;
import muramasa.gregtech.GTIRef;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;

import static io.github.gregtechintergalactical.gtrubber.GTRubberData.StickyResin;
import static com.google.common.collect.ImmutableMap.of;
import static muramasa.gregtech.data.GregTechData.*;
import static muramasa.gregtech.data.Materials.*;

public class Miscellaneous {
    public static void loadRecipes(Consumer<FinishedRecipe> output, AntimatterRecipeProvider provider) {
        provider.addItemRecipe(output, GTIRef.ID, "plantball", "misc", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), PlantBall, of(
                'C', ForgeCTags.CROPS
        ), "CCC", "C C", "CCC");

        provider.addItemRecipe(output, GTIRef.ID, "plantball2", "misc", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), PlantBall, of(
                'S', ForgeCTags.SEEDS
        ), "SSS", "S S", "SSS");
        provider.addStackRecipe(output, GTIRef.ID, "plantball3", "misc", "has_wrench", provider.hasSafeItem(AntimatterDefaultTools.WRENCH.getTag()), new ItemStack(PlantBall, 2), of(
                'S', TagUtils.getItemTag(new ResourceLocation("saplings"))
        ), "SSS", "S S", "SSS");
    }
}