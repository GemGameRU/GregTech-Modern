package com.gregtechceu.gtceu.api.gui.widget;

import com.gregtechceu.gtceu.api.capability.ICoverableBlock;
import com.gregtechceu.gtceu.api.cover.CoverBehavior;
import com.gregtechceu.gtceu.api.cover.IUICover;
import com.gregtechceu.gtceu.api.gui.GUITextures;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyConfigurator;
import com.gregtechceu.gtceu.data.item.GTItems;

import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.texture.ItemStackTexture;
import com.lowdragmc.lowdraglib.gui.widget.Widget;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.Position;
import com.lowdragmc.lowdraglib.utils.Size;

import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.Nullable;

@AllArgsConstructor
public class CoverConfigurator implements IFancyConfigurator {

    protected final ICoverableBlock coverable;
    // runtime
    @Nullable
    protected final Direction side;
    @Nullable
    protected final CoverBehavior coverBehavior;

    @Override
    public Component getTitle() {
        return Component.translatable("gtceu.gui.cover_setting.title");
    }

    @Override
    public IGuiTexture getIcon() {
        return new ItemStackTexture(GTItems.ITEM_FILTER.get());
    }

    @Override
    public Widget createConfigurator() {
        WidgetGroup group = new WidgetGroup(new Position(0, 0));
        if (side != null) {
            if (coverable.getCoverAtSide(side) instanceof IUICover iuiCover) {
                Widget coverConfigurator = iuiCover.createUIWidget();
                coverConfigurator.setBackground(GUITextures.BACKGROUND);
                coverConfigurator.setSelfPosition(new Position(4, -4));
                group.addWidget(coverConfigurator);
                group.setSize(new Size(Math.max(120, coverConfigurator.getSize().width + 8),
                        Math.max(80, 80 + coverConfigurator.getSize().height)));
            }
        }
        return group;
    }
}
