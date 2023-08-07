package com.robertx22.age_of_exile.loot.generators;

import com.robertx22.age_of_exile.config.forge.ServerContainer;
import com.robertx22.age_of_exile.loot.LootInfo;
import com.robertx22.age_of_exile.loot.blueprints.GearBlueprint;
import com.robertx22.age_of_exile.loot.blueprints.SkillGemBlueprint;
import com.robertx22.age_of_exile.uncommon.enumclasses.LootType;
import net.minecraft.world.item.ItemStack;

public class SkillGemLootGen extends BaseLootGen<GearBlueprint> {

    public SkillGemLootGen(LootInfo info) {
        super(info);

    }

    @Override
    public float baseDropChance() {

        if (true) {
            return 10000;
        }

        return (float) (ServerContainer.get().SKILL_GEM_DROPRATE.get()
                .floatValue());
    }

    @Override
    public LootType lootType() {
        return LootType.SkillGem;
    }

    @Override
    public boolean condition() {
        return true;
    }

    @Override
    public ItemStack generateOne() {
        SkillGemBlueprint blueprint = new SkillGemBlueprint(info);
        return blueprint.createStack();
    }

}