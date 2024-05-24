package com.gregtechceu.gtceu.api.machine.feature.multiblock;

import com.gregtechceu.gtceu.api.gui.GUITextures;
import com.gregtechceu.gtceu.api.gui.UITemplate;
import com.gregtechceu.gtceu.api.machine.feature.IUIMachine;

import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.texture.IGuiTexture;
import com.lowdragmc.lowdraglib.gui.util.ClickData;
import com.lowdragmc.lowdraglib.gui.widget.ComponentPanelWidget;
import com.lowdragmc.lowdraglib.gui.widget.DraggableScrollableWidgetGroup;
import com.lowdragmc.lowdraglib.gui.widget.LabelWidget;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.List;

/**
 * @author KilaBash
 * @date 2023/3/16
 * @implNote IDisplayUIMachine
 */
public interface IDisplayUIMachine extends IUIMachine, IMultiController {

    default void addDisplayText(List<Component> textList) {
        for (var part : this.getParts()) {
            part.addMultiText(textList);
        }
    }

    default void handleDisplayClick(String componentData, ClickData clickData) {}

    default IGuiTexture getScreenTexture() {
        return GUITextures.DISPLAY;
    }

    @Override
    default ModularUI createUI(Player entityPlayer) {
        var screen = new DraggableScrollableWidgetGroup(7, 4, 162, 121).setBackground(getScreenTexture());
        screen.addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()));
        screen.addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                .textSupplier(this.self().getLevel().isClientSide ? null : this::addDisplayText)
                .setMaxWidthLimit(150)
                .clickHandler(this::handleDisplayClick));
        return new ModularUI(176, 216, this, entityPlayer)
                .background(GUITextures.BACKGROUND)
                .widget(screen)
                .widget(UITemplate.bindPlayerInventory(entityPlayer.getInventory(), GUITextures.SLOT, 7, 134, true));
    }
}
