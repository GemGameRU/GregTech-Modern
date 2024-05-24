package com.gregtechceu.gtceu.api.cover;

import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.nbt.CompoundTag;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @author KilaBash
 * @date 2023/3/14
 * @implNote Filter
 */
public interface IFilter<T, S extends IFilter<T, S>> extends Predicate<T> {

    WidgetGroup openConfigurator(int x, int y);

    CompoundTag saveFilter();

    void setOnUpdated(Consumer<S> onUpdated);

    default boolean isBlackList() {
        return false;
    }
}
