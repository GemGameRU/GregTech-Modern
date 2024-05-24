package com.gregtechceu.gtceu.api.fluid.attribute;

import com.gregtechceu.gtceu.GTCEu;

import net.minecraft.network.chat.Component;

public final class GTFluidAttributes {

    /**
     * Attribute for acidic fluids.
     */
    public static final GTFluidAttribute ACID = new GTFluidAttribute(GTCEu.id("acid"),
            list -> list.add(Component.translatable("gtceu.fluid.type_acid.tooltip")),
            list -> list.add(Component.translatable("gtceu.fluid_pipe.acid_proof")));

    private GTFluidAttributes() {}
}
