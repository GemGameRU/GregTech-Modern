package com.gregtechceu.gtceu.api.cover.filter;

import com.lowdragmc.lowdraglib.syncdata.IEnhancedManaged;

import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.fluids.FluidStack;

public interface IFilterHandler {

    static FilterHandler<ItemStack, IItemFilter> item(IEnhancedManaged container) {
        return new FilterHandler<>(container) {

            @Override
            protected IItemFilter loadFilter(ItemStack filterItem) {
                return IItemFilter.loadFilter(filterItem);
            }

            @Override
            protected IItemFilter getEmptyFilter() {
                return IItemFilter.EMPTY;
            }

            @Override
            protected boolean canInsertFilterItem(ItemStack itemStack) {
                return IItemFilter.FILTERS.containsKey(itemStack.getItem());
            }
        };
    }

    static FilterHandler<FluidStack, IFluidFilter> fluid(IEnhancedManaged container) {
        return new FilterHandler<>(container) {

            @Override
            protected IFluidFilter loadFilter(ItemStack filterItem) {
                return IFluidFilter.loadFilter(filterItem);
            }

            @Override
            protected IFluidFilter getEmptyFilter() {
                return IFluidFilter.EMPTY;
            }

            @Override
            protected boolean canInsertFilterItem(ItemStack itemStack) {
                return IFluidFilter.FILTERS.containsKey(itemStack.getItem());
            }
        };
    }
}
