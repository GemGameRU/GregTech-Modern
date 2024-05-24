package com.gregtechceu.gtceu.api.cover;

import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.fluids.FluidStack;

import org.apache.commons.lang3.NotImplementedException;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author KilaBash
 * @date 2023/3/14
 * @implNote FluidFilter
 */
public interface IFluidFilter extends IFilter<FluidStack, IFluidFilter> {

    Map<ItemLike, Function<ItemStack, IFluidFilter>> FILTERS = new HashMap<>();

    static IFluidFilter loadFilter(ItemStack itemStack) {
        return FILTERS.get(itemStack.getItem()).apply(itemStack);
    }

    /**
     * Retrieves the configured fluid amount for the supplied fluid.
     *
     * @return The amount configured for the supplied fluid stack.<br>
     *         If the stack is not matched by this filter, 0 is returned instead.
     */
    int testFluidAmount(FluidStack fluidStack);

    /**
     * @return Whether this filter supports querying for exact fluid amounts.
     */
    default boolean supportsAmounts() {
        return !isBlackList();
    }

    /**
     * An empty fluid filter that allows all fluids.<br>
     * ONLY TO BE USED FOR FLUID MATCHING! All other functionality will throw an exception.
     */
    IFluidFilter EMPTY = new IFluidFilter() {

        @Override
        public boolean test(FluidStack fluidStack) {
            return true;
        }

        @Override
        public int testFluidAmount(FluidStack fluidStack) {
            return Integer.MAX_VALUE;
        }

        @Override
        public WidgetGroup openConfigurator(int x, int y) {
            throw new NotImplementedException("Not available for empty fluid filter");
        }

        @Override
        public CompoundTag saveFilter() {
            throw new NotImplementedException("Not available for empty fluid filter");
        }

        @Override
        public void setOnUpdated(Consumer<IFluidFilter> onUpdated) {
            throw new NotImplementedException("Not available for empty fluid filter");
        }
    };
}