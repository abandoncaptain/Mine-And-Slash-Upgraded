package com.robertx22.age_of_exile.database.data.profession.screen;

import com.robertx22.age_of_exile.database.data.profession.Crafting_State;
import com.robertx22.age_of_exile.database.data.profession.ProfessionBlockEntity;
import com.robertx22.age_of_exile.database.data.profession.ProfessionRecipe;
import com.robertx22.age_of_exile.mmorpg.SlashRef;
import com.robertx22.age_of_exile.uncommon.utilityclasses.ClientOnly;
import com.robertx22.age_of_exile.vanilla_mc.packets.CraftPacket;
import com.robertx22.library_of_exile.main.Packets;
import com.robertx22.library_of_exile.utils.RenderUtils;
import com.robertx22.library_of_exile.utils.TextUTIL;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

public class CraftButton extends ImageButton {

    public static int XS = 18;
    public static int YS = 19;
    public static ProfessionBlockEntity pbe;

    Minecraft mc = Minecraft.getInstance();

    public CraftButton(int xPos, int yPos, ProfessionBlockEntity be) {
        super(xPos, yPos, XS, YS, 0, 0, YS, SlashRef.guiId("craftbutton"), (button) -> {
            Packets.sendToServer(new CraftPacket(be.getBlockPos()));
        });
        pbe = be;
    }

    @Override
    public void render(GuiGraphics gui, int mouseX, int mouseY, float delta) {
        setModTooltip();
        super.render(gui, mouseX, mouseY, delta);
    }

    @Override
    public void renderWidget(GuiGraphics gui, int mouseX, int mouseY, float delta) {
        ResourceLocation tex = SlashRef.guiId("craftbutton");
        gui.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        gui.blit(tex, getX(), getY(), 0,(pbe.craftingState == Crafting_State.ACTIVE || pbe.craftingState == Crafting_State.IDLE) ? 0 : 19, 18,19);
    }

    public void setModTooltip() {
        if(pbe.craftingState == Crafting_State.ACTIVE || pbe.craftingState == Crafting_State.IDLE)
            this.setTooltip(Tooltip.create(Component.literal("Stop Crafting").withStyle(ChatFormatting.DARK_AQUA)));
        else
            this.setTooltip(Tooltip.create(Component.literal("Start Crafting").withStyle(ChatFormatting.DARK_AQUA)));
    }

}