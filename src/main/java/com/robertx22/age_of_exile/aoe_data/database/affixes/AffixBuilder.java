package com.robertx22.age_of_exile.aoe_data.database.affixes;

import com.robertx22.age_of_exile.database.data.StatMod;
import com.robertx22.age_of_exile.database.data.affixes.Affix;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.database.data.requirements.Requirements;
import com.robertx22.age_of_exile.database.data.requirements.TagRequirement;
import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AffixBuilder {

    String guid;
    List<StatMod> stats = new ArrayList<>();
    String langName = "";
    boolean allowDupli = false;
    int weight = 1000;
    public List<String> tags = new ArrayList<>();
    public Affix.Type type;

    TagRequirement tagRequirement = new TagRequirement();

    private AffixBuilder(String id) {
        this.guid = id;
    }

    public static AffixBuilder Normal(String id) {
        return new AffixBuilder(id);
    }

    public AffixBuilder Named(String name) {
        langName = name;
        return this;
    }

    public AffixBuilder includesTags(BaseGearType.SlotTag... tags) {
        this.tagRequirement.included.addAll(Arrays.stream(tags)
                .map(x -> x.getTagId())
                .collect(Collectors.toList()));
        return this;
    }

    public AffixBuilder excludesTags(BaseGearType.SlotTag... tags) {
        this.tagRequirement.excluded.addAll(Arrays.stream(tags)
                .map(x -> x.getTagId())
                .collect(Collectors.toList()));
        return this;
    }

    public AffixBuilder Weight(int weight) {
        this.weight = weight;
        return this;
    }

 
    public AffixBuilder coreStat(Stat stat) {
        return this.stats(new StatMod(2, 15, stat, ModType.FLAT));
    }

    public AffixBuilder bigCoreStat(Stat stat) {
        return this.stats(new StatMod(3, 25, stat, ModType.FLAT));
    }

    public AffixBuilder stats(StatMod... stats) {
        this.stats.addAll(Arrays.asList(stats));
        return this;
    }

    public AffixBuilder stat(StatMod stat) {
        this.stats.add(stat);
        return this;
    }

    public AffixBuilder AllowDuplicatesOnSameItem() {
        allowDupli = true;
        return this;
    }

    public AffixBuilder Prefix() {
        type = Affix.Type.prefix;
        return this;
    }

    public AffixBuilder Tool() {
        type = Affix.Type.tool;
        return this;
    }

    public AffixBuilder Suffix() {
        type = Affix.Type.suffix;
        return this;
    }

    public AffixBuilder Enchant() {
        type = Affix.Type.enchant;
        return this;
    }

    public AffixBuilder Implicit() {
        type = Affix.Type.implicit;
        return this;
    }

    public void Build() {

        Affix affix = new Affix();
        affix.guid = guid;

        affix.requirements = new Requirements(this.tagRequirement);

        affix.stats = stats;

        affix.only_one_per_item = !allowDupli;
        affix.type = type;
        affix.weight = weight;
        affix.loc_name = langName;


        affix.addToSerializables();

    }

}
